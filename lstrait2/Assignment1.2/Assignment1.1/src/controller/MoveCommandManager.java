package controller;

public class MoveCommandManager {
	private Command lastCommand;

	public MoveCommandManager() {
		this.lastCommand = null;
	}

	public int executeCommand(MoveCommand c) {
		int ret = c.execute();
		// move was valid and completed
		if (ret == 0) {
			lastCommand = c;
		}
		return ret;
	}

	public boolean isUndoAvailable() {
		return lastCommand != null;
	}

	public void undo() {
		lastCommand.undo();
		lastCommand = null;
	}
}
