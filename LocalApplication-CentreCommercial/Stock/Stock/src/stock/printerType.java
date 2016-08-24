package stock;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class printerType extends Application {
    static double widths;
    static double heights;
    @Override
    public void start(Stage printerStage) {
        
        GridPane grid=new GridPane();
        
        grid.setVgap(10);
        grid.setHgap(10);
        Label l=new Label("Printer Type");
        Button btn=new Button("Show Invoice");
        ComboBox<String> c1=new ComboBox<>();
        c1.getItems().addAll("A4","Smalll","Specifie Size");
        c1.getSelectionModel().selectFirst();
        TextField width=new TextField();
        TextField height=new TextField();
        width.setDisable(true);
        height.setDisable(true);
        btn.setDisable(true);
        GridPane grid2=new GridPane();
        grid2.setVgap(10);
        grid2.setHgap(10);
        c1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if("Specifie Size".equals(c1.getValue())){
                    width.setDisable(false);
                    height.setDisable(false);
                    btn.setDisable(false);
                }
                else{
                    width.setDisable(true);
                    height.setDisable(true);
                    btn.setDisable(true);
                }
            }
        });
        
        
        Label lw=new Label("Specifie width");
        Label lh=new Label("Specifie height");
        grid2.add(lw, 1, 1 );grid2.add(width, 2, 1);
        grid2.add(lh, 1, 2 );grid2.add(height, 2, 2);
        grid.add(c1, 1, 1);
        grid.add(grid2, 1, 4);
        grid2.add(btn, 1, 3);
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        
        Scene scene = new Scene(root, 400, 250);
        String css =this.getClass().getResource("items.css").toExternalForm();
        scene.getStylesheets().add(css);
        printerStage.setTitle("Printer Type");
        printerStage.setScene(scene);
        printerStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
