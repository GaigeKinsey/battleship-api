package edu.neumont.kinsey.battleshipapi.controller;

import edu.neumont.kinsey.battleshipapi.model.Player;
import edu.neumont.kinsey.battleshipapi.model.Ship;
import edu.neumont.kinsey.battleshipapi.view.BattleshipView;
import javafx.scene.control.RadioButton;

public class BattleshipController {
	private Player[] players = new Player[2];
	private int turn;

	private Player currentPlayer = null;
	private Player otherPlayer = null;
	private int shipsPlaced = 0;
	private int shipTurnsPassed = 0;

	private BattleshipView view;

	public BattleshipController(BattleshipView view) {
		this.view = view;
		this.view.registerController(this);
	}

	public void run() {
		this.view.init();
	}

	public void setPlayer(int index, String name) {
		players[index] = new Player(name);
		currentPlayer = players[0];
		if (players[1] != null) {
			otherPlayer = players[1];
		}
	}

	public void turnSwitcher() {
		view.alertTurnSwitch();
		switch (turn) {
		case 0:
			currentPlayer = players[1];
			otherPlayer = players[0];
			turn = 1;
			break;
		case 1:
			currentPlayer = players[0];
			otherPlayer = players[1];
			turn = 0;
			break;
		}
	}

	public void attack(int row, int col) {
		// TODO
	}

	public void placeShip(int row, int col, RadioButton direction) {
		// TODO
		boolean shipPlaced = false;
		int shipDirection = 0;
		if (direction.getText().equals("Right")) {
			shipDirection = 1;
		} else {
			shipDirection = 2;
		}
		switch (shipsPlaced) {
		case 0:
			shipPlaced = currentPlayer.placeShip(Ship.CARRIER.toString(), row, col, shipDirection);
			break;
		case 1:
			shipPlaced = currentPlayer.placeShip(Ship.BATTLESHIP.toString(), row, col, shipDirection);
			break;
		case 2:
			shipPlaced = currentPlayer.placeShip(Ship.CRUISER.toString(), row, col, shipDirection);
			break;
		case 3:
			shipPlaced = currentPlayer.placeShip(Ship.SUBMARINE.toString(), row, col, shipDirection);
			break;
		case 4:
			shipPlaced = currentPlayer.placeShip(Ship.DESTROYER.toString(), row, col, shipDirection);
		}
		if (shipPlaced) {
			shipsPlaced++;
			if (shipsPlaced == 5) {
				turnSwitcher();
				shipTurnsPassed++;
				shipsPlaced = 0;
			}
			view.updateBoards(currentPlayer.getBoard(), otherPlayer.getBoard());
			if (shipTurnsPassed > 1) {
				view.swapToAttacking();
			}
		} else {
			view.alertInvalidPlacement();
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	public int getShipTurnsPassed() {
		return shipTurnsPassed;
	}

	public void setShipTurnsPassed(int shipTurnsPassed) {
		this.shipTurnsPassed = shipTurnsPassed;
	}
}
