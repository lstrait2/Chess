package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import game.Board;
import game.ChessGame;
import game.Tile;
import pieces.BerolinaPawn;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Princess;
import pieces.Queen;
import pieces.Rook;
import player.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChessGameTests {

	Board board;
	ChessGame chessGame;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		chessGame = new ChessGame(8,8,false);
		board = chessGame.getBoard();
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	/*
	 * test construction of 8x8 board.
	 */
	@Test
	public void TestConstructorSquare() {
		
		int ranks = 8;
		int files = 8;
		ChessGame chessGame = new ChessGame(ranks, files,false);
		Board board = chessGame.getBoard();
		Tile[][] tiles = board.getTiles();
		assertEquals(board.getRanks(), ranks);
		assertEquals(board.getFiles(), files);
		Piece piece;
		// check that bottom row has correct layout of white pieces: R, Knight, B, Q, King, B, Knight, R
		piece = tiles[0][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][4].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][5].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][6].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][7].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		// check that 2nd row from bottom is filled with white pawns
		for (int i = 0; i < files; i++) {
			piece = tiles[1][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.WHITE);
		}
		// check that top row has correct layout of black pieces: R, Knight, B, Q, King, B, Knight, R
		piece = tiles[7][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][4].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][5].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][6].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[7][7].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		// check that 2nd row from top is filled with black pawns
		for (int i = 0; i < files; i++) {
			piece = tiles[6][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.BLACK);
		}
		// check that middle rows are empty
		for (int i = 2; i < 6; i++){
			for (int j = 0; j < files; j++) {
				assertEquals(tiles[i][j].getOccupant(), null);
			}
		}
		// check that all pieces are stored in whitePieces/blackPieces ArrayLists
		assertEquals(chessGame.getWhitePieces().size(), 16);
		assertEquals(chessGame.getBlackPieces().size(), 16);
		// check that white and black king are correctly assigned
		assertTrue(chessGame.getWhiteKing() instanceof King);
		assertTrue(chessGame.getBlackKing() instanceof King);
		// white should have first move
		assertEquals(chessGame.getPlayerTurn(), Color.WHITE);
	}
	
	/*
	 * test construction of non-square board.
	 */
	@Test
	public void TestConstructorRectangle() {
		
		int ranks = 10;
		int files = 25;
		ChessGame chessGame = new ChessGame(ranks, files,false);
		Board board = chessGame.getBoard();
		Tile[][] tiles = board.getTiles();
		assertEquals(board.getRanks(), ranks);
		assertEquals(board.getFiles(), files);
		Piece piece;
		// check that bottom row has correct layout of white pieces: R, Knight, B, Q, King, B, Knight, R, [Empty Spaces]
		piece = tiles[0][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][4].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][5].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][6].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][7].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		// check that 2nd row from bottom is filled with white pawns then empty spaces.
		for(int i = 0; i < 8; i++) {
			piece = tiles[1][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.WHITE);
		}
		// check that top row has correct layout of black pieces: R, Knight, B, Q, King, B, Knight, R, [Empty Spaces]
		piece = tiles[ranks - 1][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][4].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][5].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][6].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][7].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		// check that 2nd row from top is filled with black pawns then empty spaces
		for(int i = 0; i < 8; i++) {
			piece = tiles[ranks - 2][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.BLACK);
		}
		// check that middle rows are empty
		for (int i = 2; i < ranks - 2; i++){
			for (int j = 0; j < files; j++) {
				assertEquals(tiles[i][j].getOccupant(), null);
			}
		}
		// check that all pieces are stored in whitePieces/blackPieces ArrayLists
		assertEquals(chessGame.getWhitePieces().size(), 16);
		assertEquals(chessGame.getBlackPieces().size(), 16);
		// white should have first move
		assertEquals(chessGame.getPlayerTurn(), Color.WHITE);
	}
	
	/*
	 * Test custom board creation works.
	 */
	@Test
	public void TestConstructorCustom() {
		int ranks = 9;
		int files = 9;
		ChessGame chessGame = new ChessGame(ranks, files,true);
		Board board = chessGame.getBoard();
		Tile[][] tiles = board.getTiles();
		assertEquals(board.getRanks(), ranks);
		assertEquals(board.getFiles(), files);
		Piece piece;
		// check that bottom row has correct layout of white pieces: R, Knight, B, Q, King, B, Knight, R
		piece = tiles[0][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][4].getOccupant();
		assertTrue(piece instanceof Princess);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][5].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][6].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][7].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.WHITE);
		piece = tiles[0][8].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.WHITE);
		// check that 2nd row from bottom is filled with white pawns
		for (int i = 0; i < files; i++) {
			piece = tiles[1][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.WHITE);
		}
		// check that top row has correct layout of black pieces: R, Knight, B, Q, King, B, Knight, R
		piece = tiles[ranks - 1][0].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][1].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][2].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][3].getOccupant();
		assertTrue(piece instanceof Queen);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][4].getOccupant();
		assertTrue(piece instanceof Princess);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][5].getOccupant();
		assertTrue(piece instanceof King);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][6].getOccupant();
		assertTrue(piece instanceof Bishop);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][7].getOccupant();
		assertTrue(piece instanceof Knight);
		assertEquals(piece.getColor(), Color.BLACK);
		piece = tiles[ranks - 1][8].getOccupant();
		assertTrue(piece instanceof Rook);
		assertEquals(piece.getColor(), Color.BLACK);
		// check that 2nd row from top is filled with black pawns
		for (int i = 0; i < files; i++) {
			piece = tiles[ranks - 2][i].getOccupant();
			assertTrue(piece instanceof Pawn);
			assertEquals(piece.getColor(), Color.BLACK);
		}
		// check that middle rows are empty
		for (int i = 2; i < 6; i++){
			for (int j = 0; j < files; j++) {
				assertEquals(tiles[i][j].getOccupant(), null);
			}
		}
		// check that all pieces are stored in whitePieces/blackPieces ArrayLists
		assertEquals(chessGame.getWhitePieces().size(), 18);
		assertEquals(chessGame.getBlackPieces().size(), 18);
		// check that white and black king are correctly assigned
		assertTrue(chessGame.getWhiteKing() instanceof King);
		assertTrue(chessGame.getBlackKing() instanceof King);
		// white should have first move
		assertEquals(chessGame.getPlayerTurn(), Color.WHITE);
	}
	/*
	 * test that white player gets first move
	 */
	@Test
	public void TestWhiteCanMoveFirst() {
		Piece whitePawn = board.getTile(1,1).getOccupant();
		Tile newTile = board.getTile(3,1);
		chessGame.whiteMove(whitePawn, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), whitePawn);
		// no error message should have been printed
		assertEquals("", outContent.toString());
	}
	
	/*
	 * test that black player cannot move at start of game.
	 */
	@Test
	public void TestBlackCannotMoveFirst() {
		Piece blackPawn = board.getTile(6,1).getOccupant();
		Tile newTile = board.getTile(5,1);
		chessGame.blackMove(blackPawn, newTile);
		// move should not have occurred
		assertNotEquals(newTile.getOccupant(), blackPawn);
		// error message should been printed
		assertEquals("Please wait for white to make their move first\n", outContent.toString());
		// it should still be white's turn
		assertEquals(Color.WHITE, chessGame.getPlayerTurn());
	}
	
	/*
	 * test that player cannot move piece of opposite color.
	 */
	@Test
	public void TestPlayerTriesToMoveWrongColor() {
		Piece blackPawn = board.getTile(6,1).getOccupant();
		Tile newTile = board.getTile(5,1);
		chessGame.whiteMove(blackPawn, newTile);
		// move should not have occurred
		assertNotEquals(newTile.getOccupant(), blackPawn);
		// error message should have printed
		assertEquals("Please only attempt to move your own pieces\n", outContent.toString());
		// it should still be white's turn
		assertEquals(Color.WHITE, chessGame.getPlayerTurn());
	}
	
	/*
	 * test that pieces cannot make an illegal move.
	 */
	@Test
	public void TestPlayerTriesIllegalMove() {
		Piece whitePawn = board.getTile(1,1).getOccupant();
		Tile newTile = board.getTile(4,1);
		chessGame.whiteMove(whitePawn, newTile);
		// move should have occurred
		assertNotEquals(newTile.getOccupant(), whitePawn);
		// no error message should have been printed
		assertEquals("Move was illegal, please attempt a valid move\n", outContent.toString());
	}
	
	/*
	 * test a small sequence of legal turns.
	 */
	@Test
	public void TestValidSequenceOfMoves() {
		// white does a move
		Piece whitePawn = board.getTile(1,3).getOccupant();
		Tile newTile = board.getTile(3,3);
		chessGame.whiteMove(whitePawn, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), whitePawn);
		assertEquals(board.getTile(1,3).getOccupant(), null);
		
		// black does a move
		Piece blackPawn = board.getTile(6,1).getOccupant();
		newTile = board.getTile(5,1);
		chessGame.blackMove(blackPawn, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), blackPawn);
		assertEquals(board.getTile(6,1).getOccupant(), null);
		
		// white does another move
		Piece whiteBishop = board.getTile(0,2).getOccupant();
		newTile = board.getTile(5, 7);
		chessGame.whiteMove(whiteBishop, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), whiteBishop);
		assertEquals(board.getTile(0,2).getOccupant(), null);
		
		// black does another move and captures whiteBishop
		Piece blackPawn2 = board.getTile(6, 6).getOccupant();
		newTile =  board.getTile(5, 7);
		chessGame.blackMove(blackPawn2, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), blackPawn2);
		assertEquals(board.getTile(6,6).getOccupant(), null);
		// white Bishop should have been removed from board
		assertEquals(whiteBishop.getTile(), null);
		
		// no error message should have been printed throughout sequence
		assertEquals("", outContent.toString());
	}
	
	/*
	 * test that an illegal turn is handled properly (player can try again, board unchanged)
	 */
	@Test
	public void TestSequenceOfMovesWithOneInvalid() {
		// white does a move
		Piece whitePawn = board.getTile(1,3).getOccupant();
		Tile newTile = board.getTile(3,3);
		chessGame.whiteMove(whitePawn, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), whitePawn);
		assertEquals(board.getTile(1,3).getOccupant(), null);

		// black does a move
		Piece blackPawn = board.getTile(6,1).getOccupant();
		newTile = board.getTile(5,1);
		chessGame.blackMove(blackPawn, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), blackPawn);
		assertEquals(board.getTile(6,1).getOccupant(), null);

		// white tries an invalid move, it shoudld fail
		Piece whiteBishop = board.getTile(0,2).getOccupant();
		newTile = board.getTile(6, 7);
		chessGame.whiteMove(whiteBishop, newTile);
		// move should not have occurred
		assertNotEquals(newTile.getOccupant(), whiteBishop);
		assertEquals(board.getTile(0,2).getOccupant(), whiteBishop);
		// should still be white's turn
		assertEquals(Color.WHITE, chessGame.getPlayerTurn());
		
		// white does a valid move now
		whiteBishop = board.getTile(0,2).getOccupant();
		newTile = board.getTile(5, 7);
		chessGame.whiteMove(whiteBishop, newTile);
		// move should have occurred
		assertEquals(newTile.getOccupant(), whiteBishop);
		assertEquals(board.getTile(0,2).getOccupant(), null);

		// black does another move and captures whiteBishop
		Piece blackPawn2 = board.getTile(6, 6).getOccupant();
		newTile =  board.getTile(5, 7);
		chessGame.blackMove(blackPawn2, newTile);
		// move should have occurred
		// no error message should have been printed throughout sequence
				
		assertEquals(newTile.getOccupant(), blackPawn2);
		assertEquals(board.getTile(6,6).getOccupant(), null);
		// white Bishop should have been removed from board
		assertEquals(whiteBishop.getTile(), null);

		// one error message should have been printed (after whites illegal move)
		assertEquals("Move was illegal, please attempt a valid move\n", outContent.toString());
		
	}
	
	
	/*
	 * test stalemate not improperly detected
	 */
	@Test
	public void TestNoStalemateStart() {
		assertFalse(chessGame.isStalemate());
	}
	
	/*
	 * test stalemate properly detected.
	 */
	@Test
	public void TestStaleMateExists() {
		chessGame = new ChessGame(8,8,false);
		board = chessGame.getBoard();
		// clear the board
		for (Piece p : chessGame.getWhitePieces()) {
			p.setTile(null);
			p.updateValidMoves(board);
		}
		for (Piece p : chessGame.getBlackPieces()) {
			p.setTile(null);
			p.updateValidMoves(board);
		}
		// set-up simple scenario
		Pawn whitePawn = new Pawn(board.getTile(4, 4), Color.WHITE);
		chessGame.getWhitePieces().add(whitePawn);
		Pawn blackPawn = new Pawn(board.getTile(5, 4), Color.BLACK);
		chessGame.getBlackPieces().add(blackPawn);
		// check stalemate and nobody wins
		assertTrue(chessGame.isStalemate());
		assertFalse(chessGame.playerWins(Color.WHITE));
		assertFalse(chessGame.playerWins(Color.BLACK));
		// no error messages should have been printed
		assertEquals("", outContent.toString());
	}
	
	/*
	 * test black king in check is properly detected.
	 */
	@Test
	public void TestCheckMateBlackKing() {
		// white moves pawn
		Piece currPiece = board.getTile(1, 6).getOccupant();
		Tile newTile = board.getTile(3, 6);
		chessGame.whiteMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(6, 4).getOccupant();
		newTile = board.getTile(4, 4);
		chessGame.blackMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(1, 5).getOccupant();
		newTile = board.getTile(2, 5);
		chessGame.whiteMove(currPiece, newTile);
		// black moves queen
		currPiece = board.getTile(7, 3).getOccupant();
		newTile = board.getTile(3, 7);
		chessGame.blackMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(1, 0).getOccupant();
		newTile = board.getTile(2, 0);
		chessGame.whiteMove(currPiece, newTile);
		// white king is in check and cannot move
		assertTrue(chessGame.kingInCheck(chessGame.getWhiteKing()));
		assertTrue(chessGame.playerWins(Color.BLACK));
		// no error messages should have been printed
		assertEquals("", outContent.toString());
	}
	
	/*
	 * test white king in check is properly detected.
	 */
	@Test
	public void TestCheckMateWhiteKing() {
		Piece currPiece = board.getTile(1, 2).getOccupant();
		Tile newTile = board.getTile(3, 2);
		chessGame.whiteMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(6, 7).getOccupant();
		newTile = board.getTile(4, 7);
		chessGame.blackMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(1, 7).getOccupant();
		newTile = board.getTile(3, 7);
		chessGame.whiteMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(6, 0).getOccupant();
		newTile = board.getTile(4, 0);
		chessGame.blackMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(0, 3).getOccupant();
		newTile = board.getTile(3, 0);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(7, 0).getOccupant();
		newTile = board.getTile(5, 0);
		chessGame.blackMove(currPiece, newTile);
		// black moves rook
		currPiece = board.getTile(3, 0).getOccupant();
		newTile = board.getTile(4, 0);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(5, 0).getOccupant();
		newTile = board.getTile(5, 7);
		chessGame.blackMove(currPiece, newTile);
		// black moves rook
		currPiece = board.getTile(4, 0).getOccupant();
		newTile = board.getTile(6, 2);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(6, 5).getOccupant();
		newTile = board.getTile(5, 5);
		chessGame.blackMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(6, 2).getOccupant();
		newTile = board.getTile(6, 3);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(7, 4).getOccupant();
		newTile = board.getTile(6, 5);
		chessGame.blackMove(currPiece, newTile);
		// black moves king 
		currPiece = board.getTile(6, 3).getOccupant();
		newTile = board.getTile(6, 1);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(7, 3).getOccupant();
		newTile = board.getTile(2, 3);
		chessGame.blackMove(currPiece, newTile);
		// black moves queen
		currPiece = board.getTile(6, 1).getOccupant();
		newTile = board.getTile(7, 1);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(2, 3).getOccupant();
		newTile = board.getTile(6, 7);
		chessGame.blackMove(currPiece, newTile);
		// black moves queen
		currPiece = board.getTile(7, 1).getOccupant();
		newTile = board.getTile(7, 2);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(6, 5).getOccupant();
		newTile = board.getTile(5, 6);
		chessGame.blackMove(currPiece, newTile);
		// black moves king
		currPiece = board.getTile(7, 2).getOccupant();
		newTile = board.getTile(5, 4);
		chessGame.whiteMove(currPiece, newTile);
		// white moves queen
		currPiece = board.getTile(5, 6).getOccupant();
		newTile = board.getTile(4, 6);
		chessGame.blackMove(currPiece, newTile);
		// black is now in check with no valid moves and lost 
		assertTrue(chessGame.kingInCheck(chessGame.getBlackKing()));
		assertTrue(chessGame.playerWins(Color.WHITE));
		// no error message should have been printed throughout sequence, all moves were legal
		assertEquals("", outContent.toString());	
	}
	
	/*
	 * test check is no longer detected if king moves to safety afterwards
	 */
	@Test
	public void TestCheckIntoEscape() {
		// white moves pawn
		Piece currPiece = board.getTile(1, 3).getOccupant();
		Tile newTile = board.getTile(2, 3);
		chessGame.whiteMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(6, 0).getOccupant();
		newTile = board.getTile(4, 0);
		chessGame.blackMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(1, 6).getOccupant();
		newTile = board.getTile(3, 6);
		chessGame.whiteMove(currPiece, newTile);
		// black moves pawn
		currPiece = board.getTile(6, 4).getOccupant();
		newTile = board.getTile(4, 4);
		chessGame.blackMove(currPiece, newTile);
		// white moves pawn
		currPiece = board.getTile(1, 5).getOccupant();
		newTile = board.getTile(2, 5);
		chessGame.whiteMove(currPiece, newTile);
		// black moves queen
		currPiece = board.getTile(7, 3).getOccupant();
		newTile = board.getTile(3, 7);
		chessGame.blackMove(currPiece, newTile);
		// white king is now in check
		assertTrue(chessGame.kingInCheck(chessGame.getWhiteKing()));
		// white moves king
		currPiece = board.getTile(0, 4).getOccupant();
		newTile = board.getTile(1, 3);
		chessGame.whiteMove(currPiece, newTile);
		// white king is no longer in check, game continues
		assertFalse(chessGame.kingInCheck(chessGame.getWhiteKing()));
		assertFalse(chessGame.playerWins(Color.BLACK));
		// no error messages should have been printed
		assertEquals("", outContent.toString());	
	}
}
