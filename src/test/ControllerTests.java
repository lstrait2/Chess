package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import controller.*;
import model.game.*;
import model.player.Color;
public class ControllerTests {

	ChessController controller;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUp() {
		controller = new ChessController(false);
		System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Test constructor correctly creates game state.
	 */
	@Test
	public void testConstructor() {
		ChessGame game = controller.getChessGame();
		// first turn correctly allocated
		assertEquals(game.getPlayerTurn(), Color.WHITE);
		// board correctly built
		assertEquals(game.getBoard().getRanks(), 8);
		assertEquals(game.getBoard().getFiles(), 8);
		// both players have zero wins to start
		assertEquals(controller.getWhitePlayer().getWins(), 0);
		assertEquals(controller.getBlackPlayer().getWins(), 0);	
	}
	
	/**
	 * Test restarting game correctly makes a fresh instance
	 */
	@Test
	public void testRestart() {
		// white makes a move.
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// now reset the game
		controller.restartGame();
		ChessGame game = controller.getChessGame();
		// both players should still have zero wins
		assertEquals(controller.getWhitePlayer().getWins(), 0);
		assertEquals(controller.getBlackPlayer().getWins(), 0);
		// fresh game should be built now - same size board as before.
		assertEquals(game.getBoard().getRanks(), 8);
		assertEquals(game.getBoard().getFiles(), 8);
		// white should have first move
		assertEquals(game.getPlayerTurn(), Color.WHITE);
		// all pieces should be back in first and last two rows
		for (int i = 2; i < game.getBoard().getRanks() - 2; i++) {
			for (int j = 0; j < game.getBoard().getFiles(); j++) {
				assertTrue(game.getBoard().getTile(i,j).getOccupant() == null);
			}
		}
		// first two and last two rows should be filled with pieces
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < game.getBoard().getFiles(); j++) {
				assertTrue(game.getBoard().getTile(i,j).getOccupant() != null);
				assertTrue(game.getBoard().getTile(game.getBoard().getRanks() - i - 1,j).getOccupant() != null);
			}
		}
	}
	
	/**
	 * Test restarting game correctly makes a fresh instance and increments winners score counter
	 */
	@Test
	public void testForfeit() {
		// white player forfeits
		controller.forfeitGame(controller.getWhitePlayer());
		ChessGame game = controller.getChessGame();
		// black player should have a win now
		assertEquals(controller.getWhitePlayer().getWins(), 0);
		assertEquals(controller.getBlackPlayer().getWins(), 1);
		// fresh game should be built now - same size board as before.
		assertEquals(game.getBoard().getRanks(), 8);
		assertEquals(game.getBoard().getFiles(), 8);
		// white should have first move
		assertEquals(game.getPlayerTurn(), Color.WHITE);
		// all pieces should be back in first and last two rows
		for (int i = 2; i < game.getBoard().getRanks() - 2; i++) {
			for (int j = 0; j < game.getBoard().getFiles(); j++) {
				assertTrue(game.getBoard().getTile(i,j).getOccupant() == null);
			}
		}
		// first two and last two rows should be filled with pieces
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < game.getBoard().getFiles(); j++) {
				assertTrue(game.getBoard().getTile(i,j).getOccupant() != null);
				assertTrue(game.getBoard().getTile(game.getBoard().getRanks() - i - 1,j).getOccupant() != null);
			}
		}
	}
	
	/**
	 * Test forfeitting on Black's turn.
	 */
	@Test
	public void testForfeitBlack() {
		// white makes a move.
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// black forfeits now
		controller.forfeitGame(controller.getBlackPlayer());
		// white player should have a win now
		assertEquals(controller.getWhitePlayer().getWins(), 1);
		assertEquals(controller.getBlackPlayer().getWins(), 0);
		
	}
	
	/**
	 * Test a move received from the player is actually executed if valid.
	 */
	@Test
	public void testReceivedMoveValid() {
		// move a white pawn forward.
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
								controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// piece should now be moved - Commands are tested seperately
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() == null);
		assertTrue(controller.getChessGame().getBoard().getTile(2,1).getOccupant() != null);
		// player turn should be updated - now is Blacks turn
		assertEquals(controller.getCurrPlayer(), controller.getBlackPlayer());
	}
	
	/**
	 * Test a move received from the player is actually executed if valid.
	 */
	@Test
	public void testReceivedMoveInvalid() {
		// attempt to move a white pawn forward.
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
								controller.getChessGame().getBoard().getTile(4,1).getTileGUI());
		// piece should not be moved - Commands are tested seperately
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() != null);
		assertTrue(controller.getChessGame().getBoard().getTile(4,1).getOccupant() == null);
		// should still be whites turn
		assertEquals(controller.getCurrPlayer(), controller.getWhitePlayer());
	}
	
	/**
	 * Test no win conditions exist at beginning of game
	 */
	@Test
	public void testWinConditions() {
		assertFalse(controller.winConditionPresent());
	}
	
	/**
	 * Test No win condition found after long game, escape check
	 */
	@Test
	public void testNoWinConditionsLong() {
		// make a sequence of game moves
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,3).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,3).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(6,0).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(4,0).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,6).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(3,6).getTileGUI());
		
		controller.receivedMove(controller.getChessGame().getBoard().getTile(6,4).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(4,4).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,5).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,5).getTileGUI());
		// King now in Check
		controller.receivedMove(controller.getChessGame().getBoard().getTile(7,3).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(3,7).getTileGUI());
		assertTrue(controller.getChessGame().whiteKingInCheck());
		// King moves out of check
		controller.receivedMove(controller.getChessGame().getBoard().getTile(0,4).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(1,3).getTileGUI());
		// game should keep going
		assertFalse(controller.getChessGame().playerWins(Color.BLACK));
	}
	
	/**
	 * Test a Game is ended with CheckMate
	 */
	@Test
	public void testCheckMateEndsGame() {
		// make series of moves
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,6).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(3,6).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(6,4).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(4,4).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,5).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,5).getTileGUI());
		
		controller.receivedMove(controller.getChessGame().getBoard().getTile(7,3).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(3,7).getTileGUI());
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,0).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,0).getTileGUI());
		// white king should now be in checkmate. Game should have restarted and black added a win
		assertEquals(controller.getBlackPlayer().getWins(), 1);
		assertEquals(controller.getWhitePlayer().getWins(), 0);
		
	}

}
