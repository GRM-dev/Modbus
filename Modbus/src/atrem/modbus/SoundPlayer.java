package atrem.modbus;

import java.io.File;
import java.net.URL;

public class AudioFilePlayer extends Thread {

	private String desktopPath = System.getProperty("user.home") + "/Desktop/";
	private String fileName;
	Player player;

	public AudioFilePlayer(String fileName) {
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
		new AudioFilePlayer("sound.mp3").play();
	}
}