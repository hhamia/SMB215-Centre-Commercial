/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stock;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


/**
 *
 * @author skynete10
 */
public class Stock extends Application {
    static Label result=new Label("");
    TextField user=new TextField();
    PasswordField pass=new PasswordField();
    @Override
    public void start(Stage primaryStage) {
        
        Label welcome=new Label("Welcome");
        Label username=new Label("username");
        Label password=new Label("password");
        
        result.setId("result");
        username.setId("user");
        password.setId("pss");
        
        user.setId("userfield");
        pass.setId("pssfield");
        welcome.setId("welcome");
        Button loginbtn=new Button("Login");
        loginbtn.setId("loginbtn");
        loginbtn.setPrefWidth(100);
        user.setPrefSize(300, 30);
        pass.setPrefSize(300, 30);
        VBox vbox1=new VBox();
        Image title=new Image("images/finance.png");
        
        ImageView titleview=new ImageView(title);
        titleview.setFitHeight(200);
        titleview.setFitWidth(200);
        GridPane grid1=new GridPane();
        grid1.setVgap(10);
        grid1.setHgap(10);
        GridPane grid3=new GridPane();
        grid3.setVgap(10);
        grid3.setHgap(10);
        GridPane grid2=new GridPane();
        
        grid2.add(titleview, 1, 0);
        grid2.setAlignment(Pos.CENTER);
        grid3.setAlignment(Pos.CENTER);
        grid1.add(welcome, 0, 0);
        grid1.add(username, 0, 1);
        grid1.add(password, 0, 2);
        grid1.add(user, 1, 1);
        grid1.add(pass, 1, 2);
        grid1.add(loginbtn, 0,3);
        grid3.add(result, 1, 0);
        grid1.setAlignment(Pos.CENTER);
        vbox1.getChildren().add(grid1);
        //-----------------------------------------------------------------------
        user.setOnAction((event) -> {
            login();
        });
        pass.setOnAction((event) -> {
            login();
        });
        loginbtn.setOnAction((event) -> {
            login();
        });
        
        BorderPane root = new BorderPane();
        root.setCenter(grid1);
        root.setTop(grid2);
        root.setBottom(grid3);
        Scene scene = new Scene(root, 800, 550);
        String css =this.getClass().getResource("stock.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image ico = new Image("images/stockIcon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkIntConnection();
            }
        }, 0, 1000);
       
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            timer.cancel();
            primaryStage.close();
        });
        
    }
    
    private static boolean checkusers(String user,String pass) throws SQLException {
        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT username,password FROM employer WHERE username = ? and password = ?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            else{
                return false;
            }
            
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return false;
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        
        
    }
    public void login(){
        if(user.getText().equals("")&&pass.getText().equals("")){
            result.setText("empty username and password");
            
            functions.playbeep();
        }
        else{
            try {
                if(checkusers(user.getText(), pass.getText())==true){
                    Stage primaryStage = new Stage(StageStyle.DECORATED);
                    home ho=new home();
                    ho.start(primaryStage);
                }
                else{
                    result.setText("incorrect username or password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean checkIntConnection() {
        
        try {
            //make a URL to a known source
            URL url = new URL("localhost");
            
            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
            
            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();
            
        } catch (UnknownHostException e) {
            //result.setText("not connected");
            System.out.println("not connected");
            return false;
        }
        catch (IOException e) {
            //result.setText("not connected");
             System.out.println(" connected");
            return false;
        }
        //result.setText("connected");
         System.out.println(" connected");
        return true;
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
