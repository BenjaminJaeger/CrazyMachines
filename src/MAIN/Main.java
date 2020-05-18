package MAIN;

import TESTING.UI.UI;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import UI.Util;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		
		primaryStage.setTitle("Editor Alpha");
		primaryStage.setScene(new Scene(root, 900, 763));

		new UI(root, 0);
        new Simulation();

		primaryStage.show();
	}
	
}
