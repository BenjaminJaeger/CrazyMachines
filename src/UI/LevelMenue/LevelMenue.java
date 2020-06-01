package UI.LevelMenue;

import UI.Util;
import UI.ObjectTransformer.ObjectTransformationListeners;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
		
		//Simulation controls
		SideBarLevel leftSideUI = new SideBarLevel(mainScene,primaryStage);
		ObjectTransformationListeners.addListeners(leftSideUI.getObjectSettings());
		
		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane();

		outer.setStyle("-fx-background-color: rgb(102,127,102);");
		inner.setStyle("-fx-background-color: rgb(102,127,102);");
		
		inner.setCenter(Util.canvasWrapper);
		BorderPane.setAlignment(inner.getCenter(), Pos.CENTER);
		inner.setBottom(levelTabPane);
		outer.setLeft(leftSideUI);
		outer.setCenter(inner);

		this.setStyle("-fx-background-color: rgb(102,127,102);");
		this.setEffect(Util.colorAdjust);
		this.getChildren().addAll(glassPane,outer);
	}
	
}
