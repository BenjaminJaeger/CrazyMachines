package UI.EditorTabPane.TabElements;

import UI.Util;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class TabElement extends VBox {

	 private ImageView icon;
	 private String imageURL;
	 private double size = 100;
	 
    public TabElement(Pane glass,String name, String imageURL) {
        super(10);
        this.setAlignment(Pos.BOTTOM_CENTER);

        this.imageURL = imageURL;
        
        Label nameLabel = new Label(name);
        
        this.icon = new ImageView(new Image("file:res/TabImages/"+imageURL));
        icon.setFitWidth(100);
        icon.setFitHeight(100);
        
        this.getChildren().addAll(icon, nameLabel);
        
        addDragAndDrop(glass);         
    }
    
    protected void addDragAndDrop(Pane glass) {
    	  //DRAG AND DROP
    	ImageView clone = new ImageView(new Image("file:res/TabImages/"+imageURL));
		clone.setFitHeight(size);
		clone.setFitWidth(size);
		
		icon.setOnDragDetected(e->{      	
        	clone.relocate(e.getSceneX()-(size/2),e.getSceneY()-(size/2));
        	glass.getChildren().add(clone);
        	glass.toFront(); 
        });
        
		icon.setOnMouseDragged(e->{
    		clone.relocate(e.getSceneX()-(size/2),e.getSceneY()-(size/2));
    	});
    	
    	icon.setOnMouseReleased(e1->{
        	glass.toBack();
        	glass.getChildren().clear();    
        	
	
    		Util.canvasWrapper.setOnMouseEntered(e2->{
    				
            	float x = Util.convertMouseX(e2.getX());
            	float y = Util.convertMouseY(e2.getY());
            	
//            	System.out.println("e.x = "+e2.getX());
//    			System.out.println("e.y = "+e2.getY());
//    			System.out.println("canvasSizeX = "+Config.CANVAS_WIDTH);
//    			System.out.println("canvasSizeY = "+Config.CANVAS_HEIGHT);
//            	System.out.println("converted.x = "+x);
//            	System.out.println("converted.y = "+y);
//            	System.out.println();
            	
            	createObject(x, y);
    		});
    		
        	
        });
    }

    protected abstract void createObject(float x, float y);
    
//    protected void scale(double x,double y) {
//    	 float objectX = GameObject.allObjects.get( GameObject.allObjects.size()-1).getX() + Util.canvas.getWidth()/2;
//    	 float objectY = Util.canvas.getHeight()/2 -  GameObject.allObjects.get( GameObject.allObjects.size()-1).getY();
//
//    	 float mouseX = (float)x;
//    	 float mouseY = (float)y;
//    	     
//    	 float scale = (float)Math.sqrt(Math.pow((objectX-mouseX),2)+Math.pow((objectY-mouseY),2)) /300;
//    	 scale = scale < 0.4f ? 0.4f: scale;
//    	 scale = scale > 1.3f ? 1.3f: scale;
//    	     
//    	 float rotation = -(float)Math.atan2(objectY-mouseY , objectX-mouseX) * 180/(float)Math.PI;
//
//     	 GameObject.allObjects.get( GameObject.allObjects.size()-1).setScale(scale);
//     	 GameObject.allObjects.get( GameObject.allObjects.size()-1).setRotation(rotation);  
//    }
   
}
