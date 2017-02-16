package model.player;

public class Player {
	
	private Color color;
	private int wins;
	
	/**
	 * Constructor.
	 * 
	 * @param color the color of the player (either white or black).
	 */
	public Player(Color color) {
		this.wins = 0;
		this.color = color;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the number of games this player has won in current series.
	 */
	public int getWins() {
		return this.wins;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the color of this player.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Increments the games won by this player.
	 */
	public void incrementWins() {
		this.wins++;
	}

}
