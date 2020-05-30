package UI.SideBar;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class SideBar extends VBox{

	private ObjectSettings objectSettings;
	
	public SideBar() {
		super(10);
		
		SimulationControls simulationControls = new SimulationControls();
		objectSettings = new ObjectSettings();

		this.setAlignment(Pos.BASELINE_CENTER);
		this.getChildren().addAll(simulationControls,objectSettings);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettings getObjectSettings() {
		return objectSettings;
	}
	
}