package UI.TabElements;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.Ball.MetalBall;
import javafx.scene.layout.Pane;

public class MetalBallTabElement extends TabElement{

	public MetalBallTabElement(Pane glass) {
		super(glass,"MetalBall", "MetalBall.png");
	}
	
	public MetalBallTabElement(Pane glass,int ammount) {
		super(glass,"MetalBall", "MetalBall.png",ammount);
	}

	@Override
	public GameObject createObject(float x, float y) {
		return new MetalBall(x,y);
	}

}
