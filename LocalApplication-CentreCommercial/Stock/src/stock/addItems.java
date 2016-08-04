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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class addItems extends Application {
    static String staticItem;// item name using in other classes
    Stage editStage=new Stage(StageStyle.DECORATED);// create new null stage to use it at parameter
    TableView<Pers> tab1=new TableView<>();
    TableView<Pers> tab2=new TableView<>();
    //tableview using class person
    TableView<Item> tab3=new TableView<>();
    //tableview using class items
    private final ObservableList<Pers> data1 =
            FXCollections.observableArrayList();
    private final ObservableList<Pers> data2 =
            FXCollections.observableArrayList();
    private final ObservableList<Item> data3 =
            FXCollections.observableArrayList();
    //data to add classes variable and using it in table
    Label labels[]=new Label[9];//label array
    TextField txts[]=new TextField[9];//textfield array
    DatePicker datep=new DatePicker();// callendar
    int flag;
    @Override
    public void start(Stage primaryStage) throws SQLException {
        // set width and height variable
        selectAllFromTable();//select all salesman and supplier
        selectItemsFromTable();//----select all items and display it in tableview
        double widthvar=primaryStage.getWidth();//get stage width
        double heightvar=primaryStage.getHeight();//get stage height
        //-----set tableview width
        tab1.setPrefWidth(700);
        tab2.setPrefWidth(700);
        tab3.setPrefWidth(700);
        
        double tab1width=500;
        double tab2width=500;
        GridPane gridtitle=new GridPane();
        gridtitle.setStyle("-fx-background-color: #2DA2CF;");
        Label titleLabel=new Label("Items");
        gridtitle.add(titleLabel, 1, 5);
        gridtitle.setAlignment(Pos.CENTER);
        titleLabel.setId("titles");
        
        //----labels name and define and textfields array
        
        String label_name[]=new String[]{"Item name","Description","Quantity","Price","Barcode","Fond","Expiry","Salesman","Supplier"};
        GridPane labels_grid=new GridPane();
        labels_grid.setStyle("-fx-grid-lines-visible: true");
        labels_grid.setVgap(20);
        labels_grid.setHgap(10);
        
        Insets in1=new Insets(20, 20, 20, 20);
        //----distribute labels name for lables and textfield names
        labels_grid.setPadding(in1);
        for (int i=0;i<labels.length;i++) {
            labels[i]=new Label(label_name[i]);
            txts[i]=new TextField();
        }
        for(int i=0;i<4;i++){
            labels_grid.add(labels[i], 1, i);
            labels_grid.add(txts[i], 2, i);
        }
        for(int i=5;i<labels.length;i++){
            labels_grid.add(labels[i], 3, i-5);
            labels_grid.add(txts[i], 4, i-5);
        }
        labels_grid.add(new Label("Expiry"), 1, 4);
        labels_grid.add(datep, 2, 4);
        labels_grid.add(labels[4], 3, 4);
        labels_grid.add(txts[4], 4, 4);
        //-----set permission to textfield to use only numbers
        txts[2]=new TextField(){
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
        txts[3]=new TextField(){
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
        //----end append label_grid
        Label search=new Label("Search Items");
        TextField search_txt=new TextField();
        Button addIt=new Button("Add Item");
        VBox v1=new VBox();
        Button Clear=new Button("Clear Fields");
        GridPane btngrid=new GridPane();
        btngrid.setHgap(10);
        btngrid.setVgap(10);
        btngrid.add(addIt, 1, 1);
        btngrid.add(Clear, 3, 1);
        addIt.setOnAction((Event)->{
            checkRequired();//-----check textfield if empty
            if(flag==0){
                try {
                    
                    insertItems();//----if textfield not empty insert items into tableview and into mysql table
                } catch (SQLException ex) {
                    Logger.getLogger(addItems.class.getName()).log(Level.SEVERE, null, ex);
                }
                clearFields();//----clear textfields after insert
            }
            
        });
        //----clear button to clear all textfields
        Clear.setOnAction((Event)->{
            clearFields();
        });
        v1.getChildren().addAll(labels_grid,btngrid,search,search_txt,tab3);
        //----add event to tableview on duble click on row to set name in textfield of supplier and salesman
        tab1.setRowFactory( tv -> {
            TableRow<Pers> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Pers P = tab1.getSelectionModel().getSelectedItem();
                    txts[7].setText(P.getFirstName()+" "+P.getLastName());
                }
            });
            return row ;
        });
        tab2.setRowFactory( tv -> {
            TableRow<Pers> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Pers P = tab2.getSelectionModel().getSelectedItem();
                    txts[8].setText(P.getFirstName()+" "+P.getLastName());
                }
            });
            return row ;
        });
        //----end adding event to tableview on duble click on row to set name in textfield of supplier and salesman
        txts[7].textProperty().addListener((javafx.beans.Observable observable) -> {
//filter tableviews
            if( txts[7].textProperty().get().isEmpty()) {
                tab1.setItems(data1);
                return;
            }
            ObservableList<Pers> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Pers, ?>> cols = tab1.getColumns();
            for (Pers data11 : data1) {
                for (TableColumn<Pers, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(txts[7].textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            tab1.setItems(tableItems);
        });
        txts[8].textProperty().addListener((javafx.beans.Observable observable) -> {
            if(txts[8].textProperty().get().isEmpty()) {
                tab2.setItems(data2);
                return;
            }
            ObservableList<Pers> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Pers, ?>> cols = tab2.getColumns();
            for (Pers data11 : data2) {
                for (TableColumn<Pers, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(txts[8].textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            tab2.setItems(tableItems);
        });
        search_txt.textProperty().addListener((javafx.beans.Observable observable) -> {
            if(search_txt.textProperty().get().isEmpty()) {
                tab3.setItems(data3);
                return;
            }
            ObservableList<Item> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Item, ?>> cols = tab3.getColumns();
            for (Item data11 : data3) {
                for (TableColumn<Item, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(search_txt.textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            tab3.setItems(tableItems);
        });
        //----end adding event
        //----create table column
        TableColumn st1 = new TableColumn("First Name");
        st1.setPrefWidth(tab1width/3);
        st1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        TableColumn st2 = new TableColumn("Last Name");
        st2.setPrefWidth(tab1width/3);
        st2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        TableColumn st3 = new TableColumn("Company");
        st3.setPrefWidth(tab1width/3);
        st3.setCellValueFactory(
                new PropertyValueFactory<>("company"));
        TableColumn st11 = new TableColumn("First Name");
        st11.setPrefWidth(tab1width/3);
        st11.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        TableColumn st22 = new TableColumn("Last Name");
        st22.setPrefWidth(tab1width/3);
        st22.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        TableColumn st33 = new TableColumn("Company");
        st33.setPrefWidth(tab1width/3);
        st33.setCellValueFactory(
                new PropertyValueFactory<>("company"));
        TableColumn st111 = new TableColumn("Item Name");
        st111.setPrefWidth(tab1width/3);
        st111.setCellValueFactory(
                new PropertyValueFactory<>("ItemName"));
        TableColumn st222 = new TableColumn("Price");
        st222.setPrefWidth(tab1width/3);
        st222.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        TableColumn st333 = new TableColumn("Qty");
        st333.setPrefWidth(tab1width/3);
        st333.setCellValueFactory(
                new PropertyValueFactory<>("Qty"));
        //-----ending creating columns
        //----tableviews
        
        VBox vTable=new VBox();
        v1.setPadding(in1);
        vTable.setPadding(in1);
        Label sales=new Label("Salesman");
        Label supp=new Label("Supplier");
        tab1.setItems(data1);
        tab1.getColumns().addAll(st1,st2,st3);
        tab2.setItems(data2);
        tab2.getColumns().addAll(st11,st22,st33);
        tab3.setItems(data3);
        tab3.getColumns().addAll(st111,st222,st333);
        vTable.getChildren().addAll(sales,tab1,supp,tab2);
        //---tableviews
        BorderPane root = new BorderPane();
        root.setTop(gridtitle);
        root.setLeft(v1);
        root.setRight(vTable);
        Scene scene = new Scene(root, 300, 250);
        String css =this.getClass().getResource("items.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Items");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    
    //create class persone
    public static class Pers{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty company;
        private Pers(String fName, String lName, String varcompany){
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.company = new SimpleStringProperty(varcompany);
        }
        public String getFirstName() {
            return firstName.get();
        }
        
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
        
        public String getLastName() {
            return lastName.get();
        }
        
        public void setLastName(String lName) {
            lastName.set(lName);
        }
        public String getCompany() {
            return company.get();
        }
        
        public void setCompany(String varcompany) {
            company.set(varcompany);
        }
    }
    //select all salesman and suppliers from tables
    void selectAllFromTable() throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String selectSQL = "SELECT * FROM salesman ";
        String selectSQL1 = "SELECT * FROM supplier";
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement1 = dbConnection.prepareStatement(selectSQL1);
            
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rs1 = preparedStatement1.executeQuery();
            
            while (rs.next()) {
                String fname=rs.getString("fname");
                String lname=rs.getString("lname");
                String comp=rs.getString("company");
                data1.add(new Pers(fname, lname, comp));
            }
            while (rs1.next()) {
                String fname=rs1.getString("fname");
                String lname=rs1.getString("lname");
                String comp=rs1.getString("company");
                data2.add(new Pers(fname, lname, comp));
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
    //create class items
    public static class Item {
        private final SimpleStringProperty ItemName;
        private final SimpleDoubleProperty Price;
        private final SimpleIntegerProperty Qty;
        private final SimpleStringProperty Description;
        private final SimpleStringProperty Reste;
        private Item(String Itemnamevar,double Pricevar,int Qtyvar,String Desc,String RestVar){
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
    void selectItemsFromTable() throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items ";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                String itemName=rs.getString("name");
                double price=rs.getDouble("price");
                int qty=rs.getInt("qty");
                data3.add(new Item(itemName, price, qty, "", ""));
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
    
    public void clearFields(){
        for (int i=0;i<labels.length;i++) {
            txts[i].clear();
            txts[i].setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
    }
    public void insertItems() throws SQLException{
        String Fields[]=new String[10];
        for (int i=0;i<txts.length;i++){
            Fields[i]=txts[i].getText();
        }
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "INSERT INTO `items`(`name`, `description`, `qty`, `price`, `barcode`, `fond`, "
                + "`expiry`, `salesman`, `supplier`) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1,Fields[0]);
            preparedStatement.setString(2,Fields[1]);
            preparedStatement.setInt(3,Integer.parseInt(Fields[2]));
            preparedStatement.setDouble(4,Double.parseDouble(Fields[3]));
            preparedStatement.setString(5,Fields[4]);
            preparedStatement.setInt(6,Integer.parseInt(Fields[5]));
            preparedStatement.setString(7,String.valueOf(datep.getValue()));
            preparedStatement.setString(8,Fields[7]);
            preparedStatement.setString(9,Fields[8]);
            
            int rs = preparedStatement.executeUpdate();
            data3.add(new Item(Fields[0], Double.parseDouble(Fields[3]), Integer.parseInt(Fields[2]), "", ""));
            
            
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
    public void checkRequired(){
        for(int i=0;i<txts.length;i++){
            if("".equals(txts[i].getText())){
                txts[i].setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                txts[i].requestFocus();
                flag=1;
                break;
            }
            else{
                flag=0;
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
