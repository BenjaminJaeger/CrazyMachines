package UI.SideBar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class ObjectSettings extends VBox {



    public ObjectSettings() {
        super(5);
        Label head = new Label("Object Settings");


        Label xPositionL = new Label("position");
        Label xp = new Label("x");
        TextField xPosition = new TextField ("0");
        Label yp = new Label("y");
        TextField yPosition = new TextField ("0");
        HBox xPositionB = new HBox(xPositionL,xp,xPosition,yp,yPosition);

        Label scaleL = new Label("scale");
        TextField scale = new TextField ("0");
        scale.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        HBox scaleB = new HBox(scaleL,scale);

        Label rotationL = new Label("rotation");
        TextField rotation = new TextField ("90");
        HBox rotationB = new HBox(rotationL,rotation);

        Label weightL = new Label("weight");
        TextField weight = new TextField ("20");
        HBox weightB = new HBox(weightL,weight);

        Label xdirectionL = new Label("direction");
        Label xd = new Label("x");
        TextField xdirection = new TextField ("1");
        Label yd = new Label("y");
        TextField ydirection = new TextField ("0");
        HBox xdirectionB = new HBox(xdirectionL,xd,xdirection,yd,ydirection);

        Label speedL = new Label("speed");
        TextField speed = new TextField ("0");
        HBox speedB = new HBox(speedL,speed);

        this.getStyleClass().add("hbox");
        this.getChildren().addAll(head,xPositionB,scaleB,rotationB,weightB,xdirectionB,speedB);
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("file:res/css/SimulationControls.css");



    }


}
