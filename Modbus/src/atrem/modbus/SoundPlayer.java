package atrem.modbus;

import java.io.File;
import java.net.MalformedURLException;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.media.Player;

public class SoundPlayer extends Thread {
	
	private String	desktopPath	= "Sounds/";
	private String	fileName;
	Player			player;
	
	public SoundPlayer(String fileName) {
		this.fileName = fileName;
	}
	
	public void play() {
		run();
	}
	
	@Override
	public void run() {
		String bip = fileName;
		Media hit = null;
		try {
			hit = new Media(new File(desktopPath + fileName).toURI().toURL().toString());
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
	
	public static void main(String[] args) {
		new JFXPanel();
		new SoundPlayer("connect_sound.mp3").play();
	}
}
