package masterlabyrinth;
/**
 * Tiles Class. This class describes the functionalities of a tile.
 * 
 * @author Team112
 * @version 1.0
 *
 */
public class Tiles {
	private int x;
	private int y;
	private boolean move;
	private boolean tokens = false;
	private Tokens t;
	/**
	 * 
	 * @param x x coordinate of tile
	 * @param y y coordinate of tile
	 * @param move Whether the tile is movable or not
	 */
	public Tiles(int x, int y, boolean move) {
		this.x = x;
		this.y = y;
		this.move = move;
	}

	/**
	 * This method returns the x coordinate of the tile
	 * @return x returns the x coordinate of the tile
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * This method sets the x coordinate of the tile
	 * @param x the x coordinate passed as a parameter
	 */

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * This method returns the y coordinate of the tile
	 * @return y the y coordinate of the tile
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * This method sets the y coordinate of the tile
	 * @param y the y coordinate passed as a parameter
	 */

	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Describes whether the tile is movable or not
	 * @return move
	 */
	public boolean isMove() {
		return move;
	}
	/**
	 * Sets if a tile is movable or not
	 * @param move
	 */
	public void setMove(boolean move) {
		this.move = move;
	}

	/**
	 * Describes if a tile has a token or not
	 * @return tokens
	 */
	public boolean isToken() {
		return tokens;
	}

	/**
	 * Sets if a tile has token or not
	 * @param token
	 */
	public void setToken(boolean token) {
		this.tokens = token;
	}

	/**
	 * Returns the token on a tile
	 * @return
	 */
	public Tokens getT() {
		return t;
	}

	/**
	 * Sets the token on a tile
	 * @param t
	 */
	public void setT(Tokens t) {
		this.t = t;
	}
	

}
