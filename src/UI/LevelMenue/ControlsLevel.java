package UI.LevelMenue;

import java.util.concurrent.atomic.AtomicBoolean;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class ControlsLevel extends VBox{

	protected  Button clear ;
	
	public ControlsLevel(Scene mainScene,Stage primaryStage) {
		 super(10);
		
		 Image expandImg= new Image("file:res/Images/expand.png");
	     Image playImg  = new Image("file:res/Images/play.png");
	     Image pauseImg = new Image("file:res/Images/pause.png");
	     Image resetImg = new Image("file:res/Images/reset.png");
	     Image clearImg = new Image("file:res/Images/clear.png");
	     
	     Button expand = new Button();
	     expand.setGraphic(new ImageView(expandImg));
	     AtomicBoolean expanded = new AtomicBoolean(false);
	     expand.setOnAction(e->{
	         if (expanded.get()) {
	         	this.setStyle("-fx-background-color: #000; -fx-min-width: 50px;");
	             expanded.set(false);
	         }
	         else {
	         	this.setStyle("-fx-background-color: #000000; -fx-min-width: 200px;");
	            expanded.set(true);
	         }
	     });


	     Button playpause = new Button(" Play");
	     playpause.setGraphic(new ImageView(playImg));
	     playpause.setOnAction(e->{
	         if(SimulationControler.isPlaying()) {
	             playpause.setGraphic(new ImageView(playImg));
				 playpause.setText(" Play");
	             SimulationControler.pause();
	         }else {
	             playpause.setGraphic(new ImageView(pauseImg));
	             playpause.setText(" Pause");
	             SimulationControler.play();
	         }
	     });

	     Button stop = new Button(" Reset");
	     stop.setGraphic(new ImageView(resetImg));
	     stop.setOnAction(e->{
	         if (SimulationControler.isPlaying()) {
	             SimulationControler.pause();
	             playpause.setGraphic(new ImageView(playImg));
	             playpause.setText(" Play");
	         }
	         SimulationControler.restart();
	     });

	    clear = new Button(" Clear");
	    clear.setGraphic(new ImageView(clearImg));
	    clear.setOnAction(e->{
	         if (!SimulationControler.isPlaying()) {
	        	 for (int i = 0; i < GameObject.allObjects.size(); i++) 
					if(GameObject.allObjects.get(i).isEditable()) {
						GameObject.allObjects.get(i).remove();
						GameObject.allObjects.remove(i);
					}
	         }
	     });


	    Label ammount = new Label(Integer.toString(10-SimulationControler.getUpdateTime()));
	    Slider slider = new Slider();
	    
	    VBox container = new VBox(10);	
	    container.setAlignment(Pos.CENTER);
	    slider.setOrientation(Orientation.VERTICAL);
	    slider.setMin(1);
	    slider.setMax(10);
	    slider.setValue(SimulationControler.getUpdateTime());
	    slider.valueProperty().addListener(new ChangeListener<Number>() {
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	        	ammount.setText(Integer.toString(newValue.intValue()));
	         	SimulationControler.setUpdateTime(newValue.intValue());
	         	if (SimulationControler.isPlaying()) {
	 	             SimulationControler.pause();
	 	             SimulationControler.play();
	           }
	         }
	    });
	    ImageView add = new ImageView(new Image("file:res/Images/add.png"));
	    add.setFitHeight(15);
	    add.setFitWidth(15);
	    add.setOnMouseClicked(e->{
	    	if(slider.getValue() < slider.getMax())
	    		slider.setValue(slider.getValue()+1);
	    });	    
	    add.getStyleClass().add("Imageview");
	    
	    ImageView subtract = new ImageView(new Image("file:res/Images/subtract.png"));
	    subtract.setFitHeight(15);
	    subtract.setFitWidth(15);
	    subtract.setOnMouseClicked(e->{
	    	if(slider.getValue() > slider.getMin())
	    		slider.setValue(slider.getValue()-1);
	    });	
	    subtract.getStyleClass().add("Imageview");
	    container.getChildren().addAll(add,slider,subtract);
		Label speed = new Label("speed");
		HBox speedbox = new HBox(10);
		speedbox.getChildren().addAll(container,speed,ammount);
		speedbox.setAlignment(Pos.CENTER);
		
		
	    this.setAlignment(Pos.CENTER);
	    this.getChildren().addAll(playpause,stop,clear,speedbox);
	    this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
}
