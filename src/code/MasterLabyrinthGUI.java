package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
/**
 * Graphical User Interface for our Master Labyrinth board.
 * As of now, shows a visual representation of the tiles on the board.
 * Paths on individual tiles are shown in BLACK(representing the tiles "directional" variables.
 * 
 * @author Matt, Nick, team112
 *
 */
public class MasterLabyrinthGUI implements Runnable{
	private JPanel _boardPanel;
	private JFrame _window;
	private Board _board;
/**
 * Constructor.
 * 
 * @param b MasterLabyrinth Board obj
 */
	public MasterLabyrinthGUI(Board b){
		_board = b;

	}
	@Override public void run(){
		_window = new JFrame("Master Labyrinth");
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeBoard();
		_window.add(_boardPanel);
		_window.pack();
		_window.setVisible(true);
	}


/**
 * Sets up a 7x7 grid of JPanels which will hold visual representations of Tiles.
 * Each JPanel is a 3x3 grid which will hold visual representation of a Tile's NEWS values.
	 * These sub-panels are labeled 0-8 as shown:
	 * 0 1 2
	 * 3 4 5
	 * 6 7 8
	 * where sub-panel 1 should represent the Tile's North value,
	 * 5 represents the Tile's East value, etc...
	 * sub-panel 4 will always be black
 */
	private void initializeBoard() {
		_boardPanel = new JPanel();
		_boardPanel.setLayout(new GridLayout(Board.WIDTH,Board.HEIGHT));
		_boardPanel.setFocusable(false);

		for(int c=0; c<Board.WIDTH; c++){
			for(int r=0; r<Board.HEIGHT;r++){
				JPanel p = new JPanel();
				p.setBorder(new LineBorder(Color.BLACK));
				p.setLayout(new GridLayout(3,3));
				p.setOpaque(true);
				p.setFocusable(false);
				p.setPreferredSize(new Dimension(60,60));



				for(int i=0;i<3;i++){//3x3 grid on face of tile
					for(int j=0;j<3;j++){
						JPanel pan = new JPanel();
						pan.setOpaque(true);
						pan.setPreferredSize(new Dimension(20,20));
						pan.setBackground(Color.WHITE);
						p.add(pan);
					}
				}
				_boardPanel.add(p);
			}			
		}
		update();


	}
	
	
	/**
	 * Pulls data from Board and populates JPanels with said data.
	 *
	 */
public void update(){
		for(int c=0; c<Board.WIDTH;c++){
			for(int r=0; r<Board.HEIGHT;r++){
				JPanel p = (JPanel)_boardPanel.getComponent(r*Board.WIDTH+c);
			
				/*  Tile labels
				JLabel pos = new JLabel(""+c+","+(6-r));
				pos.setForeground(Color.WHITE);
				pan.add(pos);
				*/
				
				Tile t = _board.getTile(c, 6-r);//6-r because our 0,0 is at bottom left instead of top-left
				
				//setting center sub-panel to black (component 4 is center sub-panel)
				JPanel pan = (JPanel)p.getComponent(4);
				pan.setBackground(Color.BLACK);

				//setting north color based on tile's getNorth() value
				pan = (JPanel)p.getComponent(1);//see javadoc for why these are 1,5,7,3
				if(t.getNorth()==true){pan.setBackground(Color.BLACK);}
				else{pan.setBackground(Color.WHITE);}
				//setting east color based on tile's getEast() value
				pan = (JPanel)p.getComponent(5);
				if(t.getEast()==true){pan.setBackground(Color.BLACK);}
				else{pan.setBackground(Color.WHITE);}
				//setting south color based on tile's getSouth() value
				pan = (JPanel)p.getComponent(7);
				if(t.getSouth()==true){pan.setBackground(Color.BLACK);}
				else{pan.setBackground(Color.WHITE);}
				//setting west color based on tile's getWest() value
				pan = (JPanel)p.getComponent(3);
				if(t.getWest()==true){pan.setBackground(Color.BLACK);}
				else{pan.setBackground(Color.WHITE);}
			}
		}
	}



}

