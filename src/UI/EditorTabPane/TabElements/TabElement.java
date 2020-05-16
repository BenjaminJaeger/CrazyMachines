package UI.EditorTabPane.TabElements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class TabElement extends VBox {

    public TabElement(Pane glass,String name, String imageURL) {
        super(10);
        this.setAlignment(Pos.BOTTOM_CENTER);

        Label nameLabel = new Label(name);
        
        ImageView image = new ImageView(new Image("file:res/TabImages/"+imageURL));
        image.setFitWidth(100);
        image.setFitHeight(100);
        
        this.getChildren().addAll(image, nameLabel);
        
        //DRAG AND DROP
    	ImageView clone = new ImageView(new Image("file:res/TabImages/"+imageURL));
    	double size = 100;
		clone.setFitHeight(size);
		clone.setFitWidth(size);
		this.getChildren().add(clone);
		
        image.setOnDragDetected(e->{      	
        	clone.relocate(e.getSceneX()-(size/2),e.getSceneY()-(size/2));
        	glass.getChildren().add(clone);
        	glass.toFront(); 
        });
        
    	image.setOnMouseDragged(e->{
    		clone.relocate(e.getSceneX()-(size/2),e.getSceneY()-(size/2));
    	});
    	
        image.setOnMouseReleased(e->{
        	glass.toBack();
        	glass.getChildren().clear();    
        	createObject(0, 0);
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
