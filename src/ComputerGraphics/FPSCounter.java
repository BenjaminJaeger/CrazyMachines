package ComputerGraphics;

public class FPSCounter {

	private static long nextSecond = System.currentTimeMillis() + 1000;
	private static int fps = 0;
	private static int framesInCurrentSecond = 0;
	

	public static void calculateFPS() {
		long currentTime =  System.currentTimeMillis();
		if (currentTime>nextSecond) {
			nextSecond+=1000;
			fps=framesInCurrentSecond;
			framesInCurrentSecond=0;
		}
		framesInCurrentSecond++;
	}
	     
	public static int getFPS() {
		return fps;
	}
	
}
