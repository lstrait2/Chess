package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import controller.*;

public class CommandTests {

	ChessController controller;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	MoveCommandManager manager;
	
	@Before
	public void setUp() {
		controller = new ChessController(false);
		System.setOut(new PrintStream(outContent));
		manager = controller.getMoveCommandManager();
	}
	
	/**
	 * Undo feature unavailable until a piece is moved.
	 */
	@Test
	public void testUndoInitiallyUnvailable() {
		assertFalse(manager.isUndoAvailable());
	}
	
	/**
	 * Undo feature available after a move is made.
	 */
	@Test
	public void testUndoAvailableAfterMove() {
		// move a white pawn forward.
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// undo should now be available
		assertTrue(manager.isUndoAvailable());
	}
	
	/**
	 * Test undo actually undoes the move.
	 */
	@Test
	public void testUndoWorks() {
		// make a move
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// undo the move
		controller.undoMove();
		// piece should be back in original location
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() != null);
		assertTrue(controller.getChessGame().getBoard().getTile(2,1).getOccupant() == null);
		// pawn should have firstMove flag reset
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant().getFirstMove());
		// player turn should be reset as well
		assertEquals(controller.getCurrPlayer(), controller.getWhitePlayer());
		// undo should now be unavailable
		assertFalse(manager.isUndoAvailable());
	}
	
	/**
	 * Test attempting unavailable undo does not crash system
	 */
	@Test
	public void noUndoHandledGracefully() {
		// attempt an undo even though none is available.
		controller.undoMove();
		// now attempt a normal move
		controller.receivedMove(controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		// move should have worked fine
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() == null);
		assertTrue(controller.getChessGame().getBoard().getTile(2,1).getOccupant() != null);
	}
	
	/**
	 * Test Exec works for valid move.
	 */
	@Test
	public void testExecCommandWorks() {
		// generate a move
		MoveCommand move = new MoveCommand(controller.getChessGame(), controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(2,1).getTileGUI());
		manager.executeCommand(move);
		// move should have worked
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() == null);
		assertTrue(controller.getChessGame().getBoard().getTile(2,1).getOccupant() != null);
	}
	
	/**
	 * Test Exec can handle invalid moves
	 */
	@Test
	public void testExecCommandWorksInvalid() {
		// generate an invalid move
		MoveCommand move = new MoveCommand(controller.getChessGame(), controller.getChessGame().getBoard().getTile(1,1).getTileGUI(), 
				controller.getChessGame().getBoard().getTile(4,1).getTileGUI());
		manager.executeCommand(move);
		// move should not have worked
		assertTrue(controller.getChessGame().getBoard().getTile(1,1).getOccupant() != null);
		assertTrue(controller.getChessGame().getBoard().getTile(4,1).getOccupant() == null);
	}
	
}
