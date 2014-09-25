package atrem.modbus;

import atrem.modbus.frameServices.FramePairs;

public interface PairedFrameListener {
	public abstract void onPairFrame(FramePairs framePairs);
}
