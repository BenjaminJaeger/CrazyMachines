package UI.EditorMenue.Tabs;

import UI.TabElements.BasketballTabElement;
import UI.TabElements.BucketTabElement;
import UI.TabElements.HairdryerTabElement;
import UI.TabElements.MagnetTabElement;
import UI.TabElements.PlaneTabElement;
import UI.TabElements.PortalTabElement;
import UI.TabElements.SpinnerTabElement;
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
		 PlaneTabElement plane = new PlaneTabElement(glass);
		 BucketTabElement bucket = new BucketTabElement(glass);
		 SpinnerTabElement spinner = new SpinnerTabElement(glass);
		 MagnetTabElement magnetTab = new MagnetTabElement (glass);
		 
		 content.getChildren().addAll(basketBall,portal,plane,bucket, hairdryer,spinner,magnetTab);
	}
	
	
}
