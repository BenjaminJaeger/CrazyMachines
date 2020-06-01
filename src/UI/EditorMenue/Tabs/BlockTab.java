package UI.EditorMenue.Tabs;

import UI.TabElements.BucketTabElement;
import UI.TabElements.StaticPlaneTabElement;
import javafx.scene.layout.Pane;

public class BlockTab extends EditorTab{

	public BlockTab(Pane glass) {
		super(glass,"StaticPlane.png","Blocks");
	}

	@Override
	protected void createContent(Pane glass) {
		StaticPlaneTabElement plane = new StaticPlaneTabElement(glass);
		BucketTabElement bucket = new BucketTabElement(glass);
		content.getChildren().addAll(plane,bucket);
	}
	
	
}
