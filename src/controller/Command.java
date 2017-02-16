package controller;

public interface Command {
	public int execute();
	public void undo();
}
