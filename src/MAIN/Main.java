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
        
        primaryStage.setTitle("Editor Alpha");
		primaryStage.setScene(new Scene(ui, 900, 900));
		
//      primaryStage.setMinWidth(700);
//      primaryStage.setMinHeight(830);
//		primaryStage.widthProperty().addListener( e -> {
//			primaryStage.setHeight(primaryStage.getWidth()*1.19);
//		});
//		primaryStage.heightProperty().addListener( e -> {
//			primaryStage.setWidth(primaryStage.getHeight()/1.19);
//		});

		primaryStage.show();
	}
	
}
