package UI.EditorTabPane.TabElements;

import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticPlane;
import javafx.scene.layout.Pane;

public class StaticPlaneTabElement extends TabElement{

	public StaticPlaneTabElement(Pane glass) {
		super(glass,"Plane", "StaticPlane.png");
	}

	@Override
	public void createObject(float x, float y) {
		StaticPlane plane = new StaticPlane(x, y);
		plane.setScale(0.4f);
	}

}
