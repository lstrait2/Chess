package pieces;

import game.Board;
import game.Tile;
import player.Color;

/**
 * Pawn --- Class to represent Pawn piece in Chess game.
 * @author Lance
 *
 */
public class Pawn extends Piece {
	
	protected boolean firstMove; // keeps track of whether the pawn has moved yet
	
	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	public Pawn (Tile tile, Color color) {
		super(tile,color);
		this.firstMove = true;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the value of the firstMove flag (true if the pawn hasn't moved before, false otherwise)
	 */
	public boolean getFirstMove () {
		return this.firstMove;
	}
	
	/**
	 * Checks whether the proposed move is valid. Pawns can move straight forward to an unoccupied space, 
	 * or diagonally in-front 1 space to capture.
	 * 
	 * @param newTile The proposed tile to move the pawn to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the pawn is legally able to move to newTile.
	 */
	public boolean isValidMove (Tile newTile, Board board) {
		// black tiles move down board, white tiles move up board. Handle seperately.
		return (getColor() == Color.BLACK ? isValidMoveHelper(-1, newTile, board) : isValidMoveHelper(1, newTile, board));
	}
	
	
	/**
	 * Checks whether the proposed move is valid for a pawn. Helper method for isValidMove
	 * 
	 * @param dir The direction to move the piece. +1 to move upward (white pawn), -1 to move downward (black pawn).
	 * @param newTile The proposed tile to move the pawn to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the pawn is legally able to move to newTile
	 */
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

		// check if target is in same file
		if (currFile == targetFile) {
			// pawns cannot move forward into an occupied space
			if (!newTile.isEmpty()) {
				return false;
			}
			// check if target is one rank below
			if ((targetRank == currRank + dir)) {
				return true;
			}
			// check if first move for this spawn, target is two ranks below, and intermediate tile is empty 
			if (firstMove && (targetRank == currRank + (2*dir)) &&  (board.getTile(currRank + dir,  currFile) != null) && board.getTile(currRank + dir,  currFile).isEmpty()) {
				return true;
			}
		}
		// check if target is diagonally in-front and occupied by enemy piece
		if ((Math.abs(currFile - targetFile) == 1) && (targetRank == currRank + dir)
				&& !newTile.isEmpty() && newTile.getOccupant().getColor() != getColor()) {
			return true;
		}
		return false;
	}
	
	/**
	 *  Moves the pawn to the given tile (if it is a valid move) and updates the firstMove flag
	 *  if this is the first time the pawn's moved.
	 * 
	 * @param tile The proposed tile to move the pawn to.
	 * @param board The game board the piece and tile are on.
	 * @return 0 if the move was successfully completed, 1 otherwise
	 */
	public int move (Tile tile, Board board) {
		int ret = super.move(tile,board);
		// if the proposed move occurred (i.e it was valid), and change firstMove flag to false
		if(ret == 0) {
			firstMove = false;
			// need to update valid moves again, since no longer can move 2 spaces forward
			updateValidMoves(board);
		}
		return ret;
	}
}
