package UI.TabElements;

import Simulation.Objects.GameObject;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticPlane;
import javafx.scene.layout.Pane;

public class StaticPlaneTabElement extends TabElement{

	public StaticPlaneTabElement(Pane glass,int ammount) {
		super(glass,"Plane", "StaticPlane.png",ammount);
	}
	
	public StaticPlaneTabElement(Pane glass) {
		super(glass,"Plane", "StaticPlane.png");
	}

	@Override
	public GameObject createObject(float x, float y) {
		return new StaticPlane(x, y);
	}

}
