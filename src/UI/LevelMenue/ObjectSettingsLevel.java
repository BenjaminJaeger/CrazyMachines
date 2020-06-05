package UI.LevelMenue;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectSettingsLevel extends VBox {
	
	 private TextField xPosition;
	 private TextField yPosition;
	 private TextField scale;
	 private TextField rotation;
	 private TextField weight;
	 private TextField xdirection;
	 private TextField ydirection;
	 private TextField speed;

	 
    public ObjectSettingsLevel() {
        super(5);
        
        this.getStyleClass().add("hbox");      
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("file:res/css/SimulationControls.css");
    }

    public void addUI(GameObject object) {
        Label head = new Label("Object Settings");

        Label xPositionL = new Label("position");
        Label xp = new Label("x");
        xPosition = new TextField (Float.toString(object.getX()));
        xPosition.setOnAction(e->{
        	object.setX(Float.parseFloat(xPosition.getText()));
        	object.setOriginalX(Float.parseFloat(xPosition.getText()));
        });
        
        Label yp = new Label("y");
        yPosition = new TextField (Float.toString(object.getY()));
        yPosition.setOnAction(e->{
        	object.setY(Float.parseFloat(yPosition.getText()));
        	object.setOriginalY(Float.parseFloat(yPosition.getText()));
        });
        
        HBox xPositionB = new HBox(xPositionL,xp,xPosition,yp,yPosition);

        
        Label scaleL = new Label("scale");
        scale = new TextField (Float.toString(object.getScale()));
//      scale.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        scale.setOnAction(e->{
        	object.setScale(Float.parseFloat(scale.getText()));
        	object.setOriginalscale(Float.parseFloat(scale.getText()));
        });
        
        HBox scaleB = new HBox(scaleL,scale);

        Label rotationL = new Label("rotation");
        rotation = new TextField (Float.toString(object.getRotation()));
        rotation.setOnAction(e->{
        	object.setRotation(Float.parseFloat(rotation.getText()));
        	object.setOriginalrotation(Float.parseFloat(rotation.getText()));
        });
        
        HBox rotationB = new HBox(rotationL,rotation);

        Label weightL = new Label("weight");
        weight = new TextField (Float.toString(object.getMass()));
        weight.setOnAction(e->{
        	object.setMass(Float.parseFloat(weight.getText()));
        	object.setOriginalMass(Float.parseFloat(weight.getText()));
        });
        HBox weightB = new HBox(weightL,weight);

        this.getChildren().addAll(head,xPositionB,scaleB,rotationB,weightB);
        
        if(object instanceof MoveableObject) {
        	ydirection = new TextField ("0");
        	xdirection = new TextField ("0");
        	 
        	Label speedL = new Label("speed");
	        speed = new TextField (Float.toString(((MoveableObject) object).getAccelerationX()));
	        speed.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        HBox speedB = new HBox(speedL,speed);
	        
	        Label xdirectionL = new Label("direction");
	        Label xd = new Label("x");	         
	        
	        xdirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        
	        Label yd = new Label(Float.toString(((MoveableObject) object).getAccelerationY()));
	       
	        ydirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        });	       	       
	        HBox xdirectionB = new HBox(xdirectionL,xd,xdirection,yd,ydirection);
	  
	        
	        this.getChildren().addAll(xdirectionB,speedB);
        }
        
        
        Button delete = new Button("Delete");
        delete.setOnAction(e->{
        	removeUI();
        	object.remove();
        	GameObject.allObjects.remove(object);        	
        });
                     
        this.getChildren().addAll(delete);
           
    }

    public void removeUI() {
    	this.getChildren().clear();
    }
    
    public void updateUI(GameObject object) {
    	xPosition.setText(Float.toString(object.getX()));
    	yPosition.setText(Float.toString(object.getY()));
    	scale.setText(Float.toString(object.getScale()));
    	rotation.setText(Float.toString(object.getRotation()));
    	weight.setText(Float.toString(object.getMass()));
    	
    	if(object instanceof MoveableObject) {
    		xdirection.setText(Float.toString(object.getScale()));
    		ydirection.setText(Float.toString(object.getScale()));
    		speed.setText(Float.toString(object.getScale()));
    	}
    
    }
}