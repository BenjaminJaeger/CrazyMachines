package UI.SideBar;

import java.util.concurrent.atomic.AtomicBoolean;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
 
public class SimulationControls extends VBox{

	public SimulationControls() {
		super(10);
		
		 Image expandImg = new Image("file:res/Images/expand.png");
	     Image playImg  = new Image("file:res/Images/play.png");
	     Image pauseImg = new Image("file:res/Images/pause.png");
	     Image resetImg = new Image("file:res/Images/reset.png");
	     Image clearImg = new Image("file:res/Images/clear.png");
	     Image closeImg = new Image("file:res/Images/close.png");

	     
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


	     Button playpause = new Button();
	     playpause.setGraphic(new ImageView(playImg));
	     playpause.setOnAction(e->{
	         if(SimulationControler.isPlaying()) {
	             playpause.setGraphic(new ImageView(playImg));
	             SimulationControler.pause();
	         }else {
	             playpause.setGraphic(new ImageView(pauseImg));
	             SimulationControler.play();
	         }
	     });

	     Button stop = new Button();
	     stop.setGraphic(new ImageView(resetImg));
	     stop.setOnAction(e->{
	         if (SimulationControler.isPlaying()) {
	             SimulationControler.pause();
	             playpause.setGraphic(new ImageView(playImg));
	         }
	         SimulationControler.restart();
	     });

	    Button clear = new Button();
	    clear.setGraphic(new ImageView(clearImg));
	    clear.setOnAction(e->{
	         if (!SimulationControler.isPlaying()) {
	             GameObject.allObjects.clear();
	         }
	     });

	     Slider slider = new Slider();
	     slider.setOrientation(Orientation.VERTICAL);
	     slider.setMin(1);
	     slider.setMax(30);
	     slider.setValue((int)slider.getMax()-SimulationControler.getUpdateTime());
	     slider.valueProperty().addListener(new ChangeListener<Number>() {
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	         	SimulationControler.setUpdateTime((int)slider.getMax()-newValue.intValue()+1);
	          	if (SimulationControler.isPlaying()) {
	 	             SimulationControler.pause();
	 	             SimulationControler.play();
	           }
	         }
	      });

	     Button close = new Button();
	     close.setGraphic(new ImageView(closeImg));

	     this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(playpause,stop,clear,slider);
	     this.getStylesheets().add("file:res/css/SimulationControls.css");
	}
	
}