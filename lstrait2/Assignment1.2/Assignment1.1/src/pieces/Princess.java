package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Princess - Class to represent the Princess Piece in Chess (https://en.wikipedia.org/wiki/Princess_(chess))
 * @author Lance
 *
 */
public class Princess extends Piece {
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Princess (Tile tile, Color color) {
		super(tile, color);
	}
	
	/**
	 * Check whether the proposed move is valid. Princesses can make same moves as a bishop or knight.
	 * 
	 * @param newTile The proposed tile to move the Princess to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the Princess is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		// Queen can make the same moves the Bishop or Rook can
		return (isValidLMove(this, newTile, board) || isValidDiagonolMove(this, newTile, board));
	}

}
