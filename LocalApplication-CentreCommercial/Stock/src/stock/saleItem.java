/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class saleItem extends Application {
     private final TableView<items> table = new TableView<>();
    private final ObservableList<items> data =
            FXCollections.observableArrayList();
    Pane panel1=new Pane();
    Label Total=new Label("Total");
    int itemqty;
  @Override
    public void start(Stage primaryStage) {
        Connection dbConnection = null;
        dbConnection = connection.getDBConnection();
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        VBox vbox1=new VBox();
        HBox hbox1=new HBox();
        hbox1.setPrefHeight(50);
        HBox hbox2=new HBox();
        hbox2.setPrefHeight(50);
        hbox1.setStyle("-fx-background-color:gray");
        hbox1.getChildren().add(Total);
        Label l1=new Label("extra sales");
        Label l2=new Label("extra item");
        Label l3=new Label("extra price");
        Label l4=new Label("extra qty");
        TextField text1=new TextField();
        TextField text2=new TextField();
        TextField text3=new TextField();
        text1.setPrefSize(200, 30);
         text2.setPrefSize(200, 30);
          text3.setPrefSize(200, 30);
          Button btn2=new Button("Add");
         
        GridPane grid1=new GridPane();
        grid1.setVgap(10);
        grid1.setHgap(10);
        grid1.add(l1, 0, 0);
        grid1.add(l2, 1, 1);
        grid1.add(l3, 3, 1);
        grid1.add(l4, 5, 1);
        grid1.add(text1, 2, 1);
        grid1.add(text2, 4, 1);
        grid1.add(text3, 6, 1);
        grid1.add(btn2, 7, 1);
        hbox2.getChildren().add(grid1);
        //Adding the Button to the cell
        
        table.setPrefWidth(primaryScreenBounds.getWidth());
      table.setPrefHeight(primaryScreenBounds.getHeight()/1.5);
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
      table.setItems(data);
      table.getColumns().addAll(Itemnamecol,descripCol,PriceCol,QtyCol);
        BorderPane root = new BorderPane();
        TextField barItem=new TextField();
         barItem.setOnAction((event) -> {
            try {
                selectRecordsFromTable(barItem.getText());
                updatetotable(barItem.getText());
            } catch (SQLException ex) {
                Logger.getLogger(saleItem.class.getName()).log(Level.SEVERE, null, ex);
            }
          
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
   private items(String Itemnamevar,double Pricevar,int Qtyvar,String Desc){
       this.ItemName = new SimpleStringProperty(Itemnamevar);
            this.Price = new SimpleDoubleProperty(Pricevar);
            this.Qty = new SimpleIntegerProperty(Qtyvar);
            this.Description = new SimpleStringProperty(Desc);
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
   }
   
    public static void main(String[] args) {
        launch(args);
    }
   void selectRecordsFromTable(String S) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM items WHERE barcode = ?";

		try {
			dbConnection = connection.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, S);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
                                itemqty=rs.getInt("qty");
                                itemqty=itemqty-1;
				String itemname = rs.getString("name");
				String desc = rs.getString("description");
                                double price = rs.getDouble("price");
			data.add(new items(itemname, price, 1, desc));
                            
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
  


   

   
  public void keyPressed (KeyEvent ke)
{
int code=ke.getKeyCode();
if(code==KeyEvent.VK_ENTER ){
    items selectedItems = (items) table.getSelectionModel().getSelectedItems();
    data.remove(selectedItems);
}
}
   void updatetotable(String S) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "update  items set qty = ? where barcode = ?";

		try {
			dbConnection = connection.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, itemqty);
                        preparedStatement.setString(2, S);
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
