package masterlabyrinth;

public class Tiles {
	private int x;
	private int y;
	private boolean move;
	private boolean tokens = false;
	private Tokens t;

	public Tiles(int x, int y, boolean move) {
		this.x = x;
		this.y = y;
		this.move = move;
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

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean isToken() {
		return tokens;
	}

	public void setToken(boolean token) {
		this.tokens = token;
	}

	public Tokens getT() {
		return t;
	}

	public void setT(Tokens t) {
		this.t = t;
	}
	

}
