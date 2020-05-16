package MAIN;

import TESTING.UI.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		BorderPane layout = new BorderPane();
				
		primaryStage.setTitle("Editor Alpha");
		primaryStage.setScene(new Scene(root, 900, 900));
		
		UI ui = new UI();
		ui.initialize(root,layout);
	
		Simulation simulation = new Simulation();
		simulation.initialize(root,layout);
		
		primaryStage.show();
	}
	
}
