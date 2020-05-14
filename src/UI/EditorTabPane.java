package UI;

import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.MetaObjects.Moveable.TestBallMeta;
import Simulation.Objects.MetaObjects.Static.StaticPlaneMeta;
import UI.Controls.TabElement;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class EditorTabPane {

    private MetaObject draggedObject;
    private boolean isDragging = false;

    public TabPane buildTabPane () {
        //initializing TabPane Element and Tabs
        TabPane objChooser = new TabPane ();
        objChooser.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //tabs
        Tab balls = new Tab ("Balls");
        Tab blocks = new Tab ("Blocks");
        objChooser.getTabs().addAll(balls, blocks);

        //set tab content
        ScrollPane ballScroller = new ScrollPane();
        balls.setContent(ballScroller);
        ScrollPane blockScroller = new ScrollPane();
        blocks.setContent(blockScroller);

        //define elements in tab content
        defineBalls(ballScroller);
        defineBlocks(blockScroller);

        //return fully build TabPane to UI
        objChooser.getStylesheets().add("file:res/css/ObjectChooser.css");
        return objChooser;
    }

    private void defineBalls (ScrollPane content) {
        //define TabElements as MetaObjects
        TabElement metalBall = new TabElement (new TestBallMeta("Metal Ball", "file:res/Images/metalBall.png"));

        //add TabElements to content
        HBox ballContent = new HBox (10);
        ballContent.setAlignment(Pos.CENTER_LEFT);
        //add elements here
        ballContent.getChildren().addAll(metalBall);

        //adding contents to scrollPane
        content.setContent(ballContent);

        //Drag Events
        addDragFilter(metalBall);
    }

    private void defineBlocks (ScrollPane content) {
        //define TabElements as MetaObjects
        TabElement woodenPlank = new TabElement(new StaticPlaneMeta("Wooden Plank", "file:res/Images/woodenPlank.png"));

        //add TabElements to content
        HBox blockContent = new HBox (10);
        blockContent.setAlignment(Pos.CENTER_LEFT);
        //add elements here
        blockContent.getChildren().addAll(woodenPlank);

        //adding contents to scrollPane
        content.setContent(blockContent);

        //Drag Events
        addDragFilter(woodenPlank);
    }

    private void addDragFilter (TabElement draggableObject) {
        draggableObject.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            draggedObject = draggableObject.getMetaObject();
            isDragging=true;
        });
    }

    public MetaObject getDraggedObject () {
        return draggedObject;
    }

    public boolean isDragging () {
        return isDragging;
    }

    public void resetDrag () {
        draggedObject = null;
        isDragging = false;
    }

}
