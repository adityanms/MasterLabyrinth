package test;
import code.Board;
import code.Player;
import code.Tile;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class ProjectTests {
	@Test public void rotateTest(){
		Tile actual = new Tile(true,true,false,false);
		actual.rotate();
		Tile expected = new Tile(false,true,true,false);
		assertTrue("Expected: "+expected+" Actual: "+actual,actual.equals(expected));
	}
	@Test public void multipleRotationTest(){
		Tile actual = new Tile(true,true,true,false);
		actual.rotate();
		actual.rotate();
		actual.rotate();
		actual.rotate();
		Tile expected = new Tile(true,true,true,false);
		assertTrue("Expected: "+expected+" Actual: "+actual,actual.equals(expected));
	}
	@Test public void boardStaticInitTest(){
		Board b = new Board(true);
		Tile t = new Tile(true,true,false,false);
		boolean actual = b.getTile(1,1).equals(t);
		t = new Tile(true,true,true,false);
		actual=actual&&b.getTile(3, 2).equals(t);
		t = new Tile(true,false,true,false);
		actual=actual&&b.getTile(6, 1).equals(t);
		boolean expected = true;
		assertTrue("",actual==expected);
	}
	@Test public void pathingTest01(){
		Board b = new Board(true);
		b.findPath(b.getTile(3, 1));
		int expected = 14;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void pathingTest02(){
		Board b = new Board(true);
		b.findPath(b.getTile(0, 0));
		int expected = 1;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void pathingTest03(){
		Board b = new Board(true);
		b.findPath(b.getTile(0, 1));
		int expected = 2;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void pathingTest04(){
		Board b = new Board(true);
		b.findPath(b.getTile(5, 0));
		int expected = 7;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void pathingTest05(){
		Board b = new Board(true);
		b.findPath(b.getTile(1, 6));
		int expected = 3;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void pathingTest06(){
		Board b = new Board(true);
		b.findPath(b.getTile(2, 3));
		int expected = 2;
		int actual = b.getPathSize();
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest01(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,2,2);
		b.findPath(b.getTile(2, 2));
		boolean actual = b.movePlayer(p,2,3);//should return false
		boolean expected = false;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest02(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,2,2);
		b.findPath(b.getTile(2, 2));
		boolean actual = b.movePlayer(p,1,2);//should return true
		boolean expected = true;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest03(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,3,1);
		b.findPath(b.getTile(3, 1));
		boolean actual = b.movePlayer(p,1,2);//should return false
		boolean expected = false;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest04(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,3,1);
		b.findPath(b.getTile(3, 1));
		boolean actual = b.movePlayer(p,4,6);//should return true
		boolean expected = true;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest05(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,5,3);
		b.findPath(b.getTile(5, 3));
		boolean actual = b.movePlayer(p,1,2);//should return false
		boolean expected = false;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void movementTest06(){
		Board b = new Board(true);
		Player p = new Player(Color.BLUE);
		b.addPlayer(p,5,3);
		b.findPath(b.getTile(5,3));
		boolean actual = b.movePlayer(p,5,0);//should return true
		boolean expected = true;
		assertTrue("Expected: "+expected+" Actually was: "+actual,actual==expected);
	}
	@Test public void shiftTest01(){
		Board b = new Board(true);
		Tile oldfree = b.getFreeTile();
		Tile oldtop = b.getTile(3, 6);
		b.shiftColumn(3, false);//shifts column 3 from bottom
		Tile newfree = b.getFreeTile();
		boolean actual = oldfree==b.getTile(3, 0);
		actual = actual&&(newfree==oldtop);
		boolean expected = true;
		assertTrue("",actual==expected);
	}
	@Test public void shiftTest02(){
		Board b = new Board(true);
		Tile oldfree = b.getFreeTile();
		b.shiftColumn(2, true);//even index, nothing happens
		Tile newfree = b.getFreeTile();
		boolean expected = true;
		boolean actual = oldfree==newfree;//they are the same tile, by reference
		assertTrue("",actual==expected);
	}
	@Test public void shiftTest03(){
		Board b = new Board(false);
		Tile oldfree = b.getFreeTile();
		Tile oldback = b.getTile(0, 5);
		b.shiftRow(5, true);
		Tile newfree = b.getFreeTile();
		boolean expected = true;
		boolean actual = oldfree==b.getTile(6, 5);
		actual = actual&&(newfree==oldback);
		assertTrue("",actual==expected);
	}
}