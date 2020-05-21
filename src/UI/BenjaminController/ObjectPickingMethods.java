package UI.BenjaminController;

import Simulation.Collisions.Boundings.BoundingCircle;
import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import javafx.scene.input.MouseEvent;

public class ObjectPickingMethods {

    public static float calculateCircleDistance(MouseEvent e, GameObject object) {
        float x = UI.Util.convertMouseX(e.getX());
        float y = UI.Util.convertMouseY(e.getY());

        float distX = x - object.getX();
        float distY = y - object.getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));
        return distance;
    }

    public static boolean detectCircleMouseCollision(BoundingCircle circle, float distance) {
        if (distance <= circle.getRadius())        
            return true;    
        else 
            return false;
    }

    public static boolean detectPolygonMouseCollision(MouseEvent e, BoundingPolygon polygon) {
        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());

        boolean collision = false;
        int next = 0;
     
        for (int i = 0; i < polygon.getPoints().length; i++) {
            next = i + 1;

            if (next == polygon.getPoints().length) 
                next = 0;
            
            Vector2f vc = polygon.getPoints()[i];
            Vector2f vn =  polygon.getPoints()[next];

            if (((vc.y > py) != (vn.y > py)) && (px < (vn.x - vc.x) * (py - vc.y) / (vn.y - vc.y) + vc.x)) {
                collision = !collision;
            }
        }

        return collision;
    }


    public static boolean chooseCircleLine(MouseEvent e, RotationCircleUI object) {
        boolean dragged = false;

        float x = UI.Util.convertMouseX(e.getX());
        float y = UI.Util.convertMouseY(e.getY());

        float distX = x - object.getCircleModel().getX();
        float distY = y - object.getCircleModel().getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));

        if (distance >= (object.getRadius())*0.7 && distance <= (object.getRadius()) * 1.3) {
            dragged = true;
        }

        return dragged;
    }

    
    public static boolean chooseSquareUI(MouseEvent e ,ScaleSquareUI squareUI) {
        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());
      
        float rx = squareUI.getVerticesBigger().get(1).x;
        float rx2 = squareUI.getVerticesBigger().get(0).x;
        float ry = squareUI.getVerticesBigger().get(1).y;
        float ry2 = squareUI.getVerticesBigger().get(2).y;

        if (px >= rx &&         // right of the left edge AND
                px <= rx2 &&    // left of the right edge AND
                py >= ry &&         // below the top AND
                py <= ry2) {    // above the bottom
            return true;
        }
        return false;
    }
}
