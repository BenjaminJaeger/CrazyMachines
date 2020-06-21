package UI.SideBar;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ObjectSpeed extends VBox{

	public ObjectSpeed() {
		super(10);
		
		Label title = new Label("Properties");
		title.setStyle("-fx-font: 18px 'Roboto';");

	    this.getChildren().add(title);
	    
		for (GameObject object : GameObject.allObjects) {
			if(object instanceof MoveableObject) {
				ObjectSpeedElement element = new ObjectSpeedElement((MoveableObject) object);
				((MoveableObject) object).setElement(element);
				this.getChildren().add(element);
			}
		}
				
	    this.getStyleClass().add("section");      
	    this.setAlignment(Pos.TOP_LEFT);
	    this.getStylesheets().add("file:res/css/SideBar.css");
	}
}
