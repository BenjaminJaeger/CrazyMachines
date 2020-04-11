package Main;

import ComputerGraphics.ComputerGraphicsCanvas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

	private Pane root;

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{	
		root = new StackPane();

		primaryStage.setTitle("JavaFX OpenGL");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();		 
		
		ComputerGraphicsCanvas.addCanvas(root);
		
		Button btn = new Button();
		btn.setText("Sag 'Hallo Welt'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 System.out.println("Hallo Welt!");
			 }
		});
		btn.setStyle("-fx-background-color: rgba(255,255,0,0.5)");
	
		
		//root.getChildren().add(btn);
			
	}
	

}
