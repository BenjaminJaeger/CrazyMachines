package UI.MainMenue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LevelSelector extends VBox{

	public LevelSelector(Scene mainScene) {
		super(40);
		
		MenueLevelButton level1 = new MenueLevelButton("Level 1",true);
		MenueLevelButton level2 = new MenueLevelButton("Level 2",true);
		MenueLevelButton level3 = new MenueLevelButton("Level 3",false);
		MenueLevelButton level4 = new MenueLevelButton("Level 4",false);
		
		Button leave = new Button();
		leave.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		leave.setOnAction(e->{
			new MainMenue(mainScene);
		});
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(level1,level2,level3,level4,leave);
		
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}
	
}
