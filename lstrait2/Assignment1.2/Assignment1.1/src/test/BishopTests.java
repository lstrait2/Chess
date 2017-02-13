package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Piece;
import player.Color;

public class BishopTests {

	Board board;
	Bishop blackBishop;
	Bishop whiteBishop;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whiteBishop = new Bishop(board.getTile(3, 4), Color.WHITE);
		blackBishop = new Bishop(board.getTile(4, 4), Color.BLACK);
	}
	
	@Test
	public void TestConstructor() {
		// Pawns should inherit from Piece class
		assertTrue(whiteBishop instanceof Piece);
		assertTrue(blackBishop instanceof Piece);
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whiteBishop.getColor());
		assertEquals(Color.BLACK, blackBishop.getColor());
		// ensure tile is correctly assigned
		assertEquals(board.getTile(3, 4), whiteBishop.getTile());
		assertEquals(board.getTile(4, 4), blackBishop.getTile());
		// ensure tile occupant correctly references bishops
		assertEquals(board.getTile(3, 4).getOccupant(), whiteBishop);
		assertEquals(board.getTile(4, 4).getOccupant(), blackBishop);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		whiteBishop.updateValidMoves(board);
		blackBishop.updateValidMoves(board);
		// white Bishop canmove uninterrupted diagonolly
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(4, 5));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 7));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(1, 2));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(1, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(6, 1));
		whiteMoves.add(board.getTile(7, 0));
		// black Bishop can move uninterrupted diagonolly
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(3, 3));
		blackMoves.add(board.getTile(2, 2));
		blackMoves.add(board.getTile(1, 1));
		blackMoves.add(board.getTile(0, 0));
		blackMoves.add(board.getTile(5, 5));
		blackMoves.add(board.getTile(6, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(6, 2));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(3, 5));
		blackMoves.add(board.getTile(2, 6));
		blackMoves.add(board.getTile(1, 7));	
		assertEquals(whiteMoves, whiteBishop.getValidMoves());
		assertEquals(blackMoves, blackBishop.getValidMoves());
	}
	
	/*
	 * Test if bishop cannot jump over pieces.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(4, 5), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(3, 3), Color.BLACK);
		whiteBishop.updateValidMoves(board);
		blackBishop.updateValidMoves(board);
		// white Bishop is blocked to top right
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(1, 2));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(1, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(6, 1));
		whiteMoves.add(board.getTile(7, 0));
		// black bishop is blocked to bottom left
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(5, 5));
		blackMoves.add(board.getTile(6, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(6, 2));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(3, 5));
		blackMoves.add(board.getTile(2, 6));
		blackMoves.add(board.getTile(1, 7));
		assertEquals(whiteMoves, whiteBishop.getValidMoves());
		assertEquals(blackMoves, blackBishop.getValidMoves());
	}
	
	/*
	 * Test if bishop can correctly capture pieces it can reach.
	 */
	@Test
	public void TestValidMovesBlockedByCapturable() {
		// create pawns to block path
		Pawn blackPawn2 = new Pawn(board.getTile(4, 5), Color.BLACK);
		Pawn whitePawn2 = new Pawn(board.getTile(3, 3), Color.WHITE);
		whiteBishop.updateValidMoves(board);
		blackBishop.updateValidMoves(board);
		// white Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(1, 2));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(1, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(4, 3));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(6, 1));
		whiteMoves.add(board.getTile(7, 0));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(4, 5)); // tile with capturable piece should be valid move
		// black Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(5, 5));
		blackMoves.add(board.getTile(6, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(6, 2));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(3, 5));
		blackMoves.add(board.getTile(2, 6));
		blackMoves.add(board.getTile(1, 7));
		blackMoves.add(board.getTile(3, 3)); // tile with capturable piece should be valid move
		assertEquals(whiteMoves, whiteBishop.getValidMoves());
		assertEquals(blackMoves, blackBishop.getValidMoves());
	}
	
	/*
	 * test if validMoves field does not contain moves that go off board.
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		Board boardFresh = new Board(8,8);
		Bishop whiteBishop2 = new Bishop(boardFresh.getTile(7, 4), Color.WHITE);
		whiteBishop2.updateValidMoves(boardFresh);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(boardFresh.getTile(6, 3));
		whiteMoves.add(boardFresh.getTile(5, 2));
		whiteMoves.add(boardFresh.getTile(4, 1));
		whiteMoves.add(boardFresh.getTile(3, 0));
		whiteMoves.add(boardFresh.getTile(6, 5));
		whiteMoves.add(boardFresh.getTile(5, 6));
		whiteMoves.add(boardFresh.getTile(4, 7));
		assertEquals(whiteMoves, whiteBishop2.getValidMoves());
	}
	
	/*
	 * Test if validMoves field updates correctly after a piece moves.
	 */
	@Test
	public void TestValidMovesAfterFirst() {
		Board boardFresh = new Board(8,8);
		Bishop whiteBishop2 = new Bishop(boardFresh.getTile(7, 4), Color.WHITE);
		whiteBishop2.updateValidMoves(boardFresh);
		whiteBishop2.move(boardFresh.getTile(6, 3), boardFresh);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(boardFresh.getTile(7, 4));
		whiteMoves.add(boardFresh.getTile(5, 2));
		whiteMoves.add(boardFresh.getTile(4, 1));
		whiteMoves.add(boardFresh.getTile(3, 0));
		whiteMoves.add(boardFresh.getTile(7, 2));
		whiteMoves.add(boardFresh.getTile(5, 4));
		whiteMoves.add(boardFresh.getTile(4, 5));
		whiteMoves.add(boardFresh.getTile(3, 6));
		whiteMoves.add(boardFresh.getTile(2, 7));
		assertEquals(whiteMoves, whiteBishop2.getValidMoves());
		
	}

}
