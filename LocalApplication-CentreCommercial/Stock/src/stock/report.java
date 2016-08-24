/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class report extends Application {
    boolean flag;
    Button savebtn;
    String appendfilter1,appendfilter2,appendfilter3,appendfilter4,appendfilter5,appendfilter6,appendfilter7;
    TextField savetxt;
    boolean loadBool=false;
    Stage reportStageS=new Stage(StageStyle.DECORATED);
    static String varexport;
    static int varCounter;
    HBox container=new HBox();
    GridPane btngrid=new GridPane();
    String btn_names[]={"View","Clear Filter","Remove Filter","Save Filter","Load Filter","View As Grid"};
    Button btn[]=new Button[6];
    TitledPane PeriodS=new TitledPane();
    TitledPane printOptions=new TitledPane();
    TitledPane internalFilter=new TitledPane();
    GridPane pSelect=new GridPane();
    Label fromdate=new Label("From Date");
    Label todate=new Label("To Date");
    DatePicker fromdatetxt=new DatePicker();
    DatePicker todatetxt=new DatePicker();
    HBox vTitled=new HBox();
    HBox vCentered=new HBox();
    VBox semicontainer=new VBox();
    Label decimaltxt=new Label("Decimal No");
    ComboBox<String> c1=new ComboBox();
    GridPane gridPrinter=new GridPane();
    GridPane filter=new GridPane();
    Button filterbtn[]=new Button[7];
    String filter_names[]={"Item Name","Price","Qty","Salesman","Supplier","Barcode","Expiry"};
    static ComboBox<String> filtertxt[]=new ComboBox[7];
    Button clearbtn[]=new Button[7];
    
    @Override
    public void start(Stage primaryStage) {
        
        fromdatetxt.setValue(LocalDate.now());
        todatetxt.setValue(LocalDate.now());
        printOptions.setPrefWidth(250);
        c1.setPrefWidth(100);
        c1.getItems().addAll("0.####","#.###.##","##,##,###");
        c1.getSelectionModel().selectFirst();
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
        btn[2].setDisable(true);
        internalFilter.setText("Internal Filter");
        internalFilter.setContent(filter);
        vCentered.getChildren().addAll(internalFilter);
        semicontainer.getChildren().addAll(vTitled,vCentered);
        btngrid.setVgap(10);
        btngrid.setHgap(10);
        btngrid.setPadding(in);
        container.getChildren().addAll(semicontainer,btngrid);
        clearbtn[0].setOnAction((event)->{filtertxt[0].getItems().clear();});
        clearbtn[1].setOnAction((event)->{filtertxt[1].getItems().clear();});
        clearbtn[2].setOnAction((event)->{filtertxt[2].getItems().clear();});
        clearbtn[3].setOnAction((event)->{filtertxt[3].getItems().clear();});
        clearbtn[4].setOnAction((event)->{filtertxt[4].getItems().clear();});
        clearbtn[5].setOnAction((event)->{filtertxt[5].getItems().clear();});
        clearbtn[6].setOnAction((event)->{filtertxt[6].getItems().clear();});
        btn[1].setOnAction((event)->{
            filtertxt[0].getItems().clear();
            filtertxt[1].getItems().clear();
            filtertxt[2].getItems().clear();
            filtertxt[3].getItems().clear();
            filtertxt[4].getItems().clear();
            filtertxt[5].getItems().clear();
            filtertxt[6].getItems().clear();
            c1.getSelectionModel().selectFirst();
            fromdatetxt.setValue(LocalDate.now());
            todatetxt.setValue(LocalDate.now());
        });
         btn[3].setOnAction((event)->{
             savefilterOpen();
         });
         btn[4].setOnAction((event)->{
             loadfilterOpen();
         });
        filterbtn[0].setOnAction((event)->{
            varexport="itemName";
            varCounter=0;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        
        filterbtn[1].setOnAction((event)->{
            varexport="price";
            varCounter=1;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        filterbtn[2].setOnAction((event)->{
            varexport="qty";
            varCounter=2;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        filterbtn[4].setOnAction((event)->{
            varexport="supplier";
            varCounter=4;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        filterbtn[3].setOnAction((event)->{
            varexport="salesman";
            varCounter=3;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        filterbtn[5].setOnAction((event)->{
            varexport="barcode";
            varCounter=5;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
        filterbtn[6].setOnAction((event)->{
            varexport="expiry";
            varCounter=6;
            reportSearch rep=new reportSearch();
            rep.start(reportStageS);
            
        });
       
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
    void savefilterOpen(){
        BorderPane saveFilter=new BorderPane();
        GridPane gridSave=new GridPane();
        gridSave.setHgap(10);
        gridSave.setVgap(10);
        Label savelbl=new Label("Enter filter name");
        savetxt=new TextField();
        savebtn=new Button("Save filter");
        Button savecancel=new Button("Cancel");
        gridSave.add(savelbl, 1, 1);
        gridSave.add(savetxt, 1, 2);
        gridSave.add(savecancel, 1, 3);
        gridSave.add(savebtn, 2, 3);
        saveFilter.setCenter(gridSave);
        Scene scene = new Scene(saveFilter,300,150);
        String css =this.getClass().getResource("report.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
         savebtn.setOnAction((event)->{
             
            try {
                checkselected(savetxt.getText());
                if(flag==false){
                updatetotable();
                stage.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(report.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    void loadfilterOpen(){
        BorderPane saveFilter=new BorderPane();
        GridPane gridSave=new GridPane();
        gridSave.setHgap(10);
        gridSave.setVgap(10);
        Label savelbl=new Label("Choose filter name");
        ComboBox<String> c1=new ComboBox<>();
        Button savebtn=new Button("Load filter");
        Button savecancel=new Button("Cancel");
        gridSave.add(savelbl, 1, 1);
        gridSave.add(c1, 1, 2);
        gridSave.add(savecancel, 1, 3);
        gridSave.add(savebtn, 2, 3);
        saveFilter.setCenter(gridSave);
        Scene scene = new Scene(saveFilter,300,150);
        String css =this.getClass().getResource("report.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    void updatetotable() throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "INSERT INTO `filter`(`name`, `fdate`, `todate`, `item_name`, `price`, `qty`, "
                + "`salesman`, `supplier`, `barcode`, `expiry`, `decimal`) VALUES"
                + " (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            preparedStatement.setString(1, savetxt.getText());
            preparedStatement.setString(2, String.valueOf(fromdatetxt.getValue()));
            preparedStatement.setString(3, String.valueOf(todatetxt.getValue()));
            appendfilter1="";
            appendfilter2="";
            appendfilter3="";
            appendfilter4="";
            appendfilter5="";
            appendfilter6="";
            appendfilter7="";
            if(filtertxt[0].getItems().size()!=0){
               for(int i=0;i<filtertxt[0].getItems().size();i++){
                  appendfilter1=appendfilter1+","+filtertxt[0].getItems().get(i);
               }
               preparedStatement.setString(4, appendfilter1);
            }else {preparedStatement.setString(4, appendfilter1);}
            if(filtertxt[1].getItems().size()!=0){
               for(int i=0;i<filtertxt[1].getItems().size();i++){
                  appendfilter2=appendfilter2+","+filtertxt[1].getItems().get(i);
               }
               preparedStatement.setString(5, appendfilter2);
            }else {preparedStatement.setString(5, appendfilter1);}
           if(filtertxt[2].getItems().size()!=0){
               for(int i=0;i<filtertxt[2].getItems().size();i++){
                  appendfilter3=appendfilter3+","+filtertxt[2].getItems().get(i);
               }
               preparedStatement.setString(6, appendfilter3);
            }else {preparedStatement.setString(6, appendfilter1);}
           if(filtertxt[3].getItems().size()!=0){
               for(int i=0;i<filtertxt[3].getItems().size();i++){
                  appendfilter4=appendfilter4+","+filtertxt[3].getItems().get(i);
               }
               preparedStatement.setString(7, appendfilter4);
            }else {preparedStatement.setString(7, appendfilter1);}
           if(filtertxt[4].getItems().size()!=0){
               for(int i=0;i<filtertxt[4].getItems().size();i++){
                  appendfilter5=appendfilter5+","+filtertxt[4].getItems().get(i);
               }
               preparedStatement.setString(8, appendfilter5);
            }else {preparedStatement.setString(8, appendfilter1);}
           if(filtertxt[5].getItems().size()!=0){
               for(int i=0;i<filtertxt[5].getItems().size();i++){
                  appendfilter6=appendfilter6+","+filtertxt[5].getItems().get(i);
               }
               preparedStatement.setString(9, appendfilter6);
            }else {preparedStatement.setString(9, appendfilter1);}
           if(filtertxt[6].getItems().size()!=0){
               for(int i=0;i<filtertxt[6].getItems().size();i++){
                  appendfilter7=appendfilter7+","+filtertxt[6].getItems().get(i);
               }
               preparedStatement.setString(10, appendfilter7);
            }else {preparedStatement.setString(10, appendfilter1);}
            preparedStatement.setString(11, String.valueOf(c1.getValue()));
            // execute select SQL stetement
            int rs = preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        
    }
     
    void checkselected(String s) throws SQLException{
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM filter WHERE name = ? ";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, s);
           
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            String result = null;
           
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("filter already exist");
                alert.setContentText("please change filter name");
                
                alert.showAndWait();  
                flag= true;
          
            }
            else{
                
               flag=false;
            }
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
    }
}
