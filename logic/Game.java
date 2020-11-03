package logic;

import java.util.Random;

import Elements.SlayerList;
import Elements.VampireList;
import Elements.VampireManager;
import view.GamePrinter;

public class Game {
	// atributos
	private SlayerList slayerList;
	private VampireList vampireList;
	private GamePrinter gamePrinter;
	private VampireManager vampireManager;
	private Player coins;
	private Level level;
	private Random rand;
	private int numCycles;
	private final int row = 7;
	private final int column = 4;

	// constuctor
	public Game(Level level, Random rand) {
		this.level = level;
		this.rand = rand;
		this.inicializar();
	}

	// GAME STUFF
	public void update() {
		slayerList.update();
		vampireList.update();
		this.removeDead();
		this.coins.calculateCoinCicle();
	}

	public void inicializar() {
		vampireList = new VampireList();
		slayerList = new SlayerList();
		numCycles = 0;
		this.gamePrinter = new GamePrinter(this, row, column);
		this.coins = new Player(this);
		coins.setCoins(50);
		this.vampireManager = new VampireManager(this);
	}

	public void addCycle() {
		this.numCycles++;
	}

	public void removeDead() {
		if (vampireList.Delete())
			vampireManager.setVampiresAliveToAppear(vampireManager.getVampiresAliveToAppear() - 1);
			slayerList.Delete();
	}

	public boolean checkEmpty(int x, int y) {
		boolean empty = false;
		if (!slayerList.foundSlayer(x, y) && !vampireList.vampireFound(x, y)) {
			empty = true;
		}
		return empty;
	}

	public boolean insideBoard(String x, String y) {
		return Integer.parseInt(x) >= 0 && Integer.parseInt(x) < row && Integer.parseInt(y) >= 0
				&& Integer.parseInt(y) < column;
	}

	public String getObject(int x, int y) {
		String str;
		if (slayerList.foundSlayer(x, y)) {
			str = slayerList.printPosition(slayerList.searchPosition(x, y));
		} else if (vampireList.vampireFound(x, y)) {
			str = vampireList.printPosition(vampireList.searchPosition(x, y));
		} else {
			str = " ";
		}
		return str;
	}

	// SLAYER STUFF
	public void attackToVampire(int x, int damage) {
		int i = 0;
		while (i < column && !vampireList.vampireFound(x, i)) {
			i++;
		}
		if (vampireList.vampireFound(x, i)) {
			vampireList.decreaseHealth(vampireList.searchPosition(x, i), damage);
		}
	}

	// VAMPIRE STUFF
	public void attackToSlayer(int x, int y, int damage) {
		if (slayerList.foundSlayer(x, y)) {
			slayerList.decreaseHealth(slayerList.searchPosition(x, y), damage);
		}
	}

	public boolean checkWinnerVampire() {
		boolean found = false;
		if (vampireList.winnerVampire())
			found = true;
		return found;
	}

	// TO STRING
	public String toString() {
		String row1 = "Number of cycles: " + numCycles;
		String row2 = "\nCoins: " + coins.getCoins();
		String row3 = "\nRemaining vampires: " + vampireManager.getVampiresAliveToAppear();
		String row4 = "\nVampires on the board: " + vampireList.getcounter();

		return row1 + row2 + row3 + row4 + gamePrinter.toString();
	}

	// GETTERS Y SETTERS
	public Level getLevel() {
		return level;
	}

	public SlayerList getSlayerList() {
		return slayerList;
	}

	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}

	public VampireList getVampireList() {
		return vampireList;
	}

	public Random getRand() {
		return rand;
	}

	public Player getcoins() {
		return coins;
	}

	public int getNumCycles() {
		return numCycles;
	}

	public void setNumCycles(int numCycles) {
		this.numCycles = numCycles;
	}

	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}

	public VampireManager getVampireManager() {
		return vampireManager;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}