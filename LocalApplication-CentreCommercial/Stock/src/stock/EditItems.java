package stock;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditItems extends Application {
    Label labels[]=new Label[9];
    TextField txts[]=new TextField[9];
    DatePicker datep=new DatePicker();
    @Override
    public void start(Stage primaryStage) throws SQLException {
       
         String label_name[]=new String[]{"Item name","Description","Quantity","Price","Barcode","Fond","Expiry","Salesman","Supplier"};
        GridPane labels_grid=new GridPane();
        labels_grid.setVgap(20);
        labels_grid.setHgap(10);
        
        Insets in1=new Insets(20, 20, 20, 20);
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
        Button btn=new Button("Update Item");
        labels_grid.add(btn, 1, 5);
        selectRecordsFromTable(products.staticItem);
        BorderPane root = new BorderPane();
        root.setCenter(labels_grid);
        
        Scene scene = new Scene(root, 700, 450);
        String css =this.getClass().getResource("customer.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
  void selectRecordsFromTable(String S) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM items WHERE name = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, S);
           
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
               txts[0].setText(rs.getString("name"));
               txts[1].setText(rs.getString("description"));
               txts[2].setText(String.valueOf(rs.getInt("Qty")));
               txts[3].setText(String.valueOf(rs.getDouble("Price")));
               txts[4].setText(String.valueOf(rs.getInt("fond")));
               //datep.setValue(rs.getlo("date"));
               txts[6].setText(rs.getString("salesman"));
               txts[7].setText(rs.getString("supplier"));
               txts[8].setText(rs.getString("barcode"));
        }
        }
        catch (SQLException e) {
            
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
