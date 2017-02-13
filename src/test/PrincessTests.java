package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Tile;
import pieces.Pawn;
import pieces.Piece;
import pieces.Princess;
import player.Color;

public class PrincessTests {

	Board board;
	Princess whitePrincess;
	Princess blackPrincess;
	
	@Before
	public void setUp() {
		board = new Board(8,8);
		whitePrincess = new Princess(board.getTile(4, 4), Color.WHITE);
	}
	
	@Test
	public void TestConstructor() {
		// Queens should inherit from Piece class
		assertTrue(whitePrincess instanceof Piece);
		// ensure colors are correctly assigned
		assertEquals(Color.WHITE, whitePrincess.getColor());
		// ensure tile is correctly assigned
		assertEquals(board.getTile(4, 4), whitePrincess.getTile());
		// ensure tile occupant correctly references Queens
		assertEquals(board.getTile(4, 4).getOccupant(), whitePrincess);
	}
	
	/*
	 * test if validMoves works correctly with no blocking pieces.
	 */
	@Test
	public void TestValidMovesNoBlocks() {
		whitePrincess.updateValidMoves(board);
		// white Princess can move uninterrupted diagonolly and all 'L'moves allowed
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(7, 7));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));
		whiteMoves.add(board.getTile(6, 5));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 3));
		whiteMoves.add(board.getTile(3, 6));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(3, 2));
		assertEquals(whiteMoves, whitePrincess.getValidMoves());
	}
	
	/*
	 * test if validMoves works correctly when blocked by own piece.
	 */
	@Test
	public void TestValidMovesBlocked() {
		whitePrincess.updateValidMoves(board);
		// white Princess can move uninterrupted diagonolly until blocked, all 'L' moves allowed
		Pawn whitePawn2 = new Pawn(board.getTile(5, 5), Color.WHITE);
		whitePrincess.updateValidMoves(board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));
		whiteMoves.add(board.getTile(6, 5));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 3));
		whiteMoves.add(board.getTile(3, 6));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(3, 2));
		assertEquals(whiteMoves, whitePrincess.getValidMoves());
	}
	
	/*
	 * test if validMoves works correctly when blocked by own piece.
	 */
	@Test
	public void TestValidMovesBlockedByCapturable() {
		whitePrincess.updateValidMoves(board);
		// white Princess can move uninterrupted diagonolly until blocked, all 'L' moves allowed
		Pawn blackPawn2 = new Pawn(board.getTile(5, 5), Color.BLACK);
		whitePrincess.updateValidMoves(board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		whiteMoves.add(board.getTile(3, 3));
		whiteMoves.add(board.getTile(2, 2));
		whiteMoves.add(board.getTile(1, 1));
		whiteMoves.add(board.getTile(0, 0));
		whiteMoves.add(board.getTile(5,5)); // should be able to move to tile with capturable piece
		whiteMoves.add(board.getTile(5, 3));
		whiteMoves.add(board.getTile(6, 2));
		whiteMoves.add(board.getTile(7, 1));
		whiteMoves.add(board.getTile(3, 5));
		whiteMoves.add(board.getTile(2, 6));
		whiteMoves.add(board.getTile(1, 7));
		whiteMoves.add(board.getTile(6, 5));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 3));
		whiteMoves.add(board.getTile(3, 6));
		whiteMoves.add(board.getTile(2, 5));
		whiteMoves.add(board.getTile(5, 2));
		whiteMoves.add(board.getTile(2, 3));
		whiteMoves.add(board.getTile(3, 2));
		assertEquals(whiteMoves, whitePrincess.getValidMoves());
	}
	
	/*
	 * test validMoves does not allow moves off board
	 */
	@Test
	public void TestValidMovesEdgeOfBoard() {
		Princess whitePrincess = new Princess(board.getTile(7, 7), Color.WHITE);
		whitePrincess.updateValidMoves(board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		// white Princess can move diagnol until hits other princess, and only 2 'L' moves on board
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 5));
		assertEquals(whiteMoves, whitePrincess.getValidMoves());
	}
	
	/*
	 * test validMoves populates correctly after 1st move occurrs
	 */
	@Test
	public void TestValidMovesAfterFirst() {
		Princess whitePrincess = new Princess(board.getTile(6, 6), Color.WHITE);
		whitePrincess.updateValidMoves(board);
		whitePrincess.move(board.getTile(7, 7), board);
		Set<Tile> whiteMoves = new HashSet<Tile>();
		// white Princess can move diagnol until hits other princess, and only 2 'L' moves on board
		whiteMoves.add(board.getTile(5, 5));
		whiteMoves.add(board.getTile(6, 6));
		whiteMoves.add(board.getTile(5, 6));
		whiteMoves.add(board.getTile(6, 5));
		assertEquals(whiteMoves, whitePrincess.getValidMoves());
	}
}
