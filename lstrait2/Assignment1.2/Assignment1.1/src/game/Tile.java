package game;

import pieces.Piece;
import player.Color;

/**
 * Tile --- Class to represent a single tile on the game board.
 * @author Lance
 *
 */
public class Tile {
	
	private int rank; // the rank (y-coord) of the tile
	private int file; // the file (x-coord) of the tile
	private Piece occupant; // the piece sitting on this tile
	private Color color; // the color of the tile
	
	/**
	 * Constructor.
	 * 
	 * @param row the rank of the tile.
	 * @param col the file of the tile.
	 */
	public Tile(int row, int col) {
		this.rank = row;
		this.file = col;
		this.occupant = null;
		if ((row+col) % 2 == 0) {
			this.color = Color.WHITE;
		}
		else {
			this.color = Color.BLACK;
		}
	}
	
	/**
	 * Getter.
	 * 
	 * @return the rank of the tile.
	 */
	public int getRank() {
		return this.rank;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the file of the tile.
	 */
	public int getFile () {
		return this.file;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the piece sitting on the tile, null if no piece is
	 */
	public Piece getOccupant() {
		return this.occupant;
	}

	/**
	 * Setter.
	 *
	 * @param piece the piece to place on the tile.
	 */
	public void setOccupant(Piece piece) {
		this.occupant = piece;
	}
	/**
	 * Check if the tile does not contain a piece.
	 * 
	 * @return true if there is no piece sitting on the tile, false otherwise
	 */
    public boolean isEmpty () {
    	return (occupant == null);
    }
    
    /**
     * Check if a black piece sits on the tile.
     * 
     * @return true if there is a black piece sitting on the tile, false otherwise
     */
    public boolean occupiedByBlack () {
    	return !isEmpty() && occupant.getColor() == Color.BLACK;
    }
    
    /**
     * Check if a white piece sits on the tile.
     * 
     *  @return  true if there is a white piece sitting on the tile, false otherwise
     */
    public boolean occupiedByWhite () {
    	return !isEmpty() && occupant.getColor() == Color.WHITE;
    }	
    
    /**
     * Check if this is a black tile.
     * 
     * @return true if the color of the tile is black, false otherwise
     */
    public boolean isBlackTile() {
    	if (color == Color.BLACK) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Check if this is a white tile.
     * 
     * @return true if the color of the tile is white, false otherwise
     */
    public boolean isWhiteTile() {
    	if (color == Color.WHITE) {
    		return true;
    	}
    	return false;
    }
}
