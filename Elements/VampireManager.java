package Elements;

import logic.Game;

public class VampireManager {
	private Game game;
	private int leftVampire;
	private int aliveVampireLeft;

	public VampireManager(Game game) {
		this.game = game;
		this.leftVampire = game.getLevel().getNumberOfVampires();
		this.aliveVampireLeft = game.getLevel().getNumberOfVampires();
	}

	public boolean toAddVampire() {
		boolean add = false;
		if (leftVampire > 0) {
			add = game.getRand().nextDouble() < game.getLevel().getVampireFrequency();
		}
		return add;
	}

	public boolean winVampire() {
		boolean winnerVampire = false;
		int i = 0;
		while (i < game.getVampireList().getcounter() && !winnerVampire) {
			if (game.checkWinnerVampire()) {
				winnerVampire = true;
			}
			i++;
		}
		return winnerVampire;
	}

	public int getLeftVampires() {
		return leftVampire;
	}

	public void setVampires(int leftVampire) {
		this.leftVampire = leftVampire;
	}

	public int getVampiresAliveToAppear() {
		return aliveVampireLeft;
	}

	public void setVampiresAliveToAppear(int aliveVampireLeft) {
		this.aliveVampireLeft = aliveVampireLeft;
	}
}
