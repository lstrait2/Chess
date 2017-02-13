package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Queen --- Class to represent Queen piece in Chess game.
 * @author Lance
 *
 */
public class Queen extends Piece {
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Queen (Tile tile, Color color) {
		super(tile, color);
	}
	
	/**
	 * Check whether the proposed move is valid. Queens's can move along file, rank, or diagonal,
	 * but cannot jump over other pieces.
	 * 
	 * @param newTile The proposed tile to move the Queen to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the Queen is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		// Queen can make the same moves the Bishop or Rook can
		return (isValidDiagonolMove(this, newTile, board) || isValidVerticalOrHorizontalMove(this, newTile, board));
	}
}
