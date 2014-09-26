package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;
import atrem.modbus.swing.InterFrame;

public class RequestHandler implements RequestService {
	private Request request;
	private InterFrame interFrame;
	private Controller controller;

	public RequestHandler(Request request, Controller controller,
			InterFrame interFrame) {
		this.request = request;
		this.controller = controller;
		this.interFrame = interFrame;
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
		addRequest();
		controller.startNewRequestTask(request);

	}

	private void addRequest() {
		controller.addRequest(request, new RequestListener() {

			@Override
			public void receiveFrame(ResponseFrame responseFrame) {
				interFrame.addDataToTable(responseFrame);
			}
		});
	}
}
