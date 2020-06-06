package UI.EditorMenue;

import UI.EditorMenue.Tabs.AllElementsTab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class EditorTabPane extends TabPane{

   public EditorTabPane(Pane glass) {
	   this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	   this.getStylesheets().add("file:res/css/EditorTabPane.css");
	   addTabs(glass);
   }
   
   private void addTabs(Pane glass) {
//	   BallTab balls = new BallTab(glass);
//	   BlockTab blocks = new BlockTab(glass);
	   
	   AllElementsTab all = new AllElementsTab(glass);
	   
	   getTabs().addAll(all);
   }

}
