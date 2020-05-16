package MAIN;

import UI.Util;
import UI.EditorTabPane.EditorTabPane;
import UI.LeftSideUI.SimulationControls;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UI {

	public UI(StackPane root) {
		Pane glassPane = new Pane();
		
		//EDITORPANE
		EditorTabPane editorTabPane = new EditorTabPane(glassPane);
		//CANVAS
		Util.canvasWrapper = new SwingNode();
		//WRAPPER for canvas and EditorPane
		VBox canvasEditorWrapper = new VBox();
		canvasEditorWrapper.getChildren().addAll(Util.canvasWrapper,editorTabPane);
		
//		LeftSideUI leftSideUI = new LeftSideUI();
//		root.setLeft(leftSideUI);
		SimulationControls simulationControls = new SimulationControls();
		
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: rgb(102,127,102);");
		layout.setCenter(canvasEditorWrapper);
		layout.setLeft(simulationControls);
		
		root.getChildren().addAll(glassPane,layout);
	}
	
}
