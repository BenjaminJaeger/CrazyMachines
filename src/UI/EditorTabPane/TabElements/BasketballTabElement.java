package UI.EditorTabPane.TabElements;

import Simulation.Objects.MovableObjects.Ball.BasketBall;
import javafx.scene.layout.Pane;

public class BasketballTabElement extends TabElement{

	public BasketballTabElement(Pane glass) {
		super(glass,"BasketBall", "BasketBall.png");
	}

	@Override
	public void createObject(float x, float y) {
		new BasketBall(x,y);
	}

}
