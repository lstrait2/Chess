package pieces;

import game.Board;
import game.Tile;
import java.util.HashSet;
import player.Color;

/**
 * Piece --- abstract class that implements common functionality of each chess piece
 * @author Lance
 *
 */
public abstract class Piece extends Move {
	
	protected Tile tile; //  the board Tile the piece is currently on, null if not on board
	protected final Color color; //  the color of the piece
	protected HashSet<Tile> validMoves; //  the board Tiles the piece could move to from current location

	/**
	 * Constructor.
	 * 
	 * @param tile The board tile the piece is on, null if the piece is not on the board.
	 * @param color The color (black or white) indicating the player that controls the piece.
	 */
	protected Piece(Tile tile, Color color) {
		this.tile = tile;
		tile.setOccupant(this); // ensure tile points to this piece now
		this.color = color;
		this.validMoves = new HashSet<Tile>();
	}
	
	/**
	 * Getter.
	 * 
	 * @return the tile this piece can currently legally move to.
	 */
	public HashSet<Tile> getValidMoves() {
		return this.validMoves;
	}
	
	/**
	 * Setter.
	 * 
	 *
	 */
	public void setValidMoves(HashSet<Tile> validMoves) {
		this.validMoves = validMoves;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the color of the piece.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the tile this piece is on, null if piece is not on board (i.e was captured)
	 */
	public Tile getTile() {
		return this.tile;
	}
	
	/**
	 * Setter.
	 * 
	 * @param tile the tile this piece will be on.
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	/**
	 * Check is piece is black.
	 * 
	 * @return true if piece is black, false otherwise
	 */
	public boolean isBlack() {
		return getColor() == Color.BLACK;
	}
	
	/**
	 * Check if piece is white.
	 * 
	 * @return true if piece is white, false otherwise
	 */
	public boolean isWhite() {
		return getColor() == Color.WHITE;
	}
	
	/**
	 * Attempt to move the piece to the given tile, 
	 * will only make the move if it is valid.
	 * 
	 * @param newTile The proposed tile to move the pawn to.
	 * @param board The game board the piece and tile are on.
	 * @return 0 if the move was successful, 1 otherwise.
	 */
	public int move(Tile newTile, Board board) {
		// make sure possible validMoves are up to date
		updateValidMoves(board);
		// check if proposed move is valid
		if (!validMoves.contains(newTile)) {
			return 1; 
		}
		// move the piece to newTile
		movePiece(newTile);
		// update the moves now available to this piece
		updateValidMoves(board);
		return 0; 
	}
	
	/**
	 * Move the piece to the given tile, helper method for move(Tile, Board) method.
	 * 
	 * @param newTile the tile to move this piece to.
	 */
	public void movePiece(Tile newTile) {
		// remove this piece from its old tile, if it exists
		if (this.tile != null) {
			this.tile.setOccupant(null);
		}
		// displace old Piece on newTile
		if (newTile != null && newTile.getOccupant() != null) {
			newTile.getOccupant().setTile(null);
		}
		this.tile = newTile;
		// assign this piece to the newTile unless being removed from board
		if (newTile != null) {
			newTile.setOccupant(this);
		}
	}
	
	/**
	 * Check whether the piece has a legal move available.
	 * 
	 * @return true if the piece has a legal move if it can currently make, false otherwise
	 */
	public boolean hasValidMove() {
		return (validMoves.size() > 0);
	}
	
	/**
	 * Abstract method to determine if move to newTile is valid for piece. Different implementation
	 * for each type of piece.
	 * 
	 * @param newTile The proposed tile to move the pawn to.
	 * @param board The game board the piece and tile are on.
	 * @return true if piece can legally move to newTile, false otherwise.
	 */
	protected abstract boolean isValidMove (Tile newTile, Board board);
	
	/**
	 * Populates validMoves field with all tiles the piece can currently move to legally
	 * 
	 * @param board The game board the piece and tile are on.
	 */
	public void updateValidMoves (Board board) {
		HashSet<Tile> newValidMoves = new HashSet<>();
		Tile currTile = getTile();
		// if this piece is not on the board, no moves are valid
		if (currTile == null) {
			setValidMoves(newValidMoves);
			return;
		}
		Tile newMove;
		// check every tile to see if valid, TODO: optimize this if time allows
		for (int i = 0; i < board.getRanks(); i++) {
			for (int j = 0; j < board.getFiles(); j++) {
				newMove = board.getTile(i, j);
				if (isValidMove(newMove,board)) {
					newValidMoves.add(newMove);
				}
			}
		}
		setValidMoves(newValidMoves);
	}
}
