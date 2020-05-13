package MAIN;

import UI.CreateTabPaneEvents;
import UI.EditorTabPane;
import UI.PlayPauseConcept;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class UI {

	public void initialize(StackPane root,BorderPane layout) {
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
		TabPane tabPane = editorTabPane.buildTabPane();
		layout.setBottom(tabPane);


		//Simulation Control Buttons
		layout.setLeft(PlayPauseConcept.createControls());


		//initialize events
		CreateTabPaneEvents.initializeMouseRelease(root, layout, editorTabPane, dragAnimator, animateObject);
	}
	
}
