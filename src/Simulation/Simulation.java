package Simulation;

import java.util.Timer;
import java.util.TimerTask;

public class Simulation {

	public static int updateTime = 10;
	
	public static void startSimulati() {
		
		Timer t = new Timer( );
		t.scheduleAtFixedRate(new TimerTask() {

		    @Override
		    public void run() {
		      System.out.println("Hey");

		    }
		}, 0,updateTime);
		
	}
}
