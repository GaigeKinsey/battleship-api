package edu.neumont.kinsey.battleshipapi.model;

public enum Ship {
	CARRIER(0), BATTLESHIP(1), CRUISER(2), SUBMARINE(3), DESTROYER(4);
	
	private String name = "";
	private int size = 0;
	
	Ship(int selection) {
		switch (selection) {
		case 0:
			this.size = 5;
			this.name = "Carrier";
			break;
		case 1:
			this.size = 4;
			this.name = "Battleship";
			break;
		case 2:
			this.size = 3;
			this.name = "Cruiser";
			break;
		case 3:
			this.size = 3;
			this.name = "Submarine";
			break;
		case 4:
			this.size = 2;
			this.name = "Destroyer";
			break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
}
