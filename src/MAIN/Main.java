package MAIN;

import UI.MainMenue.MainMenue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {	
	
		Scene mainScene = new Scene(new Pane(),800, 800);
		
		MainMenue menue = new MainMenue(mainScene);
		
        primaryStage.setTitle("Visual Computing 2 Prototyp");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
}