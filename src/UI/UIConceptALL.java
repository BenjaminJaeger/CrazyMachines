package UI;

import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class UIConceptALL {
	
	public static void addUI(HBox root,SwingNode canvasNode) {	
		//Right Bar (Start/Stop Simulation)
		BorderPane rightBarContent = new BorderPane();
		rightBarContent.setMinWidth(230);
		VBox objectDetails = new VBox(20);
		objectDetails.setPadding(new Insets(10,10,10,10));
		ScrollPane s2 = new ScrollPane();
		s2.setContent(objectDetails);
		s2.setHbarPolicy(ScrollBarPolicy.NEVER);
		s2.getStylesheets().add(UIConceptALL.class.getResource("ScrollPane.css").toExternalForm());
		
		for (int i = 0; i < 10; i++) {
			HBox all = new HBox(20);
			all.getChildren().add(new Circle(50,Color.color(Math.random(), Math.random(), Math.random())));
			VBox details = new VBox(2);
			details.setAlignment(Pos.CENTER_LEFT);
			details.getChildren().add(new Label("Ball "+(i+1)));
			details.getChildren().add(new Label("Speed: x"));
			details.getChildren().add(new Label("Acc: y"));
			all.getChildren().add(details);
			objectDetails.getChildren().add(all);
		}
				
		HBox playpauseSimulation = new HBox(10);
		playpauseSimulation.setAlignment(Pos.CENTER);
		playpauseSimulation.setPadding(new Insets(10,10,10,10));
		playpauseSimulation.setStyle("-fx-background-color: rgb(255,255,255);");
		Polygon play = new Polygon();
		play.setFill(Color.GREEN);
		play.getPoints().addAll(new Double[]{0.0, 0.0, 50.0, 25.0, 0.0, 50.0 });	
		Rectangle pause = new Rectangle(50, 50);
		pause.setFill(Color.RED);
		playpauseSimulation.getChildren().addAll(play,pause);
		
		rightBarContent.setCenter(s2);
		rightBarContent.setBottom(playpauseSimulation);
			
		//Bottom Bar (Object chooser)
		TabPane placeAbleObjects = new TabPane();
		placeAbleObjects.setPadding(new Insets(0, 40, 0, 40));
		placeAbleObjects.setMinHeight(170);
		placeAbleObjects.setTabMinWidth(110);
		placeAbleObjects.setTabMinHeight(20);
		placeAbleObjects.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		placeAbleObjects.getStylesheets().add(UIConceptALL.class.getResource("ObjectChooser.css").toExternalForm());
		
		for (int i = 0; i < 8; i++) {
			Tab tab = new Tab("Category"+(i+1));
			HBox elementsTab = new HBox(20);
			elementsTab.setStyle("-fx-background-color: transparent;");
			ScrollPane s1 = new ScrollPane();
			s1.setContent(elementsTab);
			s1.setVbarPolicy(ScrollBarPolicy.NEVER);
			s1.getStylesheets().add(UIConceptALL.class.getResource("ScrollPane.css").toExternalForm());
			elementsTab.setPadding(new Insets(10, 10, 10, 10));
			for (int j = 0; j < 4; j++) {
				elementsTab.getChildren().add(new Circle(50,Color.color(Math.random(), Math.random(), Math.random())));
				elementsTab.getChildren().add(new Rectangle(100, 100,Color.color(Math.random(), Math.random(), Math.random())));
				Polygon triangle = new Polygon();
				triangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
				triangle.getPoints().addAll(new Double[]{0.0, 100.0,100.0, 100.0,50.0, 0.0 });
				elementsTab.getChildren().add(triangle);
				Polygon hexagon = new Polygon();
				hexagon.setFill(Color.color(Math.random(), Math.random(), Math.random()));
				hexagon.getPoints().addAll(new Double[]{20.0, 0.0,80.0, 0.0,100.0, 50.0, 80.0, 100.0,20.0, 100.0,0.0, 50.0 });
				elementsTab.getChildren().add(hexagon);
			}
			tab.setContent(s1);
			placeAbleObjects.getTabs().add(tab);
		}
		

	  	BorderPane canvasAndObjectChooser = new BorderPane();
	  	canvasAndObjectChooser.setCenter(canvasNode);
	  	canvasAndObjectChooser.setBottom(placeAbleObjects);
	  	
	  	
	  	root.getChildren().addAll(canvasAndObjectChooser,rightBarContent);
	}
	
}
