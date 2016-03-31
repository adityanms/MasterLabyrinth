package code;

import javax.swing.SwingUtilities;

public class Driver {
	/**
	 * Sample usage and movement tests.  Code demo
	 * 
	 * Image of the starting state of the static board used for testing: 
	 * https://drive.google.com/file/d/0Bxbwk_KH5lhdRHhPa0FkNmtFZ2s/view?usp=sharing
	 * @param args commandline arguments
	 */
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MasterLabyrinthGUI(new Board(false, args)));
	}
	
	
/*	public static void main(String[] args){
		Board b = new Board(true);
		Player p1 = new Player (args[0]);
		Player p2 = new Player (args[1]);
		Player p3 = new Player (args[2]);
		Player p4 = new Player (args[3]);
		
		System.out.println("Player names are " + args[0]+ args[1]+ args[2]+ args[3]);
		Player p = new Player(Color.BLUE);
		
		b.addPlayer(p,2,2);//creates a player at 2,2
		b.findPath(b.getTile(p.getX(), p.getY()));//finds path that includes 2,2
		b.movePlayer(p,3,2);//should return false and not move player (not in path)
		
		System.out.println(b.movePlayer(p,3,2));//prints false
		
		b.movePlayer(p, 1, 2);//should return true (in path)
		b.movePlayer(p, 6, 6);//should return false and not move player (not in path)
		b.movePlayer(p, 4, 6);//should return false and not move player (not in path)
		
		System.out.println(p.getX()+","+p.getY());//should print 1,2
		
		b.rotateFreeTile();//after rotation, freetile is an east-west straight tile
		
		b.shiftColumn(3, false);//inserts free tile into column 3 from bottom
		
		b.rotateFreeTile();//after rotation, freetile is a T with e,s, and w true
		
		b.shiftColumn(3, false);//inserts free tile into column 3 from bottom
		
		b.shiftColumn(3, false);//inserts free tile into column 3 from bottom
		
		b.findPath(b.getTile(p.getX(),p.getY()));
		b.movePlayer(p, 3, 2);//3,2 is now a valid move, returns true and moves player
		
		System.out.println(p.getX()+","+p.getY());//prints 3,2
		
		b.movePlayer(p, 4, 6);//also a valid move now
		
		System.out.println(p.getX()+","+p.getY());//prints 4,6
		
		b.movePlayer(p, 6, 6);//returns false, not in path, player does not move
		
		System.out.println(p.getX()+","+p.getY());//still prints 4,6
		
		b.shiftRow(5, false);//inserts free tile into row 5 from "west"
		b.findPath(b.getTile(p.getX(),p.getY()));
		
		b.movePlayer(p, 3, 2);//no longer a valid tile to move onto
		
		System.out.println(p.getX()+","+p.getY());//still prints 4,6
		
		System.out.println(b.shiftColumn(0, true));//prints false, can't shift even indices, board does not change
		System.out.println(b.shiftRow(0, true));//prints false, can't shift even indices, board does not change
	}*/
}
