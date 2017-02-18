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
	private ForfeitListener forfeitListener; // listener to check for player forfeits
	private PieceSelectionListener pieceListener; // listener to check for attempted moves
	private RestartListener restartListener; // listener to check for player restarts
	private UndoListener undoListener; // listener to check for player undos
	private Player whitePlayer;
	private Player blackPlayer;
	private boolean isCustomGame; // true if players want a custom game.
	private MoveCommandManager moveManager; // manager to allow for move execution and undo.
	
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
		this.undoListener = new UndoListener(this);
		this.boardGUI = new BoardGUI(chessGame.getBoard(), pieceListener, restartListener, forfeitListener, undoListener);
		this.moveManager = new MoveCommandManager();
	}
	
	/**
	 * Getter.
	 */
	public ChessGame getChessGame() {
		return this.chessGame;
	}
	
	/**
	 * Getter.
	 */
	public Player getWhitePlayer() {
		return this.whitePlayer;
	}
	
	/**
	 * Getter.
	 */
	public Player getBlackPlayer() {
		return this.blackPlayer;
	}
	
	/**
	 * Getter.
	 */
	public MoveCommandManager getMoveCommandManager() {
		return this.moveManager;
	}
	
	/**
	 * Handle a move received from the players passed on by PieceSelectionListener.
	 * @param oldTileGUI the tile to move a piece from
	 * @param newTileGUI the tile to move a piece to
	 */
	public void receivedMove(TileGUI oldTileGUI, TileGUI newTileGUI) {
		// check that the oldTile is not empty
		MoveCommand move = new MoveCommand(chessGame, oldTileGUI, newTileGUI);
		int ret = moveManager.executeCommand(move);
		// check that move was successful. Display error output if wasn't
		if (ret == 0) {
			if (winConditionPresent()) {
				restartGame();
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
	 * Check win (and check) conditions and handle them if present.
	 */
	public boolean winConditionPresent() {
		// check if either player wins or stalemate exists.
		if (chessGame.playerWins(Color.BLACK)) {
			JOptionPane.showMessageDialog(boardGUI, "Checkmate! Black Player has won the game.");
			blackPlayer.incrementWins();
			return true;
		}
		if (chessGame.playerWins(Color.WHITE)) {
			JOptionPane.showMessageDialog(boardGUI, "Checkmate! White Player has won the game.");
			whitePlayer.incrementWins();
			return true;
		}
		if (chessGame.isStalemate()) {
			JOptionPane.showMessageDialog(boardGUI, "Stalemate. Neither play wins.");
			return true;
		}

		// display warning if move put other king in check, even though no win conditions
		if (chessGame.blackKingInCheck()) {
			JOptionPane.showMessageDialog(boardGUI, "Black king is now in check!");
		}

		if (chessGame.whiteKingInCheck()) {
			JOptionPane.showMessageDialog(boardGUI, "White king is now in check!");
		}
		return false;
	}
	
	/**
	 * Restart the game and start a new one.
	 */
	public void restartGame() {
		this.boardGUI.setVisible(false);
		this.boardGUI.dispose();
		// generate a new instance of chess game.
		if (isCustomGame) {
			this.chessGame = new ChessGame(9,9,true); 
		}
		else {
			this.chessGame = new ChessGame(8,8,false);
		}
		// generate a fresh GUI
		this.boardGUI = new BoardGUI(chessGame.getBoard(), this.pieceListener, this.restartListener, this.forfeitListener, this.undoListener);
		this.boardGUI.updatePlayerWins(whitePlayer.getWins(), blackPlayer.getWins());
		this.moveManager = new MoveCommandManager();
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
		// whichever player did not forfeit receives a win
		if (p != whitePlayer) {
			whitePlayer.incrementWins();
		}
		else {
			blackPlayer.incrementWins();
		}
		restartGame();
	}
	
	/**
	 * Prompt the MoveCommand manager to try to undo last move.
	 */
	public void undoMove() {
		if (moveManager.isUndoAvailable()) {
			moveManager.undo();
		}
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
