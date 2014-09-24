package atrem.modbus;

import java.io.File;
import java.net.URL;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class SoundPlayer extends Thread {

	private String desktopPath = "Sounds/";
	private String fileName;
	Player player;

	public SoundPlayer(String fileName) {
		this.fileName = fileName;
		player = createPlayer();
	}

	public void play() {
		run();
	}

	@Override
	public void run() {
		try {
			player.realize();
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Player createPlayer() {
		try {
			URL url = new File(desktopPath + fileName).toURI().toURL();
			MediaLocator locator = new MediaLocator(url);
			return Manager.createPlayer(locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new SoundPlayer("sound.mp3").play();
	}
}