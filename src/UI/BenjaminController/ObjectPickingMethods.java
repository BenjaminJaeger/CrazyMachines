package UI.BenjaminController;

import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.Ball.Ball;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Camera.Camera;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Models.Model;
import Simulation.RenderEngine.Primitives.CircleLine;
import Simulation.RenderEngine.Primitives.Primitive;
import com.jogamp.opengl.awt.GLJPanel;

import javafx.embed.swing.SwingNode;
import javafx.scene.input.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ObjectPickingMethods {

    public static float calculateSphereDistance (MouseEvent e, GameObject object) {
        float x = UI.Util.convertMouseX(e.getX());
        float y = UI.Util.convertMouseY(e.getY());

        float distX = x - object.getX();
        float distY = y - object.getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));
        return distance;
    }

    public static void chooseSphere(GameObject object, float distance) {
        if (object.isSelected()) {
            if (!(distance <= object.getObjectTransformer().getCircleUI().getRadius())) {
                object.unSelectObject();
            }
        }
        else
            {
            if (distance <= ((Ball)object).getRadius()) {
                object.selectObject();
            }
            else {
                object.unSelectObject();
            }
        }
    }

    public static boolean mouseInSphere(GameObject object, float distance) {
        boolean mouseInSphere;

        if (distance <= ((Ball)object).getRadius())
        {
            mouseInSphere = true;
        }
        else {
            mouseInSphere = false;
        }

        return mouseInSphere;
    }


    public static boolean detectPolygonMouseCollision (MouseEvent e, GameObject object) {
        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());

        boolean collision = false;
        ArrayList <Vector2f> vertices = new ArrayList<Vector2f>();
        int next = 0;

        for (int i = 0; i < object.getCollisionContext().getBoundingPolygons().length; i++) {
            vertices.addAll(Arrays.asList(object.getCollisionContext().getBoundingPolygon(i).getPoints()));
        }

        for (int current = 0; current < vertices.size(); current++) {
            next = current + 1;

            if (next == vertices.size()) {
                next = 0;
            }

            Vector2f vc = vertices.get(current);
            Vector2f vn = vertices.get(next);

            if (((vc.y > py) != (vn.y > py)) && (px < (vn.x - vc.x) * (py - vc.y) / (vn.y - vc.y) + vc.x)) {
                collision = !collision;
            }
        }

        return collision;
    }

    public static void choosePolygon(MouseEvent e, GameObject object, boolean collision) {
        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());

        float distX = px - object.getX();
        float distY = py - object.getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));

        if (object.isSelected()) {
            if (!(distance <= object.getObjectTransformer().getCircleUI().getRadius())) {
                object.unSelectObject();
            }
        }
        else
        {
            if (collision == true) {
                object.selectObject();
            }
            else {
                object.unSelectObject();
            }
        }
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

    //ArrayList als Parameter übernehmen
    public static boolean chooseSquareUI (MouseEvent e, SwingNode canvas, ScaleSquareUI object, ArrayList <Vector2f> verticesSmaller, ArrayList <Vector2f> verticesBigger ) {
        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());

        float rx = verticesBigger.get(1).x;
        float rx2 = verticesBigger.get(0).x;
        float ry = verticesBigger.get(1).y;
        float ry2 = verticesBigger.get(2).y;

        if (px >= rx &&         // right of the left edge AND
                px <= rx2 &&    // left of the right edge AND
                py >= ry &&         // below the top AND
                py <= ry2) {    // above the bottom
            return true;
        }
        return false;
    }
}
