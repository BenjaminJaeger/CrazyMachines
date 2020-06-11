package UI.LevelMenue;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ObjectSettingsLevel extends VBox {
	
	 private TextField xPosition;
	 private TextField yPosition;
	 private TextField scale;
	 private TextField rotation;
	 private TextField mass;
	 private TextField xDirection;
	 private TextField yDirection;
	 private TextField speed;
	 
	 protected VBox settings;
	 protected HBox head;
	 protected Line line;
	 protected Label properties;

	 private float imageSize = 20;
	 
    public ObjectSettingsLevel() {
        super(10);
        
        this.getStyleClass().add("section");      
        this.setAlignment(Pos.BASELINE_LEFT);
        this.getStylesheets().add("file:res/css/SideBar.css");
    }

    public void addUI(GameObject object) {
        Label name = new Label(object.getClass().getSimpleName());    
        name.setStyle("-fx-font: 20px 'Roboto';");
         
        ImageView deleteImg = new ImageView(new Image("file:res/Images/delete.png"));        
        deleteImg.setFitHeight(30);
        deleteImg.setFitWidth(30);
        StackPane delete = new StackPane(deleteImg);
        delete.getStyleClass().add("delete");
        deleteImg.setOnMouseClicked(e->{
        	removeUI();
        	object.remove();
        	GameObject.allObjects.remove(object);        	
        });
        head = new HBox(10);
        head.setAlignment(Pos.CENTER_LEFT);
        head.getChildren().addAll(name,delete);
     
        line = new Line(0,20,110,20);
	    line.setStrokeWidth(3);
	    line.setStroke(Color.rgb(30, 30, 30));
        
        properties = new Label("Properties:");
        properties.setStyle("-fx-font: 14px 'Roboto';");
        
        //POSITION
        ImageView xImg = new ImageView(new Image("file:res/Images/object-settings/x.png"));
        xImg.setFitHeight(imageSize);
        xImg.setFitWidth(imageSize);
        xPosition = new TextField (Float.toString(object.getX()));
        xPosition.setOnAction(e->{
        	object.setX(Float.parseFloat(xPosition.getText()));
        	object.setOriginalX(Float.parseFloat(xPosition.getText()));
        });
        HBox xContainer = new HBox();
//        xContainer.setAlignment(Pos.CENTER);
        xContainer.getChildren().addAll(xImg,xPosition);
        
        ImageView yImg = new ImageView(new Image("file:res/Images/object-settings/y.png"));
        yImg.setFitHeight(imageSize);
        yImg.setFitWidth(imageSize);
        yPosition = new TextField (Float.toString(object.getY()));
        yPosition.setOnAction(e->{
        	object.setY(Float.parseFloat(yPosition.getText()));
        	object.setOriginalY(Float.parseFloat(yPosition.getText()));
        });
        HBox yContainer = new HBox();
//        yContainer.setAlignment(Pos.CENTER);
        yContainer.getChildren().addAll(yImg,yPosition);

       
        //SCALE
        ImageView scaleImg = new ImageView(new Image("file:res/Images/object-settings/scale.png"));
        scaleImg.setFitHeight(imageSize);
        scaleImg.setFitWidth(imageSize);
        scale = new TextField (Float.toString(object.getScale()));
        scale.setOnAction(e->{
        	object.setScale(Float.parseFloat(scale.getText()));
        	object.setOriginalscale(Float.parseFloat(scale.getText()));
        });        
        HBox scaleContainer = new HBox();
//        scaleContainer.setAlignment(Pos.CENTER);
        scaleContainer.getChildren().addAll(scaleImg,scale);

        
        ImageView rotationImg = new ImageView(new Image("file:res/Images/object-settings/rotation.png"));
        rotationImg.setFitHeight(imageSize);
        rotationImg.setFitWidth(imageSize);
        rotation = new TextField (Float.toString(object.getRotation()));
        rotation.setOnAction(e->{
        	object.setRotation(Float.parseFloat(rotation.getText()));
        	object.setOriginalrotation(Float.parseFloat(rotation.getText()));
        });        
        HBox rotationContainer = new HBox();
//        rotationContainer.setAlignment(Pos.CENTER);
        rotationContainer.getChildren().addAll(rotationImg,rotation);

        
        //MASS
        ImageView massImg = new ImageView(new Image("file:res/Images/object-settings/mass.png"));
        massImg.setFitHeight(imageSize);
        massImg.setFitWidth(imageSize);
        mass = new TextField (Float.toString(object.getMass()));
        mass.setOnAction(e->{
        	object.setMass(Float.parseFloat(mass.getText()));
        	object.setOriginalMass(Float.parseFloat(mass.getText()));
        });
        HBox massContainer = new HBox();
//        massContainer.setAlignment(Pos.CENTER);
        massContainer.getChildren().addAll(massImg,mass);

        settings = new VBox(5);
        settings.getChildren().addAll(xContainer,yContainer,scaleContainer,rotationContainer,massContainer);
        
        
        if(object instanceof MoveableObject) {
	
        	//SPEED
        	ImageView speedImg = new ImageView(new Image("file:res/Images/object-settings/speed.png"));
        	speedImg.setFitHeight(imageSize);
        	speedImg.setFitWidth(imageSize);
	        speed = new TextField (Float.toString(((MoveableObject) object).getAccelerationX()));
	        speed.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        HBox speedContainer = new HBox();
//	        speedContainer.setAlignment(Pos.CENTER);
	        speedContainer.getChildren().addAll(speedImg,speed);
	        
	        
	        //DIRECTION
	        ImageView xDirectionImg = new ImageView(new Image("file:res/Images/object-settings/dirx.png"));
	        xDirectionImg.setFitHeight(imageSize);
	        xDirectionImg.setFitWidth(imageSize);
	        xDirection = new TextField ("0");
	        xDirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	        });
	        HBox xDirectionContainer = new HBox();
//	        xDirectionContainer.setAlignment(Pos.CENTER);
	        xDirectionContainer.getChildren().addAll(xDirectionImg, xDirection);
	        
	        ImageView yDirectionImg = new ImageView(new Image("file:res/Images/object-settings/diry.png"));
	        yDirectionImg.setFitHeight(imageSize);
	        yDirectionImg.setFitWidth(imageSize);
	        yDirection = new TextField ("0");
	        yDirection.setOnAction(e->{
	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	        });	       	       
	        HBox yDirectionContainer = new HBox();
//	        xDirectionContainer.setAlignment(Pos.CENTER);
	        yDirectionContainer.getChildren().addAll(yDirectionImg,yDirection);
	  
	        
	        settings.getChildren().addAll(speedContainer,xDirectionContainer,yDirectionContainer);
        }
           
        completeUI(object);
    }

    public void completeUI(GameObject object) {
    	 this.getChildren().addAll(head,line,properties,settings);
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
    		xDirection.setText(Float.toString(object.getScale()));
    		yDirection.setText(Float.toString(object.getScale()));
    		speed.setText(Float.toString(object.getScale()));
    	}
    
    }
}
