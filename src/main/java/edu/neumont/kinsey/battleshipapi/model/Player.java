package edu.neumont.kinsey.battleshipapi.model;

public class Player {
	private Board board = new Board();

	private int carrierHealth = 5;
	private int battleShipHealth = 4;
	private int cruiserHealth = 3;
	private int submarineHealth = 3;
	private int destroyerHealth = 2;

	private int currentCol;
	private int currentRow;

	private final String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean isDead() {
		return carrierHealth + battleShipHealth + cruiserHealth + submarineHealth + destroyerHealth <= 0;
	}

	public void setBoard(int cord1, int cord2, Type type) {
		if (type != null) {
			board.setTileType(cord1, cord2, type);			
		} else {
			throw new IllegalArgumentException("Type cannot be null");
		}
	}

	public Board getBoard() {
		return board;
	}

	public boolean placeShip(String shipName, int col, int row, int shipDirection) {
		int shipIndex = -1;
		switch (shipName) {
		case "CARRIER":
			shipIndex = 0;
			break;
		case "BATTLESHIP":
			shipIndex = 1;
			break;
		case "CRUISER":
			shipIndex = 2;
			break;
		case "SUBMARINE":
			shipIndex = 3;
			break;
		case "DESTROYER":
			shipIndex = 4;
			break;
		default:
			throw new IllegalArgumentException("shipName must be one of these values.");	
		}

		boolean shipsPlaced = false;
		currentCol = col;
		currentRow = row;
		if (checkShipTiles(shipIndex, shipDirection)) {
			currentCol = col;
			currentRow = row;
			setShipTiles(shipIndex, shipDirection);
			shipsPlaced = true;
		} else {
			shipsPlaced = false;
		}
		return shipsPlaced;
	}

	public boolean recieveHit(String shipName) {
		boolean shipSunk = false;
		switch (shipName) {
		case "Carrier":
			carrierHealth--;
			if (carrierHealth == 0) {
				shipSunk = true;
			}
			break;
		case "Battleship":
			battleShipHealth--;
			if (battleShipHealth == 0) {
				shipSunk = true;
			}
			break;
		case "Cruiser":
			cruiserHealth--;
			if (cruiserHealth == 0) {
				shipSunk = true;
			}
			break;
		case "Submarine":
			submarineHealth--;
			if (submarineHealth == 0) {
				shipSunk = true;
			}
			break;
		case "Destroyer":
			destroyerHealth--;
			if (destroyerHealth == 0) {
				shipSunk = true;
			}
			break;
		default:
			throw new IllegalArgumentException("shipName must be one of these values.");
		}

		return shipSunk;
	}

	private void setShipTiles(int shipIndex, int shipDirection) {
		int tilesToPlace = Ship.values()[shipIndex].getSize();
		for (int i = 0; i < tilesToPlace; i++) {
			board.setTileType(currentCol, currentRow, Type.S);
			board.setShipType(currentCol, currentRow, shipIndex);
			nextCords(shipDirection);
		}
	}

	private boolean checkShipTiles(int shipIndex, int shipDirection) {
		boolean tilesOpen = false;
		int tilesToCheck = Ship.values()[shipIndex].getSize();
		for (int i = 0; i < tilesToCheck; i++) {
			if ((currentCol < 10 && currentCol >= 0) && (currentRow < 10 && currentRow >= 0)) {
				if (board.getTileValue(currentCol, currentRow) == Type.W) {
					tilesOpen = true;
				} else {
					tilesOpen = false;
					break;
				}
			} else {
				tilesOpen = false;
				break;
			}

			nextCords(shipDirection);
		}
		return tilesOpen;
	}

	private void nextCords(int shipDirection) {
		switch (shipDirection) {
		case 1:
			currentCol += 1;
			break;
		case 2:
			currentRow += 1;
			break;
		}
	}
}