package UI.LevelMenue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarLevel extends VBox{

	private ObjectSettingsLevel objectSettings;
	
	public SideBarLevel(Scene mainScene,Stage primaryStage) {
		super(10);
		
		ControlsLevel simulationControls = new ControlsLevel(mainScene,primaryStage);
		objectSettings = new ObjectSettingsLevel();
			
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(simulationControls,objectSettings);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettingsLevel getObjectSettings() {
		return objectSettings;
	}
	
}