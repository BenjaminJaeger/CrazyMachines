package UI.LevelMenue;

import java.io.File;
import java.util.Scanner;

import UI.TabElements.BasketballTabElement;
import UI.TabElements.BucketTabElement;
import UI.TabElements.StaticPlaneTabElement;
import UI.TabElements.TabElement;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LevelTabPane extends HBox{

   public LevelTabPane(Pane glass,String level) {
	   this.getStyleClass().add("LevelTabPane");
	   this.getStylesheets().add("file:res/css/LevelTabPane.css");
	   
	   loadElements(glass, level);
   }
   
   public void loadElements(Pane glass,String level) {
	   try	{	
			
			Scanner sc = new Scanner(new File("res/levels/"+level+".txt"));

			while (sc.hasNext()){
				
				boolean playable = Boolean.parseBoolean(sc.nextLine());
				
				if(playable) {
					
					String type=sc.nextLine();
										
					TabElement element = null;
					
					switch (type) {
					case "BasketBall": {
						element = new BasketballTabElement(glass,1);
						break;
					}
					case "StaticPlane": {
						element = new StaticPlaneTabElement(glass, 1);
						break;
					}
					case "StaticBucket": {
						element = new BucketTabElement(glass, 1);
						break;
					}
					
					default:
						throw new IllegalArgumentException("Unexpected value: " + type);
					}
					
					this.getChildren().add(element);
														
				}else 	
					sc.nextLine();
				
				
				sc.nextLine();sc.nextLine();sc.nextLine();
				sc.nextLine();sc.nextLine();sc.nextLine();
			}
			
		}catch (Exception e) {e.printStackTrace();}
   }
  
}
