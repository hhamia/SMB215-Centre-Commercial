/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import static stock.Client.clientSocket;


public class Chatting extends Application {
     
     TableView<Pers> ChatTab=new TableView<>();
    private final ObservableList<Pers> ChatData =
            FXCollections.observableArrayList();
    private final ObservableList<String> ListData =
            FXCollections.observableArrayList();
    static String static_name;
    Stage chatStage=new Stage(StageStyle.DECORATED);
    static Socket clientSocket = null;
    static PrintStream os = null;
    static DataInputStream is = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        int port_number=1111;
        String host="localhost";
        try {
            clientSocket = new Socket(host, port_number);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            System.err.println("Exception occurred : "+e.getMessage());
        }
        selectAllUsers();
        showPopupMessage("hello bashar", primaryStage);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        ChatTab.setPrefWidth(primaryScreenBounds.getWidth()/3);
        TableColumn st1 = new TableColumn("First Name");
        st1.setPrefWidth(primaryScreenBounds.getWidth()/9);
        st1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        TableColumn st2 = new TableColumn("Last Name");
        st2.setPrefWidth(primaryScreenBounds.getWidth()/9);
        st2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        TableColumn st3 = new TableColumn("status");
        st3.setPrefWidth(primaryScreenBounds.getWidth()/9);
        st3.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        ChatTab.setRowFactory( tv -> {
            TableRow<Pers> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Pers I = ChatTab.getSelectionModel().getSelectedItem();
                    static_name=(I.getFirstName()+' '+I.getLastName());
                   chatRoom chatr=new chatRoom();
                   chatr.start(chatStage);
                }
            });
            return row ;
        });
        ChatTab.setItems(ChatData);
        ChatTab.getColumns().addAll(st1,st2,st3);
        HBox container=new HBox();
        
        VBox chatScreen=new VBox();
        ListView<String> list=new ListView<>();
        list.setPrefWidth(primaryScreenBounds.getWidth()/2);
        ListData.add("mohamed");
        list.setItems(ListData);
        chatScreen.getChildren().add(list);
        GridPane settings=new GridPane();
        settings.setHgap(10);
        settings.setVgap(10);
        SwitchButton btnOnOff=new SwitchButton();
        settings.add(btnOnOff, 1, 2);
        Label Switch=new Label("Switch online offline");
        Switch.setId("switchLabel");
        settings.add(Switch, 1, 1);
        container.getChildren().addAll(list,ChatTab,settings);
        BorderPane root = new BorderPane();
        root.setCenter(container);
        
        Scene scene = new Scene(root, 300, 250);
         String css =this.getClass().getResource("Chattings.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Chatting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    public static class Pers{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty status;
        private Pers(String fName, String lName, String varstatus){
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.status = new SimpleStringProperty(varstatus);
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
        public String getStatus() {
            return status.get();
        }
        
        public void setStatus(String varstatus) {
            status.set(varstatus);
        }
    }
    void selectAllUsers() throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
      
        String selectSQL = "SELECT * FROM users ";
       
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
           
            
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
           
            
            while (rs.next()) {
                String fname=rs.getString("fname");
                String lname=rs.getString("lname");
                ChatData.add(new Pers(fname, lname, "Offline"));
               
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
    public static Popup createPopup(final String message) {
    final Popup popup = new Popup();
    popup.setAutoFix(true);
    popup.setAutoHide(true);
    popup.setHideOnEscape(true);
    Label label = new Label(message);
    label.setId("popup");
    label.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            popup.hide();
        }
    });
    
    popup.getContent().add(label);
    return popup;
}

public static void showPopupMessage(final String message, final Stage stage) {
    final Popup popup = createPopup(message);
    popup.setOnShown(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent e) {
            popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
            popup.setY(stage.getY() + stage.getHeight()/2 - popup.getHeight()/2);
        }
    });        
    popup.show(stage);
}
}
