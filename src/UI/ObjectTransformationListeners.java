package UI;

import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Config;
import UI.BenjaminController.ObjectPickingMethods;
import UI.BenjaminController.ObjectTransformationModes;
import UI.SideBar.ObjectSettings;

public class ObjectTransformationListeners {

    public static void addListeners(ObjectSettings objectSettings) {
    	
        Util.canvasWrapper.setOnMouseClicked(e-> {
        	
            for (int first = 0; first < GameObject.allObjects.size(); first++) {
            	
            	float distance = ObjectPickingMethods.calculateCircleDistance(e,GameObject.allObjects.get(first));
            	
            	if (GameObject.allObjects.get(first).isSelected()) { 
                    if (distance <= GameObject.allObjects.get(first).getObjectTransformer().getCircleUI().getRadius()) 
                    	GameObject.allObjects.get(first).selectObject();
                    else {
                    	GameObject.allObjects.get(first).unSelectObject();
                    	objectSettings.removeUI(GameObject.allObjects.get(first));
                    }
                    continue;
            	}	
         	     	          	
                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
                	if(ObjectPickingMethods.detectPolygonMouseCollision(e,GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons()[second])) {
                		Config.stopCameraRotation = true;       
                		GameObject.allObjects.get(first).selectObject();
                		objectSettings.addUI(GameObject.allObjects.get(first));
                		break;
                	}
     
                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++)  
                	if(ObjectPickingMethods.detectCircleMouseCollision(GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles()[third],distance)) {
                		Config.stopCameraRotation = true;       
                		GameObject.allObjects.get(first).selectObject();
                		objectSettings.addUI(GameObject.allObjects.get(first));
                		break;
                	}           
            }
            
            
            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
                if(GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseCircleLine(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getCircleUI()))
                {
                    GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(true);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                }
                else if (GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseSquareUI(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI()))
                {
                    GameObject.allObjects.get(objectCounter).setScalable(true);
                    GameObject.allObjects.get(objectCounter).setRotatable(false);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                }
                else {
                    GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(false);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                }
            }
            
        });


        Util.canvasWrapper.setOnMouseMoved(e-> {
            for (int first = 0; first < GameObject.allObjects.size(); first++) {
            	
                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)             
                	if (GameObject.allObjects.get(first).isSelected() && (ObjectPickingMethods.detectPolygonMouseCollision(e,GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons()[second])))
                		 GameObject.allObjects.get(first).setMoveable(true);
                	else 
                		 GameObject.allObjects.get(first).setMoveable(false);

                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++) {
                	float distance = ObjectPickingMethods.calculateCircleDistance(e,GameObject.allObjects.get(first));
                	
                    if (GameObject.allObjects.get(first).isSelected() &&(ObjectPickingMethods.detectCircleMouseCollision(GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles()[third],distance)))
                        GameObject.allObjects.get(first).setMoveable(true);
                    else
                    	 GameObject.allObjects.get(first).setMoveable(false);
                }
                
            }
        });

        
        Util.canvasWrapper.setOnMouseDragged(e-> {
            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
                if(GameObject.allObjects.get(objectCounter).isRotatable())
                {
                    ObjectTransformationModes.rotateObject(objectCounter, GameObject.allObjects, e);
                }
                else if (GameObject.allObjects.get(objectCounter).isScalable())
                {
                    ObjectTransformationModes.scaleObject(objectCounter, GameObject.allObjects, e);
                }
                else if (GameObject.allObjects.get(objectCounter).isMoveable()) {
                    ObjectTransformationModes.moveObject(objectCounter, GameObject.allObjects, e);
                }
            }
        });
    }
}
