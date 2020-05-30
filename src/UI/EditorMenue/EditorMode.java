package UI.EditorMenue;

import Simulation.Simulation;
import javafx.scene.Scene;

public class EditorMode {

	public EditorMode(Scene mainScene) {
		EditorMenue menue = new EditorMenue(mainScene);
		mainScene.setRoot(menue);
		
		Simulation simulation = new Simulation();
		simulation.initialize();
	}
	
}
