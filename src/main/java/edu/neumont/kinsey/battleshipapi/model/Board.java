package edu.neumont.kinsey.battleshipapi.model;

public class Board {
	
	public static final int MAX_ROWS = 10;
	public static final int MAX_COLUMNS = 10;
	private Tile[][] tiles = new Tile[MAX_ROWS][MAX_COLUMNS];
	
	public Board() {
		buildBoard();
	}
	
	private void buildBoard() {
		for (int i = 0; i < MAX_COLUMNS; i++) {
			for (int x = 0; x < MAX_ROWS; x++) {
				tiles[i][x] = new Tile();
			}
		}
	}
	
	public void setTileType(int cord1, int cord2, Type type) {
		if (type != null) {
			tiles[cord1][cord2].setType(type);			
		} else {
			throw new IllegalArgumentException("Type cannot be null");
		}
	}
	
	public Type getTileValue(int cord1, int cord2) {
		return tiles[cord1][cord2].getType();
	}
	
	public void setShipType(int cord1, int cord2, int shipIndex) {
		tiles[cord1][cord2].setShipType(Ship.values()[shipIndex]);
	}
	
	public String getShipType(int cord1, int cord2) {
		return tiles[cord1][cord2].getShipType().getName();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		char letter = 'A';
		sb.append("   1   2   3   4   5   6   7   8   9  10\n |---------------------------------------|\n");
		for (Tile[] row : tiles) {
			sb.append(letter + "| ");
			letter++;
			for (Tile cell : row) {
				sb.append(cell.getType()).append(" | ");
			}
			sb.append("\n |---------------------------------------|\n");
		}
		return sb.toString();
	}
}
