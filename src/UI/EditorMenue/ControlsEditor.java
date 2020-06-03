package UI.EditorMenue;

import Simulation.LevelExportImport;
import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import UI.LevelMenue.ControlsLevel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class ControlsEditor extends ControlsLevel{

	public ControlsEditor(Scene mainScene,Stage primaryStage) {
		 super(mainScene, primaryStage);
		 
		 clear.setOnAction(e->{
	         if (!SimulationControler.isPlaying()) 
	        	GameObject.allObjects.clear();
	         
	     });
		 
		Button save = new Button(" Save");
		save.setOnAction(e->{
			
			Label text = new Label("Name your Level!");
			TextField textField = new TextField();
			textField.setMaxWidth(200);
			Button enter = new Button("save");
			
	        VBox root = new VBox(20);
	        root.setAlignment(Pos.CENTER);
	        root.getChildren().addAll(text,textField,enter);
	 
	        Scene secondScene = new Scene(root, 250, 150);
	 
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Enter a name!");
	        newWindow.setScene(secondScene);
	 	           
	        enter.setOnAction(e2->{
	        	LevelExportImport.ExportLevel(textField.getText());
	        	newWindow.close();
			});

	        newWindow.initModality(Modality.WINDOW_MODAL);
	        newWindow.initOwner(primaryStage);
	          
	        newWindow.setX(primaryStage.getX() + primaryStage.getWidth()/4);
	        newWindow.setY(primaryStage.getY() + primaryStage.getHeight()/4);
	 
	        newWindow.show();
	     
	    });
		
	    this.getChildren().add(save);
	}
	
}
