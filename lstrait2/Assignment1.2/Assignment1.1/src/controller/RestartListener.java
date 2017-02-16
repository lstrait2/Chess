package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
	ChessController chessController;
	
	public RestartListener(ChessController chessController) {
		super();
		this.chessController = chessController;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		chessController.restartGame();
	}
}
