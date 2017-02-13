package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.BerolinaPawn;
import pieces.Piece;
import player.Color;

public class BerolinaPawnTests {
	Board board;
	BerolinaPawn blackPawn;
	BerolinaPawn whitePawn;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whitePawn = new BerolinaPawn(board.getTile(0, 0), Color.WHITE);
		blackPawn = new BerolinaPawn(board.getTile(7, 7), Color.BLACK);
	}
	
	/*
	 * Constructor should correctly populate fields
	 */
	@Test
	public void TestConstructor() {
		// BerolinaPawns should inherit from Piece class
		assertTrue(whitePawn instanceof Piece);
		assertTrue(blackPawn instanceof Piece);
		
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whitePawn.getColor());
		assertEquals(Color.BLACK, blackPawn.getColor());
		
		// ensure tile is correctly assigned
		assertEquals(board.getTile(0, 0), whitePawn.getTile());
		assertEquals(board.getTile(7, 7), blackPawn.getTile());
		
		// ensure tile occupant correctly references BerolinaPawn
		assertEquals(board.getTile(0, 0).getOccupant(), whitePawn);
		assertEquals(board.getTile(7, 7).getOccupant(), blackPawn);
	}
	
	/* 
	 * test that firstMove flag is set correctly on new BerolinaPawns.
	 */
	@Test
	public void TestFirstMoveStartGame() {
		assertTrue(whitePawn.getFirstMove());
		assertTrue(blackPawn.getFirstMove());
	}
	
	/*
	 * test that firstMove flag is flipped after a BerolinaPawn moves for first time.
	 */
	@Test
	public void TestFirstMoveFalse() {
		whitePawn.updateValidMoves(board);
		whitePawn.move(board.getTile(1,1), board);
		assertFalse(whitePawn.getFirstMove());
		assertTrue(blackPawn.getFirstMove());
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces and first move of a BerolinaPawn.
	 */
	@Test
	public void TestValidMovesNoCaptureFirstMove() {
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		// white berolinapawn should be able to move forward diagonally 1 or 2 spaces (since first move)
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(2, 2));
		// black berolinapawn should be able to move forward diagonally 1 or 2 spaces (since first move)
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 6));
		blackMoves.add(board.getTile(5, 5));
		
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test if validMoves works correctly for berolinapawns that have moved once.
	 */
	@Test
	public void TestValidMovesNoCaptureNoFirstMove() {
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// move both pieces to "turn off" first move
		whitePawn.move(board.getTile(1, 1), board);
		blackPawn.move(board.getTile(6, 6), board);
		// white berolinapawn should be able to move forward diagonally 1 space
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(2, 0));
		// black berolinapawn should be able to move forward diagonally 1 space
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(5, 5));
		blackMoves.add(board.getTile(5, 7));
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test that berolinapawns can capture directly forward.
	 */
	@Test 
	public void TestValidMovesCapturable() {
		// create pawns to capture
		BerolinaPawn BlackPawn2 = new BerolinaPawn(board.getTile(1, 0), Color.BLACK);
		BerolinaPawn whitePawn2 = new BerolinaPawn(board.getTile(6, 7), Color.WHITE);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// white pawn should be able to move forward diagonally 1 or 2 spaces (since first move), or capture new pawn directly forward
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 0));
		// black pawn should be able to move forward diagonally 1 or 2 spaces (since first move) or capture new pawn directly forward
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 6));
		blackMoves.add(board.getTile(5, 5));
		blackMoves.add(board.getTile(6, 7));
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test that berolinapawns can only move forward to unoccupied spaces.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		// create berolinapawns to block path
		BerolinaPawn whitePawn2 = new BerolinaPawn(board.getTile(1, 1), Color.WHITE);
		BerolinaPawn blackPawn2 = new BerolinaPawn(board.getTile(6, 6), Color.BLACK);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		// no valid moves should exist
		assertEquals(0, whitePawn.getValidMoves().size());
		assertEquals(0, blackPawn.getValidMoves().size());
	}
	
	/*
	 * test that berolinapawns can not capture in front on diagonal
	 */
	@Test
	public void TestValidMovesBlockedByPieceOtherColor() {
		// create berolinapawns to block path
		BerolinaPawn whitePawn2 = new BerolinaPawn(board.getTile(1, 1), Color.BLACK);
		BerolinaPawn blackPawn2 = new BerolinaPawn(board.getTile(6, 6), Color.WHITE);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// no valid moves should exist
		assertEquals(0, whitePawn.getValidMoves().size());
		assertEquals(0, blackPawn.getValidMoves().size());
	}
}
