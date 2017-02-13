package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import player.Color;

public class QueenTests {

	Board board;
	Queen blackQueen;
	Queen whiteQueen;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whiteQueen = new Queen(board.getTile(4, 4), Color.WHITE);
	}
	
	@Test
	public void TestConstructor() {
		// Queens should inherit from Piece class
		assertTrue(whiteQueen instanceof Piece);
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whiteQueen.getColor());
		// ensure tile is correctly assigned
		assertEquals(board.getTile(4, 4), whiteQueen.getTile());
		// ensure tile occupant correctly references Queens
		assertEquals(board.getTile(4, 4).getOccupant(), whiteQueen);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		whiteQueen.updateValidMoves(board);
		// white Bishop can move uninterrupted diagonolly, horizontally, and vertically
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(4, 5));
		whiteMoves.add(board.getTile(4, 6));
		whiteMoves.add(board.getTile(4, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(4, 2));
		whiteMoves.add(board.getTile(4, 1));
		whiteMoves.add(board.getTile(4, 0));
		whiteMoves.add(board.getTile(5, 4));
		whiteMoves.add(board.getTile(6, 4));
		whiteMoves.add(board.getTile(7, 4));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));	
		assertEquals(whiteMoves, whiteQueen.getValidMoves());
	}
	
	/*
	 * test that queens cannot jump over pieces.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		// create pawn to block path
		Pawn whitePawn2 = new Pawn(board.getTile(5, 4), Color.WHITE);
		whiteQueen.updateValidMoves(board);
		// white Bishop can move uninterrupted diagonolly, horizontally, and vertically to top
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(4, 5));
		whiteMoves.add(board.getTile(4, 6));
		whiteMoves.add(board.getTile(4, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(4, 2));
		whiteMoves.add(board.getTile(4, 1));
		whiteMoves.add(board.getTile(4, 0));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));	
		assertEquals(whiteMoves, whiteQueen.getValidMoves());
	}
	
	/**
	 * test that queens can capture pieces they can reach.
	 */
	@Test
	public void TestValidMovesBlockedByCapturable() {
		// create pawn to block path
		Pawn whitePawn2 = new Pawn(board.getTile(5, 4), Color.BLACK);
		whiteQueen.updateValidMoves(board);
		// white Bishop can move uninterrupted diagonolly, horizontally, and vertically to top
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(4, 5));
		whiteMoves.add(board.getTile(4, 6));
		whiteMoves.add(board.getTile(4, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(4, 2));
		whiteMoves.add(board.getTile(4, 1));
		whiteMoves.add(board.getTile(4, 0));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));
		whiteMoves.add(board.getTile(5, 4)); // tile with capturable piece should be a valid move
		assertEquals(whiteMoves, whiteQueen.getValidMoves());
	}
	
	/*
	 * test that queens cannot move off of board.
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		board = new Board(8,8);
		whiteQueen = new Queen(board.getTile(0, 0), Color.WHITE);
		whiteQueen.updateValidMoves(board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(4, 4));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(0, 2));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(0, 5));
		whiteMoves.add(board.getTile(0, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(1, 0));
		whiteMoves.add(board.getTile(2, 0));
		whiteMoves.add(board.getTile(3, 0));
		whiteMoves.add(board.getTile(4, 0));
		whiteMoves.add(board.getTile(5, 0));
		whiteMoves.add(board.getTile(6, 0));
		whiteMoves.add(board.getTile(7, 0));
		
		assertEquals(whiteMoves, whiteQueen.getValidMoves());
	}
	
	/*
	 * test that validMoves updates correctly after a move.
	 */
	@Test
	public void TestValidMovesAfterFirstMove() {
		board = new Board(8,8);
		whiteQueen = new Queen(board.getTile(4, 3), Color.WHITE);
		whiteQueen.updateValidMoves(board);
		whiteQueen.move(board.getTile(4, 4), board);
		// moves aboves should all still be valid
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(4, 5));
		whiteMoves.add(board.getTile(4, 6));
		whiteMoves.add(board.getTile(4, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(4, 2));
		whiteMoves.add(board.getTile(4, 1));
		whiteMoves.add(board.getTile(4, 0));
		whiteMoves.add(board.getTile(5, 4));
		whiteMoves.add(board.getTile(6, 4));
		whiteMoves.add(board.getTile(7, 4));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));	
		assertEquals(whiteMoves, whiteQueen.getValidMoves());
	}

}
