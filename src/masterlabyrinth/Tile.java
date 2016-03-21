package code;

public class Tile {
	private boolean _north,_east,_south,_west;
	
	public Tile(boolean north,boolean east, boolean south, boolean west){
		_north = north;
		_south = south;
		_east = east;
		_west = west;
	}

	public boolean getNorth(){return _north;}
	public boolean getSouth(){return _south;}
	public boolean getEast(){return _east;}
	public boolean getWest(){return _west;}
	
	public void rotate(){
		boolean temp = _north;
		_north = _west;
		_west = _south;
		_south = _east;
		_east = temp;
	}

	@Override
	public String toString() {
		return "Tile [_north=" + _north + ", _east=" + _east + ", _south=" + _south + ", _west=" + _west + "]";
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (_east != other._east)
			return false;
		if (_north != other._north)
			return false;
		if (_south != other._south)
			return false;
		if (_west != other._west)
			return false;
		return true;
	}
	
}
