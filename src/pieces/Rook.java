package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Rook -- Class to represent Rook piece in Chess game.
 * @author Lance
 *
 */
public class Rook extends Piece {
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Rook (Tile tile, Color color) {
		super(tile, color);
	}
	
	/**
	 * Check whether the proposed move is valid. Rook's can move
	 * along rank or file, but cannot jump over pieces.
	 * 
	 * @param newTile The proposed tile to move the Rook to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the Rook is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		return isValidVerticalOrHorizontalMove(this, newTile, board);
		
	}
}
