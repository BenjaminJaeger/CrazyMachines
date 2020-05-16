package UI;

import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.MetaObjects.Moveable.MetallBallMeta;
import Simulation.Objects.MetaObjects.Static.PlankMeta;
import UI.Controls.TabElement;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class EditorTabPane {

    private static MetaObject draggedObject;
    private static boolean isDragging = false;

    /**
     * Constructor for EditorTabPane. The EditorTabPane is the Drag-and-Drop TabPane in the bottom of the standard editor
     * window and is used for placing objects from multiple categories. The categories may ne declared in new methods below
     * @param root StackPane used for animating the Drag-And-Drop
     * @param layout BorderPane used for structure in the editor mode
     * @return
     */
    public static TabPane buildTabPane (StackPane root, BorderPane layout) {
        Pane dragAnimator = new Pane();
        ImageView animateObject = new ImageView();
        dragAnimator.getChildren().add(animateObject);
        root.getChildren().add(dragAnimator);

        //initializing TabPane Element and Tabs, do not change if not necessary
        TabPane objChooser = new TabPane ();
        objChooser.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Adding Tabs to TabPane, create new Tab with preferred name and add in "addAll()" brackets
        Tab balls = new Tab ("Balls");
        Tab blocks = new Tab ("Blocks");
        objChooser.getTabs().addAll(balls, blocks);

        //Create a new ScrollPane and set it as content for the corresponding Tab
        ScrollPane ballScroller = new ScrollPane();
        balls.setContent(ballScroller);
        ScrollPane blockScroller = new ScrollPane();
        blocks.setContent(blockScroller);

        //Copy-Paste "defineBalls" method below and edit
        defineBalls(ballScroller);
        defineBlocks(blockScroller);

        //add CSS from file
        objChooser.getStylesheets().add("file:res/css/ObjectChooser.css");

        //initialize mouse release, do not change if not necessary
        CreateTabPaneEvents.initializeMouseReleaseAndDrag(root, layout,  dragAnimator, animateObject);

        //return TabPane object
        return objChooser;
    }

    /////////////////////////////////////////////////////////////////////////////
    //Methods below needed for new Elements, copy-and-paste and edit afterwards//
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Defines elements inside of "balls" tab
     * @param content ScrollPane defined in "buildTabPane"
     */
    private static void defineBalls (ScrollPane content) {
        //define TabElements as MetaObjects
        TabElement metalBall = new TabElement (new MetallBallMeta("Metal Ball", "file:res/Images/metalBall.png", 30, 30, (float)Math.random(),(float)Math.random(),(float)Math.random()));

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

    /**
     * Defines elements inside of "blocks" tab
     * @param content ScrollPane defined in "buildTabPane"
     */
    private static void defineBlocks (ScrollPane content) {
        //define TabElements as MetaObjects
        TabElement woodenPlank = new TabElement(new PlankMeta("Wooden Plank", "file:res/Images/woodenPlank.png", 400, 30, (float)Math.random(),(float)Math.random(),(float)Math.random()));

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

    /////////////////////////////////////////////////////////////////////////////

    /**
     * Makes a TabElement object draggable
     * @param draggableObject
     */
    private static void addDragFilter (TabElement draggableObject) {
        draggableObject.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            draggedObject = draggableObject.getMetaObject();
            isDragging=true;
        });
    }

    //getter and setter methods below

    public static MetaObject getDraggedObject () {
        return draggedObject;
    }

    public static boolean isDragging () {
        return isDragging;
    }

    public static void resetDrag () {
        draggedObject = null;
        isDragging = false;
    }

}
