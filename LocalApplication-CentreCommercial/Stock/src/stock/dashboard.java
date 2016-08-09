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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class dashboard extends Application {
    TableView<transactions> table=new TableView<>();
    TextField sear_txt=new TextField();
    final static String panadol = "panadol";
    final static String nurebion = "nurebion";
    final static String extra = "extra";
    final static String milk = "milk";
    final static String nexum = "nexum";
    VBox v1=new VBox();
    VBox v2=new VBox();
    VBox v3=new VBox();
    HBox h1=new HBox();
     ObservableList<transactions> data1 =
                FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        
        table.setPrefWidth(350);
        table.setPrefHeight(600);
       
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Panadol", 13),
                        new PieChart.Data("nexium", 25),
                        new PieChart.Data("supralex", 10),
                        new PieChart.Data("Calcivita", 22),
                        new PieChart.Data("nerobion", 30));
        final PieChart chart = new PieChart(pieChartData);
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        
        chart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
            }); });
        chart.setTitle("Items sales");
         final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Items stock");
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(panadol, 25601.34));
        series1.getData().add(new XYChart.Data(nurebion, 20148.82));
        series1.getData().add(new XYChart.Data(extra, 10000));
        series1.getData().add(new XYChart.Data(milk, 35407.15));
        series1.getData().add(new XYChart.Data(nexum, 12000));      
        
        
        bc.getData().addAll(series1);
        final NumberAxis xAxis1 = new NumberAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<>(xAxis1,yAxis1);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Items");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
       
        lineChart.getData().add(series);
        final NumberAxis xAxis2 = new NumberAxis(1, 31, 1);
        final NumberAxis yAxis2 = new NumberAxis();
        final AreaChart<Number,Number> ac = 
            new AreaChart<Number,Number>(xAxis2,yAxis2);
        ac.setTitle("stock Monitoring (in items qty)");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        seriesApril.getData().add(new XYChart.Data(1, 4));
        seriesApril.getData().add(new XYChart.Data(3, 10));
        seriesApril.getData().add(new XYChart.Data(6, 15));
        seriesApril.getData().add(new XYChart.Data(9, 8));
        seriesApril.getData().add(new XYChart.Data(12, 5));
        seriesApril.getData().add(new XYChart.Data(15, 18));
        seriesApril.getData().add(new XYChart.Data(18, 15));
        seriesApril.getData().add(new XYChart.Data(21, 13));
        seriesApril.getData().add(new XYChart.Data(24, 19));
        seriesApril.getData().add(new XYChart.Data(27, 21));
        seriesApril.getData().add(new XYChart.Data(30, 21));
        
        XYChart.Series seriesMay = new XYChart.Series();
        seriesMay.setName("May");
        seriesMay.getData().add(new XYChart.Data(1, 20));
        seriesMay.getData().add(new XYChart.Data(3, 15));
        seriesMay.getData().add(new XYChart.Data(6, 13));
        seriesMay.getData().add(new XYChart.Data(9, 12));
        seriesMay.getData().add(new XYChart.Data(12, 14));
        seriesMay.getData().add(new XYChart.Data(15, 18));
        seriesMay.getData().add(new XYChart.Data(18, 25));
        seriesMay.getData().add(new XYChart.Data(21, 25));
        seriesMay.getData().add(new XYChart.Data(24, 23));
        seriesMay.getData().add(new XYChart.Data(27, 26));
        seriesMay.getData().add(new XYChart.Data(31, 26));
        
       
        ac.getData().addAll(seriesApril, seriesMay);
         TableColumn st1 = new TableColumn("Transaction name");
        st1.setPrefWidth(350/3);
        st1.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn st2 = new TableColumn("Date");
        st2.setPrefWidth(350/3);
        st2.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        TableColumn st3 = new TableColumn("Type");
        st3.setPrefWidth(350/3);
        st3.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        try {
            selectTransactions();
        } catch (SQLException ex) {
            Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        sear_txt.textProperty().addListener((javafx.beans.Observable observable) -> {
            if(sear_txt.textProperty().get().isEmpty()) {
                table.setItems(data1);
                return;
            }
            ObservableList<transactions> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<transactions, ?>> cols = table.getColumns();
            for (transactions data11 : data1) {
                for (TableColumn<transactions, ?> col1 : cols) {
                    TableColumn col = col1;
                    String cellValue = col.getCellData(data11).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(sear_txt.textProperty().get().toLowerCase())) {
                        tableItems.add(data11);
                        break;
                    }
                }
            }
            table.setItems(tableItems);
        });
        table.setItems(data1);
        table.getColumns().addAll(st1,st2,st3);
        v1.getChildren().addAll(chart,lineChart);
        v2.getChildren().addAll(bc,ac);
        v3.getChildren().addAll(sear_txt,table);
        h1.getChildren().addAll(v1,v2,v3);
        BorderPane root = new BorderPane();
        root.setCenter(h1);
        
        Scene scene = new Scene(root, 300, 250);
        String css =this.getClass().getResource("dashboard.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("dashboard");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public class transactions{
        private final SimpleStringProperty name;
        private final SimpleStringProperty date;
        private final SimpleStringProperty type;
        public transactions(String vname,String vdate,String vtype){
            this.name=new SimpleStringProperty(vname);
            this.date=new SimpleStringProperty(vdate);
            this.type=new SimpleStringProperty(vtype);
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
        public String getType() {
            return type.get();
        }
        
        public void setType(String vtype) {
            type.set(vtype);
        }
    }
    void selectTransactions() throws SQLException{
         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String selectSQL = "SELECT * FROM transaction";
        String selectSQL1 = "SELECT * FROM extra";
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement1 = dbConnection.prepareStatement(selectSQL1);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rs1 = preparedStatement1.executeQuery();
            while (rs.next()){
                String name=rs.getString("item_name");
                String date=rs.getString("date");
                String type="normal";
                data1.add(new transactions(name, date, type));
            }
            while (rs1.next()){
                String name1=rs1.getString("name");
                String date1=rs1.getString("date");
                String type1="extra";
                data1.add(new transactions(name1, date1, type1));
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
