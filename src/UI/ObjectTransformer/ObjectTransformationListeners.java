package UI.ObjectTransformer;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import UI.Util;
import UI.LevelMenue.ObjectSettingsLevel;
import javafx.scene.Cursor;

public class ObjectTransformationListeners {

    public static void addListeners(ObjectSettingsLevel objectSettings) {
    	
        Util.canvasWrapper.setOnMouseClicked(e-> {
        	if(!SimulationControler.isPlaying()) {
	        	
        		//Initial Object picking
	            for (int first = 0; first < GameObject.allObjects.size(); first++) {
	            	
	            	if(GameObject.allObjects.get(first).isEditable()) {
	            				            
		            	float distance = ObjectPickingMethods.calculateCircleDistance(e,GameObject.allObjects.get(first));
		            	
		            	if (GameObject.allObjects.get(first).isSelected()) { 
		                    if (!(distance <= GameObject.allObjects.get(first).getObjectTransformer().getCircleUI().getRadius()*1.5f)) {
		                    	GameObject.allObjects.get(first).unSelectObject();
		                		objectSettings.removeUI();
		                    }

		                    continue;
		            	}	
		         	     	          	
		                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
		                	if(ObjectPickingMethods.detectPolygonMouseCollision(e,GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons()[second])) {
		                		GameObject.allObjects.get(first).selectObject();
		                		objectSettings.addUI(GameObject.allObjects.get(first));
		                		break;
		                	}
		     
		                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++)  
		                	if(ObjectPickingMethods.detectCircleMouseCollision(GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles()[third],distance)) {
		                		GameObject.allObjects.get(first).selectObject();
		                		objectSettings.addUI(GameObject.allObjects.get(first));
		                		break;
		                	}
		                
		                
	            	}        	
	            }

        	}  
        });


        //Highlight Object On Mouse over
        Util.canvasWrapper.setOnMouseMoved(e-> {
        	if(!SimulationControler.isPlaying()) {         
	            for (int first = 0; first < GameObject.allObjects.size(); first++) {
	            	
	               	if(GameObject.allObjects.get(first).isEditable()) {
	               		               	            	
		            	float distance = ObjectPickingMethods.calculateCircleDistance(e,GameObject.allObjects.get(first));
		            	     	     	          	
		                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
		                	if(ObjectPickingMethods.detectPolygonMouseCollision(e,GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons()[second]))      
		                		GameObject.allObjects.get(first).highlight(true);
		                	else 
		                		GameObject.allObjects.get(first).highlight(false);
							
		     
		                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++)  
		                	if(ObjectPickingMethods.detectCircleMouseCollision(GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles()[third],distance)) 
		                		GameObject.allObjects.get(first).highlight(true);
		                    else 
		                    	GameObject.allObjects.get(first).highlight(false);
		                
	               	}
	            }
        	}
        	
            
            //Change Move Rotate Scale State of Object On Mouse clicked
            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
                if(GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseCircleLine(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getCircleUI())){
                    GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(true);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                    Util.mainScene.setCursor(Cursor.CLOSED_HAND);
                    
                }else if (GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseSquareUI(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI())){
                    GameObject.allObjects.get(objectCounter).setScalable(true);
                    GameObject.allObjects.get(objectCounter).setRotatable(false);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                    Util.mainScene.setCursor(Cursor.E_RESIZE);
                    
                } else if(GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseObject(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI())) {
                    GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(false);
                    GameObject.allObjects.get(objectCounter).setMoveable(true);
                    Util.mainScene.setCursor(Cursor.MOVE);
                    
                }else {
	            	GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(false);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                    Util.mainScene.setCursor(Cursor.DEFAULT);
                    
                }
            }

        });


        //Move Rotate Scale Object On Mouse Dragged
        Util.canvasWrapper.setOnMouseDragged(e-> {
        	if(!SimulationControler.isPlaying()) {
	            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
	            	
	                if(GameObject.allObjects.get(objectCounter).isRotatable()){
	                    ObjectTransformationModes.rotateObject(objectCounter, GameObject.allObjects, e);
	                    objectSettings.updateUI(GameObject.allObjects.get(objectCounter));
	                    
	                }else if (GameObject.allObjects.get(objectCounter).isScalable()){
	                    ObjectTransformationModes.scaleObject(objectCounter, GameObject.allObjects, e);
	                    objectSettings.updateUI(GameObject.allObjects.get(objectCounter));
	                    
	                }else if (GameObject.allObjects.get(objectCounter).isMoveable()) {	                	
	                    ObjectTransformationModes.moveObject(objectCounter, GameObject.allObjects, e);
	                    objectSettings.updateUI(GameObject.allObjects.get(objectCounter));
	                }	                
	                
	            }
        	}
        });
    }
    
}