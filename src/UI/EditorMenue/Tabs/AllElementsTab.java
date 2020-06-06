package UI.EditorMenue.Tabs;

import UI.TabElements.*;
import javafx.scene.layout.Pane;

public class AllElementsTab extends EditorTab{

	public AllElementsTab(Pane glass) {
		super(glass,"basketball.png","All");
	}

	@Override
	protected void createContent(Pane glass) {
		 BasketballTabElement basketBall = new BasketballTabElement(glass);
		 HairdryerTabElement hairdryer = new HairdryerTabElement(glass);
		 PortalTabElement portal = new PortalTabElement(glass);
		 StaticPlaneTabElement plane = new StaticPlaneTabElement(glass);
		 BucketTabElement bucket = new BucketTabElement(glass);
			
		 content.getChildren().addAll(basketBall,portal,plane,bucket, hairdryer);
	}
	
	
}
