package MAIN;

import UI.Util;
import UI.EditorTabPane.EditorTabPane;
import UI.SideBar.SideBar;
import UI.ObjectTransformationListeners;
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
		Util.canvasWrapper = new SwingNode();

		ObjectTransformationListeners.addListeners();
		//Simulation controls
		SideBar leftSideUI = new SideBar();

		BorderPane outer = new BorderPane();
		BorderPane inner = new BorderPane ();

		//outer.setStyle("-fx-background-color: rgb(102,127,102);");
		//inner.setStyle("-fx-background-color: rgb(102,127,102);");
		
		inner.setCenter(Util.canvasWrapper);
		BorderPane.setAlignment(inner.getCenter(), Pos.CENTER);
		inner.setBottom(editorTabPane);
		outer.setLeft(leftSideUI);
		outer.setCenter(inner);

		this.getChildren().addAll(glassPane,outer);
	}

}
