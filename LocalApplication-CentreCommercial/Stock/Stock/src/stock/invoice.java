package stock;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class invoice extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
      
        double widthvar=primaryStage.getWidth();
        double heightvar=primaryStage.getHeight();
        VBox container=new VBox();
        container.setPrefWidth(widthvar);
        container.setPrefHeight(heightvar);
        GridPane grid1=new GridPane();
        grid1.setVgap(10);grid1.setHgap(10);
        GridPane grid3=new GridPane();
        grid3.setVgap(10);grid3.setHgap(10);
        Label l[]=new Label[saleItem.data.size()];
        Label ll[]=new Label[saleItem.data.size()];
        Label lll[]=new Label[saleItem.data.size()];
        //----title
         grid3.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
       
        Label l1=new Label("Customer Name");
        Label l2=new Label("Address");
        Label l3=new Label("Invoice Number");
        Label l11=new Label("Item Name");
        Label l22=new Label("Qty");
        Label l33=new Label("Price");
        l11.setPrefWidth(700/3);
        l22.setPrefWidth(700/3);
        l33.setPrefWidth(700/3);
        l11.setId("label_inv");l22.setId("label_inv");l33.setId("label_inv");
        l11.setStyle(" -fx-background-color: gray;-fx-font-size:20px;");
        l22.setStyle(" -fx-background-color: gray;-fx-font-size:20px;");
        l33.setStyle(" -fx-background-color: gray;-fx-font-size:20px;");
        grid3.add(l11, 1, 1);grid3.add(l22, 2, 1);grid3.add(l33, 3, 1);
        for(int i=0;i< saleItem.data.size();i++){
            l[i]=new Label();ll[i]=new Label();lll[i]=new Label();
            l[i].setText(String.valueOf(saleItem.data.get(0)));
            ll[i].setText(String.valueOf(saleItem.data.get(0)));
            lll[i].setText(String.valueOf(saleItem.data.get(0)));
            grid3.add(l[i], 1, i+2);
            grid3.add(ll[i], 2, i+2);
            grid3.add(lll[i], 3, i+2);
        }
        ImageView img1=new ImageView(new Image("images/stockIcon.png"));
        img1.setFitHeight(100);
        img1.setFitWidth(100);
        grid1.add(l1, 1, 1);grid1.add(l2, 1, 2);grid1.add(l3, 1, 3);
        GridPane grid2=new GridPane();
        grid2.setVgap(10);grid2.setHgap(10);
        grid2.setPrefWidth(container.getWidth());
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(1);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        grid2.getColumnConstraints().addAll(col1,col2);
        grid2.add(grid1, 1, 1);grid2.add(img1, 2, 1);
        
        //-----title
        container.getChildren().addAll(grid2,grid3);
       
        BorderPane root = new BorderPane();
        root.setCenter(container);
        
        Scene scene = new Scene(root, 700, 700);
       
        primaryStage.setTitle("invoice");
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
