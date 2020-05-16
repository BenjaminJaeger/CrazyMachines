package TESTING.UI;

import UI.CreateTabPaneEvents;
import UI.EditorTabPane;
import UI.PlayPauseConcept;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class UI {

    /**
     * Builds UI
     * @param root StackPane for managing the two states (editing and drag-drop), needed for event initialization
     * @param layout Main editor layout need for event initialization
     */
	public void initialize(StackPane root,BorderPane layout) {
		//BorderPane settings
		layout.toFront();
		layout.setStyle("-fx-background-color: rgb(102,127,102);");
		root.getChildren().add(layout);
		

		//build TabPane
		layout.setBottom(EditorTabPane.buildTabPane(root, layout));

		//Simulation Control Buttons
		layout.setLeft(PlayPauseConcept.createControls());
	}
	
}
