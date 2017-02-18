package controller;

/**
 * MoveCommandManager - manage execution and undo of CommandMoves
 * @author Lance
 *
 */
public class MoveCommandManager {
	private MoveCommand lastCommand; // store last MoveCommand that was made.

	/**
	 * Constructor.
	 */
	public MoveCommandManager() {
		this.lastCommand = null;
	}

	/**
	 * Execute the given MoveCommand
	 * @param c the MoveCommand to execute
	 * @return 0 if move was successful, -1 otherwise
	 */
	public int executeCommand(MoveCommand c) {
		int ret = c.execute();
		// move was valid and completed
		if (ret == 0) {
			lastCommand = c;
		}
		return ret;
	}
	
	/**
	 * Check if a move is available to undo.
	 * 
	 * @return true if a move is available to undo.
	 */
	public boolean isUndoAvailable() {
		return lastCommand != null;
	}
	
	/**
	 * Undo the last move that was made.
	 */
	public void undo() {
		lastCommand.undo();
		lastCommand = null;
	}
}
