package UI;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

	public static MediaPlayer theme;
	public static MediaPlayer basketball;
		

	public static void stopSounds() {
		theme.stop();
	}
	
	public static void updateVolume() {
		theme.setVolume(Util.soundVolume);
		basketball.setVolume(Util.soundVolume);
	}

	public static void initSounds() {
		initMainTheme();
		initBasketBallSound();
	}
		
	public static void initMainTheme() {
		Media sound = new Media(new File("res/Sounds/theme.mp3").toURI().toString());
		theme = new MediaPlayer(sound);
		theme.setCycleCount(MediaPlayer.INDEFINITE);
		theme.setVolume(Util.soundVolume);
	}
	
	public static void playMainTheme() {
		theme.play();
	}
	
	public static void initBasketBallSound() {
		Media sound = new Media(new File("res/Sounds/basketball.wav").toURI().toString());
		basketball = new MediaPlayer(sound);
		basketball.setVolume(Util.soundVolume);
		basketball.setOnEndOfMedia(new Runnable() {		
			@Override
			public void run() {
				basketball.stop();
			}
		});
	}
	
	public static void playBasketBallSound() {
		basketball.play();
	}
	
	public static void playPlaySound() {
		Media sound = new Media(new File("res/Sounds/Start/00.mp3").toURI().toString());
		
		float random = (float)Math.random()*8f;
		
		if(random>7)
			sound = new Media(new File("res/Sounds/Start/01.mp3").toURI().toString());
		else if(random>6)
			sound = new Media(new File("res/Sounds/Start/07.mp3").toURI().toString());
		else if(random>5)
			sound = new Media(new File("res/Sounds/Start/06.mp3").toURI().toString());
		else if(random>4)
			sound = new Media(new File("res/Sounds/Start/05.mp3").toURI().toString());
		else if(random>3)
			sound = new Media(new File("res/Sounds/Start/04.mp3").toURI().toString());
		else if(random>2)
			sound = new Media(new File("res/Sounds/Start/03.mp3").toURI().toString());
		else if(random>1)
			sound = new Media(new File("res/Sounds/Start/02.mp3").toURI().toString());
		
		AudioClip play = new AudioClip(sound.getSource());
		play.setVolume(Util.soundVolume);
		play.play();
	}
	
	public static void playStopSound() {
		Media sound = new Media(new File("res/Sounds/Stop/00.mp3").toURI().toString());
		
		float random = (float)Math.random()*5f;
		
		if(random>4)
			sound = new Media(new File("res/Sounds/Stop/05.mp3").toURI().toString());
		else if(random>3)
			sound = new Media(new File("res/Sounds/Stop/04.mp3").toURI().toString());
		else if(random>2)
			sound = new Media(new File("res/Sounds/Stop/03.mp3").toURI().toString());
		else if(random>1)
			sound = new Media(new File("res/Sounds/Stop/02.mp3").toURI().toString());
		
		AudioClip stop = new AudioClip(sound.getSource());
		stop.setVolume(Util.soundVolume);
		stop.play();
	}
	
	public static void playDoneSound() {
		Media sound = new Media(new File("res/Sounds/done/00.mp3").toURI().toString());
		
		float random = (float)Math.random()*11f;
		
		if(random>10)
			sound = new Media(new File("res/Sounds/done/10.mp3").toURI().toString());
		else if(random>9)
			sound = new Media(new File("res/Sounds/done/09.mp3").toURI().toString());
		else if(random>8)
			sound = new Media(new File("res/Sounds/done/08.mp3").toURI().toString());
		else if(random>7)
			sound = new Media(new File("res/Sounds/done/01.mp3").toURI().toString());
		else if(random>6)
			sound = new Media(new File("res/Sounds/done/07.mp3").toURI().toString());
		else if(random>5)
			sound = new Media(new File("res/Sounds/done/06.mp3").toURI().toString());
		else if(random>4)
			sound = new Media(new File("res/Sounds/done/05.mp3").toURI().toString());
		else if(random>3)
			sound = new Media(new File("res/Sounds/done/04.mp3").toURI().toString());
		else if(random>2)
			sound = new Media(new File("res/Sounds/done/03.mp3").toURI().toString());
		else if(random>1)
			sound = new Media(new File("res/Sounds/done/02.mp3").toURI().toString());
		
		
		AudioClip done = new AudioClip(sound.getSource());
		done.setVolume(Util.soundVolume);
		done.play();
	}

}
