package UI.EditorMenue;

import Simulation.Objects.GameObject;
import UI.LevelMenue.ObjectSettingsLevel;
import javafx.scene.control.CheckBox;

public class ObjectSettingsEditor extends ObjectSettingsLevel {
	

       public ObjectSettingsEditor() {
    	   super();
       }
       
       public void addUI(GameObject object) {
    	   super.addUI(object);
    	     	   
	       CheckBox playable = new CheckBox(" Playable");
	       playable.setSelected(object.isPlayable());
	       playable.setOnAction(e->{
	       		object.setPlayable(playable.isSelected());
	       });
        
	       super.getChildren().addAll(playable);        
       }	

}