package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;

public interface RequestListener {
	public abstract void receiveFrame(ResponseFrame responseFrame); // TODO nie
																		// rzeczownik
																		// w
																		// nazwie
}
