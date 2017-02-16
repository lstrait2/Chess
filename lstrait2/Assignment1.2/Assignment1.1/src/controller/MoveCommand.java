package controller;

import model.game.ChessGame;
import model.game.Tile;
import model.pieces.Piece;
import model.pieces.Pawn;
import model.player.Color;
import view.TileGUI;
public class MoveCommand implements Command {

	private ChessGame model;
	private Piece sourceTileOccupantPrev;
	private Piece destTileOccupantPrev;
	private TileGUI sourceTile;
	private TileGUI destTile;
	private Color playerTurnPrev;
	private boolean isPawnAndFirstMove;
	//GUI can be undone just be updateTileImage
	
	public MoveCommand(ChessGame model, TileGUI sourceTile, TileGUI destTile) {
		this.model = model;
		this.sourceTileOccupantPrev = sourceTile.getTile().getOccupant();
		this.destTileOccupantPrev = destTile.getTile().getOccupant();
		this.sourceTile = sourceTile;
		this.destTile = destTile;
		this.playerTurnPrev = model.getPlayerTurn();
		this.isPawnAndFirstMove = (sourceTileOccupantPrev instanceof Pawn && sourceTileOccupantPrev.getFirstMove());
	}
	
	
	public int execute() {
		int ret = -1;
		if (sourceTileOccupantPrev != null && sourceTileOccupantPrev.getColor() == Color.BLACK) {
			ret = model.blackMove(sourceTileOccupantPrev, destTile.getTile());
		}
		if (sourceTileOccupantPrev != null && sourceTileOccupantPrev.getColor() == Color.WHITE) {
			ret = model.whiteMove(sourceTileOccupantPrev, destTile.getTile());
		}
		// move was valid
		if (ret == 0) {
			destTile.updateTileImage();
			sourceTile.updateTileImage();
		}
		return ret;
	}
	
	public void undo() {
		sourceTileOccupantPrev.movePiece(sourceTile.getTile());
		if (destTileOccupantPrev != null) {
			destTileOccupantPrev.movePiece(destTile.getTile());
		}
		if (isPawnAndFirstMove) {
			sourceTileOccupantPrev.setFirstMove();
		}
		sourceTileOccupantPrev.updateValidMoves(model.getBoard());
		if (destTileOccupantPrev != null) {
			destTileOccupantPrev.updateValidMoves(model.getBoard());
		}
		
		model.setPlayerTurn(playerTurnPrev);
		destTile.updateTileImage();
		sourceTile.updateTileImage();
	}
	
	
}
