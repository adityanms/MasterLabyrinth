package code;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
/**
 * A board for use in the game of MasterLabyrinth.  Essentially a collection of Tile objects.
 * It keeps track of the 49 tiles (a 7x7 grid) on the game board itself, plus the one "free" tile that is available for tile moves.
 * This is an ArrayList implementation in which tiles are placed in ArrayList columns and these columns are placed in the 
 * underlying ArrayList of ArrayLists.  Currently the board handles pathfinding as well, although this is likely to change in
 * subsequent versions.
 * 
 * @author team112
 * @version 1.0
 */
public class Board {

	public final static int HEIGHT = 7;
	public final static int WIDTH = 7;
	public final static int NUMBER_OF_ELBOWS = 15;
	public final static int NUMBER_OF_TEES = 6;
	public final static int NUMBER_OF_STRAIGHTS = 13;

	private boolean _staticboard;


	private ArrayList<ArrayList<Tile>> _board;
	private Tile _freetile;
	private ArrayList<Tile> _tileset;
	private HashSet<Tile> _playerLocations;
	private HashSet<Tile> _tokenLocations;

	private HashSet<Tile> _path;

	/**
	 * Creates a board of Tiles.  As of now, it has a parameter to set the board to a dynamic state (for playing) and a 
	 * static state (for testing).
	 * @param fixed boolean representing the fixed or non-fixed state of the board upon creation
	 */
	public Board(boolean fixed){
		_staticboard=fixed;
		_board = new ArrayList<ArrayList<Tile>>(7);
		initializeBoard();
	}

	/**
	 * Helper method for constructor.  Initializes the underlying ArrayList structure.  (At first to all null values so that using
	 * the ArrayList's set(index, Element) method works.  Then populates the non-static spaces on the board with Tiles from
	 * _tileset.
	 */
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
	}

	/**
	 * Helper method for initializeBoard().  This method creates the non-static tiles on the board according to how many there are
	 * in the actual board game.  It uses the static NUMBER_OF_* variables to do this.  It adds these specific tiles to a collection
	 * of tiles (_tileset) which are later placed on the board.  If the board is non-static, it rotates each tile a random number of 
	 * times upon creation, and then shuffles the collection of tiles before they are placed on the board.
	 */
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

	/**
	 * Helper method for initializeBoard().  It handles the creation and placement of the 16 static (non-movable, non-rotatable)
	 * tiles on the board.
	 */
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

	/**
	 * Gets a specific tile on the board.
	 * 
	 * @param x the Tile's x coordinate
	 * @param y the Tile's y coordinate
	 * @return The Tile at the given coordinates.
	 */
	public Tile getTile(int x, int y){
		Tile ret = _board.get(x).get(y);
		return ret;
	}	
	/**
	 * Given a specific Tile, returns the x,y coordinates of the tile on the board.
	 * @param t the tile whose location is desired.
	 * @return the x,y coordinates of the tile on the board as a Point object.  If not found, returns Point(-1,-1)
	 */
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
	/**
	 * Given a Tile t, generates a HashSet of Tiles that represents the legal moves available to a player.  This method will be called
	 * on a player's turn, after their tile move, with the Tile that the Player is on.
	 * @param t the tile to find available paths from
	 */
	public void findPath(Tile t){ 
		Point p = getTileLocation(t);
		_path = new HashSet<Tile>();
		_path.add(t);
		checkNeighbors(p.x,p.y);
		System.out.println("Path size is: "+_path.size());
	}
	/**
	 * Helper method for findPath.  The logic involved in checking tiles that are adjacent to a given Tile.
	 * @param x the x coordinate of the Tile to check
	 * @param y the y coordinate of the Tile to check
	 */
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
	/**
	 * Helper method for checkNeighbors.  Compares the Tile in question's _north value with it's northern neighbor's _south value.
	 * If both are true, a valid path exists between the two tiles and the northern neighbor is added to the _path HashSet.
	 * @param x the x coodinate of the tile to check the northern neighbor of
	 * @param y the y coordinate of the tile to check the northern neighbor of
	 */
	private void checkNorth(int x, int y) {
		if(getTile(x,y).getNorth()==true&&getTile(x,y+1).getSouth()==true){
			if(!_path.contains(getTile(x,y+1))){
				_path.add(getTile(x,y+1));
				checkNeighbors(x,y+1);
			}
		}
	}
	/**
	 * Helper method for checkNeighbors.  Compares the Tile in question's _east value with it's eastern neighbor's _west value.
	 * If both are true, a valid path exists between the two tiles and the eastern neighbor is added to the _path HashSet.
	 * @param x the x coodinate of the tile to check the eastern neighbor of
	 * @param y the y coordinate of the tile to check the eastern neighbor of
	 */
	private void checkEast(int x, int y) {
		if(getTile(x,y).getEast()==true&&getTile(x+1,y).getWest()==true){
			if(!_path.contains(getTile(x+1,y))){
				_path.add(getTile(x+1,y));
				checkNeighbors(x+1,y);
			}
		}
	}
	/**
	 * Helper method for checkNeighbors.  Compares the Tile in question's _south value with it's southern neighbor's _north value.
	 * If both are true, a valid path exists between the two tiles and the southern neighbor is added to the _path HashSet.
	 * @param x the x coodinate of the tile to check the southern neighbor of
	 * @param y the y coordinate of the tile to check the southern neighbor of
	 */
	private void checkSouth(int x, int y) {
		if(getTile(x,y).getSouth()==true&&getTile(x,y-1).getNorth()==true){
			if(!_path.contains(getTile(x,y-1))){
				_path.add(getTile(x,y-1));
				checkNeighbors(x,y-1);
			}
		}
	}
	/**
	 * Helper method for checkNeighbors.  Compares the Tile in question's _west value with it's western neighbor's _east value.
	 * If both are true, a valid path exists between the two tiles and the western neighbor is added to the _path HashSet.
	 * @param x the x coodinate of the tile to check the western neighbor of
	 * @param y the y coordinate of the tile to check the western neighbor of
	 */
	private void checkWest(int x, int y) {
		if(getTile(x,y).getWest()==true&&getTile(x-1,y).getEast()==true){
			if(!_path.contains(getTile(x-1,y))){
				_path.add(getTile(x-1,y));
				checkNeighbors(x-1,y);
			}
		}
	}
	/**
	 * Accessor for size of current path.
	 * @return an int value corresponding to the size of the current path
	 */
	public int getPathSize(){
		return _path.size();
	}
	/**
	 * Accessor for the current "free tile" (the one available to insert)
	 * @return the free Tile
	 */
	public Tile getFreeTile(){
		return _freetile;
	}

	public void addPlayer(Player p, int x, int y) {
		p.setX(x);
		p.setY(y);
		Tile t = getTile(x, y);
		t.setPlayer(p);
	}

	public boolean movePlayer(Player p, int x, int y) {
		if(_path.contains(getTile(x,y))){
			getTile(p.getX(), p.getY()).removePlayer(p);
			getTile(x,y).setPlayer(p);
			p.setX(x);
			p.setY(y);
			return true;
		}
		return false;
	}
	/**
	 * Inserts the "free" tile into the designated column at either the top or bottom, and shifts the other values accordingly, 
	 * generating a new free tile.  Only allows shifts on odd numbered indices.  Returns true if successful, false if unsuccessful.
	 * 
	 * @param col int representing the column to shift
	 * @param top boolean indicating whether the freetile is to be inserted at the "top" of the list 
	 */
	public boolean shiftColumn(int col, boolean top){
		if(col%2==1){
			if(top==true){
				Tile t = getTile(col,0);
				_board.get(col).remove(0);
				_board.get(col).add(_freetile);
				_freetile = t;
			}
			else{
				Tile t = getTile(col,6);
				_board.get(col).remove(6);
				_board.get(col).add(0, _freetile);
				_freetile = t;
			}
			return true;
		}
		return false;
	}
	/**
	 * Inserts the "free" tile into the designated row at either the front or back, and shifts the other values accordingly, 
	 * generating a new free tile.  Only allows shifts on odd numbered indices.  Returns true if successful, false if unsuccessful.
	 * 
	 * @param row int representing the row to shift
	 * @param back a boolean indicating whether the _freetile is inserted at the "back" of the list.
	 * @return
	 */
	public boolean shiftRow(int row, boolean back){

		if(row%2==1){
			if(back==true){
				Tile t = getTile(0,row);
				_board.get(0).set(row, getTile(1,row));
				_board.get(1).set(row, getTile(2,row));
				_board.get(2).set(row, getTile(3,row));
				_board.get(3).set(row, getTile(4,row));
				_board.get(4).set(row, getTile(5,row));
				_board.get(5).set(row, getTile(6,row));
				_board.get(6).set(row, _freetile);
				_freetile = t;
			}
			else{
				Tile t = getTile(6,row);
				_board.get(6).set(row, getTile(5,row));
				_board.get(5).set(row, getTile(4,row));
				_board.get(4).set(row, getTile(3,row));
				_board.get(3).set(row, getTile(2,row));
				_board.get(2).set(row, getTile(1,row));
				_board.get(1).set(row, getTile(0,row));
				_board.get(0).set(row, _freetile);
				_freetile = t;
			}
			return true;
		}
		return false;
	}
	public void rotateFreeTile(){
		_freetile.rotate();
	}
}