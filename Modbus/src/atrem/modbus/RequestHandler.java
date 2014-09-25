package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;

public interface RequestHandler {
	public abstract void frameReceiver(ResponseFrame responseFrame); // TODO nie
																		// rzeczownik
																		// w
																		// nazwie
}
