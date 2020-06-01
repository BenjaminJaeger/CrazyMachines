package UI.EditorTabPane.TabElements;

import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticBucket;
import javafx.scene.layout.Pane;

public class BucketTabElement extends TabElement{

	public BucketTabElement(Pane glass) {
		super(glass,"Bucket", "BasketBall.png");
	}
	
	public BucketTabElement(Pane glass,int ammount) {
		super(glass,"Bucket", "Bucket.png",ammount);
	}

	@Override
	public void createObject(float x, float y) {
		new StaticBucket(x,y);
	}

}
