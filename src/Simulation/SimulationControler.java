package Simulation;

import java.util.Timer;
import java.util.TimerTask;

import Simulation.Objects.GameObject;

public class SimulationControler {

	private static int updateTime = 5;
	private static Timer simulationTimer;
	private static boolean isPlaying;

	public static void setUpdateTime(int updateTime) {
		SimulationControler.updateTime=updateTime;
	}
	
	public static void pause() {
		if(isPlaying) {
			isPlaying=false;
			simulationTimer.cancel();
		}	
	}
	
	public static void play() {
		if(!isPlaying) {
			isPlaying=true;
			simulationTimer = new Timer();
			simulationTimer.scheduleAtFixedRate(new TimerTask() {
			    public void run() {
				      for (GameObject object : GameObject.allObjects) 
				    	  object.update();			      				    				      
				    }
				},10,5);		
		}
		
	}
	
	public static void restart() {
		 for (GameObject object : GameObject.allObjects) 
	    	  object.reset();
	}
	
	public static int getUpdateTime() {
		return updateTime;
	}
	
	public static float getUpdateTimeInSeconds() {
		return (float)updateTime/1000;
	}
	
	public static boolean isPlaying() {
		return isPlaying;
	}

}
