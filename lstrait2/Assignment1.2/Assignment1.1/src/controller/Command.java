package controller;

public interface Command {
	public void execute();
	public void undo();
}
