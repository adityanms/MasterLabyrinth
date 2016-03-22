package code;

import java.awt.Color;

public class Player {
	private Color _color;
	private int _x, _y;
	
	public Player(Color color){
		_color = color;
	}
	public int getX(){
		return _x;
	}
	public int getY(){
		return _y;
	}
	public void setX(int x){
		_x = x;
	}
	public void setY(int y){
		_y = y;
	}
	public Color getColor(){
		return _color;
	}
}
