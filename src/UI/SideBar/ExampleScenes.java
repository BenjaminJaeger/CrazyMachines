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
		
		Button ballPit = new Button("B�lle Bad");
		ballPit.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
				new StaticPlane(0, -200);
				new StaticPlane(-150, -200);
				new StaticPlane(150, -200);
				StaticPlane plane1 = new StaticPlane(-220, -120);
				plane1.setRotation(90);
				plane1.setOriginalrotation(90);
				
				StaticPlane plane2 = new StaticPlane(220, -120);
				plane2.setRotation(90);
				plane2.setOriginalrotation(90);
			}
			
			for (int i = 0; i < 4; i++) {
				new BasketBall((float)Math.random()*200, (float)Math.random()*200);
				new BasketBall(-(float)Math.random()*200, (float)Math.random()*200);
			}			
		});
		
		Button inclinedPlane = new Button("Schiefe Ebene");
		inclinedPlane.setOnAction(e->{
			if(!SimulationControler.isPlaying()) {
				GameObject.allObjects.clear();
				BasketBall ball = new BasketBall(200, 115);
				ball.setScale(0.5f);
				ball.setOriginalscale(0.5f);
				ball.setAccelerationX(-1);
				ball.setOriginalAccelerationX(-1);
				
				new StaticPlane(200, 100);
				
				StaticPlane plane1 = new StaticPlane(60, 85);
				plane1.setRotation(10);
				plane1.setOriginalrotation(10);
				
				StaticPlane plane2 = new StaticPlane(-80,0);
				plane2.setRotation(-10);
				plane2.setOriginalrotation(-10);
				
				StaticPlane plane3 = new StaticPlane(100,-100);
				plane3.setRotation(45);
				plane3.setOriginalrotation(45);
				
				StaticPlane plane4 = new StaticPlane(-200,-150);
				plane4.setRotation(90);
				plane4.setOriginalrotation(90);
				
				new StaticPlane(-150,-230);				
				new StaticPlane(0,-230);
				new StaticPlane(150,-230);
				
				StaticPlane plane5 = new StaticPlane(200,-150);
				plane5.setRotation(90);
				plane5.setOriginalrotation(90);
			}			
		});
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(title,ballPit,inclinedPlane);
		this.getStylesheets().add("file:res/css/ExampleScenes.css");  
	}
	
}
