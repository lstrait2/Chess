package controller;

import model.game.ChessGame;

import model.pieces.Piece;
import model.pieces.Pawn;
import model.player.Color;
import view.TileGUI;

/**
 * MoveCommand -- Class to represent a single move command.
 * @author Lance
 *
 */
public class MoveCommand implements Command {

	private ChessGame model; // underlying model for this chess game.
	private Piece sourceTileOccupantPrev; // occupant of the source tile initially 
	private Piece destTileOccupantPrev; // occupant of the destination tile initially
	private TileGUI sourceTile; // tile piece is moving from
	private TileGUI destTile; // tile piece is moving to.
	private Color playerTurnPrev; // player whose turn it was when move was made
	private boolean isPawnAndFirstMove; // if the piece being moved was a pawn, may need to reset firstMove flag
	
	/**
	 * Constructor.
	 * 
	 * @param model ChessGame model
	 * @param sourceTile tile piece is moving from
	 * @param destTile tile piece is moving to
	 */
	public MoveCommand(ChessGame model, TileGUI sourceTile, TileGUI destTile) {
		this.model = model;
		this.sourceTileOccupantPrev = sourceTile.getTile().getOccupant(); // save the piece we're moving
		this.destTileOccupantPrev = destTile.getTile().getOccupant(); // save piece being captured (if applicable)
		this.sourceTile = sourceTile;
		this.destTile = destTile;
		this.playerTurnPrev = model.getPlayerTurn();
		this.isPawnAndFirstMove = (sourceTileOccupantPrev instanceof Pawn && sourceTileOccupantPrev.getFirstMove());
	}
	
	/**
	 * Execute this MoveCommand
	 * 
	 * @return 0 if move correctly executed, -1 otherwise.
	 */
	public int execute() {
		int ret = -1;
		// check which player is making the move.
		if (sourceTileOccupantPrev != null && sourceTileOccupantPrev.getColor() == Color.BLACK) {
			ret = model.blackMove(sourceTileOccupantPrev, destTile.getTile());
		}
		if (sourceTileOccupantPrev != null && sourceTileOccupantPrev.getColor() == Color.WHITE) {
			ret = model.whiteMove(sourceTileOccupantPrev, destTile.getTile());
		}
		// move was valid
		if (ret == 0) {
			// update the GUI to reflect successful move.
			destTile.updateTileImage();
			sourceTile.updateTileImage();
		}
		return ret;
	}
	
	/**
	 * Undo this MoveCOmmand.
	 */
	public void undo() {
		// move piece back to its sourceTile
		sourceTileOccupantPrev.movePiece(sourceTile.getTile());
		// if piece was captured by move, restore it to destTile
		if (destTileOccupantPrev != null) {
			destTileOccupantPrev.movePiece(destTile.getTile());
		}
		// restore firstMove flag if this was firstMove a pawn made.
		if (isPawnAndFirstMove) {
			sourceTileOccupantPrev.setFirstMove();
		}
		// restore validMoves() array for both pieces
		sourceTileOccupantPrev.updateValidMoves(model.getBoard());
		if (destTileOccupantPrev != null) {
			destTileOccupantPrev.updateValidMoves(model.getBoard());
		}
		// reset player turn to before move.
		model.setPlayerTurn(playerTurnPrev);
		// restore GUI state before move was made.
		destTile.updateTileImage();
		sourceTile.updateTileImage();
	}
	
	
}
