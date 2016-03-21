package code;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Board1 {
	public final static int NUMBER_OF_ELBOWS = 15;
	public final static int NUMBER_OF_TEES = 6;
	public final static int NUMBER_OF_STRAIGHTS = 13;
	private boolean _staticboard;

	public final static int HEIGHT = 7;
	public final static int WIDTH = 7;
	private ArrayList<ArrayList<Tile>> _board;
	private Tile _freetile;
	private ArrayList<Tile> _tileset;
	private HashSet<Tile> _playerLocations;
	private HashSet<Tile> _path;

	public Board1(boolean fixed){
		_staticboard=fixed;
		_board = new ArrayList<ArrayList<Tile>>(7);
		initializeBoard();
		//setPlayers();
		//findPaths();
	}
	private void initializeTiles(){
		Random r = new Random();
		_tileset = new ArrayList<Tile>();
		for(int i=0;i<NUMBER_OF_ELBOWS;i++){
			Tile t = new Tile(true,true,false,false);
			if(_staticboard==false){
				for(int j=0;j<=r.nextInt(4);j++){
					t.rotate();
				}
			}
			_tileset.add(t);
		}
		for(int i=0;i<NUMBER_OF_TEES;i++){
			Tile t = new Tile(true,true,true,false);
			if(_staticboard==false){
				for(int j=0;j<=r.nextInt(4);j++){
					t.rotate();
				}
			}
			_tileset.add(t);
		}
		for(int i=0;i<NUMBER_OF_STRAIGHTS;i++){
			Tile t = new Tile(true,false,true,false);
			if(_staticboard==false){
				for(int j=0;j<=r.nextInt(4);j++){
					t.rotate();
				}
			}
			_tileset.add(t);
		}
		if(_staticboard==false){
			Collections.shuffle(_tileset);
		}
	}
	private void initializeStaticTiles(){
		//first column
		_board.get(0).set(0, new Tile(true,true,false,false));
		_board.get(0).set(2, new Tile(true,true,true,false));
		_board.get(0).set(4, new Tile(true,true,true,false));
		_board.get(0).set(6, new Tile(false,true,true,false));
		//third column
		_board.get(2).set(0, new Tile(true,true,false,true));
		_board.get(2).set(2, new Tile(true,true,false,true));
		_board.get(2).set(4, new Tile(true,true,true,false));
		_board.get(2).set(6, new Tile(false,true,true,true));
		//fifth column
		_board.get(4).set(0, new Tile(true,true,false,true));
		_board.get(4).set(2, new Tile(true,false,true,true));
		_board.get(4).set(4, new Tile(false,true,true,true));
		_board.get(4).set(6, new Tile(false,true,true,true));
		//seventh column
		_board.get(6).set(0, new Tile(true,false,false,true));
		_board.get(6).set(2, new Tile(true,false,true,true));
		_board.get(6).set(4, new Tile(true,false,true,true));
		_board.get(6).set(6, new Tile(false,false,true,true));
	}
	private void initializeBoard(){
		for(int i=0;i<WIDTH;i++){
			ArrayList<Tile> column = new ArrayList<Tile>();
			for(int j=0;j<HEIGHT;j++){
				Tile t = null;
				column.add(t);
			}
			_board.add(column);
		}

		initializeTiles();

		for(int i=0;i<WIDTH;i++){
			ArrayList<Tile> column = _board.get(i);
			for(int j=0;j<HEIGHT;j++){
				if(!(i%2==0&&j%2==0)){//if 2d index does not consist of two evens
					column.set(j, _tileset.get(0));
					_tileset.remove(0);
				}
			}
		}
		initializeStaticTiles();
		_freetile = _tileset.get(0);
		_tileset.remove(0);
		System.out.println(_tileset.size());
	}
	public void setPlayers(int x, int y){
		_playerLocations = new HashSet<Tile>();
		_playerLocations.add(getTile(x,y));
	}

	public Tile getTile(int x, int y){
		Tile ret = _board.get(x).get(y);
		return ret;
	}	
	public Point getTileLocation(Tile t){
		for(int i=0;i<_board.size();i++){
			for(int j=0;j<_board.get(i).size();j++){
				if(t==getTile(i,j)){
					return new Point(i,j);
				}
			}
		}
		return new Point(-1,-1);
	}

	public void findPaths(){
		Iterator<Tile> it = _playerLocations.iterator();
		while(it.hasNext()){
			Tile t = it.next();
			Point p = getTileLocation(t);
			_path = new HashSet<Tile>();
			_path.add(t);
			checkNeighbors(p.x,p.y);
			System.out.println(_path.size());
		}
	}
	private void checkNeighbors(int x, int y) {
		if(y==0){//tile is at bottom of board
			if(x==0){//tile is in first column, bottom row
				checkNorth(x, y);
				checkEast(x, y);
			}
			else if(x==WIDTH-1){//tile is in last column, bottom row
				checkNorth(x, y);
				checkWest(x, y);
			}
			else{//tile is in some middle column, bottom row
				checkNorth(x, y);
				checkEast(x, y);
				checkWest(x, y);
			}
		}
		else if(y==HEIGHT-1){//tile is at top of board
			if(x==0){//tile is in first column, top row
				checkEast(x,y);
				checkSouth(x,y);
			}
			else if(x==WIDTH-1){//tile is in last column, top row
				checkWest(x,y);
				checkSouth(x,y);
			}
			else{//tile is in some middle column, top row
				checkEast(x,y);
				checkSouth(x,y);
				checkWest(x,y);
			}
		}
		else{//tile is in a middle row
			if(x==0){//tile is in first column, in some middle row
				checkNorth(x,y);
				checkEast(x,y);
				checkSouth(x,y);
			}
			else if(x==WIDTH-1){//tile is in last column, in some middle row
				checkNorth(x,y);
				checkWest(x,y);
				checkSouth(x,y);
			}
			else{//tile is in some middle column of some middle row
				checkNorth(x,y);
				checkEast(x,y);
				checkSouth(x,y);
				checkWest(x,y);
			}
		}
	}
	private void checkWest(int x, int y) {
		if(getTile(x,y).getWest()==true&&getTile(x-1,y).getEast()==true){
			if(!_path.contains(getTile(x-1,y))){
				_path.add(getTile(x-1,y));
				checkNeighbors(x-1,y);
			}
		}
	}
	private void checkEast(int x, int y) {
		if(getTile(x,y).getEast()==true&&getTile(x+1,y).getWest()==true){
			if(!_path.contains(getTile(x+1,y))){
				_path.add(getTile(x+1,y));
				checkNeighbors(x+1,y);
			}
		}
	}
	private void checkNorth(int x, int y) {
		if(getTile(x,y).getNorth()==true&&getTile(x,y+1).getSouth()==true){
			if(!_path.contains(getTile(x,y+1))){
				_path.add(getTile(x,y+1));
				checkNeighbors(x,y+1);
			}
		}
	}
	private void checkSouth(int x, int y) {
		if(getTile(x,y).getSouth()==true&&getTile(x,y-1).getNorth()==true){
			if(!_path.contains(getTile(x,y-1))){
				_path.add(getTile(x,y-1));
				checkNeighbors(x,y-1);
			}
		}
	}

	public int getPathSize(){
		return _path.size();
	}
}