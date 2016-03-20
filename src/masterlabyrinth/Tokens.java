package masterlabyrinth;

import java.util.Random;

public class Tokens {
	private int x;
	private int y;
	private int token;

	public Tokens(int n) {
		Random generator = new Random();
		token = n;
		x = generator.nextInt(6);
		y = generator.nextInt(6);

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

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}
}

