package test;
import static org.junit.Assert.*;

import org.junit.Test;
import game.Board;

public class BoardTests {

	@Test
	public void TestConstructorSquare() {
		int ranks = 8;
		int files = 8;
		Board board = new Board(ranks,files);
		assertEquals(ranks, board.getTiles().length);
		assertEquals(files, board.getTiles()[0].length);
		
	}
	
	@Test
	public void TestConstructorRectangle() {
		int ranks = 14;
		int files = 2;
		Board board = new Board(ranks,files);
		assertEquals(ranks, board.getTiles().length);
		assertEquals(files, board.getTiles()[0].length);	
	}
	
	@Test
	public void TestGetTileValid() {
		int ranks = 8;
		int files = 8;
		Board board = new Board(ranks,files);
		assertEquals(board.getTile(0, 0), board.getTiles()[0][0]);
	}
	
	@Test
	public void TestGetTileInvalidPositive() {
		int ranks = 8;
		int files = 8;
		Board board = new Board(ranks,files);
		assertEquals(board.getTile(8, 8), null);
	}
	
	@Test
	public void TestGetTileInvalidNegative() {
		int ranks = 8;
		int files = 8;
		Board board = new Board(ranks,files);
		assertEquals(board.getTile(-1, 4), null);
	}
	
	@Test
	public void TestGetTileInvalidFile() {
		int ranks = 8;
		int files = 8;
		Board board = new Board(ranks,files);
		assertEquals(board.getTile(1, 9), null);
	}


}
