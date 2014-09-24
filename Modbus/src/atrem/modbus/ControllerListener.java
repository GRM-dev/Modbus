package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;

public interface ControllerListener {
	public abstract void frameReceiver(ResponseFrame responseFrame);
}
