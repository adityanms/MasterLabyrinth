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
		b.findPath(b.getTile(2,2));
		b.movePlayer(b.getPlayer(0), 1, 2);
	}
}