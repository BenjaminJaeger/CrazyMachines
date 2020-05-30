package UI.MainMenue;

import UI.EditorMenue.EditorMode;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenue extends StackPane{

	public MainMenue(Scene mainScene) {		
		ImageView background = new ImageView(new Image("file:res/Images/background.jpg"));
		background.setFitWidth(this.getWidth());
		background.setFitHeight(this.getHeight());
		
		VBox container = new VBox(40);
		container.getStyleClass().add("Vbox");
		
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
	
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(settings,levelSelector,editor,exit);
		
		this.getChildren().addAll(background,container);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}

}
