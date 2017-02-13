package test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import player.Color;

public class KingTests {

	Board board;
	King blackKing;
	King whiteKing;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whiteKing = new King(board.getTile(1, 4), Color.WHITE);
		blackKing = new King(board.getTile(6, 4), Color.BLACK);
	}
	
	@Test
	public void TestConstructor() {
		// Kings should inherit from Piece class
		assertTrue(whiteKing instanceof Piece);
		assertTrue(blackKing instanceof Piece);
		
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whiteKing.getColor());
		assertEquals(Color.BLACK, blackKing.getColor());
		
		// ensure tile is correctly assigned
		assertEquals(board.getTile(1, 4), whiteKing.getTile());
		assertEquals(board.getTile(6, 4), blackKing.getTile());
		
		// ensure tile occupant correctly references kings
		assertEquals(board.getTile(1, 4).getOccupant(), whiteKing);
		assertEquals(board.getTile(6, 4).getOccupant(), blackKing);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		whiteKing.updateValidMoves(board);
		blackKing.updateValidMoves(board);

		// white king should be able to move in any surrounding tile
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 3));
		whiteMoves.add(board.getTile(1, 5));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(0, 5));
		// black king should be able to move in any surrounding tile
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(5, 4));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(6, 5));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(7, 3));
		blackMoves.add(board.getTile(5, 5));
		
		assertEquals(whiteMoves, whiteKing.getValidMoves());
		assertEquals(blackMoves, blackKing.getValidMoves());
	}
	
	/*
	 * Test if king cannot jump over pieces.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(0, 5), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(5, 5), Color.BLACK);
		whiteKing.updateValidMoves(board);
		blackKing.updateValidMoves(board);
		
		// white king should be able to move in any surrounding tile except (0,5)
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 3));
		whiteMoves.add(board.getTile(1, 5));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(2, 3));
		// black king should be able to move in any surrounding tile except (5,5)
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(5, 4));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(6, 5));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(7, 3));
		
		// All 7 moves for each king should be in validMoves
		assertEquals(whiteMoves, whiteKing.getValidMoves());
		assertEquals(blackMoves, blackKing.getValidMoves());
	}
	
	/* 
	 * test that validMoves does not contain moves off board.
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		whiteKing.updateValidMoves(board);
		blackKing.updateValidMoves(board);
		whiteKing.move(board.getTile(0, 4), board);
		blackKing.move(board.getTile(7, 4), board);
		
		// white king should be able to move in any surrounding tile except those off board
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 4));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(0, 5));
		whiteMoves.add(board.getTile(1, 5));
		whiteMoves.add(board.getTile(1, 3));
		// black king should be able to move in any surrounding tile except those off board
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 4));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(7, 3));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(6, 5));
				
		// All 5 moves for each king should be in validMoves
		assertEquals(whiteMoves, whiteKing.getValidMoves());
		assertEquals(blackMoves, blackKing.getValidMoves());
	}
	
	/*
	 * test that king can capture pieces it can reach.
	 */
	@Test
	public void TestValidMovesWithCapture() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(0, 5), Color.BLACK);
		Pawn blackPawn2 = new Pawn(board.getTile(5, 5), Color.WHITE);
		whiteKing.updateValidMoves(board);
		blackKing.updateValidMoves(board);
		
		// all 8 1-square moves should exist, pawn is of opposite color
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 3));
		whiteMoves.add(board.getTile(1, 5));
		whiteMoves.add(board.getTile(0, 4));
		whiteMoves.add(board.getTile(2, 4));
		whiteMoves.add(board.getTile(0, 3));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(0, 5));
		// all 8 1-square moves should exist, pawn is of opposite color
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(7, 4));
		blackMoves.add(board.getTile(5, 4));
		blackMoves.add(board.getTile(6, 3));
		blackMoves.add(board.getTile(6, 5));
		blackMoves.add(board.getTile(7, 5));
		blackMoves.add(board.getTile(5, 3));
		blackMoves.add(board.getTile(7, 3));
		blackMoves.add(board.getTile(5, 5));
		// All 7 moves for each king should be in validMoves
		assertEquals(whiteMoves, whiteKing.getValidMoves());
		assertEquals(blackMoves, blackKing.getValidMoves());
	}
	

}
