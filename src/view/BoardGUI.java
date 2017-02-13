package view;

import game.*;
import javax.swing.*;
import java.awt.*;
import pieces.*;

/**
 * BoardGUI --- Class to represent the GUI of the chess board.
 * @author Lance
 *
 */
public class BoardGUI extends JFrame {

	private JPanel tileGUIs[][]; // holds the panels representing each tile on the board
	
	/**
	 * Constructor.
	 * 
	 * @param board the chess board to create a GUI for.
	 */
	public BoardGUI(Board board) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // application should exit if user exits out of board
		setSize(1000, 1000); // want the board to fill up most of the screen.
		int rank = board.getRanks(); // the number of ranks (rows) the board has
		int file = board.getFiles(); // the number of files (columns the board has
		tileGUIs = new JPanel[rank][file];
		GridLayout grid = new GridLayout(rank, file); // board naturally lends itself to a grid layout (rank by file grid)
		setLayout(grid);
		// loop over each tile on board and create tile image.
		for (int i = 0; i < rank; i++) {
			for (int j = 0; j < file ; j++) {
				Tile tile = board.getTile(i, j);
				tileGUIs[i][j] = buildTileGUI(tile);
				add(tileGUIs[i][j]);
			}
		}
		setVisible(true); // board GUI is built, show to user
	}
	
	/**
	 * Getter.
	 * 
	 * @return the 2D array of tile panels for the board GUI
	 */
	public JPanel[][] getTileGUIs() {
		return this.tileGUIs;
	}
	
	/**
	 * Build a GUI panel for the given board tile.
	 * 
	 * @param tile the tile to build a GUI panel for.
	 * @return the GUI panel created from the tile.
	 */
	public JPanel buildTileGUI(Tile tile) {
		JPanel tileGUI = new JPanel();
		GridLayout grid = new GridLayout(1,1); // ensure that the button placed on top of the panel fills the panel.
		tileGUI.setLayout(grid);
		// color of the GUI panel should match the color of the board tile
		if (tile.isWhiteTile()) {
			tileGUI.setBackground(Color.white);
		} else {
			tileGUI.setBackground(Color.black);
		}
		// place a button on top of the panel to facilitate user interaction
		JButton button;
		// if there is a piece on tile, the label of the button should be the picture of that piece.
		if (tile.getOccupant() != null) {
			ImageIcon pieceImage = getPieceImage(tile.getOccupant());
			button = new JButton(pieceImage);
		} else {
			button = new JButton("");
			button.setBackground(tileGUI.getBackground());
		}
		tileGUI.add(button);
		return tileGUI;
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
	
	/*
	 * This is temporary, just for static testing purposes.
	 */
	public static void main(String[] args) {
		// create the board GUI
		ChessGame chessGame = new ChessGame(9,9,true);
		Board board = chessGame.getBoard();
		BoardGUI boardGUI = new BoardGUI(board);
	}
}
