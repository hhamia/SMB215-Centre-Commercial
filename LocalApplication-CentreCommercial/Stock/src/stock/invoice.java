/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author skynete10
 */
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
        //----title
        
        Label l1=new Label("Customer Name");
        Label l2=new Label("Address");
        Label l3=new Label("Invoice Number");
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
        container.getChildren().add(grid2);
       
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
