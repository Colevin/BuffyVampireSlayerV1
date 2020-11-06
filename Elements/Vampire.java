package Elements;

import logic.Game;

public class Vampire {
	private int hp = 5;
	private int speed = 1;
	private int damage = 1;
	private int internalCycle;
	private int x;
	private int y;
	private Game game;

	public Vampire(int x, int y, Game game) {
		this.x = x;
		this.y = 6;
		this.game = game;
		this.internalCycle = 0;
	}

	public void update() {
		internalCycle += 1;

		// COMPROBAR SI HAY HUECO
		if (game.checkEmpty(x, y - 1) && internalCycle % 2 == 0) {
			this.y = this.y - speed;
		} else if (game.checkEmpty(x, y - 1) && internalCycle % 2 != 0) {
			// ESTO LO PONGO ASI PQ QUIERO QUE SE MANTENGA EN SU SITIO PERO TAMBIEN NECESITO
			// UN ELSE PARA ATACAR
			this.y = this.y;
		} else {
			if (!game.seeClose(x,y)) {
				// AQUI ATACO
				game.attackToSlayer(x, y - 1, damage);
			}
		}
	}

	public void decreaseHealth(int damage) {
		hp -= damage;
	}

	public String toString() {
		String str = "V^V [" + this.hp + "]";

		return str;
	}

	// GETTERS Y SETTERS
	public int getHealthPoints() {
		return hp;
	}

	public void setHealthPoints(int healthPoints) {
		this.hp = healthPoints;
	}

	public int getInternalCycle() {
		return internalCycle;
	}

	public void setInternalCycle(int internalCycle) {
		this.internalCycle = internalCycle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDamage() {
		return damage;
	}

}
