package edu.neumont.kinsey.battleshipapi.model;

public class Tile {
	private Type type = Type.W;
	private Ship shipType;
	
	public void setType(Type type) {
		if (type != null) {
			this.type = type;			
		} else {
			throw new IllegalArgumentException("Type cannot be null");
		}
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Ship getShipType() {
		return shipType;
	}

	public void setShipType(Ship shipType) {
		if (shipType != null) {
			this.shipType = shipType;			
		} else {
			throw new IllegalArgumentException("shipType cannot be null");
		}
	}
	
	@Override
	public boolean equals(Object other) {
		Tile tile = (Tile) other;
		return this.getType() == tile.getType();
	}
}
