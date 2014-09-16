package atrem.modbus;

import java.nio.ByteBuffer;

public class FrameIncoming {
	private int transactionIdentifier;
	private int protocolIdentifier;
	private int unitIdentifier;
	private int dataLength;
	private ByteBuffer byteBuffer;

	public FrameIncoming(int transactionIdentifier, int protocolIdentifier,
			int unitIdentifier) {
		super();
		this.transactionIdentifier = transactionIdentifier;
		this.protocolIdentifier = protocolIdentifier;
		this.unitIdentifier = unitIdentifier;
	}

	public ByteBuffer getByteBuffer() {
		return byteBuffer;
	}

	public void setByteBuffer(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}
}
