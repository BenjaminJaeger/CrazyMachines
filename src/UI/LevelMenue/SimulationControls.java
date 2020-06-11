package UI.LevelMenue;

import Simulation.SimulationControler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
 
public class SimulationControls extends VBox{

	protected float imageSize = 30;

	public SimulationControls(Scene mainScene,Stage primaryStage) {
		super(10);
		 
		 Label title = new Label("Simulation");
		 title.setStyle("-fx-font: 18px 'Roboto';");
		 
		 Line line = new Line(0,0,110,0);
	     line.setStrokeWidth(3);
	     line.setStroke(Color.rgb(30, 30, 30));
	        
	     ImageView playImg = new ImageView(new Image("file:res/Images/play.png"));
	     playImg.setFitWidth(imageSize);
	     playImg.setFitHeight(imageSize);
		    
	     ImageView pauseImg = new ImageView(new Image("file:res/Images/pause.png"));
	     pauseImg.setFitWidth(imageSize);
	     pauseImg.setFitHeight(imageSize);
	     
	     Label playpauseLabel = new Label("Play");
	     HBox playpause = new HBox(10);
	     playpause.setAlignment(Pos.CENTER_LEFT);
	     playpause.getStyleClass().add("fakeButton");
	     playpause.getChildren().addAll(playImg,playpauseLabel);
	     playpause.setOnMouseClicked(e->{
	    	 if(SimulationControler.isPlaying()) {
	    		 playpause.getChildren().set(0, playImg);
	             playpauseLabel.setText("Play");
	             SimulationControler.pause();
	         }else {
	        	 playpause.getChildren().set(0, pauseImg);
	             playpauseLabel.setText("Pause");
	             SimulationControler.play();
	         }
	     });
	     
	     ImageView stopImg = new ImageView(new Image("file:res/Images/reset.png"));
	     stopImg.setFitWidth(imageSize);
	     stopImg.setFitHeight(imageSize);
	     
	     Label stopLabel = new Label("Stop");
	     HBox stop = new HBox(10);
	     stop.setAlignment(Pos.CENTER_LEFT);
	     stop.getStyleClass().add("fakeButton");
	     stop.getChildren().addAll(stopImg,stopLabel);
	     stop.setOnMouseClicked(e->{
	    	 if (SimulationControler.isPlaying()) {
	             SimulationControler.pause();
	             playpause.getChildren().set(0, playImg);
	             playpauseLabel.setText("Play");
	         }
	         SimulationControler.restart();
	     });

	    Slider slider = new Slider();
        slider.setMajorTickUnit(3);
	    slider.setShowTickLabels(true);
	    slider.setOrientation(Orientation.VERTICAL);
	    slider.setMin(1);
	    slider.setMax(10);
	    slider.setValue(SimulationControler.getUpdateTime());
	    slider.valueProperty().addListener(new ChangeListener<Number>() {
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	         	SimulationControler.setUpdateTime(newValue.intValue());
	         	if (SimulationControler.isPlaying()) {
	 	             SimulationControler.pause();
	 	             SimulationControler.play();
	           }
	         }
	    });
	  
		Label speed = new Label("Speed");
		speed.setStyle("-fx-font: 15px 'Roboto';");
		
		HBox speedbox = new HBox(10);
		speedbox.getChildren().addAll(slider,speed);
		speedbox.setAlignment(Pos.CENTER_LEFT);
		
	    this.setAlignment(Pos.BASELINE_LEFT);
	    this.getStyleClass().add("section");
	    this.getChildren().addAll(title,line,playpause,stop,speedbox);
	    this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
}
