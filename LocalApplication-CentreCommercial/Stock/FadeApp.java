package stock;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * Example of displaying a splash page for a standalone JavaFX application
 */
public class FadeApp extends Application {
    //public static final String APPLICATION_ICON =
          //  "http://cdn1.iconfinder.com/data/icons/Copenhagen/PNG/32/people.png";
    public static final String SPLASH_IMAGE =
            "images/inventory.jpg";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 600;
    private static final int SPLASH_HEIGHT = 227;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        splash.setFitHeight(400);
        splash.setFitWidth(600);
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will testing data . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: lightblue; " +
                "-fx-border-width:5; " +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(
                                "checking connection","checking data"
                        );

                updateMessage("Finding tasks . . .");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(2000);
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("finding tasks . . . found " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("All tasks checked.");

                return foundFriends;
            }
        };

        showSplash(
                initStage,
                friendTask,
                () -> showMainStage(friendTask.valueProperty())
        );
        new Thread(friendTask).start();
    }

    private void showMainStage(
            ReadOnlyObjectProperty<ObservableList<String>> friends
    ) {
        mainStage = new Stage(StageStyle.DECORATED);
        Stock stk=new Stock();
        stk.start(mainStage);
        /*try {
            Process process = new ProcessBuilder("C:\\BEE2\\Bee\\Projects\\Bee\\Bee.exe","param1","param2").start();
        } catch (IOException ex) {
            Logger.getLogger(FadeApp.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(0.6), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    public interface InitCompletionHandler {
        void complete();
    }
}