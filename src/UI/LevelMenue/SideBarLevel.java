package UI.LevelMenue;

import Simulation.SimulationControler;
import UI.MainMenue.MainMenue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarLevel extends VBox{

	private ObjectSettingsLevel objectSettings;
	
	public SideBarLevel(Scene mainScene,Stage primaryStage) {
		super(10);
		
		ControlsLevel simulationControls = new ControlsLevel(mainScene,primaryStage);
		objectSettings = new ObjectSettingsLevel();
		
		
		Button exit = new Button(" Exit");
		exit.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		exit.setOnAction(e->{
			new MainMenue(mainScene,primaryStage);
			SimulationControler.pause();			
		});
			
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(simulationControls,objectSettings,exit);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettingsLevel getObjectSettings() {
		return objectSettings;
	}
	
}