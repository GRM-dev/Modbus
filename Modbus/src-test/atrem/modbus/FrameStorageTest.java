package atrem.modbus;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import frames.RequestFrame;

public class FrameStorageTest {
	@Test
	public void hasNoResponseTest() {

		List<RequestFrame> sentFrames = new ArrayList<RequestFrame>();
		sentFrames.add(new RequestFrame(0, 0, 0, 0, 0));
		FrameStorage frameStorage = new FrameStorage(sentFrames);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(!frameStorage.hasNoResponse(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(!frameStorage.hasNoResponse(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		assertTrue(frameStorage.hasNoResponse(0));
	}

}
