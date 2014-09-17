package atrem.modbus;

import java.nio.ByteBuffer;

public class Assembler {
	ByteBuffer byteBuffer;
	public void measure(byte[] bytes) { // dla temperatury na razie
		
		byteBuffer.put(bytes);
		int l1 = byteBuffer.getInt();
		System.out.println("mo¿liwosci ukladu bajtow: ");
		System.out.println(l1);
		int l2 = orderOfBytes(3, 2, 1, 0, bytes);
		System.out.println(l2);
		int l3 = orderOfBytes(3, 1, 2, 0, bytes);
		System.out.println(l3);
		int l4 = orderOfBytes(0, 2, 1, 3, bytes);
		System.out.println(l4);
		
		
		

	}
	
	public int orderOfBytes(int a, int b, int c, int d,byte [] bytes)
	{
		byteBuffer.clear();
		byteBuffer.put(bytes[a]);
		byteBuffer.put(bytes[b]);
		byteBuffer.put(bytes[c]);
		byteBuffer.put(bytes[d]);
		return byteBuffer.getInt();
	}
		
		
	}
}
