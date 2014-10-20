package atrem.modbus.frames.services;

import java.util.Comparator;

import atrem.modbus.frames.ResponseFrame;

public class FrameComparator implements Comparator<ResponseFrame> {

	@Override
	public int compare(ResponseFrame o1, ResponseFrame o2) {
		if (o1.getTransactionIdentifier() > o2.getTransactionIdentifier()) {
			return -1;
		} else if (o1.getTransactionIdentifier() < o2
				.getTransactionIdentifier()) {
			return 1;
		}
		return 0;

	}

}
