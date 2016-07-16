/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 *
 * @author skynete10
 */
public class addItems extends Application {
   private final Button btnNew=new Button("New Item");
   private final Button btnEdit=new Button("Edit Item");
   private final Button btnSave=new Button("Save Item");
   private final Button btncancel=new Button("Cancel Add");
   private final Button btnDelete=new Button("Delete Item");
   private final Button btnFind=new Button("Find Item");
   private TableView<items> table = new TableView<>();
   private TableView<supplier> table1 = new TableView<>();
   GridPane menugrid=new GridPane();
   HBox menu=new HBox();
   GridPane container=new GridPane();
   //----first container
    VBox v1=new VBox();
    VBox origin=new VBox();
    GridPane grid0=new GridPane();
    
    
    GridPane grid1=new GridPane();
    GridPane grid2=new GridPane();
    //---end first container
    //----seconde container
    VBox v11=new VBox();
    GridPane grid00=new GridPane();
    GridPane grid11=new GridPane();
    GridPane grid22=new GridPane();
    //----end second container
    private final Label label[]=new Label[9];
    String strings_en[]=new String[]{"Code","Description 1","Description 2","Barcode","Class","Base unit",
    "Expiry date","Shortcut key","Default unit"};
    private final TextField[] text=new TextField[9];

    public addItems() {
        this.table = new TableView<>();
    }
    @Override
    public void start(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double btnWidth=primaryScreenBounds.getWidth()/6;
        table.setPrefWidth(primaryScreenBounds.getWidth()/3);
        grid0.setVgap(10);
        grid0.setHgap(10);
        grid0.setStyle("-fx-border: 2px solid; -fx-border-color: red;");
        grid0.setPadding(new Insets(10, 50, 50, 50));
        grid1.setVgap(10);
        grid1.setHgap(10);
        grid2.setVgap(10);
        grid2.setHgap(10);
        grid2.setStyle("-fx-border: 2px solid; -fx-border-color: red;");
        grid2.setPadding(new Insets(10, 50, 50, 50));
        grid00.setVgap(10);
        grid00.setHgap(10);
        grid11.setVgap(10);
        grid11.setHgap(10);
        grid22.setVgap(10);
        grid22.setHgap(10);
        Label l1=new Label("Information");
        l1.setId("title");
        grid0.add(l1, 1, 0);
        Label supp=new Label("Supplier");
        Label name=new Label("Name");
        Label item=new Label("Item");
        TextField suppText=new TextField();
        TextField nametext=new TextField();
        TextField itemText=new TextField();
        suppText.setPrefWidth(200);
        nametext.setPrefWidth(200);
        itemText.setPrefWidth(200);
        Label cur=new Label("currency");
        Label lastdate=new Label("last date");
        Label price=new Label("price");
        Label lowprice=new Label("low price");
        Label hightprice=new Label("hight price");
        TextField curtext=new TextField();
        TextField lasttext=new TextField();
        TextField pricetext=new TextField();
        TextField lowtext=new TextField();
        TextField highttext=new TextField();
        grid00.add(supp, 1, 0);
        grid00.add(name, 2, 0);
        grid00.add(item, 3, 0);
        grid00.add(suppText, 1, 1);
        grid00.add(nametext, 2, 1);
        grid00.add(itemText, 3, 1);
        grid00.setStyle("-fx-border: 2px solid; -fx-border-color: red;");
        grid00.setPadding(new Insets(10, 50, 50, 50));
        
        grid11.add(cur, 1, 0);
        grid11.add(lastdate, 2, 0);
        grid11.add(price, 3, 0);
        grid11.add(lowprice, 1, 3);
        grid11.add(hightprice, 2, 3);
        grid11.add(curtext, 1, 1);
        grid11.add(lasttext, 2, 1);
        grid11.add(pricetext, 3, 1);
        grid11.add(lowtext, 1, 4);
        grid11.add(highttext, 2, 4);
        grid11.setStyle("-fx-border: 2px solid; -fx-border-color: red;");
        
        //v11.setPadding(new Insets(10, 50, 50, 50));
        v11.setSpacing(10);
        grid11.setPadding(new Insets(10, 50, 50, 50));
         origin.setPadding(new Insets(10, 50, 50, 50));
         origin.setSpacing(10);
        v11.getChildren().add(grid00);
        v11.getChildren().add(grid11);
        //---initialise text fields
        for (int i=0;i<text.length;i++){
           text[i] = new TextField(); 
        }
        //---end initialise text fields
        //-----add labels
        for (int i = 0; i < label.length; i++) {
            label[i]=new Label(strings_en[i]);  
        }
        for (int i=0;i<3;i++){
            grid0.add(label[i], 0, i+1);
            grid0.add(text[i], 1, i+1);
            text[i].setPrefWidth(400);
        }
        v1.getChildren().add(grid0);
        for(int i=3;i<6;i++){
            grid2.add(label[i], 0, i);
            grid2.add(text[i], 1, i);
        }
        for(int i=6;i<9;i++){
            grid2.add(label[i], 2, i-3);
            grid2.add(text[i], 3, i-3);
        }
        v1.getChildren().add(grid2);
        
        grid1.add(v1, 0, 0);
        grid1.add(v11, 0, 2);
        //----end adding labels
        menugrid.add(btnNew, 0, 0);
        menugrid.add(btnEdit, 1, 0);
        menugrid.add(btnSave, 2, 0);
        menugrid.add(btncancel, 3, 0);
        menugrid.add(btnDelete, 4, 0);
        menugrid.add(btnFind, 5, 0);
        btnNew.setPrefSize(btnWidth, 50);
        btnEdit.setPrefSize(btnWidth, 50);
        btnSave.setPrefSize(btnWidth, 50);
        btncancel.setPrefSize(btnWidth, 50);
        btnDelete.setPrefSize(btnWidth, 50);
        btnFind.setPrefSize(btnWidth, 50);
        BorderPane root = new BorderPane();
        menu.getChildren().add(menugrid);
        root.setTop(menu);
        root.setLeft(grid1);
        origin.getChildren().add(table);
        origin.getChildren().add(table1);
        root.setRight(origin);
        Scene scene = new Scene(root, 800, 550);
        String css =this.getClass().getResource("items.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        Image ico = new Image("images/Add-item-icon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("add items");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
        
        
    }
    
   public static class items {
       
   }
   public static class supplier {
       
   }
    public static void main(String[] args) {
        launch(args);
    }
    
}
