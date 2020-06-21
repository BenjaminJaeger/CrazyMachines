package UI.SideBar;

import Simulation.Util;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.StaticObjects.StaticExternalObjects.Hairdryer;
import Simulation.Objects.StaticObjects.StaticExternalObjects.Magnet;
import Simulation.RenderEngine.Core.Math.Vector2f;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ObjectSettingsLevel extends VBox {
	
	private ObjectSettingsTextField xPosition;
	private ObjectSettingsTextField yPosition;
	private ObjectSettingsTextField scale;
	private ObjectSettingsTextField rotation;
	 
	protected VBox settings;
	protected HBox head;
	protected Line line;
	protected Label properties;
 
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
        
        //XPOSITION
        xPosition = new ObjectSettingsTextField("x", object, "m", object.getX());
        xPosition.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(xPosition.getTextField().getText().length() == 0) {
		      		object.setX(0);
		          	object.setOriginalX(0);
		      	}else {
		      		object.setX(Float.valueOf(xPosition.getTextField().getText()));
		          	object.setOriginalX(Float.valueOf(xPosition.getTextField().getText()));
		      	}	   	
        	}
        });
        
        //YPOSITION
        yPosition = new ObjectSettingsTextField("y", object, "m", object.getY());
        yPosition.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(yPosition.getTextField().getText().length() == 0) {
		      		object.setY(0);
		          	object.setOriginalY(0);
		      	}else {
		      		object.setY(Float.valueOf(yPosition.getTextField().getText()));
		          	object.setOriginalY(Float.valueOf(yPosition.getTextField().getText()));
		      	}	   	
        	}
        });

        //ROTATION
        rotation = new ObjectSettingsTextField("rotation", object, "°", object.getRotation());
        rotation.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(rotation.getTextField().getText().length() == 0) {
		      		object.setRotation(0);
            		object.setOriginalrotation(0);
		      	}else {
		      		object.setRotation(Float.valueOf(rotation.getTextField().getText()));
		          	object.setOriginalrotation(Float.valueOf(rotation.getTextField().getText()));
		      	}	   	
        	}
        });
                
        //SCALE
        scale = new ObjectSettingsTextField("scale", object, "%", object.getScale()*100);
        scale.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(scale.getTextField().getText().length() == 0) 
		      		object.setScale(1);
		      	else 
		      		object.setScale(Float.valueOf(scale.getTextField().getText())/100);    		   	
        	}
        });
       
        //MASS
        ObjectSettingsTextField mass = new ObjectSettingsTextField("mass", object, "kg", object.getMass());
        mass.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(mass.getTextField().getText().length() == 0) 
		      		object.setMass(0);
		      	else 
		      		object.setMass(Float.valueOf(mass.getTextField().getText()));
        	}	   	       	
        });
        
        //ELASTICITY
        ObjectSettingsTextField elasticity = new ObjectSettingsTextField("elasticity", object, "%", object.getCoefficientOfRestitution()*100);    
        elasticity.getTextField().textProperty().addListener(new ChangeListener<String>() {
        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		      	if(elasticity.getTextField().getText().length() == 0) 
		      		object.setCoefficientOfRestitution(1);
		      	else 
		      		object.setCoefficientOfRestitution(Float.valueOf(elasticity.getTextField().getText())/100);
        	}	   	       	
        });

              
        settings = new VBox(5);
        settings.getChildren().addAll(xPosition,yPosition,scale,rotation,mass,elasticity);
        
        
        if(object instanceof MoveableObject) {
	
        	Vector2f directionVector = new Vector2f(((MoveableObject) object).getVelocityX(), ((MoveableObject) object).getVelocityY());
	        	        
        	//SPEED
        	ObjectSettingsTextField speed = new ObjectSettingsTextField("speed", object, "m/s", directionVector.length());
        	speed.getTextField().textProperty().addListener(new ChangeListener<String>() {
               	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
       		      	if(speed.getTextField().getText().length() == 0) {
	       		      	((MoveableObject) object).setAccelerationX(0);
	    	        	((MoveableObject) object).setAccelerationY(0);
	    	        	((MoveableObject) object).setOriginalAccelerationX(0);
	    	        	((MoveableObject) object).setOriginalAccelerationY(0);
       		      	}else {
//	       		      	((MoveableObject) object).setAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
//	    	        	((MoveableObject) object).setAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
//	    	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getText())*Float.parseFloat(speed.getText()));
//	    	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getText())*Float.parseFloat(speed.getText()));
       		      	}	   	
               	}
            });

        
	        directionVector.normalize();
	        
	        //XDIRECTION
	        ObjectSettingsTextField xDirection = new ObjectSettingsTextField("dirx", object, "", directionVector.x);	        
	        xDirection.getTextField().textProperty().addListener(new ChangeListener<String>() {
               	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
       		      	if(xDirection.getTextField().getText().length() == 0) {
	       		      	((MoveableObject) object).setAccelerationX(0);
	    	        	((MoveableObject) object).setOriginalAccelerationX(0);
       		      	}else {
	       		        ((MoveableObject) object).setVelocityX(Float.parseFloat(xDirection.getTextField().getText())*Float.parseFloat(speed.getTextField().getText()));	            		
	    	        	((MoveableObject) object).setOriginalAccelerationX(Float.parseFloat(xDirection.getTextField().getText())*Float.parseFloat(speed.getTextField().getText()));
       		      	}	   	
               	}
            });
	       
	        //YDIRECTION
	        ObjectSettingsTextField yDirection = new ObjectSettingsTextField("diry", object, "", directionVector.y);
	        yDirection.getTextField().textProperty().addListener(new ChangeListener<String>() {
           	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	   		      	if(yDirection.getTextField().getText().length() == 0) {
	       		      	((MoveableObject) object).setAccelerationY(0);
	    	        	((MoveableObject) object).setOriginalAccelerationY(0);
	   		      	}else {
	       		        ((MoveableObject) object).setVelocityY(Float.parseFloat(yDirection.getTextField().getText())*Float.parseFloat(speed.getTextField().getText()));	            		
	    	        	((MoveableObject) object).setOriginalAccelerationY(Float.parseFloat(yDirection.getTextField().getText())*Float.parseFloat(speed.getTextField().getText()));
	   		      	}	   	
	           	}
	        });   	       
	  
	        
	        settings.getChildren().addAll(speed,xDirection,yDirection);
        }
        
        if(object instanceof Hairdryer) {
        	
        	ObjectSettingsTextField wind = new ObjectSettingsTextField("wind", object, "", 0);
        	wind.getTextField().textProperty().addListener(new ChangeListener<String>() {
             	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
     		      	if(wind.getTextField().getText().length() == 0) 
     		      		System.out.println("TODO");
     		      	else 
     		      		System.out.println("TODO"); 	
             	}
             });

	        
	        settings.getChildren().add(wind);
        }
        
        if(object instanceof Magnet) {
        	
        	ObjectSettingsTextField magnet = new ObjectSettingsTextField("magnetism", object, "", 0);
        	magnet.getTextField().textProperty().addListener(new ChangeListener<String>() {
	         	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	 		      	if(magnet.getTextField().getText().length() == 0) 
	 		      		System.out.println("TODO");
	 		      	else 
	 		      		System.out.println("TODO"); 	
	         	}
	         });

	        settings.getChildren().add(magnet);
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
    	xPosition.getTextField().setText(Util.getRoundedString(object.getX()));
    	yPosition.getTextField().setText(Util.getRoundedString(object.getY()));
    	scale.getTextField().setText(Util.getRoundedString(object.getScale()*100));
    	rotation.getTextField().setText(Util.getRoundedString(object.getRotation()));    		    
    }
    
    
}
