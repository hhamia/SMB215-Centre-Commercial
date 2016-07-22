package stock;

import java.io.File;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import javafx.css.PseudoClass;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
public class customer extends Application {
    ObservableList<company.Person> data1 =company.data1;
    ObservableList<family.Person> data2 =family.data2;
    Connection dbConnection = null;
    Stage compStage=new Stage(StageStyle.DECORATED);
    Stage famStage=new Stage(StageStyle.DECORATED);
    GridPane grid1=new GridPane();
    String imagepath="";
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    private final TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList();
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
    final HBox hb = new HBox();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        
       
        
            
        
        HBox des=new HBox();
        GridPane dess=new GridPane();
        dess.setHgap(10);
        dess.setVgap(10);
        Button save=new Button("Save");
        Button clear=new Button("Clear Fields");
        
        Image img1 = new Image("images/stockIcon.png");
        ImageView imgview1=new ImageView(img1);
        imgview1.setFitHeight(150);
        imgview1.setFitWidth(150);
        Button addimage=new Button("add image");
        addimage.setOnAction((event) -> {
            FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
         imagepath = file.getPath();
        
        Image image = new Image("file:///"+imagepath);
        imgview1.setImage(image);
    }           
        });
        VBox v1=new VBox();
        
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
        
        
         final PseudoClass errorClass = PseudoClass.getPseudoClass("error");


         
        ComboBox<String> c1=new ComboBox<>();
        
        String[] locales = Locale.getISOCountries();
        
        for (String countryCode : locales) {
            
            Locale obj = new Locale("", countryCode);
            
            c1.getItems().add(obj.getDisplayCountry());
            c1.getSelectionModel().selectFirst();
            c1.setEditable(true);
        }
        ComboBox<String> c2 = new ComboBox<>(FXCollections.observableArrayList("Simgle","Company","Family"));
        
         
        c2.getSelectionModel().selectFirst();
        c2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if("Company".equals(c2.getValue())){
                company cop=new company();
                cop.start(compStage);
                }
                 if("Family".equals(c2.getValue())){
                family fam=new family();
                fam.start(famStage);
                }
            }
        });
        
        save.setOnAction((event) -> {
            
        
         
          if(checkrequiredField()==true){
              
          }   
          else{
         String fname=t2.getText();
         String company=t3.getText();
         String stt1=t4.getText();
         String city=t5.getText();
         String zip=t6.getText();
         String fax=t7.getText();
         String lname=t8.getText();
         String phone=t9.getText();
         String stt2=t10.getText();
         String sp=t11.getText();
         String country=c1.getValue();
         String type=c2.getValue();
             data.add(new Person(fname, company, stt1,city,zip,fax,lname,phone,stt2,sp,country,type));
          String insertSQL = "INSERT INTO `customer`(`fname`, `lname`, `company`, "
                  + "`phone`, `str1`, `str2`, `city`, `state`, `zip`, `country`, `fax`, `type`, `picture`)"
                  + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
          String insertSQL1 ="INSERT INTO `company`(`customerPhone`,`fname`, `lastName`, `phone`)"
                  + " VALUES (?,?,?,?)";
           String insertSQL2 ="INSERT INTO `family`(`customerPhone`,`fname`, `lastName`, `phone`)"
                  + " VALUES (?,?,?,?)";
        try {
            dbConnection = connection.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertSQL);
            preparedStatement1 = dbConnection.prepareStatement(insertSQL1);
            preparedStatement2 = dbConnection.prepareStatement(insertSQL2);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, company);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, stt1);
            preparedStatement.setString(6, stt2);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, sp);
            preparedStatement.setString(9, zip);
            preparedStatement.setString(10, country);
            preparedStatement.setString(11, fax);
            preparedStatement.setString(12, type);
            preparedStatement.setString(13, imagepath);
             
            
                 int rs = preparedStatement.executeUpdate();
            if(data1.size()>0){     
            for(int i=0;i<data1.size();i++){
                preparedStatement1.setString(1, phone);
                preparedStatement1.setString(2, data1.get(i).getFirstName());
                preparedStatement1.setString(3, data1.get(i).getLastName());
                preparedStatement1.setString(4, data1.get(i).getPhone());
                
                int rs1 = preparedStatement1.executeUpdate();
            }
            }
             if(data2.size()>0){     
            for(int i=0;i<data2.size();i++){
                preparedStatement2.setString(1, phone);
                preparedStatement2.setString(2, data2.get(i).getFirstName());
                preparedStatement2.setString(3, data2.get(i).getLastName());
                preparedStatement2.setString(4, data2.get(i).getPhone());
                
                int rs2 = preparedStatement2.executeUpdate();
            }
            }
            clearFields();
            resetrequiredField();
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
           
        } finally {
            
          
            
        }   
          }
        });
         
       clear.setOnAction((event) -> {
         clearFields();
         resetrequiredField();
       });
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
        grid1.add(t6, 2, 5);grid1.add(c1, 4, 5);
        grid1.add(t7, 2, 6);grid1.add(c2, 4, 6);
        grid1.add(save, 5, 1);grid1.add(clear, 5, 2);
        v1.getChildren().add(l1);
        v1.getChildren().add(grid1);
        des.getChildren().addAll(v1,dess,imgview1,addimage);
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Customer");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double widthVar=(primaryScreenBounds.getWidth())/12;
        table.setPrefWidth(primaryScreenBounds.getWidth());
        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        
        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(widthVar);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setFirstName(t.getNewValue());
                    }
                }
        );
        
        TableColumn st1 = new TableColumn("Last Name");
        st1.setMinWidth(widthVar);
        st1.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        st1.setCellFactory(cellFactory);
        st1.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setLastName(t.getNewValue());
                    }
                }
        );
        TableColumn st2 = new TableColumn("Company");
        st2.setMinWidth(widthVar);
        st2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("company"));
        st2.setCellFactory(cellFactory);
        st2.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setCompany(t.getNewValue());
                    }
                }
        );
        TableColumn st3 = new TableColumn("Street address 1");
        st3.setMinWidth(widthVar);
        st3.setCellValueFactory(
                new PropertyValueFactory<Person, String>("stadd1"));
        st3.setCellFactory(cellFactory);
        st3.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setStadd1(t.getNewValue());
                    }
                }
        );
        TableColumn st4 = new TableColumn("City");
        st4.setMinWidth(widthVar);
        st4.setCellValueFactory(
                new PropertyValueFactory<Person, String>("city"));
        st4.setCellFactory(cellFactory);
        st4.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setCity(t.getNewValue());
                    }
                }
        );
        TableColumn st5 = new TableColumn("Zip/Postal Code");
        st5.setMinWidth(widthVar);
        st5.setCellValueFactory(
                new PropertyValueFactory<Person, String>("zip"));
        st5.setCellFactory(cellFactory);
        st5.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setZip(t.getNewValue());
                    }
                }
        );
        TableColumn st6 = new TableColumn("Fax");
        st6.setMinWidth(widthVar);
        st6.setCellValueFactory(
                new PropertyValueFactory<Person, String>("fax"));
        st6.setCellFactory(cellFactory);
        st6.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setFax(t.getNewValue());
                    }
                }
        );
        TableColumn st7 = new TableColumn("Phone");
        st7.setMinWidth(widthVar);
        st7.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phone"));
        st7.setCellFactory(cellFactory);
        st7.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setPhone(t.getNewValue());
                    }
                }
        );
        TableColumn st8 = new TableColumn("Street address 2");
        st8.setMinWidth(widthVar);
        st8.setCellValueFactory(
                new PropertyValueFactory<Person, String>("stadd2"));
        st8.setCellFactory(cellFactory);
        st8.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setStadd2(t.getNewValue());
                    }
                }
        );
        TableColumn st9 = new TableColumn("State/Province");
        st9.setMinWidth(widthVar);
        st9.setCellValueFactory(
                new PropertyValueFactory<Person, String>("state"));
        st9.setCellFactory(cellFactory);
        st9.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setState(t.getNewValue());
                    }
                }
        );
        TableColumn st10 = new TableColumn("Country");
        st10.setMinWidth(widthVar);
        st10.setCellValueFactory(
                new PropertyValueFactory<Person, String>("country"));
        st10.setCellFactory(cellFactory);
        st10.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setCountry(t.getNewValue());
                    }
                }
        );
        TableColumn st11 = new TableColumn("Type");
        st11.setMinWidth(widthVar);
        st11.setCellValueFactory(
                new PropertyValueFactory<Person, String>("type"));
        st11.setCellFactory(cellFactory);
        st11.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setType(t.getNewValue());
                    }
                }
        );
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, st1,st2,st3,st4,st5,st6,st7,st8,st9,st10,st11);
        
        
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        
        
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        BorderPane root = new BorderPane();
        root.setLeft(vbox);
        root.setTop(des);
        
        scene=new Scene(root);
        String css =this.getClass().getResource("customer.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image ico = new Image("images/customer-service.png");
        stage.getIcons().add(ico);
        stage.setScene(scene);
        stage.show();
        
        
    }

    private void clearFields() {
  t2.setText("");
  t3.setText("");
  t4.setText("");
  t5.setText("");
  t6.setText("");
  t7.setText("");
  t8.setText("");
  t9.setText("");
  t10.setText("");
  t11.setText("");
  
}
    private boolean checkrequiredField(){
        if("".equals(t2.getText())){
            t2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t2.requestFocus();
            return true;
        }
        else if("".equals(t3.getText())){
            t3.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t3.requestFocus();
            return true;
        }
        else if("".equals(t4.getText())){
            t4.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t4.requestFocus();
            return true;
        }
        else if("".equals(t5.getText())){
            t5.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t5.requestFocus();
            return true;
        }
        else if("".equals(t6.getText())){
            t6.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t6.requestFocus();
            return true;
        }
        else if("".equals(t7.getText())){
            t7.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t7.requestFocus();
            return true;
        }
        else if("".equals(t8.getText())){
            t8.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t8.requestFocus();
            return true;
        }
        else if("".equals(t9.getText())){
            t9.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t9.requestFocus();
            return true;
        }
        else if("".equals(t10.getText())){
            t10.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t10.requestFocus();
            return true;
        }
        else if("".equals(t11.getText())){
            t11.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            t11.requestFocus();
            return true;
        }
        else{
            return false;
        }
        
    }
    
     private void resetrequiredField(){
       
            t2.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
         
            t3.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
          
            t4.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
          
            t5.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
            t6.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
            t7.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
            t8.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
            t9.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            
            t10.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
            t11.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
           
        
    }

    public static class Person {
        
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty company;
        private final SimpleStringProperty stadd1;
        private final SimpleStringProperty city;
        private final SimpleStringProperty zip;
        private final SimpleStringProperty fax;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty stadd2;
        private final SimpleStringProperty state;
        private final SimpleStringProperty country;
        private final SimpleStringProperty type;
        
        private Person(String fName, String lName, String varcompany,String varstadd1,String varcity
                ,String varzip,String varfax,String varphone,String varstadd2,String varstate,String varcountry,String vartype
        ) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.company = new SimpleStringProperty(varcompany);
            this.stadd1 = new SimpleStringProperty(varstadd1);
            this.city = new SimpleStringProperty(varcity);
            this.zip =new SimpleStringProperty(varzip);
            this.fax = new SimpleStringProperty(varfax);
            this.phone = new SimpleStringProperty(varphone);
            this.stadd2 = new SimpleStringProperty(varstadd2);
            this.state = new SimpleStringProperty(varstate);
            this.country = new SimpleStringProperty(varcountry);
            this.type = new SimpleStringProperty(vartype);
            
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
        public String getCompany() {
            return company.get();
        }
        
        public void setCompany(String varcompany) {
            company.set(varcompany);
        }
        
        public String getStadd1() {
            return stadd1.get();
        }
        
        public void setStadd1(String varstadd1) {
            stadd1.set(varstadd1);
        }
        public String getCity() {
            return city.get();
        }
        
        public void setCity(String varcity) {
            city.set(varcity);
        }
        
        public String getZip() {
            return zip.get();
        }
        
        public void setZip(String varzip) {
            zip.set(varzip);
        }
        public String getFax() {
            return fax.get();
        }
        
        public void setFax(String varfax) {
            fax.set(varfax);
        }
        
        public String getPhone() {
            return phone.get();
        }
        
        public void setPhone(String varphone) {
            phone.set(varphone);
        }
        public String getStadd2() {
            return stadd2.get();
        }
        
        public void setStadd2(String varstadd2) {
            stadd2.set(varstadd2);
        }
        
        public String getState() {
            return state.get();
        }
        
        public void setState(String varstate) {
            state.set(varstate);
        }
        public String getCountry() {
            return country.get();
        }
        
        public void setCountry(String varcountry) {
            country.set(varcountry);
        }
        
        public String getType() {
            return type.get();
        }
        
        public void setType(String vartype) {
            type.set(vartype);
        }
        
    }
    
    class EditingCell extends TableCell<Person, String> {
        
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
        
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                        Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }
        
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
        
    }
    
}