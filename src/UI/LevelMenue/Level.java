package UI.LevelMenue;

import Simulation.Simulation;
import javafx.scene.Scene;

public class Level {

	public Level(Scene mainScene,String level) {
		LevelMenue menue = new LevelMenue(mainScene,level);
		mainScene.setRoot(menue);
		
		Simulation simulation = new Simulation();
		simulation.initialize(level);
	}
	
}
