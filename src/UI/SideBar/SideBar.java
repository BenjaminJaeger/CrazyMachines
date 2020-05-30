package UI.SideBar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SideBar extends VBox{

	private ObjectSettings objectSettings;
	
	public SideBar(Scene mainScene) {
		super(10);
		
		SimulationControls simulationControls = new SimulationControls(mainScene);
		objectSettings = new ObjectSettings();
			
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(simulationControls,objectSettings);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettings getObjectSettings() {
		return objectSettings;
	}
	
}