package stock;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButton extends Label
{
    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(true);

    public SwitchButton()
    {
        Button switchBtn = new Button();
        switchBtn.setPrefWidth(70);
        switchBtn.setOnAction((ActionEvent t) -> {
            switchedOn.set(!switchedOn.get());
        });

        setGraphic(switchBtn);

        switchedOn.addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if (t1)
            {
                setText("Online");
                setStyle("-fx-background-color: green;-fx-text-fill:white;-fx-font-size:15;-fx-font-style:italic");
                setContentDisplay(ContentDisplay.RIGHT);
            }
            else
            {
                setText("Offline");
                setStyle("-fx-background-color: grey;-fx-text-fill:black;-fx-font-size:15;-fx-font-style:italic");
                setContentDisplay(ContentDisplay.LEFT);
            }
        });

        switchedOn.set(false);
    }

    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
}