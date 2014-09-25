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
	
	public static void play(String path) {
		new JFXPanel();
		new SoundPlayer(path).run();
		;
	}
	
	@Override
	public void run() {
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
		SoundPlayer.play("connect_sound.mp3");
	}
}