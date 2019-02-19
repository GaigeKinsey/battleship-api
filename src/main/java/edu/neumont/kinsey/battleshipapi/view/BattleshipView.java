package edu.neumont.kinsey.battleshipapi.view;

import edu.neumont.kinsey.battleshipapi.controller.BattleshipController;
import edu.neumont.kinsey.battleshipapi.model.Board;

public interface BattleshipView {

	void registerController(BattleshipController battleshipController);

	void init();
	
	void updateBoards(Board ownBoard, Board otherBoard);

	void alertTurnSwitch();

	void updateButtonEventHandlers();

	void alertInvalidPlacement();

	void swapToAttacking();

}
