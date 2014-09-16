package ConsoleServiceJUnitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ConsoleService.ConsoleInputService;

public class ConsoleInputTest {

	@Test
	public void portTest() {
		ConsoleInputService consoleInputService = new ConsoleInputService();
		int port = consoleInputService.insertPort();
		assertEquals(1234, port);
	}
}