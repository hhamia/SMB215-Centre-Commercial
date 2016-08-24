package stock;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class reportSearch extends Application {
    
    TextField search_txt=new TextField();
    TableView<String> table=new TableView();
    final ObservableList<String> data =
            FXCollections.observableArrayList();
    TableView<String> table1=new TableView();
    final static ObservableList<String> data1 =
            FXCollections.observableArrayList();
    VBox v=new VBox();
    HBox h=new HBox();
    Button accept=new Button("Accept Selected");
    @Override
    public void start(Stage primaryStage) {
        data1.clear();
        for(int i=0;i<report.filtertxt[report.varCounter].getItems().size();i++){
            data1.add(report.filtertxt[report.varCounter].getItems().get(i));
        }
        accept.setPrefWidth(600);
        v.setSpacing(10);
        h.setPrefWidth(600);
        table.setPrefWidth(300);
        table1.setPrefWidth(300);
        h.getChildren().addAll(table,table1);
        v.getChildren().addAll(search_txt,h,accept);
        
        TableColumn<String, String> st1 = new TableColumn(report.varexport);
        st1.setPrefWidth(300);
        st1.setCellValueFactory((p) -> {
            return new ReadOnlyStringWrapper(p.getValue());
        });
        table.setItems(data);
        table.getColumns().addAll(st1);
        TableColumn<String, String> st2 = new TableColumn(report.varexport+" Selected");
        st2.setPrefWidth(300);
        st2.setCellValueFactory((p) -> {
            return new ReadOnlyStringWrapper(p.getValue());
        });
        table1.setItems(data1);
        table1.getColumns().addAll(st2);
        table.setRowFactory( tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    String P = table.getSelectionModel().getSelectedItem();
                    data1.add(P);
                    data.remove(P);
                }
            });
            return row ;
        });
        table1.setRowFactory( tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    String P = table1.getSelectionModel().getSelectedItem();
                    data.add(P);
                    data1.remove(P);
                }
            });
            return row ;
        });
        accept.setOnAction((event)->{
            report.filtertxt[report.varCounter].getItems().clear();
            for (int i=0;i<data1.size();i++){
                report.filtertxt[report.varCounter].getItems().add(data1.get(i));
            }
            report.filtertxt[report.varCounter].getSelectionModel().selectFirst();
            
            primaryStage.close();
        });
        search_txt.textProperty().addListener((javafx.beans.Observable observable) -> {
            if(search_txt.textProperty().get().isEmpty()) {
                table.setItems(data);
                return;
            }
            ObservableList<String> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<String, ?>> cols = table.getColumns();
            for (String data11 : data) {
                for (TableColumn<String, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(search_txt.textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            table.setItems(tableItems);
        });
        if("supplier".equals(report.varexport)){
            appendSupplier();
        }
        if("salesman".equals(report.varexport)){
            appendSalesman();
        }
        if("itemName".equals(report.varexport)){
            appendItem();
        }
        if("barcode".equals(report.varexport)){
            appendbarcode();
        }
        if("expiry".equals(report.varexport)){
            appendexpiry();
        }
         if("price".equals(report.varexport)){
            appendprice();
        }
          if("qty".equals(report.varexport)){
            appendqty();
        }
        BorderPane root = new BorderPane();
        root.setCenter(v);
        
        Scene scene = new Scene(root, 600, 500);
        String css =this.getClass().getResource("report.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle(report.varexport);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    void appendSupplier(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM supplier";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(rs.getString("fname"));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
    void appendSalesman(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM Salesman";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(rs.getString("fname"));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
    void appendItem(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(rs.getString("name"));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
    void appendbarcode(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(rs.getString("barcode"));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
     void appendexpiry(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(rs.getString("expiry"));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
      void appendprice(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(String.valueOf(rs.getDouble("price")));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
       void appendqty(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                data.addAll(String.valueOf(rs.getInt("qty")));
            }
        }
        
        
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
        
    }
}

