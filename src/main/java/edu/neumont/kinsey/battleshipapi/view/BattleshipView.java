package edu.neumont.kinsey.battleshipapi.view;

import edu.neumont.kinsey.battleshipapi.controller.BattleshipController;

public interface BattleshipView {

	void registerController(BattleshipController battleshipController);

	void init();

}
