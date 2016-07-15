/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;

import java.awt.ItemSelectable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed.ElDakdouki
 */
public class customer extends Application {
    TableView<String> tcompany;
    
 
    @Override
    public void start(Stage primaryStage) {
        final List<String> items=new ArrayList<>(Arrays.asList());
   
       final   ComboBox<String> comboBox = new ComboBox<>(
        FXCollections.observableArrayList(new String[]{"lebanon","syria"}));
        
    comboBox.setEditable(true);
    comboBox.getEditor().textProperty()
        .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                appendList();
            } catch (SQLException ex) {
                Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
            }
            final TextField editor = comboBox.getEditor();
            final String selected = comboBox.getSelectionModel()
                    .getSelectedItem();
            if (selected == null || !selected.equals(editor.getText())) {
                filterItems(newValue, comboBox, items);
                comboBox.show();
                if (comboBox.getItems().size() == 1) {
                    final String onlyOption = comboBox.getItems().get(0);
                    final String current = editor.getText();
                    if (onlyOption.length() > current.length()) {
                        editor.setText(onlyOption);
                        // Not quite sure why this only works using
                        // Platform.runLater(...)
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                editor.selectRange(current.length(), onlyOption.length());
                            }
                        });
                    }
                }
            }
        });
    
        VBox v1=new VBox();
        GridPane grid1=new GridPane();
        grid1.setVgap(10);grid1.setHgap(10);
        Label l1=new Label("Personnal Information");
        l1.setId("l1");
        l1.setPrefSize(1000, 30);
        l1.setStyle("-fx-background-color: gray;-fx-font-size: 14px;");
        Label l2=new Label("First Name");
        Label l3=new Label("Company");
        Label l4=new Label("Street address 1");
        Label l5=new Label("City");
        Label l6=new Label("ZIP/Postal Code");
        Label l7=new Label("Fax");
        Label l8=new Label("Last Name");
        Label l9=new Label("Phone");
        Label l10=new Label("Street address 2");
        Label l11=new Label("State/Province");
        Label l12=new Label("Country");
        Label l13=new Label("Type");
        tcompany=new TableView<>();
        TableColumn<String,String> partner=new TableColumn<>();
        tcompany.getColumns().addAll(partner);
        
        TextField t2=new TextField();
        TextField t3=new TextField();
        TextField t4=new TextField();
        TextField t5=new TextField();
        TextField t6=new TextField();
        TextField t7=new TextField();
        TextField t8=new TextField();
        TextField t9=new TextField();
        TextField t10=new TextField();
        TextField t11=new TextField();
        
        ComboBox<String> c2=new ComboBox<>();
        grid1.add(l2, 1, 1);grid1.add(l8, 3, 1);
        grid1.add(l3, 1, 2);grid1.add(l9, 3, 2);
        grid1.add(l4, 1, 3);grid1.add(l10, 3, 3);
        grid1.add(l5, 1, 4);grid1.add(l11, 3, 4);
        grid1.add(l6, 1, 5);grid1.add(l12, 3, 5);
        grid1.add(l7, 1, 6);grid1.add(l13, 3, 6);
        grid1.add(t2, 2, 1);grid1.add(t8, 4, 1);
        grid1.add(t3, 2, 2);grid1.add(t9, 4, 2);
        grid1.add(t4, 2, 3);grid1.add(t10, 4, 3);
        grid1.add(t5, 2, 4);grid1.add(t11, 4, 4);
        grid1.add(t6, 2, 5);grid1.add(comboBox, 4, 5);
        grid1.add(t7, 2, 6);grid1.add(c2, 4, 6);
        v1.getChildren().add(l1);
        v1.getChildren().add(grid1);
        BorderPane root = new BorderPane();
        root.setTop(v1);
        root.setRight(tcompany);
        Scene scene = new Scene(root, 1000, 450);
      //  primaryStage.setResizable(false);
        primaryStage.setTitle("Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    private <T> void filterItems(String filter, ComboBox<T> comboBox,
      List<T> items) {
    List<T> filteredItems = new ArrayList<>();
    for (T item : items) {
      if (item.toString().toLowerCase().startsWith(filter.toLowerCase())) {
        filteredItems.add(item);
      }
    }
    comboBox.setItems(FXCollections.observableArrayList(filteredItems));
  }
    
    private static void appendList() throws SQLException {
        
       
    }
        
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
