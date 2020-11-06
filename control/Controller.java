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

		while (game.vampiresLeft() && (!game.winVamp()) && !exit) {
			if (coins && !help && !list) {
				game.update();
				game.updateGamePrinter();
				System.out.println(game);
			}
			// COMANDOS
			System.out.println("Command > ");
			String[] comand = new String[3];
			comand = scanner.nextLine().toLowerCase().split(" ");
			help = false;
			list = false;

			if (comand[0].equals("a") || comand[0].equals("add")) {
				game.addCommandExe(Integer.parseInt(comand[1]), Integer.parseInt(comand[2])); 
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
				game.noCommandExe();
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
