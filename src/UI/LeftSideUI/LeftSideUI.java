package UI.LeftSideUI;

import javafx.scene.layout.VBox;

public class LeftSideUI extends VBox{

	public LeftSideUI() {
		super(10);
		
		SimulationControls simulationControls = new SimulationControls();
		
		this.getChildren().add(simulationControls);
	}
}
