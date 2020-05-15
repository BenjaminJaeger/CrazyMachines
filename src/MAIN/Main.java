package MAIN;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		BorderPane layout = new BorderPane();
				
		primaryStage.setTitle("Editor Alpha");
		primaryStage.setScene(new Scene(root, 900, 763));
		
		VBox layout2 = new VBox();
		
		UI ui = new UI();
		ui.initialize(root,layout,layout2);
	
		Simulation simulation = new Simulation();
		simulation.initialize(root,layout2);
		
		layout2.getChildren().add(Util.canvasWrapper);
		layout2.getChildren().add(Util.tabPane);
		
		layout.setCenter(layout2);
		
		primaryStage.show();
	}
	
}
