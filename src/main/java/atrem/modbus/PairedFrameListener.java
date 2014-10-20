package atrem.modbus;

import atrem.modbus.frames.services.FramePairs;

public interface PairedFrameListener {
	public abstract void onPairFrame(FramePairs framePairs);
}
