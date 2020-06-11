package UI.EditorMenue;

import Simulation.Objects.GameObject;
import UI.LevelMenue.ObjectSettingsLevel;
import javafx.scene.control.CheckBox;

public class ObjectSettingsEditor extends ObjectSettingsLevel {
	

       public ObjectSettingsEditor() {
    	   super();
       }
       

       public void completeUI(GameObject object) {
    	   CheckBox playable = new CheckBox("Interactive");
  	    	playable.setSelected(object.isPlayable());
  	    	playable.setOnAction(e->{
  	    		object.setPlayable(playable.isSelected());
  	    	});
  	    	
      	 this.getChildren().addAll(head,playable,line,properties,settings);
      }
}