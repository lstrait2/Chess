package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.game.Tile;
import view.TileGUI;

/**
 * PieceSelectionListener - Class to listen for user interaction with tileGUIs.
 * @author Lance
 *
 */
public class PieceSelectionListener implements ActionListener {
	
	TileGUI currSelectedTile; 
	ChessController chessController;
	
	/**
	 * Constructor.
	 * 
	 * @param chessController the controller for the current chess game.
	 */
	public PieceSelectionListener(ChessController chessController) {
		super();
		this.chessController = chessController;
		currSelectedTile = null;
	}
	
	/**
	 * Handle the user interaction with the TileGUI (button)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		TileGUI selectedTile = (TileGUI) actionEvent.getSource();
	
		// player has not selected a tile yet, this is the source tile.
		if (currSelectedTile == null) {
			// highlight the current tile.
			selectedTile.setBackground(Color.pink);
			//TODO: match with color of player turn.
			// highlight all valid moves from this tile.
			if (selectedTile.getTile().getOccupant() != null) {
				for(Tile validTile: selectedTile.getTile().getOccupant().getValidMoves()) {
					validTile.getTileGUI().setBackground(Color.green);
				}
			}
			currSelectedTile = selectedTile;
		}
		// player has already selected a tile, this is the destination tile.
		else {
			// unhighlight the valid moves from the source tile.
			if (currSelectedTile.getTile().getOccupant() != null) {
				for(Tile validTile: currSelectedTile.getTile().getOccupant().getValidMoves()) {
					validTile.getTileGUI().setBackground(validTile.getTileGUI().getOriginalColor());
				}
			}
			// send selected move to the controller.
			chessController.receivedMove(currSelectedTile, selectedTile);
			// unhighlight the source tile.
			currSelectedTile.setBackground(currSelectedTile.getOriginalColor());
			currSelectedTile = null;
		}
	}
}

