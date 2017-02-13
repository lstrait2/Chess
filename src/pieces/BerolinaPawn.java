package pieces;

import game.Board;
import game.Tile;
import player.Color;
/**
 * BerolinaPawn --- Class to represent BerolinaPawn in Chess game (https://en.wikipedia.org/wiki/Fairy_chess_piece)
 * @author Lance
 *
 */
public class BerolinaPawn extends Pawn {

	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public BerolinaPawn(Tile tile, Color color) {
		super(tile,color); // pawns constructor handles firstMove field
	}
	
	
	/**
	 * Checks whether the proposed move is valid for a BerolinaPawn. Helper method for isValidMove defined 
	 * in parent class (Pawn).
	 * 
	 * @param dir The direction to move the piece. +1 to move upward (white BerolinaPawn), -1 to move downward (black BerolinaPawn).
	 * @param newTile The proposed tile to move the BerolinaPawn to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the BerolinaPawn is legally able to move to newTile
	 */
	@Override
	public boolean isValidMoveHelper (int dir, Tile newTile, Board board) {
		Tile currTile = getTile();
		// if this piece is off board or newTile is off board, move is invalid
		if (currTile == null || newTile == null) {
			return false;
		}
		int currRank = currTile.getRank();
		int currFile = currTile.getFile();
		int targetRank = newTile.getRank();
		int targetFile = newTile.getFile();
		
		// check if target is directly in front and occupied by an enemy piece
		if ( (currFile == targetFile) && (targetRank == currRank + dir) && !newTile.isEmpty() && (newTile.getOccupant().getColor() != getColor())) {
			return true;
		}
		// check if target is diagonally in-front and empty
		if ((Math.abs(currFile - targetFile) == 1) && (targetRank == currRank + dir) && newTile.isEmpty()) {
			return true;
		}
		// check if target is diagonally 2 spaces in-front and empty and firstMoves
		if ((Math.abs(currFile - targetFile) == 2) && (targetRank == currRank + (2*dir)) && newTile.isEmpty() && getFirstMove()) {
			// check if intermediate spaces are empty
			if(currFile > targetFile) {
				return board.getTile(currRank + dir, currFile - 1).isEmpty();
			} else {
				return board.getTile(currRank + dir, currFile + 1).isEmpty();
			}	
		}
		return false;
	}
}
