package UI.SideBar;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.Ball.BasketBall;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticPlane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ExampleScenes extends VBox{

	public ExampleScenes() {
		super(10);
		
		Label title = new Label("Beispiel Szenen");
		title.setAlignment(Pos.CENTER);
		
		Button ballPit = new Button("Bälle Bad");
		ballPit.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
			
				StaticPlane plane1 = new StaticPlane(-220, -120);
				plane1.setRotation(90);
				plane1.setOriginalrotation(90);
				
				StaticPlane plane2 = new StaticPlane(220, -120);
				plane2.setRotation(90);
				plane2.setOriginalrotation(90);
				
				StaticPlane plane3 = new StaticPlane(0, -220);
				plane3.setScale(1.2f);
				plane3.setOriginalscale(1.2f);
				
				for (int i = 0; i < 4; i++) {
					new BasketBall((float)Math.random()*200, (float)Math.random()*200);
					new BasketBall(-(float)Math.random()*200, (float)Math.random()*200);
				}		
			}
						
		});
		
		Button inclinedPlane = new Button("Schiefe Ebene");
		inclinedPlane.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
				BasketBall ball = new BasketBall(200, 120);
				ball.setScale(0.5f);
				ball.setOriginalscale(0.5f);
				ball.setAccelerationX(-1);
				ball.setOriginalAccelerationX(-1);
				
				new StaticPlane(200, 100);
				
				StaticPlane plane1 = new StaticPlane(28, 84);
				plane1.setRotation(10);
				plane1.setOriginalrotation(10);
				
				StaticPlane plane2 = new StaticPlane(-85,0);
				plane2.setRotation(-10);
				plane2.setOriginalrotation(-10);
				
				StaticPlane plane3 = new StaticPlane(100,-100);
				plane3.setRotation(45);
				plane3.setOriginalrotation(45);
				
				StaticPlane plane4 = new StaticPlane(-200,-150);
				plane4.setRotation(90);
				plane4.setOriginalrotation(90);
						
				StaticPlane plane5 = new StaticPlane(0,-250);
				plane5.setScale(0.9f);
				plane5.setOriginalscale(0.9f);
				
				StaticPlane plane6 = new StaticPlane(200,-150);
				plane6.setRotation(90);
				plane6.setOriginalrotation(90);
			}			
		});
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(title,ballPit,inclinedPlane);
		this.getStylesheets().add("file:res/css/ExampleScenes.css");  
	}
	
}
