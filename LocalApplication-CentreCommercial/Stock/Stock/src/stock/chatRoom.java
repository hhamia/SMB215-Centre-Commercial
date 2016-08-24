/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class chatRoom extends Application {
     ListView<Chat> chat_list=new ListView<>();
     TextArea chat_text=new TextArea();
     Button send_btn=new Button("Send message");
    @Override
    public void start(Stage primaryStage) {
        HBox container=new HBox();
        GridPane grid=new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        container.getChildren().add(grid);
        grid.add(chat_list, 1, 1);
        grid.add(chat_text, 1, 2);
        grid.add(send_btn, 1, 3);
        Insets in=new Insets(20, 20, 20, 20);
        grid.setPadding(in);
        BorderPane root = new BorderPane();
        root.setCenter(container);
        Scene scene = new Scene(root, 1000, 450);
        
        primaryStage.setTitle(Chatting.static_name);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   public class Chat{
       
   }
}
