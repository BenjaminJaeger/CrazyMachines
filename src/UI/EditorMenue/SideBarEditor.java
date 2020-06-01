package UI.EditorMenue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarEditor extends VBox{

	private ObjectSettingsEditor objectSettings;
	
	public SideBarEditor(Scene mainScene,Stage primaryStage) {
		super(10);
		
		ControlsEditor simulationControls = new ControlsEditor(mainScene,primaryStage);
		objectSettings = new ObjectSettingsEditor();
			
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(simulationControls,objectSettings);
		this.getStyleClass().add("vbox");
		this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public ObjectSettingsEditor getObjectSettings() {
		return objectSettings;
	}
	
}