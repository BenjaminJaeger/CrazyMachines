package UI;

import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Config;
import UI.BenjaminController.ObjectPickingMethods;
import UI.BenjaminController.ObjectTransformationModes;

public class ObjectTransformationListeners {

    public static void addListeners() {
        Util.canvasWrapper.setOnMouseClicked(e-> {
            for (int first = 0; first < GameObject.allObjects.size(); first++) {
                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
                {
                    ObjectPickingMethods.choosePolygon(e, GameObject.allObjects.get(first), ObjectPickingMethods.detectPolygonMouseCollision(e,GameObject.allObjects.get(first)));
                    Config.stopCameraRotation = true;
                }

                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++)
                {
                    ObjectPickingMethods.chooseSphere(GameObject.allObjects.get(first), ObjectPickingMethods.calculateSphereDistance(e, GameObject.allObjects.get(first)));
                    Config.stopCameraRotation = true;
                }
            }

            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
                if(GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseCircleLine(e, GameObject.allObjects.get(objectCounter).getObjectTransformer().getCircleUI()) == true)
                {
                    GameObject.allObjects.get(objectCounter).setScalable(false);
                    GameObject.allObjects.get(objectCounter).setRotatable(true);
                    GameObject.allObjects.get(objectCounter).setMoveable(false);
                }
                else if (GameObject.allObjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseSquareUI(e, Util.canvasWrapper, GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI(),
                        GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI().getVerticesSmaller(),
                        GameObject.allObjects.get(objectCounter).getObjectTransformer().getSquareUI().getVerticesBigger()))
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

        //Fallunterscheidung Sphere oder Polygon
        Util.canvasWrapper.setOnMouseMoved(e-> {
            for (int first = 0; first < GameObject.allObjects.size(); first++) {
                for (int second = 0; second < GameObject.allObjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
                {
                    if (GameObject.allObjects.get(first).isSelected() &&
                            (!ObjectPickingMethods.detectPolygonMouseCollision(e, GameObject.allObjects.get(first)))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else if(!GameObject.allObjects.get(first).isSelected() &&
                            (!ObjectPickingMethods.detectPolygonMouseCollision(e, GameObject.allObjects.get(first)))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else if(!GameObject.allObjects.get(first).isSelected() &&
                            (ObjectPickingMethods.detectPolygonMouseCollision(e, GameObject.allObjects.get(first)))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else {
                        GameObject.allObjects.get(first).setMoveable(true);
                    }
                }

                for (int third = 0; third < GameObject.allObjects.get(first).getCollisionContext().getBoundingCircles().length; third++)
                {
                    if (GameObject.allObjects.get(first).isSelected() &&
                            (!ObjectPickingMethods.mouseInSphere(GameObject.allObjects.get(first), ObjectPickingMethods.calculateSphereDistance(e, GameObject.allObjects.get(first))))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else if(!GameObject.allObjects.get(first).isSelected() &&
                            (!ObjectPickingMethods.mouseInSphere(GameObject.allObjects.get(first), ObjectPickingMethods.calculateSphereDistance(e, GameObject.allObjects.get(first))))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else if(!GameObject.allObjects.get(first).isSelected() &&
                            (ObjectPickingMethods.mouseInSphere(GameObject.allObjects.get(first), ObjectPickingMethods.calculateSphereDistance(e, GameObject.allObjects.get(first))))) {
                        GameObject.allObjects.get(first).setMoveable(false);
                    }
                    else {
                        GameObject.allObjects.get(first).setMoveable(true);
                    }
                }
            }
        });

        Util.canvasWrapper.setOnMouseDragged(e-> {
            for (int objectCounter = 0; objectCounter < GameObject.allObjects.size(); objectCounter++) {
                if(GameObject.allObjects.get(objectCounter).isRotatable() == true)
                {
                    ObjectTransformationModes.rotateObject(objectCounter, GameObject.allObjects, e);
                }
                else if (GameObject.allObjects.get(objectCounter).isScalable() == true)
                {
                    ObjectTransformationModes.scaleObject(objectCounter, GameObject.allObjects, e);
                }
                else if (GameObject.allObjects.get(objectCounter).isMoveable() == true) {
                    ObjectTransformationModes.moveObject(objectCounter, GameObject.allObjects, e);
                }
            }
        });
    }
}
