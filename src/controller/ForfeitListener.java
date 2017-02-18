package controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TileGUI;

/**
 * ForfeitListener - listen for forfeit actions by players.
 * @author Lance
 *
 */
public class ForfeitListener implements ActionListener {
	ChessController chessController;
	
	public ForfeitListener(ChessController chessController) {
		super();
		this.chessController = chessController;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// players can only forfeit on their turn.
		chessController.forfeitGame(chessController.getCurrPlayer());
	}
}