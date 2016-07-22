package stock;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class family extends Application {
    boolean flagexist=false;
   
       static  ObservableList<Person> data2 =
            FXCollections.observableArrayList();
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage famStage) {
        
        Scene scene = new Scene(new Group());
        famStage.setTitle("Family member");
        famStage.setWidth(450);
        famStage.setHeight(550);
        
        final Label label = new Label("Partener");
        label.setFont(new Font("Arial", 20));
        
        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
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
        
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setLastName(t.getNewValue());
                    }
                }
        );
        
        TableColumn phonecol = new TableColumn("Phone");
        phonecol.setMinWidth(200);
        phonecol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phone"));
        phonecol.setCellFactory(cellFactory);
        phonecol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setPhone(t.getNewValue());
                    }
                }
        );
        
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, phonecol);
        
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(phonecol.getPrefWidth());
        addEmail.setPromptText("Phone");
        
        final Button addButton = new Button("Add partener");
        final Button saveButton = new Button("Save");
        
        saveButton.setOnAction((ActionEvent e) -> {
            
            for (Object o : table.getItems()) {
                String fname=(String) firstNameCol.getCellData(o);
                String lname=(String) lastNameCol.getCellData(o);
                String phone=(String) phonecol.getCellData(o);
                data2.add(new Person(fname, lname, phone));
               
                
            }
            famStage.close();
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkIfExist(addEmail.getText(), phonecol);
                if(flagexist==false){
                    
                    
                    data.add(new Person(
                            addFirstName.getText(),
                            addLastName.getText(),
                            addEmail.getText()));
                    addFirstName.clear();
                    addLastName.clear();
                    addEmail.clear();
                }
                else{
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Look, an Information Dialog");
                    alert.setContentText("Contact allready exist");
                    
                    alert.showAndWait();
                     addFirstName.clear();
                    addLastName.clear();
                    addEmail.clear();
                }
            }
            private void checkIfExist(String text, TableColumn phonecol) {
                for (Object o : table.getItems()) {
                    String celltext=(String) phonecol.getCellData(o);
                    if(celltext.equals(text)){
                        flagexist=true;
                        
                    }
                    else{flagexist=false;}
                }
               
            }
        });
        
        hb.getChildren().addAll(addFirstName, addLastName, addEmail,addButton,saveButton);
        hb.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        famStage.setScene(scene);
        famStage.show();
    }
    
    public static class Person {
        
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty phone;
        
        private Person(String fName, String lName, String Fphone) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.phone = new SimpleStringProperty(Fphone);
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
        
        public void setLastName(String fName) {
            lastName.set(fName);
        }
        
        public String getPhone() {
            return phone.get();
        }
        
        public void setPhone(String Fphone) {
            phone.set(Fphone);
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