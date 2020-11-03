package Elements;

public class VampireList {
	private Vampire[] list;
	int counter = 0;
	private static final int NUM_MAX_VAMPIRES = 10;

	public VampireList() {
		list = new Vampire[NUM_MAX_VAMPIRES];
	}

	public boolean Add(Vampire vampire) {
		if (counter < NUM_MAX_VAMPIRES) {
			list[counter] = vampire;
			counter++;
		}
		return counter < NUM_MAX_VAMPIRES;
	}

	public boolean Delete() {
		boolean deleted = false;
		for (int i = 0; i < counter; i++) {
			if (list[i].getHealthPoints() <= 0) {
				for(int j = i; j < counter; j++) {
					list[j] = list[j + 1];
					deleted = true;
				}
				counter--;
			}
		}
		return deleted;
	}

	public void update() {
		for (int i = 0; i < counter; i++) {
			list[i].update();
		}
	}

	public void decreaseHealth(int pos, int damage) {
		list[pos].decreaseHealth(damage);
	}

	public boolean vampireFound(int x, int y) {
		int i = 0;
		boolean found = false;
		while (i < counter && !found) {
			if ((list[i].getX() == x) && (list[i].getY() == y)) {
				found = true;
			} else {
				i++;
			}
		}
		return found;
	}

	public boolean winnerVampire() {
		int i = 0;
		boolean found = false;
		while (i < counter && !found) {
			if (list[i].getY() == 0) {
				found = true;
			}
			i++;
		}
		return found;
	}

	public int searchPosition(int x, int y) {
		int i = 0;
		boolean encontrado = false;
		while (i < counter && !encontrado) {
			if ((list[i].getX() == x) && (list[i].getY() == y)) {
				encontrado = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public String printPosition(int i) {
		return list[i].toString();
	}

	public int getcounter() {
		return counter;
	}
}