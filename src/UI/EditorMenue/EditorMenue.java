package UI.EditorMenue;

import java.util.Properties;

import UI.Util;
import UI.ObjectTransformer.ObjectTransformationListeners;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditorMenue extends StackPane{
	
	public EditorMenue(Scene mainScene,Stage primaryStage) {

		Pane glassPane = new Pane();

		//Editorpane
		EditorTabPane editorTabPane = new EditorTabPane(glassPane);
		
		//Canvas
		Util.canvasWrapper = new SwingNode();
		Util.canvasWrapper.setStyle("-fx-translate-x: 150;");
				
		Properties props = System.getProperties(); 
		props.setProperty("swing.jlf.contentPaneTransparent", "true");
		
		//Simulation controls
		SideBarEditor leftSideUI = new SideBarEditor(mainScene,primaryStage);
		ObjectTransformationListeners.addListeners(leftSideUI.getObjectSettings());
		
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: transparent;");
		
		StackPane container = new StackPane(Util.canvasWrapper,editorTabPane);
		container.setAlignment(Pos.BOTTOM_CENTER);
		layout.setCenter(container);
		layout.setLeft(leftSideUI);
		
		ImageView background = new ImageView(new Image(Util.background));
		background.setOpacity(0.4);

		this.setStyle("-fx-background-color: transparent;");
		this.setEffect(Util.colorAdjust);
		this.getChildren().addAll(glassPane,background,layout);
	}
	
	
}
