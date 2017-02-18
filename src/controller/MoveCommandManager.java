package controller;

import java.util.Stack;

/**
 * MoveCommandManager - manage execution and undo of CommandMoves
 * @author Lance
 *
 */
public class MoveCommandManager {
	private Stack<MoveCommand> prevCommands; // stack of commands that have been executed.

	/**
	 * Constructor.
	 */
	public MoveCommandManager() {
		this.prevCommands = new Stack<MoveCommand>();
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
			// push this command to top of stack
			prevCommands.push(c);
		}
		return ret;
	}
	
	/**
	 * Check if a move is available to undo.
	 * 
	 * @return true if a move is available to undo.
	 */
	public boolean isUndoAvailable() {
		return (prevCommands.size() != 0);
	}
	
	/**
	 * Undo the last move that was made.
	 */
	public void undo() {
		MoveCommand lastCommand = prevCommands.pop();
		lastCommand.undo();
	}
}
