package stock;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
    Label labels[]=new Label[8];
    TextField txts[]=new TextField[8];
    DatePicker datep=new DatePicker();
    Label labelId0=new Label("code:");
    Label labelId=new Label();
    @Override
    public void start(Stage primaryStage) throws SQLException {
       
         String label_name[]=new String[]{"Item name","Description","Quantity","Price","Barcode","Fond","Salesman","Supplier"};
        GridPane labels_grid=new GridPane();
        labels_grid.setVgap(20);
        labels_grid.setHgap(10);
        
        Insets in1=new Insets(20, 20, 20, 20);
        labels_grid.setPadding(in1);
        for (int i=0;i<labels.length;i++) {
            labels[i]=new Label(label_name[i]);
            txts[i]=new TextField();
        }
        labels_grid.add(labelId0, 0, 0);
        labels_grid.add(labelId, 1, 0);
        for(int i=0;i<4;i++){
            labels_grid.add(labels[i], 1, i+1);
            labels_grid.add(txts[i], 2, i+1);
        }
        for(int i=5;i<labels.length;i++){
            labels_grid.add(labels[i], 3, i-4);
            labels_grid.add(txts[i], 4, i-4);
        }
        labels_grid.add(new Label("Expiry"), 1, 5);
        labels_grid.add(datep, 2, 5);
        labels_grid.add(labels[4], 3, 4);
        labels_grid.add(txts[4], 4, 4);
        Button btn=new Button("Update Item");
        Button btn1=new Button("Delete Item");
        btn.setOnAction((ActionEvent Event) -> {
             try {
                 updatetotable(Integer.parseInt(labelId.getText()));
             } catch (SQLException ex) {
                 Logger.getLogger(EditItems.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            clearFields();
             
            primaryStage.close();
           
         });
        btn1.setOnAction((Event)->{
             try {  
                 deletfromtable(Integer.parseInt(labelId.getText()));
             } catch (SQLException ex) {
                 Logger.getLogger(EditItems.class.getName()).log(Level.SEVERE, null, ex);
             }
             
              primaryStage.close();
              
        });
        labels_grid.add(btn, 1, 6);
         labels_grid.add(btn1, 2, 6);
        selectRecordsFromTable(products.staticItem);
        BorderPane root = new BorderPane();
        root.setCenter(labels_grid);
        
        Scene scene = new Scene(root, 700, 450);
        String css =this.getClass().getResource("customer.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("");
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
                labelId.setText(String.valueOf(rs.getInt("iditem")));
               txts[0].setText(rs.getString("name"));
               txts[1].setText(rs.getString("description"));
               txts[2].setText(String.valueOf(rs.getInt("Qty")));
               txts[3].setText(String.valueOf(rs.getDouble("Price")));
               txts[5].setText(String.valueOf(rs.getInt("fond")));
               Date date = rs.getDate("expiry");
               Instant instant = Instant.ofEpochMilli(date.getTime());
               LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
               datep.setValue(res);
               
               txts[6].setText(rs.getString("salesman"));
               txts[7].setText(rs.getString("supplier"));
               txts[4].setText(rs.getString("barcode"));
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
  void updatetotable(int id) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "update  items set name=?,description=?,qty=?,supplier=?,salesman=?,price=?,expiry=?,barcode=?,fond=? where iditem = ? ";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            preparedStatement.setString(1, txts[0].getText());
            preparedStatement.setString(2, txts[1].getText());
            preparedStatement.setString(3, txts[2].getText());
            preparedStatement.setString(4, txts[7].getText());
            preparedStatement.setString(5, txts[6].getText());
            preparedStatement.setString(6, txts[3].getText());
            preparedStatement.setString(7, String.valueOf(datep.getValue()));
            preparedStatement.setString(8, txts[4].getText());
            preparedStatement.setInt(9, Integer.parseInt(txts[5].getText()));
            preparedStatement.setString(10, labelId.getText());
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
    //delete from transaction table  if i make remove item
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
  public void clearFields(){
        for (int i=0;i<labels.length;i++) {
            txts[i].clear();
            txts[i].setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
    }
   void deletfromtable(int id) throws SQLException {
        //products.data1.remove(new products.Item(txts[0].getText(),Double.parseDouble(txts[3].getText()),Integer.parseInt(txts[2].getText()),"",""));
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "delete from items where iditem = ? ";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            preparedStatement.setInt(1, id);
           
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
}
