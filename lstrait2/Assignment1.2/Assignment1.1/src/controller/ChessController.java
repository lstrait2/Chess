package controller;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.game.*;
import model.pieces.*;
import model.player.*;
import view.*;
/**
 * ChessController --- Class that handles main loop of chess game
 * @author Lance
 *
 */
public class ChessController {
	
	private ChessGame chessGame;
	private BoardGUI boardGUI;
	private ForfeitListener forfeitListener;
	private PieceSelectionListener pieceListener;
	private RestartListener restartListener;
	private Player whitePlayer;
	private Player blackPlayer;
	private boolean isCustomGame;
	
	/**
	 * Constructor.
	 * 
	 */
	public ChessController(boolean isCustomGame) {
		this.whitePlayer = new Player(Color.WHITE);
		this.blackPlayer = new Player(Color.BLACK);
		if (isCustomGame) {
			this.chessGame = new ChessGame(9,9,true);
		}
		else {
			this.chessGame = new ChessGame(8,8,false);
		}
		this.isCustomGame = isCustomGame;
		this.pieceListener = new PieceSelectionListener(this);
		this.restartListener = new RestartListener(this);
		this.forfeitListener = new ForfeitListener(this);
		this.boardGUI = new BoardGUI(chessGame.getBoard(), pieceListener, restartListener, forfeitListener);
	}
	
	//TODO: check player making move is own turn, need Player class
	//TODO: clean this and ChessGame file up a lot!
	/**
	 * Handle a move received from the players passed on by PieceSelectionListener.
	 * @param oldTileGUI the tile to move a piece from
	 * @param newTileGUI the tile to move a piece to
	 */
	public void receivedMove(TileGUI oldTileGUI, TileGUI newTileGUI) {
		// get the game tiles the tileGUIs correspond to.
		Tile oldTile = oldTileGUI.getTile();
		Tile newTile = newTileGUI.getTile();
		// check that the oldTile is not empty
		int ret = 1;
		if (oldTile.getOccupant() != null && oldTile.getOccupant().getColor() == Color.BLACK) {
			ret = chessGame.blackMove(oldTile.getOccupant(), newTile);
		}
		if (oldTile.getOccupant() != null && oldTile.getOccupant().getColor() == Color.WHITE) {
			ret = chessGame.whiteMove(oldTile.getOccupant(), newTile);
		}
		// check that move was successful. Display error output if wasn't
		if (ret == 0) {
			oldTileGUI.updateTileImage();
			newTileGUI.updateTileImage();
			//TODO: refactor these to "checkConditions method"
			if (chessGame.playerWins(Color.BLACK)) {
				JOptionPane.showMessageDialog(boardGUI, "Checkmate! Black Player has won the game.");
				blackPlayer.incrementWins();
				restartGame();
				return;
			}
			if (chessGame.playerWins(Color.WHITE)) {
				JOptionPane.showMessageDialog(boardGUI, "Checkmate! White Player has won the game.");
				whitePlayer.incrementWins();
				restartGame();
				return;
			}
			if (chessGame.isStalemate()) {
				JOptionPane.showMessageDialog(boardGUI, "Stalemate. Neither play wins.");
				restartGame();
				return;
			}

			// display warning if move put other king in check
			if (chessGame.blackKingInCheck()) {
				JOptionPane.showMessageDialog(boardGUI, "Black king is now in check!");
			}
			
			if (chessGame.whiteKingInCheck()) {
				JOptionPane.showMessageDialog(boardGUI, "White king is now in check!");
			}
		}
		// tried to move out of turn
		else if (ret == 1){
			JOptionPane.showMessageDialog(boardGUI, "Please wait for other player to complete their turn before attempting a move.");
		}
		// attempted an illegal move, display warning
		else {
			JOptionPane.showMessageDialog(boardGUI, "This is an invalid move, try again");
		}
	}
	
	/**
	 * Restart the game and start a new one.
	 */
	public void restartGame() {
		this.boardGUI.setVisible(false);
		this.boardGUI.dispose();
		if (isCustomGame) {
			this.chessGame = new ChessGame(9,9,true); 
		}
		else {
			this.chessGame = new ChessGame(8,8,false);
		}
		this.boardGUI = new BoardGUI(chessGame.getBoard(), this.pieceListener, this.restartListener, this.forfeitListener);
		this.boardGUI.updatePlayerWins(whitePlayer.getWins(), blackPlayer.getWins());
		//TODO: reset CommandManager.
	}
	
	/**
	 * Getter the Player whose turn it currently is.
	 */
	public Player getCurrPlayer() {
		if (chessGame.getPlayerTurn() == Color.BLACK) {
			return this.blackPlayer;
		}
		return this.whitePlayer;
	}
	
	/**
	 * Function to forfeit the game for player p
	 * 
	 * @param p the player who chose to forfeit
	 */
	public void forfeitGame(Player p) {
		if (p != whitePlayer) {
			whitePlayer.incrementWins();
		}
		else {
			blackPlayer.incrementWins();
		}
		restartGame();
	}
	
	public void undoMove() {
		
	}
	
	
	public static void main(String[] args) {
		// let user choose customer or classic game. TODO: refactor!
		String[] options = {"classic", "custom"};
		String s = (String) JOptionPane.showInputDialog(null, "Choose game type", "", JOptionPane.PLAIN_MESSAGE, null, options, "classic");
		ChessController chessController = new ChessController(s.equals("custom"));
		while(true) {
			// game keeps going until users exit out of the GUI
		}
	}

}
