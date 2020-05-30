package UI.MainMenue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsMenue extends StackPane{

	public SettingsMenue(Scene mainScene) {
		ImageView background = new ImageView(new Image("file:res/Images/background.jpg"));
		background.setFitWidth(this.getWidth());
		background.setFitHeight(this.getHeight());
		
		VBox container = new VBox(40);
		container.getStyleClass().add("Vbox");
		
		MenueCheckBox showFPS = new MenueCheckBox("show FPS");
		
		MenueSlider fpsSlider = new MenueSlider("FPS");
		
		Button leave = new Button();
		leave.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		leave.setOnAction(e->{
			new MainMenue(mainScene);
		});
		
	
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(showFPS,fpsSlider,leave);
		
		this.getChildren().addAll(background,container);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}
}
