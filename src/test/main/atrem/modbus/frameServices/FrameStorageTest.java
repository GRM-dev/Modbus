package atrem.modbus.frameServices;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrem.modbus.frames.RequestFrame;
import atrem.modbus.frames.services.FrameStorage;

public class FrameStorageTest {
	@Test
	public void hasNoResponseTest() {

		List<RequestFrame> sentFrames = new ArrayList<RequestFrame>();
		RequestFrame requestFrame = new RequestFrame(0, 0, 0, 0, 0);
		FrameStorage frameStorage = new FrameStorage(sentFrames);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(!frameStorage.hasNoResponse(requestFrame));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(!frameStorage.hasNoResponse(requestFrame));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(frameStorage.hasNoResponse(requestFrame));
	}

}
