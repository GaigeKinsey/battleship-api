package edu.neumont.kinsey.battleshipapi.controller;

import edu.neumont.kinsey.battleshipapi.model.Player;
import edu.neumont.kinsey.battleshipapi.model.Ship;
import edu.neumont.kinsey.battleshipapi.model.Type;
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
		this.view.turnSwitch();
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

	public void attack(int col, int row) {
		boolean valid = false;
		if (otherPlayer.getBoard().getTileValue(col, row) == Type.S) {
			if (otherPlayer.recieveHit(otherPlayer.getBoard().getShipType(col, row))) {
				view.alertShipSunk(otherPlayer.getBoard().getShipType(col, row));
			}
			otherPlayer.getBoard().setTileType(col, row, Type.X);
			valid = true;
			view.alertHit();
		} else if (otherPlayer.getBoard().getTileValue(col, row) == Type.W) {
			otherPlayer.getBoard().setTileType(col, row, Type.O);
			valid = true;
			view.alertMiss();
		} else {
			view.alertInvalidAttack();
		}
		if (valid) {
			turnSwitcher();
		}
		view.updateBoards();
		if (currentPlayer.isDead()) {
			this.playerWon();
		}
	}

	public void placeShip(int col, int row, RadioButton direction) {
		boolean shipPlaced = false;
		int shipDirection = 0;
		if (direction.getText().equals("Right")) {
			shipDirection = 1;
		} else {
			shipDirection = 2;
		}
		switch (shipsPlaced) {
		case 0:
			shipPlaced = currentPlayer.placeShip(Ship.CARRIER.toString(), col, row, shipDirection);
			break;
		case 1:
			shipPlaced = currentPlayer.placeShip(Ship.BATTLESHIP.toString(), col, row, shipDirection);
			break;
		case 2:
			shipPlaced = currentPlayer.placeShip(Ship.CRUISER.toString(), col, row, shipDirection);
			break;
		case 3:
			shipPlaced = currentPlayer.placeShip(Ship.SUBMARINE.toString(), col, row, shipDirection);
			break;
		case 4:
			shipPlaced = currentPlayer.placeShip(Ship.DESTROYER.toString(), col, row, shipDirection);
		}
		if (shipPlaced) {
			shipsPlaced++;
			if (shipsPlaced == 5) {
				turnSwitcher();
				shipTurnsPassed++;
				shipsPlaced = 0;
			}
			if (shipTurnsPassed > 1) {
				view.swapToAttacking();
			}
			view.updateBoards();
			switch (shipsPlaced) {
			case 0:
				view.updateShipSize(5);
				break;
			case 1:
				view.updateShipSize(4);
				break;
			case 2:
				view.updateShipSize(3);
				break;
			case 3:
				view.updateShipSize(3);
				break;
			case 4:
				view.updateShipSize(2);
				break;
			}
		} else {
			view.alertInvalidPlacement();
		}
	}

	private void playerWon() {
		if (view.askForShutdown()) {
			this.reset();
		} else {
			view.shutdown();
		}
	}

	public void reset() {
		players = new Player[2];
		turn = 0;
		currentPlayer = null;
		otherPlayer = null;
		shipsPlaced = 0;
		shipTurnsPassed = 0;
		view.reset();
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
