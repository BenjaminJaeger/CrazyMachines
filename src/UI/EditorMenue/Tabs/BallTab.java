package UI.EditorMenue.Tabs;

import UI.TabElements.BasketballTabElement;
import UI.TabElements.BeachBallTabElement;
import UI.TabElements.MetalBallTabElement;
import javafx.scene.layout.Pane;

public class BallTab extends EditorTab{

	public BallTab(Pane glass) {
		super(glass,"basketball.png","Balls");
	}

	@Override
	protected void createContent(Pane glass) {
		 BasketballTabElement basketBall = new BasketballTabElement(glass);
		 MetalBallTabElement metalBall = new MetalBallTabElement(glass);
		 BeachBallTabElement beachball = new BeachBallTabElement(glass);
		 
		 content.getChildren().addAll(basketBall,metalBall,beachball);
	}
	
	
}
