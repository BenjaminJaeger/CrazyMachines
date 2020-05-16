package UI.EditorTabPane;

import UI.EditorTabPane.Tabs.BallTab;
import UI.EditorTabPane.Tabs.BlockTab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class EditorTabPane extends TabPane{

   public EditorTabPane(Pane glass) {
	   this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	   this.getStylesheets().add("file:res/css/ObjectChooser.css");
	   addTabs(glass);
   }
   
   private void addTabs(Pane glass) {
	   BallTab balls = new BallTab(glass);
	   BlockTab blocks = new BlockTab(glass);
	   getTabs().addAll(balls, blocks);
   }

}
