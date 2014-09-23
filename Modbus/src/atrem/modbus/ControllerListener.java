package atrem.modbus;

import atrem.modbus.frames.RequestFrame;

public interface ControllerListener {
	public abstract void update(RequestFrame requestFrame);
}
