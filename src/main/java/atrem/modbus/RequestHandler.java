package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;
import atrem.modbus.swing.InterFrame;

public class RequestHandler implements RequestService, InterFrameService {
	private Request request;
	private InterFrameService interFrame;
	private Controller controller;

	public RequestHandler(Request request, Controller controller,
			InterFrame interFrame) {
		this.request = request;
		this.controller = controller;
		this.interFrame = interFrame;
		controller.addRequestHandler(request, this);
	}

	@Override
	public void removeRequest() {
		controller.removeRequest(request);

	}

	@Override
	public void pauseRequest() {
		controller.pauseRequest(request);

	}

	@Override
	public void startRequest() {

		controller.startRequest(request);

	}

	@Override
	public void addResponseFrame(ResponseFrame responseFrame) {
		interFrame.addResponseFrame(responseFrame);

	}
}
