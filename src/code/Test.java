package code;

import javax.swing.SwingUtilities;

public class Test {
	

	public static void main(String[] args) {
		String[] names = new String[4];
		names[0] = "Matt";
		names[1] = "Adi";
		names[2] = "Mal";
		names[3] = "Jayne";
		Board b = new Board(true, names);
		SwingUtilities.invokeLater(new MasterLabyrinthGUI(b));
		b.findPath(b.getTile(4,2));
		b.movePlayer(b.getPlayer(3), 4, 3);
		b.findPath(b.getTile(4,2));
		b.movePlayer(b.getPlayer(2), 4, 3);
		
	}
}