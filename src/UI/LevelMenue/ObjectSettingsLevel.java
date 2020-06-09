package UI.LevelMenue;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectSettingsLevel extends VBox {
	
	 private TextField xPosition;
	 private TextField yPosition;
	 private TextField scale;
	 private TextField rotation;
	 private TextField mass;
	 private TextField xdirection;
	 private TextField ydirection;
	 private TextField speed;

	 
    public ObjectSettingsLevel() {
        super(5);
        
        this.getStyleClass().add("hbox");      
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("file:res/css/SideBar.css");
    }

    public void addUI(GameObject object) {
        Label head = new Label("Object Settings");

        //POSITION
        Label xPositionL = new Label("Position");
        xPositionL.setMinWidth(50);
        Label xp = new Label("X");
        
        xPosition = new TextField (Float.toString(object.getX()));
        xPosition.setOnAction(e->{
        	object.setX(Float.parseFloat(xPosition.getText()));
        	object.setOriginalX(Float.parseFloat(xPosition.getText()));
        });
        
        Label yp = new Label("Y");
        yPosition = new TextField (Float.toString(object.getY()));
        yPosition.setOnAction(e->{
        	object.setY(Float.parseFloat(yPosition.getText()));
        	object.setOriginalY(Float.parseFloat(yPosition.getText()));
        });
        
        HBox positionContainer = new HBox(10);
        positionContainer.getChildren().addAll(xPositionL,xp,xPosition,yp,yPosition);

       
        //SCALE
        Label scaleL = new Label("Scale");
        scaleL.setMinWidth(50);
        
        scale = new TextField (Float.toString(object.getScale()));
        scale.setOnAction(e->{
        	object.setScale(Float.parseFloat(scale.getText()));
        	object.setOriginalscale(Float.parseFloat(scale.getText()));
        });
        
        HBox scaleContainer = new HBox(10);
        scaleContainer.getChildren().addAll(scaleL,scale);

        
        //ROTATION
        Label rotationL = new Label("Rotation");
        rotationL.setMinWidth(50);
        
        rotation = new TextField (Float.toString(object.getRotation()));
        rotation.setOnAction(e->{
        	object.setRotation(Float.parseFloat(rotation.getText()));
        	object.setOriginalrotation(Float.parseFloat(rotation.getText()));
        });
        
        HBox rotationContainer = new HBox(10);
        rotationContainer.getChildren().addAll(rotationL,rotation);

        
        //MASS
        Label massL = new Label("Mass");
        massL.setMinWidth(50);
        
        mass = new TextField (Float.toString(object.getMass()));
        mass.setOnAction(e->{
        	object.setMass(Float.parseFloat(mass.getText()));
        	object.setOriginalMass(Float.parseFloat(mass.getText()));
        });
        HBox massContainer = new HBox(10);
        massContainer.getChildren().addAll(massL,mass);

        this.getChildren().addAll(head,positionContainer,scaleContainer,rotationContainer,massContainer);
        
        
        
        if(object instanceof MoveableObject) {
	
        	//SPEED
        	Label speedL = new Label("Speed");
        	speedL.setMinWidth(50);
        	
	        speed = new TextField (Float.toString(((MoveableObject) object).getAccelerationX()));
	        speed.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        HBox speedContainer = new HBox(10);
	        speedContainer.getChildren().addAll(speedL,speed);
	        
	        
	        //DIRECTION
	        Label directionL = new Label("Direction");
	        directionL.setMinWidth(50);
	        
	        Label xd = new Label("X");	         	        
	        xdirection = new TextField ("0");
	        xdirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xdirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        
	        Label yd = new Label("Y");	       
	        ydirection = new TextField ("0");
	        ydirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(ydirection.getText())*Float.parseFloat(speed.getText()));
	        });	       	       
	        HBox directionContainer = new HBox(10);
	        directionContainer.getChildren().addAll(directionL,xd,xdirection,yd,ydirection);
	  
	        
	        this.getChildren().addAll(speedContainer,directionContainer);
        }
        
        
        Button delete = new Button("Delete");
        delete.setGraphic(new ImageView(new Image("file:res/Images/delete.png")));
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
    	mass.setText(Float.toString(object.getMass()));
    	
    	if(object instanceof MoveableObject) {
    		xdirection.setText(Float.toString(object.getScale()));
    		ydirection.setText(Float.toString(object.getScale()));
    		speed.setText(Float.toString(object.getScale()));
    	}
    
    }
}
