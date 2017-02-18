package controller;

/**
 * Command - Interface for Command pattern discussed in notes.
 * @author Lance
 *
 */
public interface Command {
	public int execute();
	public void undo();
}
