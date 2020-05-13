package UI;

import MAIN.Util;
import Simulation.Objects.GameObject;
import Simulation.Objects.MetaObjects.MetaObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CreateTabPaneEvents {
    public static void dragReleased (StackPane root, BorderPane layout, EditorTabPane editorTabPane, MouseEvent e) {
        layout.toFront();
        //define specs of mouse pointer and canvas size
        double mX = e.getSceneX();
        double mY = e.getSceneY();
        double cX = Util.canvasWrapper.getLayoutX();
        double cY = Util.canvasWrapper.getLayoutY();
        double cH = Util.canvas.getHeight();
        double cW = Util.canvas.getWidth();
        //Check if pointer is hovering above canvas
        if (editorTabPane.isDragging()
                && mX >= cX && mY >= cY
                && mX <= cX+cW && mY <= cY+cH) {
            //get currently dragged object from EditorTabPane
            MetaObject currentlyDraggedObject = editorTabPane.getDraggedObject();

            //Convert Mouse Position
            float x = ((float)e.getX() - (float)root.getWidth()/2) * 1.35f;
            float y = ((float)root.getHeight()/2 - (float)e.getY()) * 0.55f;

           currentlyDraggedObject.createObject(x, y);
           
           Util.objectDragged = true;
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

    public static void scaleObject (java.awt.event.MouseEvent e) {
           float objectX = GameObject.allObjects.get( GameObject.allObjects.size()-1).getX() + Util.canvas.getWidth()/2;
           float objectY = Util.canvas.getHeight()/2 -  GameObject.allObjects.get( GameObject.allObjects.size()-1).getY();

            float mouseX = (float)e.getX();
            float mouseY = (float)e.getY();

            float scale = (float)Math.sqrt(Math.pow((objectX-mouseX),2)+Math.pow((objectY-mouseY),2)) /100;

            float rotation = -(float)Math.atan2(objectY-mouseY , objectX-mouseX) * 180/(float)Math.PI;

            GameObject.allObjects.get( GameObject.allObjects.size()-1).setScale(scale);
            GameObject.allObjects.get( GameObject.allObjects.size()-1).setRotation(rotation);  
    }

    public static void initializeMouseRelease (StackPane root, BorderPane layout, EditorTabPane editorTabPane, Pane dragAnimator, ImageView animateObject) {
        root.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            CreateTabPaneEvents.dragReleased(root, layout, editorTabPane, e);
            if (editorTabPane.isDragging())
                editorTabPane.resetDrag();
        });
        root.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            CreateTabPaneEvents.onDrag(editorTabPane, dragAnimator, animateObject, e);
        });
    }

}
