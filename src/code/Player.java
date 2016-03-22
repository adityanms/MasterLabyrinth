package code;

import java.awt.Color;

public class Player {
	Color _color;
	int _x, _y;
	
	public Player(Color color, int x, int y){
		_color = color;
		_x = x;
		_y = y;
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
}
