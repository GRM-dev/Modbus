package atrem.modbus;

import atrem.modbus.frames.ResponseFrame;
import atrem.modbus.swing.InterFrame;

public class RequestHandler {
	private Request request;
	private Controller controller;

	public RequestHandler(Controller controller, InterFrame interFrame) {
		this.controller = controller;
		this.interFrame = interFrame;
	}

	private InterFrame interFrame;

	public void addRequest() {
		controller.addRequest(request, new RequestListener() {

			@Override
			public void receiveFrame(ResponseFrame responseFrame) {
				interFrame.addDataToTable(responseFrame);
			}
		});
	}
}
