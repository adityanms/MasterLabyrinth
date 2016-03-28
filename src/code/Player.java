package code;

import java.awt.Color;

public class Player {
	private Color _color;
	private int _x, _y;
	private String _name;
	
	/**
	 * Constructor
	 * @param color color of the player
	 */
	public Player(Color color){
		_color = color;
	}
	
	public Player(String name){
		_name=name;
	}
	
	/**
	 * Returns the x coordinate of the player
	 * @return
	 */
	public int getX(){
		return _x;
	}
	
	/**
	 * Returns the y coordinate of the player
	 * @return
	 */
	public int getY(){
		return _y;
	}
	
	/** 
	 * Sets the x coordinate of the player
	 * @param x
	 */
	public void setX(int x){
		_x = x;
	}
	
	/** 
	 * Sets the y coordinate of the player
	 * @param y
	 */
	public void setY(int y){
		_y = y;
	}
	
	/**
	 * Gets the color of the player
	 * @return
	 */
	public Color getColor(){
		return _color;
	}
}
