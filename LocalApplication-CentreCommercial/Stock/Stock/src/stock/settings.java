package stock;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

public class settings extends Application {
    TextField sear_txt=new TextField(); //searsh costomer in table view
  
    static final ObservableList<User> data0 =
            FXCollections.observableArrayList();
    private final TableView<User> table = new TableView<>();
    final VBox vv=new VBox();
     final HBox hh=new HBox();
    GridPane grid1=new GridPane();
   TextField compNametxt=new TextField();
   TextField compPhonetxt=new TextField();
   TextField compMobiletxt=new TextField();
   TextField compFaxtxt=new TextField();
   TextField compCapitaltxt=new TextField();
    Button btnUpdate=new Button("Update Info");
 GridPane grid2=new GridPane();
   TextField connURL=new TextField();
   TextField connUSER=new TextField();
   TextField connPASS=new TextField();
   Button btnUpdateconn=new Button("Update Connection Info");
   GridPane grid3=new GridPane();
   TextField user1=new TextField();
   TextField user2=new TextField();
   TextField user3=new TextField();
   TextField user4=new TextField();
   TextField user5=new TextField();
   TextField user6=new TextField();
   TextField user7=new TextField();
   Label l1=new Label("Email");
     Label l2=new Label("Username");
      Label l3=new Label("Password");
       Label l4=new Label("Type");
        Label l5=new Label("First Name");
         Label l6=new Label("Last Name");
         Label l7=new Label("Address");
   Button btnInsertUser=new Button("Add New User");
   void addtotable(){
       data0.add(new User(String.valueOf(data0.size()+1),user1.getText(), user2.getText(), user3.getText(), 
               user4.getText(), user5.getText(), user6.getText(), user7.getText()));
   }
    @Override
    public void start(Stage primaryStage) {
        
      grid1.setVgap(10);
      grid1.setHgap(10);
         Label conURL=new Label("Connection url");
        Label conUSER=new Label("connection username");
        Label conPASS=new Label("Connection password");
        Label compName=new Label("Company Name");
        Label compPhone=new Label("Company Phone");
        Label compMobile=new Label("Company Mobile");
        Label compFax=new Label("Company Fax");
        Label compCapital=new Label("Company Capital");
        grid1.add(compName, 1, 1); grid1.add(compNametxt, 1, 2);
        grid1.add(compPhone, 1, 3); grid1.add(compPhonetxt, 1, 4);
        grid1.add(compMobile, 1, 5); grid1.add(compMobiletxt, 1, 6);
        grid1.add(compFax, 1, 7); grid1.add(compFaxtxt, 1, 8);
        grid1.add(compCapital, 1, 9); grid1.add(compCapitaltxt, 1, 10);
         grid1.add(btnUpdate, 1, 11);
         grid2.add(conURL, 1, 1); grid2.add(connURL, 1, 2);
        grid2.add(conUSER, 1, 3); grid2.add(connUSER, 1, 4);
        grid2.add(conPASS, 1, 5); grid2.add(connPASS, 1,6);
         grid2.add(btnUpdateconn, 1, 7);
         grid3.add(l1, 1, 1);grid3.add(user1, 1, 2);
         grid3.add(l2, 1, 3);grid3.add(user2, 1, 4);
         grid3.add(l3, 1, 5);grid3.add(user3, 1, 6);
         grid3.add(l4, 1, 7);grid3.add(user4, 1, 8);
         grid3.add(l5, 1, 9);grid3.add(user5, 1, 10);
         grid3.add(l6, 1, 11);grid3.add(user6, 1, 12);
         grid3.add(l7, 1, 13);grid3.add(user7, 1, 14);
         grid3.add(btnInsertUser, 1, 15);
         btnInsertUser.setOnAction((event)->{
             addtotable();
             user1.clear();user2.clear();user3.clear();user4.clear();user5.clear();user6.clear();user7.clear();
             user1.requestFocus();
         });
        doit();
        iniCon();
        hh.setSpacing(20);
        hh.getChildren().addAll(grid1,grid2,grid3);
        BorderPane root = new BorderPane();
        root.setTop(hh);
        vv.getChildren().addAll(sear_txt,table);
        root.setBottom(vv);
        btnUpdate.setOnAction((event)->{
            writeIt();
        });
        btnUpdateconn.setOnAction((event)->{
            writeItconn();
        });
        Scene scene = new Scene(root, 300, 250);
        String css =this.getClass().getResource("settings.css").toExternalForm();
        scene.getStylesheets().add(css);
        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double widthVar=(primaryScreenBounds.getWidth())/8;
        TableColumn st1 = new TableColumn("ID");
        st1.setMinWidth(widthVar);
        st1.setCellValueFactory(
                new PropertyValueFactory<>("ID"));
        st1.setCellFactory(cellFactory);
        st1.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setID(t.getNewValue());
                    }

         
                }
        );
        TableColumn st2 = new TableColumn("Username");
        st2.setMinWidth(widthVar);
        st2.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        st2.setCellFactory(cellFactory);
        st2.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setUsername(t.getNewValue());
                    }
                }
        );
        TableColumn st3 = new TableColumn("Password");
        st3.setMinWidth(widthVar);
        st3.setCellValueFactory(
                new PropertyValueFactory<User, String>("password"));
        st3.setCellFactory(cellFactory);
        st3.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setPassword(t.getNewValue());
                    }
                }
        );
        TableColumn st4 = new TableColumn("Type");
        st4.setMinWidth(widthVar);
        st4.setCellValueFactory(
                new PropertyValueFactory<User, String>("type"));
        st4.setCellFactory(cellFactory);
        st4.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setType(t.getNewValue());
                    }
                }
        );
        TableColumn st5 = new TableColumn("First Name");
        st5.setMinWidth(widthVar);
        st5.setCellValueFactory(
                new PropertyValueFactory<User, String>("fname"));
        st5.setCellFactory(cellFactory);
        st5.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setFname(t.getNewValue());
                    }
                }
        );
        TableColumn st6 = new TableColumn("Last Name");
        st6.setMinWidth(widthVar);
        st6.setCellValueFactory(
                new PropertyValueFactory<User, String>("lname"));
        st6.setCellFactory(cellFactory);
        st6.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setLname(t.getNewValue());
                    }
                }
        );
        TableColumn st7 = new TableColumn("Email");
        st7.setMinWidth(widthVar);
        st7.setCellValueFactory(
                new PropertyValueFactory<User, String>("email"));
        st7.setCellFactory(cellFactory);
        st7.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setEmail(t.getNewValue());
                    }
                }
        );
        TableColumn st8 = new TableColumn("Address");
        st8.setMinWidth(widthVar);
        st8.setCellValueFactory(
                new PropertyValueFactory<User, String>("address"));
        st8.setCellFactory(cellFactory);
        st8.setOnEditCommit(
                new EventHandler<CellEditEvent<User, String>>() {
                    @Override
                    public void handle(CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setAddress(t.getNewValue());
                    }
                }
        );
       table.setItems(data0);
        table.getColumns().addAll(st1,st2,st3,st4,st5,st6,st7,st8);
        
        //searsh data in the table view and get it apper on the table vew  selon searsh box
         sear_txt.textProperty().addListener((javafx.beans.Observable observable) -> {
            if(sear_txt.textProperty().get().isEmpty()) {
                table.setItems(data0);
                return;
            }
            ObservableList<User> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<User, ?>> cols = table.getColumns();
            for (User data11 : data0) {
                for (TableColumn<User, ?> col1 : cols) {
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
        selectUsersFrom();
      
        //set Stage boundaries to visible bounds of the main screen
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
    private void iniCon() {
    try{
      Properties p = new Properties();
      p.load(new FileInputStream("C:\\Users\\skynete10\\Documents\\NetBeansProjects\\Stock\\src\\stock\\connection.ini"));
      connURL.setText(p.getProperty("url"));
      connUSER.setText(p.getProperty("username"));
      connPASS.setText(p.getProperty("password"));
      
      }
    catch (Exception e) {
      System.out.println(e);
      }
    
    }
     public void doit() {
    try{
      Properties p = new Properties();
      p.load(new FileInputStream("C:\\Users\\skynete10\\Documents\\NetBeansProjects\\Stock\\src\\stock\\config.ini"));
      compNametxt.setText(p.getProperty("name"));
      compPhonetxt.setText(p.getProperty("phone"));
      compMobiletxt.setText(p.getProperty("mobile"));
      compFaxtxt.setText(p.getProperty("fax"));
      compCapitaltxt.setText(p.getProperty("capital"));
      
      
      }
    catch (Exception e) {
      System.out.println(e);
      }
    }
     private void writeIt() {
        // Try to update values
        try {
            Properties pri = new Properties();
           
            pri.setProperty("name", compNametxt.getText());
            pri.setProperty("phone", compPhonetxt.getText());
            pri.setProperty("mobile", compMobiletxt.getText());
            pri.setProperty("fax", compFaxtxt.getText());
            pri.setProperty("capital", compCapitaltxt.getText());
            pri.store(new FileOutputStream("C:\\Users\\skynete10\\Documents\\NetBeansProjects\\Stock\\src\\stock\\config.ini"),"" );
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
    }
      private void writeItconn() {
        // Try to update values
        try {
            Properties pri = new Properties();
           
            pri.setProperty("url", connURL.getText());
            pri.setProperty("username", connUSER.getText());
            pri.setProperty("password", connPASS.getText());
           
            pri.store(new FileOutputStream("C:\\Users\\skynete10\\Documents\\NetBeansProjects\\Stock\\src\\stock\\connection.ini"),"" );
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
    }
      public static class User {
        private final SimpleStringProperty ID;
        private final SimpleStringProperty email;
        private final SimpleStringProperty username;
        private final SimpleStringProperty password;
        private final SimpleStringProperty type;
        private final SimpleStringProperty fname;
        private final SimpleStringProperty lname;
        private final SimpleStringProperty address;
       
        
        private User(String IDvar,String emailname,String uname, String pname, String typename,String fname1,String lname1
                ,String addname) {
            this.ID = new SimpleStringProperty(IDvar);
            this.email = new SimpleStringProperty(emailname);
            this.username = new SimpleStringProperty(uname);
            this.password = new SimpleStringProperty(pname);
            this.type = new SimpleStringProperty(typename);
            this.fname = new SimpleStringProperty(fname1);
            this.lname =new SimpleStringProperty(lname1);
            this.address = new SimpleStringProperty(addname);
           
            
            
        }
        public String getID() {
            return ID.get();
        }
        
        public void setID(String IDvar) {
            ID.set(IDvar);
        }
        public String getEmail() {
            return email.get();
        }
        
        public void setEmail(String emailname) {
            email.set(emailname);
        }
        
        public String getUsername() {
            return username.get();
        }
        
        public void setUsername(String uname) {
            username.set(uname);
        }
        public String getPassword() {
            return password.get();
        }
        
        public void setPassword(String pname) {
            password.set(pname);
        }
        public String getType() {
            return type.get();
        }
        
        public void setType(String typename) {
            type.set(typename);
        }
        public String getFname() {
            return fname.get();
        }
        
        public void setFname(String fname1) {
            fname.set(fname1);
        }
        public String getLname() {
            return lname.get();
        }
        
        public void setLname(String lname1) {
            lname.set(lname1);
        }
        
        public String getAddress() {
            return address.get();
        }
        
        public void setAddress(String addname) {
            address.set(addname);
        }
       
        
        
        
    }
       class EditingCell extends TableCell<User, String> {
        
        private TextField textField;
        
        public EditingCell() {
        }
        
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
        
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            
            setText((String) getItem());
            setGraphic(null);
        }
        
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                       
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
        //edit table view  onsite
            private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                        Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                        User p = table.getSelectionModel().getSelectedItem();
                         Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "UPDATE `users` SET `email`=?,`username`=?,`password`=?,`type`=?,`fname`=?,`lname`=?,`address`=?"
                +"WHERE iduser=?";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            
            preparedStatement.setString(1,p.getEmail());
            preparedStatement.setString(2, p.getUsername());
            preparedStatement.setString(3, p.getPassword());
            preparedStatement.setString(4, p.getType());
            preparedStatement.setString(5, p.getFname());
            preparedStatement.setString(6, p.getLname());
            preparedStatement.setString(7, p.getAddress());
            preparedStatement.setString(8, p.getID());
          
           
           
            // execute select SQL stetement
            int rs = preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
                    }
                }

                
            });
        }
        
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
        
    }
       void selectUsersFrom(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM users";
        
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
           
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
              data0.add(new User(String.valueOf(rs.getInt("iduser")),rs.getString("email"), rs.getString("username"), 
                      rs.getString("password"), rs.getString("type"), 
                      rs.getString("fname"), rs.getString("lname"), rs.getString("address")));
            }
             //data of table view
        table.setItems(data0);
        }
            
            
        catch (SQLException e) {
            
            System.out.println(e.getMessage());
           
        } finally {
            
          
            
        }
        
    }
}

