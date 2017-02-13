package test;
import static org.junit.Assert.*;


import game.Tile;
import org.junit.*;
import pieces.Pawn;
import player.Color;

public class TileTests {

	Tile tile;
	Tile tile2;
	Pawn blackPawn;
	
	@Before
	public void setUp() {
		tile = new Tile(5,5);
		tile2 = new Tile(7,7);
		blackPawn = new Pawn(tile2, Color.BLACK);
	}
	
	@Test
	public void TestConstructor() {
		assertEquals(tile.getRank(), 5);
		assertEquals(tile.getFile(), 5);
		assertEquals(tile.getOccupant(), null);
	}
	
	/*
	 * test that tile with no piece is empty.
	 */
	@Test
	public void TestIsEmptyTrue() {
		assertTrue(tile.isEmpty());
	}
	
	/*
	 * test that tile with a piece is not empty.
	 */
	@Test
	public void TestIsEmptyFalse() {
		assertFalse(tile2.isEmpty());
	}
	
	/*
	 * test that tile with black piece is occupiedByBlack
	 */
	@Test
	public void TestOccupiedBlack() {
		assertTrue(tile2.occupiedByBlack());
		assertFalse(tile2.occupiedByWhite());
	}
	
	/*
	 * test that tile with white piece is occupiedByWhite
	 */
	@Test
	public void TestOccupiedEmptyTile() {
		assertFalse(tile.occupiedByBlack());
		assertFalse(tile.occupiedByWhite());
	}

}
