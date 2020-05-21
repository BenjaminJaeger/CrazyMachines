package MAIN;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		UI ui = new UI();
        Simulation simulation = new Simulation();
        simulation.initialize();
        
        primaryStage.setTitle("Visual Computing 2 Prototyp");
		primaryStage.setScene(new Scene(ui, 1088, 1000));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
}
