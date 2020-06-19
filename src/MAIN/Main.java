package MAIN;

import Simulation.Simulation;
import UI.MainMenue.MainMenue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {	
	
		Simulation.initialize();

		Font.loadFont("file:res/Roboto.ttf", 10);
		
		Scene mainScene = new Scene(new Pane(),1480, 920);
		
		new MainMenue(mainScene,primaryStage);
		
        primaryStage.setTitle("Visual Computing 2 Prototyp");
		primaryStage.setScene(mainScene);
		primaryStage.setMinWidth(1080);
		primaryStage.setMinHeight(720);
		primaryStage.show();
	}
	
}