package edu.neumont.kinsey.battleshipapi.controller;

import edu.neumont.kinsey.battleshipapi.model.Player;
import edu.neumont.kinsey.battleshipapi.view.BattleshipView;

public class BattleshipController {
	private Player[] players = new Player[2];
	private int turn;
	
	public Player currentPlayer = null;
	public boolean placingShips = true;

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
	}

	public void turnSwitcher() {
		switch (turn) {
		case 0:
			currentPlayer = players[0];
			turn = 1;
			break;
		case 1:
			currentPlayer = players[1];
			turn = 0;
			break;
		}
	}

	public void attack(int row, int col) {
		System.out.println("Attacked");
	}

	public void placeShip(int row, int col) {
		System.out.println("Ship placed");
	}
}
