package UI.MainMenue;

import UI.Util;
import UI.MainMenue.Elements.MenueCheckBox;
import UI.MainMenue.Elements.MenueLeaveButton;
import UI.MainMenue.Elements.Slider.MenueBrightnessSlider;
import UI.MainMenue.Elements.Slider.MenueContrastSlider;
import UI.MainMenue.Elements.Slider.MenueFPSSlider;
import UI.MainMenue.Elements.Slider.MenueSoundSlider;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsMenue extends StackPane{

	public SettingsMenue(Scene mainScene,Stage primaryStage) {
		ImageView background = new ImageView(new Image("file:res/Images/background.jpg"));
		background.setFitWidth(this.getWidth());
		background.setFitHeight(this.getHeight());
		
		VBox container = new VBox(40);
		container.getStyleClass().add("Vbox");
		
		MenueCheckBox showFPS = new MenueCheckBox("show FPS");
		MenueFPSSlider fps = new MenueFPSSlider();
		
		MenueCheckBox muteSound = new MenueCheckBox("Mute Sound");
		MenueSoundSlider soundValue = new MenueSoundSlider();
		
		MenueBrightnessSlider brightness = new MenueBrightnessSlider();
		MenueContrastSlider contrast = new  MenueContrastSlider();
		
		MenueLeaveButton leave = new MenueLeaveButton();
		leave.setOnAction(e->{
			new MainMenue(mainScene,primaryStage);
		});
			
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(showFPS,fps,muteSound,soundValue,brightness,contrast,leave);
				
		this.setEffect(Util.colorAdjust);
		
		this.getChildren().addAll(background,container);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}
}
