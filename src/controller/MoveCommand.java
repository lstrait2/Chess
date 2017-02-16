package controller;

import model.game.ChessGame;
import model.game.Tile;
import model.pieces.Piece;
public class MoveCommand implements Command {

	private ChessGame model;
	private Piece sourceTileOccupantPrev;
	private Piece destTileOccupantPrev;
	//GUI can be undone just be updateTileImage
	
	public MoveCommand(ChessGame model, Tile sourceTile, Tile destTile) {
		this.model = model;
		this.sourceTileOccupantPrev = sourceTile.getOccupant();
		this.destTileOccupantPrev = destTile.getOccupant();
	}
	
	
	public void execute() {
		
		
	}
	
	public void undo() {
		
	}
	
	
}
