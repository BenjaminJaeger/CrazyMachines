package UI.EditorMenue;

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

public class EditorMenue extends StackPane{
	
	public EditorMenue(Scene mainScene,Stage primaryStage) {

		Pane glassPane = new Pane();

		//Editorpane
		EditorTabPane editorTabPane = new EditorTabPane(glassPane);
		
		//Canvas
		Util.canvasWrapper = new SwingNode();
		Util.canvasWrapper.setStyle("-fx-background-color: transparent;");
		
		Properties props = System.getProperties(); 
		props.setProperty("swing.jlf.contentPaneTransparent", "true");
		
		//Simulation controls
		SideBarEditor leftSideUI = new SideBarEditor(mainScene,primaryStage);
		ObjectTransformationListeners.addListeners(leftSideUI.getObjectSettings());
		
		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane ();

		outer.setStyle("-fx-background-color: transparent;");
		inner.setStyle("-fx-background-color: transparent;");
		
		inner.setCenter(Util.canvasWrapper);
		inner.setBottom(editorTabPane);
		outer.setLeft(leftSideUI);
		outer.setCenter(inner);
		
		ImageView background = new ImageView(new Image("file:res/Images/backgroundGame.jpg"));
		background.setOpacity(0.4);
		background.setFitHeight(this.getHeight());
		background.setFitWidth(this.getWidth());

		this.setStyle("-fx-background-color: transparent;");
		this.setEffect(Util.colorAdjust);
		this.getChildren().addAll(glassPane,background,outer);
	}
	
	
}
