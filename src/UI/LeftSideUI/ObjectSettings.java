package UI.LeftSideUI;

import Simulation.Objects.GameObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectSettings extends VBox {

    Label head = new Label("Object Settings");

    Label name = new Label("Ball");

    Label xPositionL = new Label("xPosition");
    TextField xPosition = new TextField ();
    HBox xPositionB = new HBox(xPositionL,xPosition);

    Label yPositionL = new Label("yPosition");
    TextField yPosition = new TextField ();
    HBox yPositionB = new HBox(yPositionL,yPosition);

    Label scaleL = new Label("scale");
    TextField scale = new TextField ();
    HBox scaleB = new HBox(scaleL,scale);

    Label rotationL = new Label("rotation");
    TextField rotation = new TextField ();
    HBox rotationB = new HBox(rotationL,rotation);

    Label weightL = new Label("weight");
    TextField weight = new TextField ();
    HBox weightB = new HBox(weightL,weight);

    Label xdirectionL = new Label("xdirection");
    TextField xdirection = new TextField ();
    HBox xdirectionB = new HBox(xdirectionL,xdirection);

    Label ydirectionL = new Label("ydirection");
    TextField ydirection = new TextField ();
    HBox ydirectionB = new HBox(ydirectionL,ydirection);

    Label speedL = new Label("speed");
    TextField speed = new TextField ();
    HBox speedB = new HBox(speedL,speed);

    public ObjectSettings() {
        super(5);
        this.setAlignment(Pos.CENTER);
        addObjectSetings();
        this.getStylesheets().add("file:res/css/SimulationControls.css");
    }

    public void addObjectSetings(){
        this.getChildren().addAll(head,name,xPositionB,yPositionB,scaleB,rotationB,weightB,xdirectionB,ydirectionB,speedB);
    }

}
