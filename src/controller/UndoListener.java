package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoListener implements ActionListener {
	ChessController chessController;

	public UndoListener(ChessController chessController) {
		super();
		this.chessController = chessController;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		chessController.undoMove();
	}
}
