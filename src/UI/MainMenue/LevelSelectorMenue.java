package UI.MainMenue;

import java.io.File;
import java.util.Scanner;

import UI.Util;
import UI.LevelMenue.Level;
import UI.MainMenue.Elements.MenueLevelButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LevelSelectorMenue extends StackPane{

	private boolean[] levelsDone;
	
	public LevelSelectorMenue(Scene mainScene) {
		ImageView background = new ImageView(new Image("file:res/Images/background.jpg"));
		background.setFitWidth(this.getWidth());
		background.setFitHeight(this.getHeight());
		
		VBox container = new VBox(40);
		container.getStyleClass().add("Vbox");
		
		loadLevels();
		MenueLevelButton level1 = new MenueLevelButton("Level 1",levelsDone[0]);
		level1.setOnMouseClicked(e->{
			new Level(mainScene, "test");
		});
		MenueLevelButton level2 = new MenueLevelButton("Level 2",levelsDone[1]);
		MenueLevelButton level3 = new MenueLevelButton("Level 3",levelsDone[2]);
		MenueLevelButton level4 = new MenueLevelButton("Level 4",levelsDone[3]);
		
		Button leave = new Button();
		leave.setGraphic(new ImageView(new Image("file:res/Images/close.png")));
		leave.setOnAction(e->{
			new MainMenue(mainScene);
		});
		
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(level1,level2,level3,level4,leave);
		
		this.getChildren().addAll(background,container);
		
		this.setEffect(Util.colorAdjust);
		this.getStyleClass().add("MainMenue");
		this.getStylesheets().add("file:res/css/MainMenue.css");	
		
		mainScene.setRoot(this);
	}
	
	public void loadLevels() {
		levelsDone= new boolean[4];
		try	{	
			
			Scanner sc = new Scanner(new File("res/level.txt"));
				
			int counter = 0;
			while (sc.hasNext()){	
				int scannedLine=Integer.valueOf(sc.nextLine());
				
				if(scannedLine==0)
					levelsDone[counter] = false;
				else
					levelsDone[counter] = true;
				
				counter++;
			}
			
		}catch (Exception e) {e.printStackTrace();}
	}
}
