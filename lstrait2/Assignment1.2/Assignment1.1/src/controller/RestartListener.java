package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RestartListener - listen for restart actions by players.
 * @author Lance
 *
 */
public class RestartListener implements ActionListener {
	ChessController chessController;
	
	public RestartListener(ChessController chessController) {
		super();
		this.chessController = chessController;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// have the controller reset the game.
		chessController.restartGame();
	}
}
