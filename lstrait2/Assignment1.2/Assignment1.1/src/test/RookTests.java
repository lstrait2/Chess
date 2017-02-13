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
import pieces.Rook;
import player.Color;

public class RookTests {

	Board board;
	Rook blackRook;
	Rook whiteRook;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whiteRook = new Rook(board.getTile(0, 4), Color.WHITE);
		blackRook = new Rook(board.getTile(7, 3), Color.BLACK);
	}
	
	@Test
	public void TestConstructor() {
		// Rooks should inherit from Piece class
		assertTrue(whiteRook instanceof Piece);
		assertTrue(blackRook instanceof Piece);
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whiteRook.getColor());
		assertEquals(Color.BLACK, blackRook.getColor());
		// ensure tile is correctly assigned
		assertEquals(board.getTile(0, 4), whiteRook.getTile());
		assertEquals(board.getTile(7, 3), blackRook.getTile());
		// ensure tile occupant correctly references rooks
		assertEquals(board.getTile(0, 4).getOccupant(), whiteRook);
		assertEquals(board.getTile(7, 3).getOccupant(), blackRook);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		whiteRook.updateValidMoves(board);
		blackRook.updateValidMoves(board);
		// white Rook can move uninterrupted vertically and horizontally
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(0, 2));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(0, 5));
		whiteMoves.add(board.getTile(0, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(4, 4));
		whiteMoves.add(board.getTile(5, 4));
		whiteMoves.add(board.getTile(6, 4));
		whiteMoves.add(board.getTile(7, 4));
		// black Rook can move uninterrupted vertically and horizontally
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 0));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(7, 2));
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(7, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(4, 3));
		blackMoves.add(board.getTile(3, 3));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(1, 3));
		blackMoves.add(board.getTile(0, 3));
		assertEquals(whiteMoves, whiteRook.getValidMoves());
		assertEquals(blackMoves, blackRook.getValidMoves());
	}
	
	/*
	 * test that rook cannot jump over pieces horizontally.
	 */
	@Test
	public void TestValidMovesBlockedByPieceHorizontal() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(0, 5), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(7, 2), Color.BLACK);
		whiteRook.updateValidMoves(board);
		blackRook.updateValidMoves(board);
		// white Rook can move uninterrupted vertically and horizontally to right
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(0, 2));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(3, 4));
		whiteMoves.add(board.getTile(4, 4));
		whiteMoves.add(board.getTile(5, 4));
		whiteMoves.add(board.getTile(6, 4));
		whiteMoves.add(board.getTile(7, 4));
		// black Rook can move uninterrupted vertically and horizontally to right
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(7, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(4, 3));
		blackMoves.add(board.getTile(3, 3));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(1, 3));
		blackMoves.add(board.getTile(0, 3));
		assertEquals(whiteMoves, whiteRook.getValidMoves());
		assertEquals(blackMoves, blackRook.getValidMoves());
	}
	
	/*
	 * test that rook cannot jump over pieces vertically.
	 */
	@Test
	public void TestValidMovesBlockedByPieceVertical() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(1, 4), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(6, 3), Color.BLACK);
		whiteRook.updateValidMoves(board);
		blackRook.updateValidMoves(board);
		// white Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(0, 2));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(0, 5));
		whiteMoves.add(board.getTile(0, 6));
		whiteMoves.add(board.getTile(0, 7));
		// black Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 0));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(7, 2));
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(7, 6));
		blackMoves.add(board.getTile(7, 7));
		assertEquals(whiteMoves, whiteRook.getValidMoves());
		assertEquals(blackMoves, blackRook.getValidMoves());
	}
	
	/*
	 * test that rook can capture pieces it can reach.
	 */
	@Test
	public void TestValidMovesBlockedByCapturable() {
		// create pawns to block path
		Pawn blackPawn2 = new Pawn(board.getTile(1, 4), Color.BLACK);
		Pawn whitePawn2 = new Pawn(board.getTile(6, 3), Color.WHITE);
		whiteRook.updateValidMoves(board);
		blackRook.updateValidMoves(board);
		// white Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(0, 1));
		whiteMoves.add(board.getTile(0, 2));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(0, 5));
		whiteMoves.add(board.getTile(0, 6));
		whiteMoves.add(board.getTile(0, 7));
		whiteMoves.add(board.getTile(1, 4)); // should be able to move to space with capturable piece
		// black Rook can move uninterrupted horizontally, completely blocked vertically
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 0));
		blackMoves.add(board.getTile(7, 1));
		blackMoves.add(board.getTile(7, 2));
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(7, 6));
		blackMoves.add(board.getTile(7, 7));
		blackMoves.add(board.getTile(6, 3)); // should be able to move to space with capturable piece
		assertEquals(whiteMoves, whiteRook.getValidMoves());
		assertEquals(blackMoves, blackRook.getValidMoves());
	}
	
	/*
	 * test that rook can move horizontally, vertically until blocked.
	 */
	@Test
	public void TestValidMovesMiddleOfBoard() {
		Board boardFresh = new Board(8,8);
		Rook whiteRook2 = new Rook(boardFresh.getTile(4, 4), Color.WHITE);
		whiteRook2.updateValidMoves(boardFresh);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(boardFresh.getTile(4, 0));
		whiteMoves.add(boardFresh.getTile(4, 1));
		whiteMoves.add(boardFresh.getTile(4, 2));
		whiteMoves.add(boardFresh.getTile(4, 3));
		whiteMoves.add(boardFresh.getTile(4, 5));
		whiteMoves.add(boardFresh.getTile(4, 6));
		whiteMoves.add(boardFresh.getTile(4, 7));
		whiteMoves.add(boardFresh.getTile(1, 4));
		whiteMoves.add(boardFresh.getTile(2, 4));
		whiteMoves.add(boardFresh.getTile(3, 4));
		whiteMoves.add(boardFresh.getTile(0, 4));
		whiteMoves.add(boardFresh.getTile(5, 4));
		whiteMoves.add(boardFresh.getTile(6, 4));
		whiteMoves.add(boardFresh.getTile(7, 4));
		assertEquals(whiteMoves, whiteRook2.getValidMoves());
	}
	
	/* 
	 * test that validMoves is correctly updated after first move.
	 */
	@Test
	public void TestValidMovesAfterFirst() {
		Board boardFresh = new Board(8,8);
		Rook whiteRook2 = new Rook(boardFresh.getTile(4, 4), Color.WHITE);
		whiteRook2.updateValidMoves(boardFresh);
		whiteRook2.move(boardFresh.getTile(0, 4), boardFresh);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(boardFresh.getTile(0, 0));
		whiteMoves.add(boardFresh.getTile(0, 1));
		whiteMoves.add(boardFresh.getTile(0, 2));
		whiteMoves.add(boardFresh.getTile(0, 3));
		whiteMoves.add(boardFresh.getTile(0, 5));
		whiteMoves.add(boardFresh.getTile(0, 6));
		whiteMoves.add(boardFresh.getTile(0, 7));
		whiteMoves.add(boardFresh.getTile(1, 4));
		whiteMoves.add(boardFresh.getTile(2, 4));
		whiteMoves.add(boardFresh.getTile(3, 4));
		whiteMoves.add(boardFresh.getTile(4, 4));
		whiteMoves.add(boardFresh.getTile(5, 4));
		whiteMoves.add(boardFresh.getTile(6, 4));
		whiteMoves.add(boardFresh.getTile(7, 4));
		assertEquals(whiteMoves, whiteRook2.getValidMoves());
	}
}
