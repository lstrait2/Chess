package test;

import static org.junit.Assert.*;

import org.junit.Test;
import model.player.*;

public class PlayerTests {
	
	/**
	 * Test constructor initializes state correctly.
	 */
	@Test
	public void testConstructor() {
		Player p = new Player(Color.BLACK);
		// test color and wins correctly set
		assertEquals(p.getColor(), Color.BLACK);
		assertEquals(p.getWins(), 0);
	}
	
	/**
	 * Test incrementWins works as expected()
	 */
	@Test
	public void testIncrementWins() {
		Player p = new Player(Color.WHITE);
		p.incrementWins();
		// wins should now be 1
		assertEquals(p.getWins(), 1);
	}
}
