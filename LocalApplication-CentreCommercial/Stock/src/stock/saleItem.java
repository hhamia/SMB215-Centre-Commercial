/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class saleItem extends Application {
    double TotalPrice1;
    int itemqty1;
    TextField barItem=new TextField();
    TextField text1;
    TextField text2;
    TextField text3;
    TextField text4;
    private final TableView<items> table = new TableView<>();
    private final ObservableList<items> data =
            FXCollections.observableArrayList();
    Pane panel1=new Pane();
    Label Total=new Label("Total (L.L)");
    Label TotalPrice=new Label("0.0");
     Label curr=new Label("L.L");
    int itemqty;
    @Override
    public void start(Stage primaryStage) {
        Connection dbConnection = null;
        dbConnection = connection.getDBConnection();
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Total.setId("total");
        VBox vbox1=new VBox();
        HBox hbox1=new HBox();
        hbox1.setPrefHeight(50);
        HBox hbox2=new HBox();
        hbox2.setPrefHeight(50);
        hbox1.setStyle("-fx-background-color:gray");
        hbox1.setSpacing(200);
        TotalPrice.setId("total");
        hbox1.getChildren().add(Total);
        hbox1.getChildren().add(TotalPrice);
        
        Label l1=new Label("Extra sales");
        Label l2=new Label("Extra item");
        Label l3=new Label("Extra price");
        Label l4=new Label("Extra qty");
        Label l5=new Label("Extra Description");
        text1=new TextField();
        text2=new TextField(){
            @Override public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }
            
            @Override public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };
        text3=new TextField(){
            @Override public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }
            
            @Override public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };
        text4=new TextField();
        text1.setPrefSize(200, 30);
        text2.setPrefSize(200, 30);
        text3.setPrefSize(200, 30);
        text4.setPrefSize(200, 30);
        Button btn2=new Button("Add extra item");
        btn2.setPrefWidth(200);
        Button btn3=new Button("Generate Invoice");
        btn3.setPrefWidth(200);
        GridPane grid1=new GridPane();
        grid1.setVgap(10);
        grid1.setHgap(10);
        grid1.add(l1, 0, 0);
        grid1.add(l2, 1, 1);
        grid1.add(l3, 3, 1);
        grid1.add(l4, 5, 1);
        grid1.add(l5, 7, 1);
        grid1.add(text1, 2, 1);
        grid1.add(text2, 4, 1);
        grid1.add(text3, 6, 1);
        grid1.add(text4, 8, 1);
        grid1.add(btn2, 4, 2);
        grid1.add(btn3, 6, 2);
        btn2.setOnAction((event) -> {
            if(checkrequiredField()==false){
                String extraname=text1.getText();
                String extradesc=text4.getText();
                double extraprice=Double.valueOf(text2.getText());
                int extraqty=Integer.parseInt(text3.getText());
                data.add(new items(extraname,extraprice,extraqty,extradesc,"Extra items"));
                TotalPrice1=TotalPrice1+(extraprice*extraqty);
                   TotalPrice.setText(String.valueOf(TotalPrice1));
                try {
                    insertIntoextra(extraname, extraqty, extraprice, extradesc, getDate());
                } catch (SQLException ex) {
                    Logger.getLogger(saleItem.class.getName()).log(Level.SEVERE, null, ex);
                }
                barItem.clear();
                clearFields();
            }
        });
        hbox2.getChildren().add(grid1);
        //Adding the Button to the cell
        
        table.setPrefWidth(primaryScreenBounds.getWidth());
        table.setPrefHeight(primaryScreenBounds.getHeight()/1.5);
        table.setRowFactory((TableView<items> tableView) -> {
            final TableRow<items> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Remove item");
            removeMenuItem.setOnAction((ActionEvent event) -> {
            items item = table.getSelectionModel().getSelectedItem();         
                table.getItems().remove(row.getItem());
                if("Extra items".equals(item.getReste())){
                try {
                    deletFromextra(item.getItemName());
                    TotalPrice1=TotalPrice1-(item.getPrice()*item.getQty());
                    TotalPrice.setText(String.valueOf(TotalPrice1));
                } catch (SQLException ex) {
                    Logger.getLogger(saleItem.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                else{
                try {
                    deletFromtrans(item.getItemName(), itemqty1+1);
                    TotalPrice1=TotalPrice1-item.getPrice();
                    TotalPrice.setText(String.valueOf(TotalPrice1));
                } catch (SQLException ex) {
                    Logger.getLogger(saleItem.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                barItem.requestFocus();
            });
            contextMenu.getItems().add(removeMenuItem);
            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );
            return row ;
        });  
        TableColumn Itemnamecol=new TableColumn("Item Name");
        Itemnamecol.setPrefWidth(300);
        Itemnamecol.setCellValueFactory(
                new PropertyValueFactory<>("ItemName"));
        TableColumn descripCol=new TableColumn("Description");
        descripCol.setPrefWidth(700);
        descripCol.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        TableColumn PriceCol=new TableColumn("Price");
        PriceCol.setPrefWidth(100);
        PriceCol.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        
        
        TableColumn QtyCol=new TableColumn("Qty");
        QtyCol.setPrefWidth(100);
        QtyCol.setCellValueFactory(
                new PropertyValueFactory<>("Qty"));
        TableColumn QtyResCol=new TableColumn("Qty Reste");
        QtyResCol.setPrefWidth(170);
        QtyResCol.setCellValueFactory(
                new PropertyValueFactory<>("Reste"));
        
        table.setItems(data);
        table.getColumns().addAll(Itemnamecol,descripCol,PriceCol,QtyCol,QtyResCol);
        BorderPane root = new BorderPane();
        
        
        barItem.setOnAction((event) -> {
            
            try {
                selectRecordsFromTable(barItem.getText());
            } catch (SQLException ex) {
                Logger.getLogger(saleItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            barItem.clear();
        });
        vbox1.getChildren().add(barItem);
        barItem.setPrefWidth(primaryScreenBounds.getWidth());
        barItem.setPrefHeight(primaryScreenBounds.getWidth()/30);
        barItem.setPromptText("enter item or barcode here");
        vbox1.getChildren().add(table);
        vbox1.getChildren().add(hbox1);
        vbox1.getChildren().add(hbox2);
        root.setTop(vbox1);
        Scene scene = new Scene(root, 800, 550);
        
        String css =this.getClass().getResource("items.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setMaximized(true);
        Image ico = new Image("images/Add-item-icon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("sale items");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        
    }
    
    public static class items {
        private final SimpleStringProperty ItemName;
        private final SimpleDoubleProperty Price;
        private final SimpleIntegerProperty Qty;
        private final SimpleStringProperty Description;
        private final SimpleStringProperty Reste;
        private items(String Itemnamevar,double Pricevar,int Qtyvar,String Desc,String RestVar){
            this.ItemName = new SimpleStringProperty(Itemnamevar);
            this.Price = new SimpleDoubleProperty(Pricevar);
            this.Qty = new SimpleIntegerProperty(Qtyvar);
            this.Description = new SimpleStringProperty(Desc);
            this.Reste = new SimpleStringProperty(RestVar);
        }
        public String getItemName() {
            return ItemName.get();
        }
        
        public void setItemName(String itemName) {
            ItemName.set(itemName);
        }
        public String getDescription() {
            return Description.get();
        }
        
        public void setDescription(String itemDesc) {
            Description.set(itemDesc);
        }
        public double getPrice() {
            return Price.get();
        }
        
        public void setPrice(double itemprice) {
            Price.set(itemprice);
        }
        public int getQty() {
            return Qty.get();
        }
        
        public void setQty(int itemqty) {
            Qty.set(itemqty);
        }
        public String getReste() {
            return Reste.get();
        }
        
        public void setReste(String ResV) {
            Reste.set(ResV);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    void selectRecordsFromTable(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items WHERE barcode = ? or name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
            preparedStatement.setString(2, S);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            String result = null;
            int fond;
            if (rs.next()) {
                int itemqty1=rs.getInt("qty");
                fond=rs.getInt("fond");
                itemqty=itemqty1-1;
                if(itemqty1<fond && itemqty1>0){
                    result=itemqty+" low than "+fond+" items";
                    String itemname = rs.getString("name");
                    String desc = rs.getString("description");
                    double price = rs.getDouble("price");
                    data.add(new items(itemname, price, 1, desc,result));
                    insertIntoTransaction(itemname,1,price,getDate(),"");
                     TotalPrice1=TotalPrice1+price;
                   TotalPrice.setText(String.valueOf(TotalPrice1));
                    updatetotable(itemname);
                }
                
                else if(itemqty1==0){
                    
                    result=itemqty1+" no items qty available";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(result);
                    alert.setContentText(result);
                    
                    alert.showAndWait();
                }
                else{
                    String itemname = rs.getString("name");
                    String desc = rs.getString("description");
                    double price = rs.getDouble("price");
                    result=itemqty+" qty available";
                    
                    data.add(new items(itemname, price, 1, desc,result));
                    insertIntoTransaction(itemname,1,price,getDate(),"");
                    TotalPrice1=TotalPrice1+price;
                     TotalPrice.setText(String.valueOf(TotalPrice1));
                    updatetotable(barItem.getText());
                }
                
                
                
            }
            else{
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Item not found");
                alert.setContentText("Item not found");
                
                alert.showAndWait();
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
     public String getDate(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Date date = new Date();

        return dateFormat.format(date);
     }
    private void clearFields() {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text1.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        
        text2.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        
        text3.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        
        text4.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        
    }
    
    private boolean checkrequiredField(){
        if("".equals(text1.getText())){
            text1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            text1.requestFocus();
            return true;
        }
        else if("".equals(text2.getText())){
            text2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            text2.requestFocus();
            return true;
        }
        else if("".equals(text3.getText())){
            text3.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            text3.requestFocus();
            return true;
        }
        
        else{
            return false;
        }
    }
    
    
    public void keyPressed (KeyEvent ke)
    {
        int code=ke.getKeyCode();
        if(code==KeyEvent.VK_ENTER ){
            items selectedItems = (items) table.getSelectionModel().getSelectedItems();
            data.remove(selectedItems);
        }
    }
    void insertIntoTransaction(String itemname,int qty,double price,String date,String time) throws SQLException{
     Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "INSERT INTO `transaction`(`item_name`, `qty`, `price`, `date`, `time`)"
                + " VALUES (?,?,?,?,?)";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, itemname);
            preparedStatement.setInt(2, qty);
            preparedStatement.setDouble(3, price);
              preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
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
    void insertIntoextra(String itemname,int qty,double price,String desc,String date) throws SQLException{
     Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "INSERT INTO `extra`(`name`, `qty`, `price`, `description`, `date`) VALUES"
                + "(?,?,?,?,?)";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, itemname);
            preparedStatement.setInt(2, qty);
            preparedStatement.setDouble(3, price);
              preparedStatement.setString(4, desc);
            preparedStatement.setString(5, date);
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
    void updatetotable(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "update  items set qty = ? where barcode = ? or name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, itemqty);
            preparedStatement.setString(2, S);
            preparedStatement.setString(3, S);
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
  void deletFromtrans(String S,int qtyValue) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
         PreparedStatement preparedStatement1 = null;
        String selectSQL = "delete from transaction where item_name = ?";
         String selectSQL1 = "update  items set qty = ? where barcode = ? or name = ?";
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
             preparedStatement1 = dbConnection.prepareStatement(selectSQL1);
            preparedStatement.setString(1, S);
            preparedStatement1.setInt(1, qtyValue);
            preparedStatement1.setString(2, S);
            preparedStatement1.setString(3, S);
            // execute select SQL stetement
            int rs = preparedStatement.executeUpdate();
            int rs1 = preparedStatement1.executeUpdate();
            
            
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
  void deletFromextra(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "delete from extra where name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            preparedStatement.setString(1, S);
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
   void checkItemQty(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items WHERE barcode = ? or name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
            preparedStatement.setString(2, S);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            String result = null;
            int fond;
            if (rs.next()) {
                 itemqty1=rs.getInt("qty");
                
          
            }
            else{
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Item not found");
                alert.setContentText("Item not found");
                
                alert.showAndWait();
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
