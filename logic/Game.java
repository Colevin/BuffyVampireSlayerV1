package logic;

import java.util.Random;

import Elements.Board;
import Elements.Slayer;
import Elements.Vampire;
import Elements.VampireManager;
import view.GamePrinter;

public class Game {
	// Atributos
	private Board board;

	private GamePrinter gamePrinter;
	private VampireManager vampireManager;
	private Player player;
	private Level level;
	private Random rand;
	private int numCycles;
	private final int row = 7;
	private final int column = 4;

	// Constuctor
	public Game(Level level, Random rand) {
		this.level = level;
		this.rand = rand;
		this.inicializar();
	}

	// GAME STUFF
	public void update() {
		board.update();
		this.removeDead();
		this.player.calculateCoinCicle();
	}

	public void inicializar() {
		this.board = new Board();
		board.initialize();
		numCycles = 0;
		this.gamePrinter = new GamePrinter(this, row, column);
		this.player = new Player(this);
		player.setCoins(50);
		this.vampireManager = new VampireManager(this);
	}

	public void addCycle() {
		this.numCycles++;
	}

	public void removeDead() {
		if (board.deleteVamp())
			vampireManager.setVampiresAliveToAppear(vampireManager.getVampiresAliveToAppear() - 1);
		board.deleteSlayer();
		;
	}

	public boolean checkEmpty(int x, int y) {
		return board.checkEmpty(x, y);
	}

	public boolean insideBoard(String x, String y) {
		return Integer.parseInt(x) >= 0 && Integer.parseInt(x) < row && Integer.parseInt(y) >= 0
				&& Integer.parseInt(y) < column;
	}

	public String getObject(int x, int y) {
		return board.getObject(x, y);
	}

	// SLAYER STUFF
	public void attackToVampire(int x, int damage) {
		int i = 0;
		while (i < column && !board.foundVampire(x, i)) {
			i++;
		}
		if (board.foundVampire(x, i)) {
			board.vampDecreaseHealth(board.searchPositionV(x, i), damage);
		}
	}

	// VAMPIRE STUFF
	public void attackToSlayer(int x, int y, int damage) {
		if (board.foundSlayer(x, y)) {
			board.slayDecreaseHealth(board.searchPositionS(x, y), damage);
		}
	}
	public boolean seeClose(int x , int y) {
		return board.foundVampire(x, y-1);
	}

	public boolean checkWinnerVampire() {
		return board.checkWinnerVampire();
	}
	public int getCounterV() {
		return board.vampCounter();
	}

	// TO STRING
	public String toString() {
		String row1 = "Number of cycles: " + numCycles;
		String row2 = "\nCoins: " + player.getCoins();
		String row3 = "\nRemaining vampires: " + vampireManager.getVampiresAliveToAppear();
		String row4 = "\nVampires on the board: " + getCounterV();

		return row1 + row2 + row3 + row4 + gamePrinter.toString();
	}

	// COMANDOS
	public void addCommandExe(int param1, int param2) {
		if (checkEmpty(param1, param2) && insideBoard(Integer.toString(param1), Integer.toString(param2))) {
			// HAY DINERO
			if (player.getCoins() >= Slayer.getCost()) {
				player.setCoins(player.getCoins() - Slayer.getCost());
				// CREAR Y AÑADIR EL BICHO EN LA LISTA
				Slayer s = new Slayer(param1, param2, this);
				board.addSlayer(s);
				// AVANCE O CREACION DE VAMPIRO
				int vampireRow = rand.nextInt(getRow() - 1);
				if (vampireManager.toAddVampire() && checkEmpty(vampireRow, getColumn() - 1)) {
					Vampire vampire = new Vampire(vampireRow, getColumn() - 1, this);
					board.addVamp(vampire);
					vampireManager.setVampires(vampireManager.getLeftVampires() - 1);
				}
				addCycle();
			} else {
				System.out.println("You dont have enought coins");
			}
		}
	}

	public void noCommandExe() {
		int getRow = getColumn();
		int vampireRow = getRand().nextInt(getRow);
		if (vampireManager.toAddVampire() && checkEmpty(vampireRow, getColumn() - 1)) {
			Vampire vampire = new Vampire(vampireRow, getColumn() - 1, this);
			board.addVamp(vampire);
			vampireManager.setVampires(vampireManager.getLeftVampires() - 1);
		}
		addCycle();
	}

	public boolean vampiresLeft() {
		return vampireManager.getVampiresAliveToAppear() > 0;
	}

	public boolean winVamp() {
		return vampireManager.winVampire();
	}

	public void updateGamePrinter() {
		this.setGamePrinter(new GamePrinter(this, this.row, this.column));
	}

	// GETTERS Y SETTERS
	public Level getLevel() {
		return level;
	}

	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}

	public Random getRand() {
		return rand;
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