package MAIN;

import UI.CreateTabPaneEvents;
import UI.EditorTabPane;
import UI.PlayPauseConcept;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UI {

	public void initialize(StackPane root,BorderPane layout,VBox layout2) {
		//pane for moving around the Image
		Pane dragAnimator = new Pane();
		ImageView animateObject = new ImageView();
		dragAnimator.getChildren().add(animateObject);
		root.getChildren().add(dragAnimator);
		

		//BorderPane settings
		layout.toFront();
		layout.setStyle("-fx-background-color: rgb(102,127,102);");
		root.getChildren().add(layout);
		

		//build TabPane
		EditorTabPane editorTabPane = new EditorTabPane();
		Util.tabPane = editorTabPane.buildTabPane();

		//Simulation Control Buttons
		layout.setLeft(PlayPauseConcept.createControls());
		

		//initialize events
		CreateTabPaneEvents.initializeMouseRelease(root, layout, editorTabPane, dragAnimator, animateObject);
	}
	
}
