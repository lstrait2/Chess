package view;

import javax.swing.*;

import controller.*;
import model.game.*;

import java.awt.*;

/**
 * BoardGUI --- Class to represent the GUI of the chess board.
 * @author Lance
 *
 */
public class BoardGUI extends JFrame {

	/**
	 * Constructor.
	 * 
	 * @param board the chess board to create a GUI for.
	 * @param chessController the controller for the chess game this GUI represents
	 */
	
	Label player1Label;
	Label player2Label;
	
	public BoardGUI(Board board, PieceSelectionListener pieceListener, RestartListener restartListener, ForfeitListener forfeitListener, UndoListener undoListener) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // application should exit if user exits out of board
		setSize(1000, 1000); // want the board to fill up most of the screen.
		int rank = board.getRanks(); // the number of ranks (rows) the board has
		int file = board.getFiles(); // the number of files (columns the board has
		GridLayout grid = new GridLayout(rank+1, file); // board naturally lends itself to a grid layout (rank by file grid)
		setLayout(grid);
		// loop over each tile on board and create tile image.
		for (int i = 1; i < rank + 1; i++) {
			for (int j = 0; j < file ; j++) {
				Tile tile = board.getTile(i-1, j);
				TileGUI tileGUI = new TileGUI(tile);
				tile.setTileGUI(tileGUI);
				tileGUI.addActionListener(pieceListener);
				add(tileGUI);
			}
		}
		JButton button;
		// add forfeit button with listener for controller.
		button = new JButton("forfeit");
		button.addActionListener(forfeitListener);
		add(button);
		// add restart button with listener for controller.
		button = new JButton("restart");
		button.addActionListener(restartListener);
		add(button);
		// add undo button with listener for controller.
		button = new JButton("undo move");
		button.addActionListener(undoListener);
		add(button);
		// add blanks to give padding for player scores
		Label blank = new Label("");
		add(blank);
		blank = new Label("");
		add(blank);
		// add score labels to display to players.
		this.player1Label = new Label("Black: 0");
		player1Label.setFont(new Font("sans-serif", Font.BOLD, 16));
		add(player1Label);
		add(blank);
		this.player2Label = new Label ("White: 0");
		player2Label.setFont(new Font("sans-serif", Font.BOLD, 16));
		add(player2Label);
		// board GUI is built, show to user
		setVisible(true);
	}
	
	/**
	 * Update the player win counts displayed on buttons.
	 */
	public void updatePlayerWins(int whiteWins, int blackWins) {
		player1Label.setText("White: " + whiteWins);
		player2Label.setText("Black: " + blackWins);
	}
	
}
