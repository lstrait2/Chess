package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UndoListener -- listener to check for undo actions.
 * @author Lance
 *
 */
public class UndoListener implements ActionListener {
	ChessController chessController;

	public UndoListener(ChessController chessController) {
		super();
		this.chessController = chessController;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// have controller prompt moveCommandManager to attempt to redo move.
		chessController.undoMove();
	}
}
