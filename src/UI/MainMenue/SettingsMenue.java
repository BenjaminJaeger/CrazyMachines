package UI.MainMenue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SettingsMenue extends VBox{

	public SettingsMenue(Scene mainScene) {
		super(40);
		
		MenueCheckBox showFPS = new MenueCheckBox("show FPS");
		
		MenueSlider fpsSlider = new MenueSlider("FPS");
		
		Button leave = new Button();
		leave.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		leave.setOnAction(e->{
			new MainMenue(mainScene);
		});
		
	
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(showFPS,fpsSlider,leave);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}
}
