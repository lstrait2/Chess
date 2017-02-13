package pieces;

import game.Board;
import game.Tile;
import player.Color;

public class Move {

	/**
	 * Check whether piece can be moved to newTile by traveling along a rank or
	 * file. Used by both Queen and Rook.
	 * 
	 * @param piece
	 *            The piece to attempt to move horizontally or vertically to
	 *            newTile.
	 * @param newTile
	 *            The proposed tile to move the piece to.
	 * @param board
	 *            The game board the piece and tile are on.
	 * @return true if the piece can be moved to newTile along a rank or file,
	 *         false otherwise
	 */
	public static boolean isValidVerticalOrHorizontalMove(Piece piece, Tile newTile, Board board) {
		Tile currTile = piece.getTile();
		// if this piece or newTile is not on board, move is invalid
		if (currTile == null || newTile == null) {
			return false;
		}
		// if own piece is on newTile, cannot move
		if ((newTile.occupiedByBlack() && piece.getColor() == Color.BLACK)
				|| (newTile.occupiedByWhite() && piece.getColor() == Color.WHITE)) {
			return false;
		}
		int currRank = currTile.getRank();
		int currFile = currTile.getFile();
		int newRank = newTile.getRank();
		int newFile = newTile.getFile();

		// check if possible to move along file
		if (newFile == currFile) {
			// move downwards along file
			if (currRank > newRank) {
				// check that all tiles in between currTile and newTile are
				// empty
				for (int i = 1; i < (currRank - newRank); i++) {
					if (board.getTile(newRank + i, currFile).getOccupant() != null) {
						return false;
					}
				}
				return true;
				// move upwards along file
			} else {
				// check that all tiles in between currTile and newTile are
				// empty
				for (int i = 1; i < (newRank - currRank); i++) {
					if (board.getTile(currRank + i, currFile).getOccupant() != null) {
						return false;
					}
				}
				return true;
			}
			// check if possible to move along rank
		} else if (newRank == currRank) {
			// move downward along rank
			if (currFile > newFile) {
				// check that all tiles in between currTile and newTile are
				// empty
				for (int i = 1; i < (currFile - newFile); i++) {
					if (board.getTile(newRank, newFile + i).getOccupant() != null) {
						return false;
					}
				}
				return true;
				// move upward along rank
			} else {
				// check that all tiles in between currTile and newTile are
				// empty
				for (int i = 1; i < (newFile - currFile); i++) {
					if (board.getTile(newRank, currFile + i).getOccupant() != null) {
						return false;
					}
				}
				return true;
			}
		} else {
			return false; // Rooks can only move vertically or horizontally
		}
	}

	/**
	 * Check whether piece can be moved to newTile by traveling along a
	 * diagonal. Used by both Queen and Bishop.
	 * 
	 * @param piece
	 *            The piece to attempt to move diagonally to newTile.
	 * @param newTile
	 *            The proposed tile to move the piece to.
	 * @param board
	 *            The game board the piece and tile are on.
	 * @return true if the piece can be moved to newTile along a diagonal, false
	 *         otherwise
	 */
	public boolean isValidDiagonolMove(Piece piece, Tile newTile, Board board) {
		Tile currTile = piece.getTile();
		// if this piece or newTile is not on board, move is invalid
		if (currTile == null || newTile == null) {
			return false;
		}
		// if own piece is on newTile, cannot move
		if ((newTile.occupiedByBlack() && piece.getColor() == Color.BLACK)
				|| (newTile.occupiedByWhite() && piece.getColor() == Color.WHITE)) {
			return false;
		}
		int currRank = currTile.getRank();
		int currFile = currTile.getFile();
		int newRank = newTile.getRank();
		int newFile = newTile.getFile();
		// check newTile is diagonal from currTile
		if (Math.abs(currRank - newRank) != Math.abs(currFile - newFile)) {
			return false;
		}

		// check if move along lower-left diagonal is valid
		if (currRank > newRank && currFile > newFile) {
			for (int i = 1; i < (currRank - newRank); i++) {
				// check that all tiles in between currTile and newTile are
				// empty (non-inclusive)
				if (board.getTile(newRank + i, newFile + i).getOccupant() != null) {
					return false;
				}
			}
			return true;
		}
		// check if move along upper-right diagonal is valid.
		else if (currRank < newRank && currFile < newFile) {
			for (int i = 1; i < (newRank - currRank); i++) {
				// check that all tiles in between currTile and newTile are
				// empty (non-inclusive)
				if (board.getTile(currRank + i, currFile + i).getOccupant() != null) {
					return false;
				}
			}
			return true;
		}
		// check if move along lower-right diagonal is valid
		else if (currRank > newRank && currFile < newFile) {
			for (int i = 1; i < (currRank - newRank); i++) {
				// check that all tiles in between currTile and newTile are
				// empty (non-inclusive)
				if (board.getTile(currRank - i, currFile + i).getOccupant() != null) {
					return false;
				}
			}
			return true;
		}
		// check if move along upper-left diagonal is valid.
		else if (currRank < newRank && currFile > newFile) {
			for (int i = 1; i < (newRank - currRank); i++) {
				// check that all tiles in between currTile and newTile are
				// empty (non-inclusive)
				if (board.getTile(currRank + i, currFile - i).getOccupant() != null) {
					return false;
				}
			}
			return true;
		} else {
			// bishops can only move along diagonals.
			return false;
		}
	}

	/**
	 * Check wether piece can be moved to newTile traveling along an "L". That
	 * is 2 tiles in a vertical or horizontal direction, and 1 tile in the
	 * other.
	 * 
	 * @param piece
	 * @param newTile
	 * @param board
	 * @return
	 */
	public boolean isValidLMove(Piece piece, Tile newTile, Board board) {
		Tile currTile = piece.getTile();
		// if this piece or newTile is not on board, move is invalid
		if (currTile == null || newTile == null) {
			return false;
		}
		// if own piece is on newTile, cannot move
		if ((newTile.occupiedByBlack() && piece.getColor() == Color.BLACK)
				|| (newTile.occupiedByWhite() && piece.getColor() == Color.WHITE)) {
			return false;
		}
		int currRank = currTile.getRank();
		int currFile = currTile.getFile();
		int newRank = newTile.getRank();
		int newFile = newTile.getFile();
		/*
		 * knight can jump over other pieces, so always has 8 valid moves (if on
		 * board). check each of the closest squares that are not on the same
		 * rank, file, or diagonal (I.e 2 tiles either vertical or horizontally,
		 * and 1 tile in the other direction.
		 */
		if (Math.abs(currRank - newRank) * Math.abs(currFile - newFile) == 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check whether the proposed move is valid and one square away.
	 * 
	 * @param newTile The proposed tile to move the King to.
	 * @param board The game board the piece and tile are on.
	 * @return true only if the king is legally able to move to newTile.
	 */
	public boolean isValidOneTileMove (Piece piece, Tile newTile, Board board) {
		// if this piece or newTile is not on board, move is invalid
		if (piece.getTile() == null || newTile == null) {
			return false;
		}
		// if piece of same color is on newTile, cannot move there
		if ((newTile.occupiedByBlack() && piece.getColor() == Color.BLACK) || (newTile.occupiedByWhite() && piece.getColor() == Color.WHITE)) {
			return false;
		}
		// any space that is one away from current space is valid
		if (Math.abs(newTile.getRank() - piece.getTile().getRank()) < 2 && Math.abs(newTile.getFile() - piece.getTile().getFile()) < 2) {
			return true;
		}
		return false;
	}

}
