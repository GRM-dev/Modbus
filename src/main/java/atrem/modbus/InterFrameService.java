package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;

public interface InterFrameService {
	public abstract void addResponseFrame(ResponseFrame responseFrame);
}
