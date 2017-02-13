package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Bishop --- Class to represent Bishop piece in Chess game.
 * @author	Lance Strait
 */
public class Bishop extends Piece {

	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Bishop (Tile tile, Color color) {
		super(tile, color);
	}
	
	/**
	 * Check whether the proposed move is valid. Bishop's can move any number of squares diagonally.
	 * 
	 * @param newTile The proposed tile to move the Bishop to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if moving to newTile is allowed.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		// Bishops can move along the diagonals until blocked by another piece or end of the board
		return isValidDiagonolMove(this,newTile,board);
	}
}
