package atrem.modbus;

import frames.RequestFrame;
import frames.ResponseFrame;

public class FramePairs {
	@Override
	public String toString() {
		return "FramePair\n" + requestFrame + "\n" + responseFrame;
	}

	private RequestFrame requestFrame;
	private ResponseFrame responseFrame;

	public RequestFrame getRequestFrame() {
		return requestFrame;
	}

	public void setRequestFrame(RequestFrame requestFrame) {
		this.requestFrame = requestFrame;
	}

	public ResponseFrame getResponseFrame() {
		return responseFrame;
	}

	public void setResponseFrame(ResponseFrame responseFrame) {
		this.responseFrame = responseFrame;
	}

}
