package test;
import code.Board;
import code.Tile;
import static org.junit.Assert.assertTrue;
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
}

