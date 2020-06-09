package UI.EditorMenue;

import Simulation.SimulationControler;
import UI.MainMenue.MainMenue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarEditor extends VBox{

	private ObjectSettingsEditor objectSettings;
	
	public SideBarEditor(Scene mainScene,Stage primaryStage) {
		super(10);
		
		ControlsEditor simulationControls = new ControlsEditor(mainScene,primaryStage);
		objectSettings = new ObjectSettingsEditor();
			
		Button exit = new Button(" Exit");
		exit.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		exit.setOnAction(e->{
			new MainMenue(mainScene,primaryStage);
			SimulationControler.pause();			
		});
		
		this.setAlignment(Pos.BOTTOM_LEFT);
		this.getChildren().addAll(simulationControls,objectSettings,exit);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettingsEditor getObjectSettings() {
		return objectSettings;
	}
	
}