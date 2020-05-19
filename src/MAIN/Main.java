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
		
		primaryStage.setTitle("Editor");
		primaryStage.setScene(new Scene(root, 700, 830));

		new UI(root, 0);
        new Simulation();

        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(830);
		primaryStage.widthProperty().addListener( e -> {
			primaryStage.setHeight(primaryStage.getWidth()*1.19);
		});
		primaryStage.heightProperty().addListener( e -> {
			primaryStage.setWidth(primaryStage.getHeight()/1.19);
		});

		primaryStage.show();
	}
	
}
