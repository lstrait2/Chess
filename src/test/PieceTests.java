package test;
import static org.junit.Assert.*;

import game.Board;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
import pieces.Pawn;
import pieces.Rook;
import player.Color;

public class PieceTests {
	
	Board board;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
	}
	
	/*
	 * test that captured pieces are removed from board properly
	 */
	@Test
	public void TestCapturedPieceRemoved() {
		Pawn whitePawn = new Pawn(board.getTile(0, 0), Color.WHITE);
		Pawn blackPawn = new Pawn(board.getTile(7, 7), Color.BLACK);
		Pawn blackPawn2 = new Pawn(board.getTile(1, 1), Color.BLACK);
		Pawn whitePawn2 = new Pawn(board.getTile(6, 6), Color.WHITE);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		
		whitePawn.move(board.getTile(1,1), board);
		blackPawn.move(board.getTile(6,6), board);
		
		// check that whitepawn2 and blackpawn2 were removed from board
		assertEquals(whitePawn2.getTile(), null);
		assertEquals(blackPawn2.getTile(), null);
		// check that whitePawn and blackPawn moved 1 space diagnolly
		assertEquals(whitePawn.getTile(), board.getTile(1, 1));
		assertEquals(blackPawn.getTile(), board.getTile(6, 6));
		assertEquals(blackPawn, board.getTile(6, 6).getOccupant());
		assertEquals(whitePawn, board.getTile(1, 1).getOccupant());
		// check spaces occupied by whitePawn and blackPawn are now empty
		assertEquals(board.getTile(7, 7).getOccupant(), null);
		assertEquals(board.getTile(0, 0).getOccupant(), null);
		
	}
	
	/* test that tiles are updated properly after a normal move (no capture)
	 */
	@Test
	public void TestMoveNoCapture() {
		Pawn whitePawn = new Pawn(board.getTile(0, 0), Color.WHITE);
		Pawn blackPawn = new Pawn(board.getTile(7, 7), Color.BLACK);
		whitePawn.updateValidMoves(board);
		blackPawn.updateValidMoves(board);
		whitePawn.move(board.getTile(1,0), board);
		blackPawn.move(board.getTile(6,7), board);
		// check that whitePawn and blackPawn moved 1 space diagnolly
		assertEquals(whitePawn.getTile(), board.getTile(1, 0));
		assertEquals(blackPawn.getTile(), board.getTile(6, 7));
		// check spaces occupied by whitePawn and blackPawn are now empty
		assertEquals(board.getTile(7, 7).getOccupant(), null);
		assertEquals(board.getTile(0, 0).getOccupant(), null);
	}
	
	/*
	 * test pieces cannot make an invalid move.
	 */
	@Test
	public void InvalidMoveNotAllowed() {
		Pawn whitePawn = new Pawn(board.getTile(0, 0), Color.WHITE);
		whitePawn.updateValidMoves(board);
		whitePawn.move(board.getTile(0,1), board);
		// check that whitePawn did not move
		assertEquals(whitePawn.getTile(), board.getTile(0, 0));
		// check space occupied by whitePawn did not change
		assertEquals(board.getTile(0, 0).getOccupant(), whitePawn);
		// check that feedback was presented to user
	}
	
	/*
	 * test pieces cannot make an invalid move off the board.
	 */
	@Test
	public void InvalidMoveOffBoardNotAllowed() {
		Rook whiteRook = new Rook(board.getTile(0, 0), Color.WHITE);
		whiteRook.updateValidMoves(board);
		whiteRook.move(board.getTile(0,-1), board);
		// check that whitePawn did not move
		assertEquals(whiteRook.getTile(), board.getTile(0, 0));
		// check space occupied by whitePawn did not change
		assertEquals(board.getTile(0, 0).getOccupant(), whiteRook);
		// check that feedback was presented to user
	}
	/*
	 * test pieces can be removed from board.
	 */
	@Test
	public void RemovePieceFromBoard() {
		Pawn whitePawn = new Pawn(board.getTile(0, 0), Color.WHITE);
		whitePawn.updateValidMoves(board);
		whitePawn.movePiece(null);
		
		assertEquals(whitePawn.getTile(), null);
		// check that old tile is no longer occupied
		assertEquals(board.getTile(0, 0).getOccupant(), null);
	}
}
