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

import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ObjectPickingMethods {

    public static float calculateSphereDistance (MouseEvent e, GLJPanel canvas, Camera camera, MoveableObject object) {
        float x = ((float)e.getX() - (float)canvas.getWidth()/2 +camera.getX());
        float y = ((float)canvas.getHeight()/2 -(float)e.getY() +camera.getY());

        x *= camera.getZ() * 0.75;
        y *= camera.getZ() * 0.75;

        float distX = x - object.getX();
        float distY = y - object.getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));
        return distance;
    }

    public static void chooseSphere(MoveableObject object, float distance) {
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

    public static boolean mouseInSphere(MoveableObject object, float distance) {
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


    //auf GameObject casten
    public static void choosePolygon (MouseEvent e, GLJPanel canvas, Camera camera, MoveableObject object) {
        float px = ((float)e.getX() - (float)canvas.getWidth()/2 +camera.getX());
        float py = ((float)canvas.getHeight()/2 -(float)e.getY() +camera.getY());

        px *= camera.getZ() * 0.75;
        py *= camera.getZ() * 0.75;

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

    public static boolean chooseCircleLine(MouseEvent e, GLJPanel canvas, Camera camera, RotationCircleUI object) {
        boolean dragged = false;

        float x = ((float)e.getX() - (float)canvas.getWidth()/2 +camera.getX());
        float y = ((float)canvas.getHeight()/2 -(float)e.getY() +camera.getY());

        x *= camera.getZ() * 0.75;
        y *= camera.getZ() * 0.75;

        float distX = x - object.getCircleModel().getX();
        float distY = y - object.getCircleModel().getY();

        float distance = (float) Math.sqrt((distX*distX) + (distY*distY));

        if (distance >= (object.getRadius())*0.8 && distance <= (object.getRadius()) + (object.getRadius()) * 0.2) {
            dragged = true;
        }

        return dragged;
    }

    //ArrayList als Parameter übernehmen
    public static boolean chooseSquareUI (MouseEvent e, GLJPanel canvas, Camera camera, ScaleSquareUI object, ArrayList <Vector2f> vertices) {
        boolean clicked = false;

        float px = UI.Util.convertMouseX(e.getX());
        float py = UI.Util.convertMouseY(e.getY());
        int next = 0;

        for (int current = 0; current < vertices.size(); current++) {
            next = current + 1;

            if (next == vertices.size()) {
                next = 0;
            }

            Vector2f vc = vertices.get(current);
            Vector2f vn = vertices.get(next);

            //Datensatz verändern
            //a bissl größer als das Originalrechteck, a bissl kleiner als das Originalrechteck
            if (((vc.y*1.1 > py) != (vn.y+1.1 > py)) && (px < (vn.x*1.1 - vc.x*1.1) * (py - vc.y*1.1) / (vn.y*1.1 - vc.y*1.1) + vc.x*1.1 ))
            {
                System.out.println("True1");
                if (!(((vc.y*0.9 > py) != (vn.y+0.9 > py)) && (px < (vn.x*0.9 - vc.x*0.9) * (py - vc.y*0.9) / (vn.y*0.9 - vc.y*0.9) + vc.x*0.9))){
                    System.out.println("True");
                    clicked = !clicked;
                }
            }
        }
        return clicked;
    }
}
