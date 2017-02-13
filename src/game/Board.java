package game;


/**
 * Board --- class representing the chess board.
 * @author Lance
 *
 */
public class Board {
	
	private final int ranks; // number of ranks (rows) the board has
	private final int files; // number of files (columns) the board has
	private Tile[][] tiles; // the tiles on the board
	
	/**
	 * Constructor.
	 * 
	 * @param rows the number of ranks(rows) the board has.
	 * @param cols the number of files (columns) the board has.
	 */
	public Board(int rows, int cols) {
		this.files = cols;
		this.ranks = rows;
		tiles = new Tile[rows][cols];
		initializeBoard();
	}
	
	/**
	 * Getter.
	 * 
	 * @return the number of ranks (rows) the board has.
	 */
	public int getRanks() {
		return this.ranks;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the number of files (columns) the board has.
	 */
	public int getFiles() {
		return this.files;
	}
	
	/**
	 * Getter.
	 * 
	 * @return the 2-D array of tiles representing the board.
	 */
	public Tile[][] getTiles() {
		return this.tiles;
	}
	
	/**
	 * Get the tile at position (file, rank) if that position is on board.
	 * 
	 * @param rank the rank of the tile to look-up.
	 * @param file the file of the tile to look-up.
	 * @return the Tile at the given location, null if the position is not on the board.
	 */
	public Tile getTile(int rank, int file) {
		// this rank falls outside of board
		if (rank < 0 || rank > (ranks-1)) {
			return null;
		}
		// this tile falls outside of board
		if (file < 0 || file > tiles[rank].length - 1) {
			return null;
		}
		return tiles[rank][file];
	}
	
	/**
	 * Initialize a tile for each position (file, rank) of the board.
	 */
	private void initializeBoard() {
		for (int rank = 0; rank < ranks; rank ++) {
			for (int file = 0; file < files ; file++) {
				tiles[rank][file] = new Tile(rank,file);
			}
		}
	}	
}
