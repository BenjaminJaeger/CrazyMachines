package UI.SideBar;

import javafx.scene.layout.VBox;

public class SideBar extends VBox{

	public SideBar() {
		super(10);
		
		ExampleScenes exampleScenes = new ExampleScenes();
		SimulationControls simulationControls = new SimulationControls();
			
		this.getChildren().addAll(exampleScenes,simulationControls);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");  
	}
}
