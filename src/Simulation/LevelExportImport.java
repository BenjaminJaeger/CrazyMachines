package Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.Ball.BasketBall;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticBucket;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticPlane;

public class LevelExportImport {

	public static void ExportLevel(String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"/res/Levels/"+fileName+".txt");
			
			for (GameObject	object : GameObject.allObjects) {	
				String name = object.getClass().getSimpleName();
				file.write(name.getBytes());
				file.write("\n".getBytes());
				
				String position = object.getX() +"/"+object.getY();
				file.write(position.getBytes());
				file.write("\n".getBytes());
				
				String scale = Float.toString(object.getScale());
				file.write(scale.getBytes());
				file.write("\n".getBytes());
				
				String rotation = Float.toString(object.getRotation());
				file.write(rotation.getBytes());
				file.write("\n".getBytes());
				
				String mass =  Float.toString(object.getMass());
				file.write(mass.getBytes());
				file.write("\n".getBytes());
				
				String direction = "direction" + " TODO ";
				file.write(direction.getBytes());
				file.write("\n".getBytes());
				
				String speed = "speed" + " TODO ";
				file.write(speed.getBytes());
				file.write("\n".getBytes());
			}
			
			file.close();
			
		} catch (FileNotFoundException e1) {e1.printStackTrace();} catch (IOException e1) {e1.printStackTrace();}
	}
	
	public static void ImportLevel(String file) {
	try	{	
				
			Scanner sc = new Scanner(new File("res/levels/"+file+".txt"));

			while (sc.hasNext()){
				String type=sc.nextLine();
				String position = sc.nextLine();
				float x = Float.valueOf(position.split("/")[0]);
				float y = Float.valueOf(position.split("/")[1]);
				GameObject object = null;
				
				switch (type) {
				case "BasketBall": {
					object = new BasketBall(x,y);	
					break;
				}
				case "StaticPlane": {
					object = new StaticPlane(x,y);	
					break;
				}
				case "StaticBucket": {
					object = new StaticBucket(x,y);	
					break;
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
				
				float scale = Float.valueOf(sc.nextLine());
				float rotation = Float.valueOf(sc.nextLine());
				
				String massString = sc.nextLine();
				float mass;
				if(massString == "1.0E9")
					mass = 999999999;
				else 
					mass = Float.valueOf(massString);
			
				sc.nextLine();
				sc.nextLine();
								
				object.setScale(scale);
				object.setOriginalscale(scale);
				object.setRotation(rotation);
				object.setOriginalrotation(rotation);
				object.setMass(mass);
				object.setOriginalMass(mass);
			}
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
}
