package UI.MainMenue;

import Simulation.RenderEngine.Core.Config;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class MenueSlider extends HBox{
	
	public MenueSlider(String name){
		super(100);
		
		HBox container = new HBox(10);
		
		Label sliderValue = new Label(Integer.toString(Config.FRAME_RATE));
		
		Slider slider = new Slider();
		slider.setMin(10);
		slider.setMax(120);
		slider.setValue(Config.FRAME_RATE);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	         	Config.FRAME_RATE = (int)slider.getValue();
	         	sliderValue.setText(Integer.toString(Config.FRAME_RATE));
	         }
		});
		
		container.getChildren().addAll(slider,sliderValue);
		
		Label btnText = new Label(name);
		
		this.getChildren().addAll(btnText,container);		
		this.setAlignment(Pos.CENTER);
		
		this.getStyleClass().add("MenueSlider");
		this.getStylesheets().add("file:res/css/MenueSlider.css");			
	}
	
}
