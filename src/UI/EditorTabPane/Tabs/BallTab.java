package UI.EditorTabPane.Tabs;

import UI.EditorTabPane.TabElements.BasketballTabElement;
import javafx.scene.layout.Pane;

public class BallTab extends EditorTab{

	public BallTab(Pane glass) {
		super(glass,"basketball.png","Balls");
	}

	@Override
	protected void createContent(Pane glass) {
		 BasketballTabElement basketBall = new BasketballTabElement(glass,5);
		 content.getChildren().add(basketBall);
	}
	
	
}
