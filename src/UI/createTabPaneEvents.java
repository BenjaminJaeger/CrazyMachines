package UI;

import Simulation.Objects.GameObject;
import Simulation.Objects.MetaObjects.MetaObject;
import com.jogamp.opengl.awt.GLJPanel;
import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class createTabPaneEvents {
    public static void dragReleased (StackPane root, BorderPane layout, SwingNode canvasWrapper, GLJPanel canvas, EditorTabPane editorTabPane, ArrayList<GameObject> allObjects, MouseEvent e) {
        layout.toFront();
        //define specs of mouse pointer and canvas size
        double mX = e.getSceneX();
        double mY = e.getSceneY();
        double cX = canvasWrapper.getLayoutX();
        double cY = canvasWrapper.getLayoutY();
        double cH = canvas.getHeight();
        double cW = canvas.getWidth();
        //Check if pointer is hovering above canvas
        if (editorTabPane.isDragging()
                && mX >= cX && mY >= cY
                && mX <= cX+cW && mY <= cY+cH) {
            //get currently dragged object from EditorTabPane
            MetaObject currentlyDraggedObject = editorTabPane.getDraggedObject();

            //Convert Mouse Position
            float x = ((float)e.getX() - (float)root.getWidth()/2) * 1.35f;
            float y = ((float)root.getHeight()/2 - (float)e.getY()) * 0.55f;

            allObjects.add(currentlyDraggedObject.createObject(x, y));
        }
    }

    public static void onDrag (EditorTabPane editorTabPane, Pane dragAnimator, ImageView animateObject, MouseEvent e) {
        if (editorTabPane.isDragging()) {
            dragAnimator.toFront();
            animateObject.setImage(new Image(editorTabPane.getDraggedObject().getObjectImageURL()));
            double size = 100;
            animateObject.setFitHeight(size);
            animateObject.setFitWidth(size);
            animateObject.relocate(e.getX()-(size/2), e.getY()-(size/2));
        }
    }

    public static void scaleObject (boolean objectDragged, ArrayList<GameObject> allObjects,  GLJPanel canvas, java.awt.event.MouseEvent e) {
        if(objectDragged) {

            float objectX = allObjects.get(allObjects.size()-1).getX() + canvas.getWidth()/2;
            float objectY = canvas.getHeight()/2 - allObjects.get(allObjects.size()-1).getY();

            float mouseX = (float)e.getX();
            float mouseY = (float)e.getY();

            float scale = (float)Math.sqrt(Math.pow((objectX-mouseX),2)+Math.pow((objectY-mouseY),2)) /100;

            float rotation = -(float)Math.atan2(objectY-mouseY , objectX-mouseX) * 180/(float)Math.PI;

            allObjects.get(allObjects.size()-1).setScale(scale);
            allObjects.get(allObjects.size()-1).setRotation(rotation);
        }
    }
}
