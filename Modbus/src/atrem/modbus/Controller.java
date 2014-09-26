package atrem.modbus;

public interface Controller {
	public abstract void removeRequest(Request request);

	public abstract void pauseRequest(Request request);

	public abstract void startRequest(Request request);

	public void addDeviceListener(DeviceListener listener);

	public void addRequest(Request request, RequestListener requestHandler);

}
