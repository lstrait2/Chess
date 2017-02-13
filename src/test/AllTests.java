package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all tests for the chess library
 * @author Lance
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BerolinaPawnTests.class, BishopTests.class, BoardTests.class, BoardGUITests.class, ChessGameTests.class, KingTests.class, KnightTests.class, 
	PawnTests.class, PieceTests.class, PrincessTests.class, QueenTests.class, RookTests.class, TileTests.class})
public class AllTests {   
}  