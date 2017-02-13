package game;

import java.util.HashSet;

import pieces.BerolinaPawn;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Princess;
import pieces.Queen;
import pieces.Rook;
import player.Color;
import view.BoardGUI;

/**
 * ChessGame --- Class representing a game of chess.
 * @author Lance
 *
 */
public class ChessGame {

	private Board board; // the board the game is being played on.
	private King whiteKing; // the white player's king.
	private King blackKing; // the black player's king.
	private HashSet<Piece> whitePieces; // the pieces controlled by the white player.
	private HashSet<Piece> blackPieces; // the pieces controlled by the black player.
	private Color playerTurn; // the player whose turn it currently is.
	
	/**
	 * Constructor.
	 * 
	 * @param ranks the number of ranks (rows) on the game board.
	 * @param files the number of files (columns) on the game board.
	 */
	public ChessGame(int ranks, int files, boolean custom) {
		playerTurn = Color.WHITE; // white player goes first.
		board = new Board(ranks,files);
		whitePieces = new HashSet<>();
		blackPieces = new HashSet<>();
		// create the pieces for each player and place them on the board.
		if(custom == false) {
			setUpWhitePieces();
			setUpBlackPieces();
		}
		else {
			setUpWhitePiecesCustom();
			setUpBlackPiecesCustom();
		}
	}

	
	/**
	 * Getter.
	 * 
	 * @return the board the game is being played on.
	 */
	public Board getBoard () {
		return this.board;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the pieces controlled by the white player.
	 */
	public HashSet<Piece> getWhitePieces () {
		return this.whitePieces;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the pieces controlled by the black player.
	 */
	public HashSet<Piece> getBlackPieces () {
		return this.blackPieces;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the king controlled by the white player.
	 */
	public King getWhiteKing () {
		return this.whiteKing;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the king controlled by the black player.
	 */
	public King getBlackKing () {
		return this.blackKing;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the player whose turn it is (Color.WHITE or Color.BLACK).
	 */
	public Color getPlayerTurn () {
		return this.playerTurn;
	}
	
	/**
	 * Setter.
	 * 
	 * @param color the player whose turn it will now be.
	 */
	public void setPlayerTurn (Color color) {
		this.playerTurn = color;
	}
	
	
	/**
	 * End the current player's turn and move onto the next players turn.
	 */
	public void endTurn() {
		if (getPlayerTurn() == Color.BLACK) {
			setPlayerTurn(Color.WHITE);
		} else {
			setPlayerTurn(Color.BLACK);
		}
	}
	
	
	/**
	 * Create all the white pieces and place them on the board. 
	 * Assume only 16 pieces regardless of size of board (this may change in future weeks).
	 */
	private void setUpWhitePieces () {
		// white pieces go on bottom 2 rows of board.
		Tile[][] tiles = board.getTiles();
		// fill first row with 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king
		Rook rookLeft = new Rook(tiles[0][0], Color.WHITE);
		Rook rookRight = new Rook(tiles[0][7], Color.WHITE);
		whitePieces.add(rookLeft);
		whitePieces.add(rookRight);
		Knight knightLeft = new Knight(tiles[0][1], Color.WHITE);
		Knight knightRight = new Knight(tiles[0][6], Color.WHITE);
		whitePieces.add(knightLeft);
		whitePieces.add(knightRight);
		Bishop bishopLeft = new Bishop(tiles[0][2], Color.WHITE);
		Bishop bishopRight = new Bishop (tiles[0][5], Color.WHITE);
		whitePieces.add(bishopLeft);
		whitePieces.add(bishopRight);
		Queen queenWhite = new Queen(tiles[0][3], Color.WHITE);
		whitePieces.add(queenWhite);
		this.whiteKing = new King(tiles[0][4], Color.WHITE);
		whitePieces.add(whiteKing);
		// fill 2nd row from bottom with 8 white pawns
		for(int i = 0; i < 8; i++) {
			Pawn pawn = new Pawn(tiles[1][i], Color.WHITE);
			whitePieces.add(pawn);
		}
		// precompute valid moves for each piece
		whitePieces.forEach(piece->piece.updateValidMoves(board));
	}
	
	/**
	 * Create all the white pieces and place them on the board. 
	 * Assume only 17 pieces regardless of size of board (this may change in future weeks).
	 */
	private void setUpWhitePiecesCustom () {
		// white pieces go on bottom 2 rows of board.
		Tile[][] tiles = board.getTiles();
		// fill first row with 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king
		Rook rookLeft = new Rook(tiles[0][0], Color.WHITE);
		Rook rookRight = new Rook(tiles[0][8], Color.WHITE);
		whitePieces.add(rookLeft);
		whitePieces.add(rookRight);
		Knight knightLeft = new Knight(tiles[0][1], Color.WHITE);
		Knight knightRight = new Knight(tiles[0][7], Color.WHITE);
		whitePieces.add(knightLeft);
		whitePieces.add(knightRight);
		Bishop bishopLeft = new Bishop(tiles[0][2], Color.WHITE);
		Bishop bishopRight = new Bishop (tiles[0][6], Color.WHITE);
		whitePieces.add(bishopLeft);
		whitePieces.add(bishopRight);
		Queen queenWhite = new Queen(tiles[0][3], Color.WHITE);
		whitePieces.add(queenWhite);
		Princess princessWhite = new Princess(tiles[0][4], Color.WHITE);
		whitePieces.add(princessWhite);
		this.whiteKing = new King(tiles[0][5], Color.WHITE);
		whitePieces.add(whiteKing);
		// fill 2nd row from bottom with 8 white pawns
		for(int i = 0; i < 9; i++) {
			// Pawns are on even spaces
			if (i % 2 == 0) {
				Pawn pawn = new Pawn(tiles[1][i], Color.WHITE);
				whitePieces.add(pawn);
			}
			// BerolinaPawns are on odd spaces
			else {
				BerolinaPawn berolinaPawn= new BerolinaPawn(tiles[1][i], Color.WHITE);
				whitePieces.add(berolinaPawn);
			}
		}
		
		// precompute valid moves for each piece
		whitePieces.forEach(piece->piece.updateValidMoves(board));
	}
	
	/**
	 * Create all the black pieces and place them on the board. 
	 * Assume only 17 pieces regardless of size of board (this may change in future weeks).
	 */
	private void setUpBlackPiecesCustom () {
		// black pieces go on top 2 rows of board.
		Tile[][] tiles = board.getTiles();
		int ranks = board.getRanks();
		// fill top row with 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king
		Rook rookLeft = new Rook(tiles[ranks - 1][0], Color.BLACK);
		Rook rookRight = new Rook(tiles[ranks - 1][8], Color.BLACK);
		blackPieces.add(rookLeft);
		blackPieces.add(rookRight);
		Knight knightLeft = new Knight(tiles[ranks - 1][1], Color.BLACK);
		Knight knightRight = new Knight(tiles[ranks - 1][7], Color.BLACK);
		blackPieces.add(knightLeft);
		blackPieces.add(knightRight);
		Bishop bishopLeft = new Bishop(tiles[ranks - 1][2], Color.BLACK);
		Bishop bishopRight = new Bishop (tiles[ranks - 1][6], Color.BLACK);
		blackPieces.add(bishopLeft);
		blackPieces.add(bishopRight);
		Queen queenWhite = new Queen(tiles[ranks - 1][3], Color.BLACK);
		blackPieces.add(queenWhite);
		Princess princessBlack = new Princess(tiles[ranks - 1][4], Color.BLACK);
		blackPieces.add(princessBlack);
		this.blackKing = new King(tiles[ranks - 1][5], Color.BLACK);
		blackPieces.add(blackKing);
		// fill 2nd row from top with black pawns
		for(int i = 0; i < 9; i++) {
			if (i % 2 == 0) {
				Pawn pawn = new Pawn(tiles[ranks - 2][i], Color.BLACK);
				blackPieces.add(pawn);
			}
			else {
				BerolinaPawn berolinaPawn = new BerolinaPawn(tiles[ranks - 2][i], Color.BLACK);
				blackPieces.add(berolinaPawn);
			}
		}
		// precompute valid moves for each piece
		blackPieces.forEach(piece->piece.updateValidMoves(board));
	}
	
	/**
	 * Create all the black pieces and place them on the board. 
	 * Assume only 16 pieces regardless of size of board (this may change in future weeks).
	 */
	private void setUpBlackPieces () {
		// black pieces go on top 2 rows of board.
		Tile[][] tiles = board.getTiles();
		int ranks = board.getRanks();
		// fill top row with 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king
		Rook rookLeft = new Rook(tiles[ranks - 1][0], Color.BLACK);
		Rook rookRight = new Rook(tiles[ranks - 1][7], Color.BLACK);
		blackPieces.add(rookLeft);
		blackPieces.add(rookRight);
		Knight knightLeft = new Knight(tiles[ranks - 1][1], Color.BLACK);
		Knight knightRight = new Knight(tiles[ranks - 1][6], Color.BLACK);
		blackPieces.add(knightLeft);
		blackPieces.add(knightRight);
		Bishop bishopLeft = new Bishop(tiles[ranks - 1][2], Color.BLACK);
		Bishop bishopRight = new Bishop (tiles[ranks - 1][5], Color.BLACK);
		blackPieces.add(bishopLeft);
		blackPieces.add(bishopRight);
		Queen queenWhite = new Queen(tiles[ranks - 1][3], Color.BLACK);
		blackPieces.add(queenWhite);
		this.blackKing = new King(tiles[ranks - 1][4], Color.BLACK);
		blackPieces.add(blackKing);
		// fill 2nd row from top with black pawns
		for(int i = 0; i < 8; i++) {
			Pawn pawn = new Pawn(tiles[ranks - 2][i], Color.BLACK);
			blackPieces.add(pawn);
		}
		// precompute valid moves for each piece
		blackPieces.forEach(piece->piece.updateValidMoves(board));
	}
	
	/**
	 * Move a white piece on the board to newTile if it is white's turn and the move is legal.
	 * 
	 * @param piece the piece to move.
	 * @param newTile the tile to move the piece to.
	 */
	public void whiteMove(Piece piece, Tile newTile) {
		playerMove(Color.WHITE, piece, newTile);
	}
	
	/**
	 * Move a black piece on the board to newTile if it is blacks's turn and the move is legal.
	 * 
	 * @param piece the piece to move.
	 * @param newTile the tile to move the piece to.
	 */
	public void blackMove(Piece piece, Tile newTile) {
		playerMove(Color.BLACK, piece, newTile);
	}
	
	/**
	 * Move a piece on the board to newTile if it is that players turn and the move is legal.
	 * 
	 * @param color the color of the player whose turn it is.
	 * @param piece the piece to move.
	 * @param newTile the tile to move the piece to.
	 */
	public void playerMove(Color color, Piece piece, Tile newTile) {
		// it must be color's turn to move
		if (getPlayerTurn() != color) {
			System.out.print("Please wait for white to make their move first\n");
			return;
		}
		// only colors pieces can be moved on this turn.
		if (piece.getColor() != color) {
			System.out.print("Please only attempt to move your own pieces\n");
			return;
		}
		// attempt the move.
		int ret = piece.move(newTile,getBoard());
		if (ret == 0) {
			// move was successful, now it is the other players turn
			endTurn();
			// update valid moves for all other pieces (may have been affected by this move)
			whitePieces.forEach(p->p.updateValidMoves(board));
			blackPieces.forEach(p->p.updateValidMoves(board));
		}
		else {
			// move unsuccessful, prompt player to try again
			System.out.print("Move was illegal, please attempt a valid move\n");
		}
	}
	
	/**
	 * Checks if current state of the game is a stalemate.
	 * 
	 * @return true if player whose turn it is has no legal moves to make, false otherwise.
	 */
	public boolean isStalemate () {
		HashSet<Piece> currPlayerPieces = (getPlayerTurn() == Color.BLACK ? getBlackPieces() : getWhitePieces());
		for (Piece piece: currPlayerPieces) {
			// if a piece has a legal move, cannot be a stalemate
			if(piece.hasValidMove()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Determine if the king is in check.
	 * 
	 * @param king the king to determine check condition for.
	 * @return true if king is in check, false otherwise.
	 */
	public boolean kingInCheck(King king) {
		Tile kingLoc = king.getTile();
		HashSet<Piece> enemyPieces = (king.getColor() == Color.BLACK ? getWhitePieces() : getBlackPieces());
		for (Piece piece: enemyPieces) {
			// check if this enemy piece can reach king
			if (piece.getValidMoves().contains(kingLoc)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if king is in checkmate
	 * 
	 * @param king king the king to determine checkmate condition for.
	 * @return true if king is in checkmate, false otherwise.
	 */
	public boolean kingInCheckmate(King king) {
		return (kingInCheck(king) && !king.hasValidMove());
	}
	
	/**
	 * Check if the given player wins.
	 * 
	 * @param color the color of the player to check for win condition
	 * @return true if the player has win condition, false otherwise.
	 */
	public boolean playerWins(Color color) {
		King enemyKing = (color == Color.BLACK ? getWhiteKing() : getBlackKing());
		return (kingInCheckmate(enemyKing) || (kingInCheck(enemyKing)) && getPlayerTurn() == color);
	}
}
