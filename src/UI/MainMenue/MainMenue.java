package UI.MainMenue;

import UI.EditorMenue.EditorMode;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MainMenue extends VBox{

	public MainMenue(Scene mainScene) {		
		super(40);
		
		MenueButton exit = new MenueButton("Spiel Beenden");
		exit.setOnMouseClicked(e->{
			Platform.exit();
	        System.exit(0);
		});
		
		MenueButton settings = new MenueButton("Einstellungen");
		settings.setOnMouseClicked(e->{
			new SettingsMenue(mainScene);
		});
		
		MenueButton editor = new MenueButton("Level Editor");
		editor.setOnMouseClicked(e->{
			new EditorMode(mainScene);
		});
		
		MenueButton levelSelector = new MenueButton("Level Auswahl");
		levelSelector.setOnMouseClicked(e->{
			new LevelSelector(mainScene);
		});
	
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(settings,levelSelector,editor,exit);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}

}
