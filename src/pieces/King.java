package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * King --- Class to represent King piece in Chess game.
 * @author Lance
 *
 */
public class King extends Piece {
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public King (Tile tile, Color color) {
		super(tile,color);
	}
	
	/**
	 * Check whether the proposed move is valid. King's can move one square in any direction.
	 * 
	 * @param newTile The proposed tile to move the King to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the king is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		return isValidOneTileMove(this, newTile, board);
	}
}
