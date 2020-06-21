package UI.SideBar;

import Simulation.Util;
import Simulation.Objects.GameObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ObjectSettingsTextField extends HBox{

	private int imageSize = 20;
	private TextField input;
	
	public ObjectSettingsTextField(String imgFile,GameObject object,String unit,float startValue) {
		super(5);
		
		ImageView img = new ImageView(new Image("file:res/Images/object-settings/"+imgFile+".png"));
		img.setFitHeight(imageSize);
		img.setFitWidth(imageSize);
	    input = new TextField(Util.getRoundedString(startValue));
	    input.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	char[] letters = newValue.toCharArray();
            	String text = "";
            	for (char c : letters) 
					if(c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='+' || c=='-' || c=='.'|| c==',') 
						text+=String.valueOf(c);
   	
             	input.setText(text);
            }
		});
	    Label unitLabel = new Label(unit);
	    
	    
	    this.getChildren().addAll(img,input,unitLabel);
	    this.getStylesheets().add("file:res/css/SideBar.css");
	}
	
	public TextField getTextField() {
		return input;
	}
	
}
