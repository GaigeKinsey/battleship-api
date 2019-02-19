package edu.neumont.kinsey.battleshipapi.model;

public class Board {

	public static final int MAX_COLUMNS = 10;
	public static final int MAX_ROWS = 10;
	private Tile[][] tiles = new Tile[MAX_COLUMNS][MAX_ROWS];

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

	public void setTileType(int col, int row, Type type) {
		if (type != null) {
			tiles[col][row].setType(type);
		} else {
			throw new IllegalArgumentException("Type cannot be null");
		}
	}

	public Type getTileValue(int col, int row) {
		return tiles[col][row].getType();
	}

	public void setShipType(int col, int row, int shipIndex) {
		tiles[col][row].setShipType(Ship.values()[shipIndex]);
	}

	public String getShipType(int col, int row) {
		return tiles[col][row].getShipType().getName();
	}
}
