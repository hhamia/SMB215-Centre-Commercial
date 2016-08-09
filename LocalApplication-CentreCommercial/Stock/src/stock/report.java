/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.time.Period;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class report extends Application {
     HBox container=new HBox();
     GridPane btngrid=new GridPane();
     String btn_names[]={"View","Clear Filter","Remove Filter","Remove Filter","Save Filter","Load Filter","View As Grid"};
     Button btn[]=new Button[7]; 
     TitledPane PeriodS=new TitledPane();
     TitledPane printOptions=new TitledPane();
     TitledPane internalFilter=new TitledPane();
     GridPane pSelect=new GridPane();
     Label fromdate=new Label("From Date");
     Label todate=new Label("To Date");
     TextField fromdatetxt=new TextField();
     TextField todatetxt=new TextField();
     HBox vTitled=new HBox();
     HBox vCentered=new HBox();
     VBox semicontainer=new VBox();
     Label decimaltxt=new Label("Decimal No");
     ComboBox<String> c1=new ComboBox();
     GridPane gridPrinter=new GridPane();
     GridPane filter=new GridPane();
     Button filterbtn[]=new Button[6];
     String filter_names[]={"Item Name","Price","Qty","Salesman","Supplier","Barcode"};
     ComboBox<String> filtertxt[]=new ComboBox[6];
     Button clearbtn[]=new Button[6];
     @Override
    public void start(Stage primaryStage) {
        printOptions.setPrefWidth(250);
        c1.setPrefWidth(100);
        PeriodS.setPrefWidth(500);
        gridPrinter.setHgap(10);
        filter.setHgap(10);filter.setVgap(10);
        gridPrinter.add(decimaltxt, 0, 0);gridPrinter.add(c1, 1, 0);
        pSelect.setHgap(10);
        pSelect.add(fromdate, 0, 0); pSelect.add(todate, 2, 0);
        pSelect.add(fromdatetxt, 1, 0); pSelect.add(todatetxt, 4, 0);
        PeriodS.setText("Periode Selection");
        PeriodS.setContent(pSelect);
        printOptions.setText("Print Options");
        printOptions.setContent(gridPrinter);
        
        vTitled.getChildren().addAll(PeriodS,printOptions);
        
        Insets in=new Insets(10);
        Rectangle2D Bounds = Screen.getPrimary().getVisualBounds(); 
        for(int i=0;i<btn_names.length;i++){
            btn[i]=new Button(btn_names[i]);
            btn[i].setPrefSize(200, 80);
            btngrid.add(btn[i], 0, i);
            btn[i].setId("btnViewer");
        }
        for(int i=0;i<filter_names.length;i++){
           filterbtn[i]=new Button(filter_names[i]);
           filtertxt[i]=new ComboBox();
           filtertxt[i].setPrefSize(300, 30);
           clearbtn[i]=new Button("clear");
           filterbtn[i].setPrefWidth(150);
           
             filter.add(filterbtn[i], 0, i);
             filter.add(filtertxt[i], 1, i);
             filter.add(clearbtn[i], 2, i);
          
  
           
        }
        internalFilter.setText("Internal Filter");
        internalFilter.setContent(filter);
        vCentered.getChildren().addAll(internalFilter);
        semicontainer.getChildren().addAll(vTitled,vCentered);
        btngrid.setVgap(10);
        btngrid.setHgap(10);
        btngrid.setPadding(in);
        container.getChildren().addAll(semicontainer,btngrid);
        
        BorderPane root = new BorderPane();
        root.setCenter(container);
        
        Scene scene = new Scene(root, Bounds.getWidth()/1.5, Bounds.getHeight()/1.5);
         String css =this.getClass().getResource("report.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("report");
        primaryStage.setResizable(false);
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
