package atrem.modbus;

import frames.RequestFrame;
import frames.ResponseFrame;

public class FramePairs {

	private String separator = System.lineSeparator();

	@Override
	public String toString() {
		return "FramePair" + separator + separator + requestFrame + separator
				+ responseFrame;
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
