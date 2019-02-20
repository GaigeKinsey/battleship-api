package edu.neumont.kinsey.battleshipapi.view;

import edu.neumont.kinsey.battleshipapi.controller.BattleshipController;

public interface BattleshipView {

	void registerController(BattleshipController battleshipController);

	void init();
	
	void updateBoards();

	void turnSwitch();

	void alertInvalidPlacement();

	void swapToAttacking();

	void alertHit();

	void alertMiss();

	void alertInvalidAttack();
	
	void shutdown();

	boolean askForShutdown();

	void reset();

	void alertShipSunk(String shipType);

}
