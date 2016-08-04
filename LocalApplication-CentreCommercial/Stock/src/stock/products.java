package stock;




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
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class products extends Application {
    TableView<Item> tab1=new TableView<>();
    static String staticItem;
    double tab1width=500;
    Stage editStage=new Stage(StageStyle.DECORATED);
    private final ObservableList<Item> data1 =
            FXCollections.observableArrayList();
    VBox box=new VBox();
    
    GridPane gridtitle=new GridPane();
    Label search=new Label("Search");
    TextField sear_txt=new TextField();
    @Override
    public void start(Stage primaryStage) throws SQLException {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        Label titleLabel=new Label("Stock Card");
        gridtitle.add(titleLabel, 1, 5);
        gridtitle.setAlignment(Pos.CENTER);
       titleLabel.setId("titles");
       titleLabel.setAlignment(Pos.CENTER);
         final LineChart<String,Number> lineChart = 
                new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle("Items Stock");
        //defining a series
         
         xAxis.setLabel("Month");
        
       
        lineChart.setTitle("Stock Items");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Stock");
        
        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Sales");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));
        series2.getData().add(new XYChart.Data("May", 39));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 55));
        series2.getData().add(new XYChart.Data("Aug", 54));
        series2.getData().add(new XYChart.Data("Sep", 48));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 29));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Return");
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));
        series3.getData().add(new XYChart.Data("May", 31));
        series3.getData().add(new XYChart.Data("Jun", 26));
        series3.getData().add(new XYChart.Data("Jul", 22));
        series3.getData().add(new XYChart.Data("Aug", 25));
        series3.getData().add(new XYChart.Data("Sep", 43));
        series3.getData().add(new XYChart.Data("Oct", 44));
        series3.getData().add(new XYChart.Data("Nov", 45));
        series3.getData().add(new XYChart.Data("Dec", 44));
        lineChart.getData().addAll(series1, series2, series3);
        selectItemsFromTable();
        box.getChildren().addAll(sear_txt,tab1);
        
        BorderPane root = new BorderPane();
        root.setTop(gridtitle);
        root.setLeft(box);
        root.setRight(lineChart);
        Scene scene = new Scene(root, 300, 250);
        String css =this.getClass().getResource("items.css").toExternalForm();
        scene.getStylesheets().add(css);
        TableColumn st1 = new TableColumn("Item Name");
        st1.setPrefWidth(tab1width/3);
        st1.setCellValueFactory(
                new PropertyValueFactory<>("ItemName"));
        TableColumn st2 = new TableColumn("Price");
        st2.setPrefWidth(tab1width/3);
        st2.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        TableColumn st3 = new TableColumn("Qty");
        st3.setPrefWidth(tab1width/3);
        st3.setCellValueFactory(
                new PropertyValueFactory<>("Qty"));
        tab1.setItems(data1);
        tab1.getColumns().addAll(st1,st2,st3);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        box.setPrefWidth(primaryScreenBounds.getWidth()/2);
        box.setPrefHeight(primaryScreenBounds.getHeight());
        tab1.setPrefHeight(primaryScreenBounds.getHeight());
        tab1.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Item I = tab1.getSelectionModel().getSelectedItem();
                    staticItem=(I.getItemName());
                    EditItems editN=new EditItems();
                    try {
                        editN.start(editStage);
                    } catch (SQLException ex) {
                        Logger.getLogger(addItems.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row ;
        });
        /*sear_txt.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            data1.clear();
            try {
                selectItemsBFromTable(sear_txt.getText());
            } catch (SQLException ex) {
                Logger.getLogger(addItems.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        sear_txt.textProperty().addListener((javafx.beans.Observable observable) -> {
            if(sear_txt.textProperty().get().isEmpty()) {
                tab1.setItems(data1);
                return;
            }
            ObservableList<Item> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Item, ?>> cols = tab1.getColumns();
            for (Item data11 : data1) {
                for (TableColumn<Item, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(sear_txt.textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            tab1.setItems(tableItems);
        });
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
                data1.add(new Item(itemName, price, qty, "", ""));
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
     void selectItemsBFromTable(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items WHERE barcode like ? or name like ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%"+S+"%");
            preparedStatement.setString(2, "%"+S+"%");
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String itemName=rs.getString("name");
                double price=rs.getDouble("price");
                int qty=rs.getInt("qty");
                data1.add(new Item(itemName, price, qty, "", ""));
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
