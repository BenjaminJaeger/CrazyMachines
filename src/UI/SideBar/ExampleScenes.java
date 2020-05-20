package UI.SideBar;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ExampleScenes extends VBox{

	public ExampleScenes() {
		super(10);
		
		Label title = new Label("Beispiel Szenen");
		title.setAlignment(Pos.CENTER);
		
		Button ballPit = new Button("Bälle Bad");
		ballPit.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
				
			}
			

		});
		
		Button inclinedPlane = new Button("Schiefe Ebene");
		inclinedPlane.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
				
			}
			

		});
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(title,ballPit,inclinedPlane);
		this.getStylesheets().add("file:res/css/ExampleScenes.css");  
	}
	
}
