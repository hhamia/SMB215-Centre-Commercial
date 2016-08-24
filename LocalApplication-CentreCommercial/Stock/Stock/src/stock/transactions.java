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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class transactions extends Application {
    ObservableList<transaction> data =FXCollections.observableArrayList();
    VBox v1=new VBox();
    GridPane grid1=new GridPane();
    TableView<transaction> table=new TableView<>();
    GridPane grid2=new GridPane();
    
    Label item=new Label("Item"); Label name=new Label("Name"); Label barcode=new Label("Barcode");
    TextField itemtxt=new TextField();TextField nametxt=new TextField();TextField barcodetxt=new TextField();
    @Override
    public void start(Stage primaryStage) throws SQLException {
        grid2.setVgap(10);grid2.setHgap(10);
        DatePicker t1=new DatePicker();
        DatePicker t2=new DatePicker();
        Label l1=new Label("From date");
        Label l2=new Label("To date");
        Button filter=new Button("Filter");
        Button clear=new Button("Clear Filter");
    Rectangle2D Bounds = Screen.getPrimary().getVisualBounds(); 
    t1.setPrefWidth(Bounds.getWidth()/5); 
    t2.setPrefWidth(Bounds.getWidth()/5); 
    grid2.add(t1, 2, 1);grid2.add(t2, 4, 1);
    grid2.add(l1, 1, 1);grid2.add(l2, 3, 1);
    grid2.add(filter, 5, 1);grid2.add(clear, 6, 1);
    t1.setValue(LocalDate.now()); t2.setValue(LocalDate.now());
    
    
    grid1.setVgap(10);grid1.setHgap(10);
    grid1.add(item, 1, 1);grid1.add(name, 3, 1);grid1.add(barcode, 5, 1);
     grid1.add(itemtxt, 2, 1);grid1.add(nametxt, 4, 1);grid1.add(barcodetxt, 6, 1);
    itemtxt.setPrefWidth(200);nametxt.setPrefWidth(200);barcodetxt.setPrefWidth(200);
     table.setPrefWidth(Bounds.getWidth());
     table.setPrefHeight(Bounds.getHeight()/1.5);
     table.setPlaceholder(new Label("My table is empty message"));
      TableColumn c1 = new TableColumn("Item Name");
        c1.setPrefWidth(Bounds.getWidth()/4.5);
        c1.setCellValueFactory(
                new PropertyValueFactory<>("name"));
         TableColumn c2 = new TableColumn("Date");
        c2.setPrefWidth(Bounds.getWidth()/4.5);
        c2.setCellValueFactory(
                new PropertyValueFactory<>("date"));
         TableColumn c3 = new TableColumn("Qty");
        c3.setPrefWidth(Bounds.getWidth()/4.5);
        c3.setCellValueFactory(
                new PropertyValueFactory<>("qty"));
        table.setItems(data);
        table.getColumns().addAll(c1,c2,c3);
        Insets in=new Insets(20, 10, 10, 10);
        v1.setPadding(in);
        v1.setSpacing(20);
         v1.getChildren().addAll(grid1,grid2,table);
         selectItemDetails(products.staticItem);
         selectTransactions(products.staticItem);
         filter.setOnAction((event)->{
            try {
                gridFilter(t1.getValue().toString(), t2.getValue().toString());
            } catch (SQLException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
         clear.setOnAction((event)->{
            try {
                selectTransactions(products.staticItem);
            } catch (SQLException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
        BorderPane root = new BorderPane();
        root.setCenter(v1);
        
        Scene scene = new Scene(root, Bounds.getWidth()/1.5, Bounds.getHeight()/1.5);
        String css =this.getClass().getResource("transactions.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public class transaction{
        private final SimpleStringProperty name;
        private final SimpleStringProperty date;
        private final SimpleIntegerProperty qty;
        public transaction(String vname,String vdate,int vqty){
            this.name=new SimpleStringProperty(vname);
            this.date=new SimpleStringProperty(vdate);
            this.qty=new SimpleIntegerProperty(vqty);
        }
        public String getName() {
            return name.get();
        }
        
        public void setName(String vname) {
            name.set(vname);
        }
        public String getDate() {
            return date.get();
        }
        
        public void setDate(String vdate) {
            date.set(vdate);
        }
        public int getQty() {
            return qty.get();
        }
        
        public void setQty(int vqty) {
            qty.set(vqty);
        }
    }
    void selectTransactions(String S) throws SQLException{
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM transaction where item_name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()){
                String vname=rs.getString("item_name");
                String vdate=rs.getString("date");
                int vqty=rs.getInt("qty");
                data.add(new transaction(vname, vdate, vqty));
            }
           
            
            
        } catch (SQLException e) {
            
            
            
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        
    }
    void selectItemDetails(String S) throws SQLException{
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items where name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()){
                String vname=rs.getString("name");
                String vdesc=rs.getString("description");
                String vbarc=rs.getString("barcode");
                itemtxt.setText(vname);
                nametxt.setText(vdesc);
                barcodetxt.setText(vbarc);
            }
           
            
            
        } catch (SQLException e) {
            
           
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        
    }
    void gridFilter(String S,String S1) throws SQLException{
            Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM transaction where date between ? and ?";
        data.clear();
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
            preparedStatement.setString(2, S1);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                String vname=rs.getString("item_name");
                String vdate=rs.getString("date");
                int vqty=rs.getInt("qty");
                data.add(new transaction(vname, vdate, vqty));
            }
            
            
            
        } catch (SQLException e) {
            
            
            
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
