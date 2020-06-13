package UI.LevelMenue;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        xPosition = new TextField (Float.toString(Math.round(object.getX())));
        xPosition.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d*")) 
            		xPosition.setText(newValue.replaceAll("\\D", ""));
	 
            	if(xPosition.getText().length() == 0) {
            		object.setX(0);
                	object.setOriginalX(0);
            	}else {
            		object.setX(Float.parseFloat(xPosition.getText()));
                	object.setOriginalX(Float.parseFloat(xPosition.getText()));
            	}
         	
            }
        });
        Label xUnit = new Label("m");
        HBox xContainer = new HBox(5);
        xContainer.getChildren().addAll(xImg,xPosition,xUnit);
        
        ImageView yImg = new ImageView(new Image("file:res/Images/object-settings/y.png"));
        yImg.setFitHeight(imageSize);
        yImg.setFitWidth(imageSize);
        yPosition = new TextField (Float.toString(Math.round(object.getY())));
        yPosition.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d*")) 
            		yPosition.setText(newValue.replaceAll("\\D", ""));
	 
            	if(yPosition.getText().length() == 0) {
            		object.setY(0);
                	object.setOriginalY(0);
            	}else {
            		object.setY(Float.parseFloat(yPosition.getText()));
                	object.setOriginalY(Float.parseFloat(yPosition.getText()));
            	}
         	
            }
        });
        Label yUnit = new Label("m");
        HBox yContainer = new HBox(5);
        yContainer.getChildren().addAll(yImg,yPosition,yUnit);

       
        //SCALE
        ImageView scaleImg = new ImageView(new Image("file:res/Images/object-settings/scale.png"));
        scaleImg.setFitHeight(imageSize);
        scaleImg.setFitWidth(imageSize);
        scale = new TextField (Float.toString(Math.round(object.getScale()*100)));
        scale.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d*")) 
            		scale.setText(newValue.replaceAll("\\D", ""));
	 
            	if(scale.getText().length() == 0) {
            		object.setScale(1);
                	object.setOriginalscale(1);
            	}else {
            		object.setScale(Float.parseFloat(scale.getText())/100);
                	object.setOriginalscale(Float.parseFloat(scale.getText())/100);
            	}
         	
            }
        });
        Label scaleUnit = new Label("%");
        HBox scaleContainer = new HBox(5);
        scaleContainer.getChildren().addAll(scaleImg,scale,scaleUnit);

        
        ImageView rotationImg = new ImageView(new Image("file:res/Images/object-settings/rotation.png"));
        rotationImg.setFitHeight(imageSize);
        rotationImg.setFitWidth(imageSize);
        rotation = new TextField (Float.toString(Math.round(object.getRotation())));
        rotation.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d*")) 
            		rotation.setText(newValue.replaceAll("[^\\d]", ""));
	 
            	if(rotation.getText().length() == 0) {
            		object.setRotation(0);
            		object.setOriginalrotation(0);
            	}else {
            		object.setRotation(Float.parseFloat(rotation.getText()));
            		object.setOriginalrotation(Float.parseFloat(rotation.getText()));
            	}
				
	            	
            }
        });

        Label rotationUnit = new Label("°");
        HBox rotationContainer = new HBox(5);
        rotationContainer.getChildren().addAll(rotationImg,rotation,rotationUnit);

        
        //MASS
        ImageView massImg = new ImageView(new Image("file:res/Images/object-settings/mass.png"));
        massImg.setFitHeight(imageSize);
        massImg.setFitWidth(imageSize);
        if(object.getMass()<999999)
        	mass = new TextField(Float.toString(Math.round(object.getMass())));
        else 
        	mass = new TextField("99");
        mass.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d*")) 
            		mass.setText(newValue.replaceAll("\\D", ""));
	 
            	if(mass.getText().length() == 0) {
            		object.setScale(1);
                	object.setOriginalscale(1);
            	}else {
            		object.setMass(Float.parseFloat(mass.getText()));
                	object.setOriginalMass(Float.parseFloat(mass.getText()));
            	}
         	
            }
        });

        Label massUnit = new Label("kg");
        HBox massContainer = new HBox(5);
        massContainer.getChildren().addAll(massImg,mass,massUnit);

        settings = new VBox(5);
        settings.getChildren().addAll(xContainer,yContainer,scaleContainer,rotationContainer,massContainer);
        
        
        if(object instanceof MoveableObject) {
	
        	Vector2f directionVector = new Vector2f(((MoveableObject) object).getVelocityX(), ((MoveableObject) object).getVelocityY());
	        	        
        	//SPEED
        	ImageView speedImg = new ImageView(new Image("file:res/Images/object-settings/speed.png"));
        	speedImg.setFitHeight(imageSize);
        	speedImg.setFitWidth(imageSize);
	        speed = new TextField (Float.toString(Math.round(directionVector.length())));
	        speed.textProperty().addListener(new ChangeListener<String>() {
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            	if (!newValue.matches("\\d*")) 
	            		speed.setText(newValue.replaceAll("\\D", ""));
		 
	            	if(speed.getText().length() == 0) {
	            		((MoveableObject) object).setAccelerationX(0);
	    	        	((MoveableObject) object).setAccelerationY(0);
	    	        	((MoveableObject) object).setOriginalAccelerationX(0);
	    	        	((MoveableObject) object).setOriginalAccelerationY(0);
	            	}else {
	            		((MoveableObject) object).setAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	    	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	    	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	    	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	            	}
	         	
	            }
	        });
	        Label speedUnit = new Label("m/s");
	        HBox speedContainer = new HBox(5);
	        speedContainer.getChildren().addAll(speedImg,speed,speedUnit);
	        
	        directionVector.normalize();
	        
	        //DIRECTION
	        ImageView xDirectionImg = new ImageView(new Image("file:res/Images/object-settings/dirx.png"));
	        xDirectionImg.setFitHeight(imageSize);
	        xDirectionImg.setFitWidth(imageSize);
	        xDirection = new TextField (Float.toString(Math.round(directionVector.x)));
	        xDirection.textProperty().addListener(new ChangeListener<String>() {
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            	if (!newValue.matches("\\d*")) 
	            		xDirection.setText(newValue.replaceAll("\\D", ""));
		 
	            	if(xDirection.getText().length() == 0) {
	            		((MoveableObject) object).setAccelerationX(0);
	    	        	((MoveableObject) object).setOriginalAccelerationX(0);
	            	}else {
	            		((MoveableObject) object).setVelocityX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	            		System.out.println(((MoveableObject) object).getAccelerationX());
	    	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
	            	}
	         	
	            }
	        });
	        HBox xDirectionContainer = new HBox(5);
	        xDirectionContainer.getChildren().addAll(xDirectionImg, xDirection);
	        
	        ImageView yDirectionImg = new ImageView(new Image("file:res/Images/object-settings/diry.png"));
	        yDirectionImg.setFitHeight(imageSize);
	        yDirectionImg.setFitWidth(imageSize);
	        yDirection = new TextField (Float.toString(Math.round(directionVector.y)));
	        yDirection.textProperty().addListener(new ChangeListener<String>() {
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            	if (!newValue.matches("\\d*")) 
	            		yDirection.setText(newValue.replaceAll("\\D", ""));
		 
	            	if(yDirection.getText().length() == 0) {
	            		((MoveableObject) object).setAccelerationY(0);
	    	        	((MoveableObject) object).setOriginalAccelerationY(0);
	            	}else {
	            		((MoveableObject) object).setAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	    	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
	            	}
	         	
	            }
	        });      	       
	        HBox yDirectionContainer = new HBox(5);
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
//    	xPosition.setText(Float.toString(Math.round(object.getX())));
//    	yPosition.setText(Float.toString(Math.round(object.getY())));
//    	scale.setText(Float.toString(Math.round(object.getScale()*100)));
//    	rotation.setText(Float.toString(Math.round(object.getRotation())));
//    	
//    	if(object.getMass()>999999)
//    		mass.setText("99");
//    	else 
//    		mass.setText(Float.toString(Math.round(object.getMass())));
//		
//    	
//    	if(object instanceof MoveableObject) {
//    		Vector2f directionVector = new Vector2f(((MoveableObject) object).getVelocityX(), ((MoveableObject) object).getVelocityY());
//    		
//    		speed.setText(Float.toString(Math.round(directionVector.length())));
//    		
//    		directionVector.normalize();
//    		xDirection.setText(Float.toString(Math.round(directionVector.x)));
//    		yDirection.setText(Float.toString(Math.round(directionVector.y)));
//    		
//    	}
    
    }
}
