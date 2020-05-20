package TESTING.UI;

import UI.Util;
import UI.EditorTabPane.EditorTabPane;
import UI.SideBar.SimulationControls;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
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

	public UI (StackPane root, int i) {
		Pane glassPane = new Pane ();

		//Editorpane
		EditorTabPane editorTabPane = new EditorTabPane(glassPane);
		//Canvas
		Util.canvasWrapper = new SwingNode ();
		//Simulation controls
		SimulationControls simulationControls = new SimulationControls();

		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane();

		//outer.setStyle("-fx-background-color: rgb(102,127,102);");;
		//inner.setStyle("-fx-background-color: rgb(102,127,102);");
		
		inner.setCenter(Util.canvasWrapper);
		inner.setAlignment(Util.canvasWrapper, Pos.CENTER);
		inner.setBottom(editorTabPane);
		outer.setLeft(simulationControls);
		outer.setCenter(inner);

		root.getChildren().addAll(glassPane,outer);
	}
	
}
