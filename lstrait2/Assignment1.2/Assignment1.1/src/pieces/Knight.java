package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Knight --- Class to represent Knight piece in Chess game.
 * @author Lance
 *
 */
public class Knight extends Piece {
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Knight (Tile tile, Color color) {
		super(tile, color);
	}

	/**
	 * Check whether the proposed move is valid. 
	 * The Knight can in an "L" shape - 2 squares horizontally and 1 vertically 
	 * or 1 square vertically and 2 horizontally
	 * 
	 * 
	 * @param newTile The proposed tile to move the King to.
	 * @param board board The game board the piece and tile are on.
	 * @return true only if the king is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		return isValidLMove(this, newTile, board);
	}
}
