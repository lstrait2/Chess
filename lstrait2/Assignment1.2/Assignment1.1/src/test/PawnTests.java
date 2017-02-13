package test;
import static org.junit.Assert.*;

import game.Board;
import game.Tile;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import pieces.Pawn;
import pieces.Piece;
import player.Color;

public class PawnTests {
	
	Board board;
	Pawn blackPawn;
	Pawn whitePawn;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whitePawn = new Pawn(board.getTile(0, 0), Color.WHITE);
		blackPawn = new Pawn(board.getTile(7, 7), Color.BLACK);
	}
	
	@Test
	public void TestConstructor() {
		// Pawns should inherit from Piece class
		assertTrue(whitePawn instanceof Piece);
		assertTrue(blackPawn instanceof Piece);
		
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whitePawn.getColor());
		assertEquals(Color.BLACK, blackPawn.getColor());
		
		// ensure tile is correctly assigned
		assertEquals(board.getTile(0, 0), whitePawn.getTile());
		assertEquals(board.getTile(7, 7), blackPawn.getTile());
		
		// ensure tile occupant correctly references pawn
		assertEquals(board.getTile(0, 0).getOccupant(), whitePawn);
		assertEquals(board.getTile(7, 7).getOccupant(), blackPawn);
	}
	
	/* 
	 * test that firstMove flag is set correctly on new pawns.
	 */
	@Test
	public void TestFirstMoveStartGame() {
		assertTrue(whitePawn.getFirstMove());
		assertTrue(blackPawn.getFirstMove());
	}
	
	/*
	 * test that firstMove flag is flipped after a pawn moves for first time.
	 */
	@Test
	public void TestFirstMoveFalse() {
		whitePawn.updateValidMoves(board);
		whitePawn.move(board.getTile(1,0), board);
		assertFalse(whitePawn.getFirstMove());
		assertTrue(blackPawn.getFirstMove());
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces and first move of a pawn.
	 */
	@Test
	public void TestValidMovesNoCaptureFirstMove() {
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		// white pawn should be able to move forward 1 or 2 spaces (since first move)
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 0));
		whiteMoves.add(board.getTile(2, 0));
		// black pawn should be able to move forward 1 or 2 spaces (since first move)
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 7));
		blackMoves.add(board.getTile(5, 7));
		
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test if validMoves works correctly for pawns that have moved once.
	 */
	@Test
	public void TestValidMovesNoCaptureNoFirstMove() {
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// move both pieces to "turn off" first move
		whitePawn.move(board.getTile(1,0), board);
		blackPawn.move(board.getTile(6, 7), board);
		// white pawn should be able to move forward 1 space
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(2, 0));
		// black pawn should be able to move forward 1 space
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(5, 7));
		
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test that pawns can capture diagonally.
	 */
	@Test 
	public void TestValidMovesCapturable() {
		// create pawns to capture
		Pawn BlackPawn2 = new Pawn(board.getTile(1, 1), Color.BLACK);
		Pawn whitePawn2 = new Pawn(board.getTile(6, 6), Color.WHITE);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// white pawn should be able to move forward 1 or 2 spaces (since first move), or capture new pawn on diagonol
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(1, 0));
		whiteMoves.add(board.getTile(2, 0));
		whiteMoves.add(board.getTile(1, 1));
		// black pawn should be able to move forward 1 or 2 spaces (since first move) or capture new pawn on diagonol
		Set<Tile> blackMoves = new HashSet<Tile>();
		blackMoves.add(board.getTile(6, 7));
		blackMoves.add(board.getTile(5, 7));
		blackMoves.add(board.getTile(6, 6));
		assertEquals(whiteMoves, whitePawn.getValidMoves());
		assertEquals(blackMoves, blackPawn.getValidMoves());
	}
	
	/*
	 * test that pawns can only move forward to unoccupied spaces.
	 */
	@Test
	public void TestValidMovesBlockedByPiece() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(1, 0), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(6, 7), Color.BLACK);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		// no valid moves should exist
		assertEquals(0, whitePawn.getValidMoves().size());
		assertEquals(0, blackPawn.getValidMoves().size());
	}
	
	/*
	 * test that pawns can not capture in front
	 */
	@Test
	public void TestValidMovesBlockedByPieceOtherColor() {
		// create pawns to block path
		Pawn whitePawn2 = new Pawn(board.getTile(1, 0), Color.BLACK);
		Pawn blackPawn2 = new Pawn(board.getTile(6, 7), Color.WHITE);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		// no valid moves should exist
		assertEquals(0, whitePawn.getValidMoves().size());
		assertEquals(0, blackPawn.getValidMoves().size());
	}
	
	
	/*
	 * test that pawns cannot move off board.
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		// pawns are at edge of board, no moves available
		Pawn whitePawn2 = new Pawn(board.getTile(7, 0), Color.WHITE);
		Pawn blackPawn2 = new Pawn(board.getTile(0, 7), Color.BLACK);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		// no valid moves should exist
		assertEquals(0, whitePawn2.getValidMoves().size());
		assertEquals(0, blackPawn2.getValidMoves().size());
	}
}
