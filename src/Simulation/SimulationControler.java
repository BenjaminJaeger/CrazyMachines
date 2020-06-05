package Simulation;

import java.util.Timer;
import java.util.TimerTask;

import Simulation.Objects.GameObject;

public class SimulationControler {

	private static int updateTime = 50;
	private static Timer simulationTimer;
	private static int counter = 0;
	private static boolean isPlaying;

	public static void setUpdateTime(int updateTime) {
		SimulationControler.updateTime=updateTime;
	}
	
	public static void pause() {
		counter = 0;
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
			    	counter += updateTime;
				      for (GameObject object : GameObject.allObjects) 
				    	  object.update();			      
				    }
				},10,updateTime);		
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
		return (float)updateTime/100;
	}
	
	public static boolean isPlaying() {
		return isPlaying;
	}

	public static float getTime() {
		return counter/1000;
	}

	public static void setCounter(int counter) {
		SimulationControler.counter = counter;
	}

}
