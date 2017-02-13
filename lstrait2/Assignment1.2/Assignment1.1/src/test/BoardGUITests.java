package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import game.*;
import view.BoardGUI;

public class BoardGUITests {

	@Test
	public void TestGUICreation() {
		// create the board GUI
		ChessGame chessGame = new ChessGame(8,8,false);
		Board board = chessGame.getBoard();
		BoardGUI boardGUI = new BoardGUI(board);
		// board should be 8x8
		assertEquals(boardGUI.getTileGUIs().length, 8);
		assertEquals(boardGUI.getTileGUIs()[0].length, 8);
		// board tiles should have the right colors
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// even tiles are white
				if ( (i+j) % 2 == 0) {
					assertEquals(boardGUI.getTileGUIs()[i][j].getBackground(), Color.white);
				} else {
					assertEquals(boardGUI.getTileGUIs()[i][j].getBackground(), Color.black);
				}
			}
		}
	}
}
