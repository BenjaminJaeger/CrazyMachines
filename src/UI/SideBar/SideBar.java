package UI.SideBar;

import javafx.scene.layout.VBox;

public class SideBar extends VBox{

	private ObjectSettings objectSettings;
	
	public SideBar() {
		super(10);
		
		ExampleScenes exampleScenes = new ExampleScenes();
		SimulationControls simulationControls = new SimulationControls();
		objectSettings = new ObjectSettings();

		this.getChildren().addAll(simulationControls,objectSettings,exampleScenes);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettings getObjectSettings() {
		return objectSettings;
	}
	
}