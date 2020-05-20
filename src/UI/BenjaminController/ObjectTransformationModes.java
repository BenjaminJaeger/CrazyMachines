package UI.BenjaminController;

import Simulation.Objects.MovableObjects.MoveableObject;
import com.jogamp.opengl.awt.GLJPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ObjectTransformationModes {
    public static void rotateObject (int objectCounter, ArrayList<MoveableObject> allobjects, GLJPanel canvas, MouseEvent e) {
        System.out.println(objectCounter);
        float objectX = allobjects.get(objectCounter).getX() + canvas.getWidth()/2;
        float objectY = canvas.getHeight()/2 - allobjects.get(objectCounter).getY();

        float mouseX = (float)e.getX();
        float mouseY = (float)e.getY();

        float rotation = -(float)Math.atan2(objectY-mouseY , objectX-mouseX) * 180/(float)Math.PI;
        allobjects.get(objectCounter).setRotation(rotation);
    }

    public static void scaleObject(int objectCounter, ArrayList<MoveableObject> allobjects, GLJPanel canvas, MouseEvent e) {
        float objectX = allobjects.get(objectCounter).getX() + canvas.getWidth()/2; //Remappen der Objektkoordinaten
        float objectY = canvas.getHeight()/2 - allobjects.get(objectCounter).getY(); //Remappen der Objektkoordinaten

        float mouseX = (float)e.getX(); // die Mausposition
        float mouseY = (float)e.getY(); // die Mausposition

        float scale = (float)Math.sqrt(Math.pow((objectX-mouseX),2)+Math.pow((objectY-mouseY),2)) /100; //Skalierungsvektor
        allobjects.get(objectCounter).setScale(scale);
    }

    public static void moveObject (int objectCounter, ArrayList<MoveableObject> allobjects, MouseEvent e) {
        allobjects.get(objectCounter).setX(UI.Util.convertMouseX(e.getX()));
        allobjects.get(objectCounter).setY(UI.Util.convertMouseY(e.getY()));

        System.out.println(UI.Util.convertMouseX(e.getX()));
        System.out.println(UI.Util.convertMouseY(e.getY()));

        System.out.println(allobjects.get(objectCounter).getX());
        System.out.println(allobjects.get(objectCounter).getY());
    }
}
