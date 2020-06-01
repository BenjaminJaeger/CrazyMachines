package UI.LevelMenue;

import UI.EditorTabPane.TabElements.BasketballTabElement;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LevelTabPane extends HBox{

   public LevelTabPane(Pane glass,String level) {
	   //read file and add TabElements to this
	   BasketballTabElement element = new BasketballTabElement(glass, 1);
	   this.getChildren().addAll(element);
	   
	   this.getStyleClass().add("LevelTabPane");
	   this.getStylesheets().add("file:res/css/LevelTabPane.css");
   }
   
  
}
