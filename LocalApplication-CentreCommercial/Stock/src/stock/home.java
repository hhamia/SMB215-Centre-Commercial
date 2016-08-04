package stock;




import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class home extends Application {
    private final Label label[]=new Label[10];
    Stage addItemsStage=new Stage(StageStyle.DECORATED);
    public final static Label toplabel=new Label("Stock Soft");
    final VBox vv=new VBox();
    String label_name[]=new String[]{"Customer","Salesman","Items","Sales","Settings","invoice"
    ,"Reports","Dashboard","Stock","Chat Settings"};
    
    @Override
    public void start(Stage primaryStage) {
        toplabel.setId("toplabel");
        for (int i = 0; i < label.length; i++) {
            label[i]=new Label(label_name[i]); 
            label[i].setAlignment(Pos.CENTER);
        }
        Label bottlabel=new Label("skynete");
        
        GridPane togrid=new GridPane();
        toplabel.setAlignment(Pos.CENTER);
        togrid.setAlignment(Pos.CENTER);
        togrid.add(toplabel, 1, 4);
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");
        final Tooltip tooltip1 = new Tooltip();
        tooltip1.setText("Customer page");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        vv.getChildren().add(menuBar);
        vv.getChildren().add(togrid);
        GridPane grid1=new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);
        Button b1=new Button("");
        Button b2=new Button("");
        Button b3=new Button("");
        Button b4=new Button("");
        Button b5=new Button("");
        Button b6=new Button("");
        Button b7=new Button("");
        Button b8=new Button("");
        Button b9=new Button("");
        Button b10=new Button("");
        b1.setId("btnstyle");
        b1.setTooltip(tooltip1);
        b2.setId("btnstyle");
        b3.setId("btnstyle");
        b4.setId("btnstyle");
        b5.setId("btnstyle");
        b6.setId("btnstyle");
        b7.setId("btnstyle");
        b8.setId("btnstyle");
        b9.setId("btnstyle");
        b10.setId("btnstyle");
        b1.setStyle("-fx-background-image: url('images/supplier.png');-fx-background-size: cover;");
        b2.setStyle("-fx-background-image: url('images/customer-service.png');-fx-background-size: cover;");
        b3.setStyle("-fx-background-image: url('images/Add-item-icon.png');-fx-background-size: cover;");
        b4.setStyle("-fx-background-image: url('images/invoice-md.png');-fx-background-size: cover;");
        b5.setStyle("-fx-background-image: url('images/money.png');-fx-background-size: cover;");
        b6.setStyle("-fx-background-image: url('images/reports.png');-fx-background-size: cover;");
        b7.setStyle("-fx-background-image: url('images/sales.png');-fx-background-size: cover;");
        b8.setStyle("-fx-background-image: url('images/stock-icon.png');-fx-background-size: cover;");
        b9.setStyle("-fx-background-image: url('images/settings.png');-fx-background-size: cover;");
        b10.setStyle("-fx-background-image: url('images/chat-2-icon.png');-fx-background-size: cover;");
        grid1.setAlignment(Pos.CENTER);
        grid1.add(b1, 0, 0);
        grid1.add(b2, 1, 0);
        grid1.add(b3, 2, 0);
        grid1.add(b7, 3, 0);
        grid1.add(b9, 4, 0);
        grid1.add(b4, 0, 2);
        grid1.add(b5, 1, 2);
        grid1.add(b6, 2, 2);
        grid1.add(b8, 3, 2);
        grid1.add(b10, 4, 2);
        for (int i=0;i<5;i++){
            grid1.add(label[i], i, 1);
            label[i].setId("titles");
            
        }
        for (int i=5;i<10;i++){
            grid1.add(label[i], i-5, 3);
            label[i].setId("titles");
            
        }
        
        BorderPane root = new BorderPane();
        root.setTop(vv);
        root.setCenter(grid1);
        root.setBottom(bottlabel);
        Scene scene = new Scene(root, 300, 250);
        String css =this.getClass().getResource("home.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        grid1.setPrefHeight(primaryScreenBounds.getHeight());
        b1.setOnAction((event) -> {
           supplier supp=new supplier();
            supp.start(primaryStage);
        });
        b2.setOnAction((event) -> {
            customer co=new customer();
            co.start(primaryStage);
        });
        b3.setOnAction((event) -> {
            addItems it=new addItems();
            try {
                it.start(addItemsStage);
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        b7.setOnAction((event) -> {
            saleItem sl=new saleItem();
            sl.start(addItemsStage);
        });
        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
primaryStage.setMaximized(true);
        Image ico = new Image("images/stockIcon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
