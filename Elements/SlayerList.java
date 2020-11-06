package Elements;

public class SlayerList {

	private Slayer[] list;
	private int counter = 0;
	private static final int BOARD_SIZE = 32;

	public SlayerList() {
		list = new Slayer[BOARD_SIZE];
	}

	public boolean Add(Slayer slayer) {
		if (counter < BOARD_SIZE) {
			list[counter] = slayer;
			counter++;
		}
		return counter < BOARD_SIZE;
	}

	public void update() {
		for (int i = 0; i < counter; i++) {
			list[i].update();
		}
	}

	public boolean foundSlayer(int x, int y) {
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

	public void Delete() {
		for (int i = 0; i < counter; i++) {
			if (counter > 0 && list[i].getHealthPoints() <= 0) {
				list[i] = list[i + 1];
				counter--;
			}
		}
	}

	public void decreaseHealth(int pos, int damage) {
		list[pos].decreaseHealth(damage);
	}

	public int searchPosition(int x, int y) {
		int i = 0;
		boolean found = false;
		while (i < counter && !found) {
			if ((list[i].getX() == x) && (list[i].getY() == y)) {
				found = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public String printPosition(int i) {
		return list[i].toString();
	}

	public int getCounter() {
		return counter;
	}
}