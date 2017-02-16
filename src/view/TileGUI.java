package view;

import javax.swing.*;

import model.game.*;
import model.pieces.*;

import java.awt.*;

public class TileGUI extends JButton {
	
	private Tile tile;
	private Color originalColor;
	
	/**
	 * Constructor.
	 * 
	 * @param tile game tile that the TileGUI will correspond to.
	 */
	public TileGUI(Tile tile) {
		this.tile = tile;
		GridLayout grid = new GridLayout(1,1); // ensure that the button placed on top of the panel fills the panel.
		setLayout(grid);
		// place a button on top of the panel to facilitate user interaction
		// color of the GUI panel should match the color of the board tile
		if (tile.isWhiteTile()) {
			setBackground(Color.white);
			this.originalColor = Color.white;
		} else {
			setBackground(Color.darkGray);
			this.originalColor = Color.darkGray;
		}
		// if there is a piece on tile, the label of the button should be the picture of that piece.
		updateTileImage();
	}
	
	/**
	 * Getter.
	 * 
	 * @return the tile this TileGUI corresponds to.
	 */
	public Tile getTile() {
		return this.tile;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the color of this Tile at the beginning of the game
	 */
	public Color getOriginalColor() {
		return this.originalColor;
	}
	
	/**
	 * Update the Image displayed on this tile
 	 */
	public void updateTileImage() {
		ImageIcon pieceImage = null;
		if (tile.getOccupant() != null) {
			pieceImage = getPieceImage(tile.getOccupant());
		}
		setIcon(pieceImage);
	}
	
	/**
	 * Lookup the image for the given piece from resources directory
	 * Images are stored at resources/{Color}{Piece}.png
	 * For example, white pawn image stored at resources/WhitePawn.png
	 * 
	 * @param piece the piece to lookup an image for
	 * @return the image for the given piece.
	 */
	public ImageIcon getPieceImage(Piece piece) {
		String color;
		// get the color of the piece.
		if(piece.isBlack()) {
			color = "Black";
		} else {
			color = "White";
		}
		// get the name of the piece without package prefix
		String pieceType = piece.getClass().getSimpleName();
		// generate the path for the image.
		String path = "resources/" + color + pieceType + ".png"; 
		// create an ImageIcon from the given file and return
		return new ImageIcon(path);	
	}
}
