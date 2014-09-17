package atrem.modbus;

import java.util.Comparator;

public class FrameComparator implements Comparator<FrameIncoming> {

	@Override
	public int compare(FrameIncoming o1, FrameIncoming o2) {
		if (o1.getTransactionIdentifier() > o2.getTransactionIdentifier()) {
			return -1;
		} else if (o1.getTransactionIdentifier() < o2
				.getTransactionIdentifier()) {
			return 1;
		}
		return 0;

	}

}
