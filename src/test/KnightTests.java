package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.Bishop;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import player.Color;

public class KnightTests {

	Board board;
	Knight blackKnight;
	Knight whiteKnight;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		blackKnight = new Knight(board.getTile(4, 4), Color.BLACK);
		whiteKnight = new Knight(board.getTile(7, 7), Color.WHITE);
	}
	
	@Test
	public void TestConstructor() {
		// Knights should inherit from Piece class
		assertTrue(blackKnight instanceof Piece);
		assertTrue(whiteKnight instanceof Piece);
		// ensure colors are correctly assigned
		assertEquals(Color.BLACK, blackKnight.getColor());
		assertEquals(Color.WHITE, whiteKnight.getColor());
		// ensure tile is correctly assigned
		assertEquals(board.getTile(4, 4), blackKnight.getTile());
		assertEquals(board.getTile(7, 7), whiteKnight.getTile());
		// ensure tile occupant correctly references Knights
		assertEquals(board.getTile(4, 4).getOccupant(), blackKnight);
		assertEquals(board.getTile(7, 7).getOccupant(), whiteKnight);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		blackKnight.updateValidMoves(board);
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 5));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(2, 5));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(5, 6));
		blackMoves.add(board.getTile(3, 6));
		blackMoves.add(board.getTile(5, 2));
		blackMoves.add(board.getTile(3, 2));
		assertEquals(blackMoves, blackKnight.getValidMoves());
	}
	
	/*
	 * test if knight can not travel to tile with its own piece on it.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		Pawn blackPawn2 = new Pawn(board.getTile(6, 5), Color.BLACK);
		Pawn blackPawn3 = new Pawn(board.getTile(6, 3), Color.BLACK);
		blackKnight.updateValidMoves(board);
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(2, 5));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(5, 6));
		blackMoves.add(board.getTile(3, 6));
		blackMoves.add(board.getTile(5, 2));
		blackMoves.add(board.getTile(3, 2));
		assertEquals(blackMoves, blackKnight.getValidMoves());
	}
	
	/*
	 * test if knight can capture pieces.
	 */
	@Test
	public void TestValidMovesBlockedByCapturable() {
		Pawn blackPawn2 = new Pawn(board.getTile(6, 5), Color.WHITE);
		Pawn blackPawn3 = new Pawn(board.getTile(6, 3), Color.WHITE);
		blackKnight.updateValidMoves(board);
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(2, 5));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(5, 6));
		blackMoves.add(board.getTile(3, 6));
		blackMoves.add(board.getTile(5, 2));
		blackMoves.add(board.getTile(3, 2));
		blackMoves.add(board.getTile(6, 5)); // should still be a valid move if blocked by other color
		blackMoves.add(board.getTile(6, 3)); // should still be a valid move if blocked by other color
		assertEquals(blackMoves, blackKnight.getValidMoves());
	}
	
	/*
	 * test that knight can jump over pieces of both colors in path.
	 */
	@Test
	public void TestValidMovesJumpOver() {
		Pawn whitePawn = new Pawn(board.getTile(5, 4), Color.WHITE);
		Pawn blackPawn = new Pawn(board.getTile(5, 3), Color.BLACK);
		Queen whiteQueen = new Queen(board.getTile(5, 4), Color.WHITE);
		Queen blackQueen= new Queen(board.getTile(5, 3), Color.BLACK);
		Bishop whiteBishop = new Bishop(board.getTile(3, 5), Color.WHITE);
		Bishop blackBishop= new Bishop(board.getTile(4, 5), Color.BLACK);
		blackKnight.updateValidMoves(board);
		Set<Tile> blackMoves = new HashSet<Tile>();
		// all moves should still be reachable, Knight can jump over pieces of both colors
		blackMoves.add(board.getTile(2, 5));
		blackMoves.add(board.getTile(2, 3));
		blackMoves.add(board.getTile(5, 6));
		blackMoves.add(board.getTile(3, 6));
		blackMoves.add(board.getTile(5, 2));
		blackMoves.add(board.getTile(3, 2));
		blackMoves.add(board.getTile(6, 5)); 
		blackMoves.add(board.getTile(6, 3)); 
		assertEquals(blackMoves, blackKnight.getValidMoves());
	}
	
	/* 
	 * test that validMoves does not contain moves off board.
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		whiteKnight.updateValidMoves(board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 5));
		assertEquals(whiteMoves, whiteKnight.getValidMoves());
	}
	
	/*
	 * test that validMoves updates correctly after a move.
	 */
	@Test
	public void TestValidMovesAfterFirst() {
		blackKnight.updateValidMoves(board);
		blackKnight.move(board.getTile(2,5), board);
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(0, 4));
		blackMoves.add(board.getTile(0, 6));
		blackMoves.add(board.getTile(4, 4));
		blackMoves.add(board.getTile(4, 6));
		blackMoves.add(board.getTile(3, 7));
		blackMoves.add(board.getTile(3, 3));
		blackMoves.add(board.getTile(1, 7));
		blackMoves.add(board.getTile(1, 3));
		assertEquals(blackMoves, blackKnight.getValidMoves());
	}

}
