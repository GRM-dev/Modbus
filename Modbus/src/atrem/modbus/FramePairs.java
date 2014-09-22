package atrem.modbus;

import frames.RequestFrame;
import frames.ResponseFrame;

public class FramePairs {

	private String separator = System.lineSeparator();

	private RequestFrame requestFrame;
	private ResponseFrame responseFrame;

	public FramePairs(RequestFrame requestFrame, ResponseFrame responseFrame) {
		this.requestFrame = requestFrame;
		this.responseFrame = responseFrame;
	}

	@Override
	public String toString() {
		return "FramePair" + separator + separator + requestFrame + separator
				+ responseFrame;
	}

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
