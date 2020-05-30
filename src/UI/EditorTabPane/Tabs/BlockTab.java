package UI.EditorTabPane.Tabs;

import UI.EditorTabPane.TabElements.StaticPlaneTabElement;
import javafx.scene.layout.Pane;

public class BlockTab extends EditorTab{

	public BlockTab(Pane glass) {
		super(glass,"StaticPlane.png","Blocks");
	}

	@Override
	protected void createContent(Pane glass) {
		StaticPlaneTabElement plane = new StaticPlaneTabElement(glass,5);
		content.getChildren().add(plane);
	}
	
	
}
