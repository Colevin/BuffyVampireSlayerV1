package Elements;

import logic.Game;

public class Slayer {
	private final static int cost = 50;
	private int hp = 3;
	private int frequency = 1;
	private final static int damage = 1;
	private int x;
	private int y;
	private Game game;

	public Slayer(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public void update() {
		game.attackToVampire(x, damage);
	}

	public void decreaseHealth(int damage) {
		hp -= damage;
	}

	public String toString() {
		String str = "<-> [" + this.hp + "]";

		return str;
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

	public static int getCost() {
		return cost;
	}

	public int getHealthPoints() {
		return hp;
	}

	public void setHealthPoints(int healthPoints) {
		this.hp = healthPoints;
	}

	public int getFrequency() {
		return frequency;
	}

	public static int getDamage() {
		return damage;
	}
}