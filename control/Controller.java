package control;

import java.util.Random;
import java.util.Scanner;

import Elements.Slayer;
import Elements.Vampire;
import logic.Buffy;
import logic.Game;
import view.GamePrinter;

public class Controller {

	public static final String helpMsg = String.format(
			"Available commands:%n" + "[a]dd <x> <y>: add a slayer in position x, y%n" + "[h]elp: show this help%n"
					+ "[r]eset: reset game%n" + "[e]xit: exit game%n" + "[n]one | []: update%n");
	public final String prompt = "Command > ";

	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

	private Game game;
	private Scanner scanner;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	public void printGame() {
		System.out.println(game);
	}

	public void run() {
		// USO ESTAS VARIABLES PARA ACABAR SI ALGO NO ESTA BIEN
		boolean exit = false;
		boolean coins = true;
		boolean help = false;
		boolean list = false;

		while ((game.getVampireManager().getVampiresAliveToAppear() > 0) && (!game.getVampireManager().winVampire())
				&& !exit) {
			if (coins && !help && !list) {
				game.update();

				game.setGamePrinter(new GamePrinter(game, game.getRow(), game.getColumn()));
				System.out.println(game);
			}
			// COMANDOS
			System.out.println("Command > ");
			String[] comand = new String[3];
			comand = scanner.nextLine().toLowerCase().split(" ");
			help = false;
			list = false;

			if (comand[0].equals("a") || comand[0].equals("add")) {
				// MIRAR SI EL HUECO ESTA LIBRE
				if (game.checkEmpty(Integer.parseInt(comand[1]), Integer.parseInt(comand[2]))
						&& game.insideBoard(comand[1], comand[2])) {
					// HAY DINERO
					if (game.getcoins().getCoins() >= Slayer.getCost()) {
						coins = true;
						game.getcoins().setCoins(game.getcoins().getCoins() - Slayer.getCost());
						// CREAR Y AÑADIR EL BICHO EN LA LISTA
						Slayer s = new Slayer(Integer.parseInt(comand[1]), Integer.parseInt(comand[2]), game);
						game.getSlayerList().Add(s);
						// AVANCE O CREACION DE VAMPIRO
						int vampireRow = game.getRand().nextInt(game.getRow() - 1);
						if (game.getVampireManager().toAddVampire()
								&& game.checkEmpty(vampireRow, game.getColumn() - 1)) {
							Vampire vampire = new Vampire(vampireRow, game.getColumn() - 1, game);
							game.getVampireList().Add(vampire);
							game.getVampireManager().setVampires(game.getVampireManager().getLeftVampires() - 1);
						}
						game.addCycle();
					} else {
						System.out.println("You dont have enought coins");
						coins = false;
					}
				}
			} else if (comand[0].equals("r") || comand[0].equals("reset")) {
				game.inicializar();
			} else if (comand[0].equals("h") || comand[0].equals("help")) {
				help = true;
				System.out.println("[a]dd <x> <y>: add a slayer in position x, y");
				System.out.println("[h]elp: show this help");
				System.out.println("[r]eset: reset game\r\n");
				System.out.println("[e]xit: exit game");
				System.out.println("[n]one | []: update");
			} else if (comand[0].equals("") || comand[0].equals("none")) {
				coins = true;
				int getRow = game.getColumn();
				int vampireRow = game.getRand().nextInt(getRow);
				if (game.getVampireManager().toAddVampire() && game.checkEmpty(vampireRow, game.getColumn() - 1)) {
					Vampire vampire = new Vampire(vampireRow, game.getColumn() - 1, game);
					game.getVampireList().Add(vampire);
					game.getVampireManager().setVampires(game.getVampireManager().getLeftVampires() - 1);
				}
				game.addCycle();
			} else if ((comand[0].equals("e") || comand[0].equals("exit"))) {
				exit = true;
			} else {
				System.out.println("wrong command");
				comand = scanner.nextLine().toLowerCase().split(" ");
			}
		}
		if (exit) {
			System.out.println("Game Over");
		} else if (game.getVampireManager().winVampire()) {
			System.out.println("\nVampires win");
		} else {
			System.out.println("\nPlayer wins");
		}
	}

}
