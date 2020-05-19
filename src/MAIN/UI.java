package MAIN;

import UI.Util;
import UI.EditorTabPane.EditorTabPane;
import UI.LeftSideUI.SimulationControls;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class UI extends StackPane{

	public UI() {
		Pane glassPane = new Pane ();

		//Editorpane
		EditorTabPane editorTabPane = new EditorTabPane(glassPane);
		//Canvas
		Util.canvasWrapper = new SwingNode ();
		//Simulation controls
		SimulationControls simulationControls = new SimulationControls();

		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane ();

		//outer.setStyle("-fx-background-color: rgb(102,127,102);");
		//inner.setStyle("-fx-background-color: rgb(102,127,102);");
		
		inner.setCenter(Util.canvasWrapper);
		BorderPane.setAlignment(inner.getCenter(), Pos.CENTER);
		inner.setBottom(editorTabPane);
		outer.setLeft(simulationControls);
		outer.setCenter(inner);

		this.getChildren().addAll(glassPane,outer);
	}

}
