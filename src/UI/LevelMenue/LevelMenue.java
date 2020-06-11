package UI.LevelMenue;

import java.util.Properties;

import UI.Util;
import UI.ObjectTransformer.ObjectTransformationListeners;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LevelMenue extends StackPane{
	
	public LevelMenue(Scene mainScene,String level,Stage primaryStage) {

		Pane glassPane = new Pane();

		//Editorpane
		LevelTabPane levelTabPane = new LevelTabPane(glassPane,level);
		
		//Canvas
		Util.canvasWrapper = new SwingNode();
		Util.canvasWrapper.setStyle("-fx-background-color: transparent;");
		
		Properties props = System.getProperties(); 
		props.setProperty("swing.jlf.contentPaneTransparent", "true");
		
		//Simulation controls
		SideBarLevel leftSideUI = new SideBarLevel(mainScene,primaryStage);
		ObjectTransformationListeners.addListeners(leftSideUI.getObjectSettings());
		
		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane();

		outer.setStyle("-fx-background-color: transparent;");
		inner.setStyle("-fx-background-color: transparent;");
		
		inner.setCenter(Util.canvasWrapper);
		inner.setBottom(levelTabPane);
		outer.setLeft(leftSideUI);
		outer.setCenter(inner);
		
		ImageView background = new ImageView(new Image(Util.background));
		background.setOpacity(0.4);


		this.setStyle("-fx-background-color: transparent;");
		this.setEffect(Util.colorAdjust);
		this.getChildren().addAll(glassPane,background,outer);
	}
	
}
