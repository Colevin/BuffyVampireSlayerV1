package Elements;

public class Board {
	private VampireList vampireList;
	private SlayerList slayerList;

	public Board() {
		this.vampireList = new VampireList();
		this.slayerList = new SlayerList();
	}
	public boolean checkEmpty(int x, int y) {
		boolean empty = false;
		if (!foundSlayer(x, y) && !foundVampire(x, y)) {
			empty = true;
		}
		return empty;
	}
	public String getObject(int x, int y) {
		String str;
		if (foundSlayer(x, y)) {
			str = printPositionS(searchPositionS(x, y));
		} else if (foundVampire(x, y)) {
			str = printPositionV(searchPositionV(x, y));
		} else {
			str = " ";
		}
		return str;
	}
	
	public boolean checkWinnerVampire() {
		boolean found = false;
		if (vampireList.winnerVampire())
			found = true;
		return found;
	}
	public void update() {
		slayerList.update();
		vampireList.update();
	}

	public void initialize() {
		vampireList = new VampireList();
		slayerList = new SlayerList();
	}

	public boolean deleteVamp() {
		return vampireList.Delete();
	}

	public void deleteSlayer() {
		slayerList.Delete();
	}

	public boolean foundSlayer(int x, int y) {
		return slayerList.foundSlayer(x, y);
	}

	public boolean foundVampire(int x, int y) {
		return vampireList.vampireFound(x, y);
	}

	public int searchPositionS(int x, int y) {
		return slayerList.searchPosition(x, y);
	}

	public int searchPositionV(int x, int y) {
		return vampireList.searchPosition(x, y);
	}

	public String printPositionS(int i) {
		return slayerList.printPosition(i);
	}

	public String printPositionV(int i) {
		return vampireList.printPosition(i);
	}
	public void vampDecreaseHealth(int pos, int damage) {
		vampireList.decreaseHealth(pos, damage);
	}
	public void slayDecreaseHealth(int pos, int damage) {
		slayerList.decreaseHealth(pos, damage);
	}
	public int vampCounter() {
		return vampireList.getcounter();
	}
	public void addVamp(Vampire vamp) {
		vampireList.Add(vamp);
	}
	public void addSlayer(Slayer slay) {
		slayerList.Add(slay);
	}
}
