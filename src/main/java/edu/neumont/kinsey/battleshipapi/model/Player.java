package edu.neumont.kinsey.battleshipapi.model;

public class Player {
	private Board board = new Board();

	private int carrierHealth = 5;
	private int battleShipHealth = 4;
	private int cruiserHealth = 3;
	private int submarineHealth = 3;
	private int destroyerHealth = 2;

	private int currentXCord;
	private int currentYCord;

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

	public String getHiddenBoard() {
		return getBoard().toString().replaceAll("S", "W");
	}

	public boolean placeShip(String shipName, int xcord, int ycord, int shipDirection) {
		int shipIndex = -1;
		switch (shipName) {
		case "Carrier":
			shipIndex = 0;
			break;
		case "Battleship":
			shipIndex = 1;
			break;
		case "Cruiser":
			shipIndex = 2;
			break;
		case "Submarine":
			shipIndex = 3;
			break;
		case "Destroyer":
			shipIndex = 4;
			break;
		default:
			throw new IllegalArgumentException("shipName must be one of these values.");	
		}

		boolean shipsPlaced = false;
		currentXCord = xcord;
		currentYCord = ycord;
		if (checkShipTiles(shipIndex, shipDirection)) {
			currentXCord = xcord;
			currentYCord = ycord;
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
			board.setTileType(currentXCord, currentYCord, Type.S);
			board.setShipType(currentXCord, currentYCord, shipIndex);
			nextCords(shipDirection);
		}
	}

	private boolean checkShipTiles(int shipIndex, int shipDirection) {
		boolean tilesOpen = false;
		int tilesToCheck = Ship.values()[shipIndex].getSize();
		for (int i = 0; i < tilesToCheck; i++) {
			if ((currentXCord < 10 && currentXCord >= 0) && (currentYCord < 10 && currentYCord >= 0)) {
				if (board.getTileValue(currentXCord, currentYCord) == Type.W) {
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
			currentYCord += 1;
			break;
		case 2:
			currentYCord -= 1;
			break;
		case 3:
			currentXCord -= 1;
			break;
		case 4:
			currentXCord += 1;
			break;
		}
	}
}