package UI.MainMenue;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MenueCheckBox extends HBox{
	
	private Boolean checkedBool = true;
	
	public MenueCheckBox(String name){
		super(100);
		
		ImageView unchecked = new ImageView(new Image("file:res/Images/unchecked.png"));
		unchecked.setFitHeight(20);
		unchecked.setFitWidth(20);
		ImageView checked = new ImageView(new Image("file:res/Images/checked.png"));
		checked.setFitHeight(20);
		checked.setFitWidth(20);
		
		Label btnText = new Label(name);
		
		this.getChildren().addAll(btnText,checked);		
		this.setAlignment(Pos.CENTER);
		
		this.getStyleClass().add("MenueCheckBox");
		this.getStylesheets().add("file:res/css/MenueCheckBox.css");
			
		this.setOnMouseClicked(e->{
			if(checkedBool)
				this.getChildren().set(1, unchecked);			
			else
				this.getChildren().set(1, checked);	
			checkedBool=!checkedBool;
		});
		
	}
	
}
