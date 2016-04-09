package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import code.Observer;

/**
 * Graphical User Interface for our Master Labyrinth board. As of now, shows a
 * visual representation of the tiles on the board. Paths on individual tiles
 * are shown in BLACK(representing the tiles "directional" variables.
 * 
 * @author Matt, Nick, team112
 *
 */
public class MasterLabyrinthGUI implements Runnable, Observer {
	private JPanel _boardPanel, _dataPanel, _gamePanel;
	private JFrame _window;
	private Board _board;
	private Tile _freeTile;
	private JPanel _freeTilePanel;
	private ArrayList<JButton> _playerButtons;

	/**
	 * Constructor.
	 * 
	 * @param b
	 *            MasterLabyrinth Board obj
	 */
	public MasterLabyrinthGUI(Board b) {
		_board = b;
		_board.setObserver(this);
	}

	/**
	 * Function required as MasterLabyrinthGUI implements Runnable
	 */
	@Override
	public void run() {
		_window = new JFrame("Master Labyrinth");
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_freeTile = _board.getFreeTile();
		_dataPanel = new JPanel();
		_freeTilePanel = new JPanel();
		initializeBoard();
		initializeGamePanel();
		initializeData();
		update();
	
		_window.setLayout(new GridLayout(1, 2));
		_window.add(_gamePanel);
		//_window.add(_boardPanel);
		_window.add(_dataPanel);
		_window.pack();
		_window.setVisible(true);

	}
	
	private void initializeGamePanel(){
		_gamePanel = new JPanel();
		_gamePanel.setLayout(new BorderLayout());
		_gamePanel.add(_boardPanel, BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		top.setLayout(new GridLayout(1,7));
		bottom.setLayout(new GridLayout(1,7));
		
	
		for(int i=0;i<7;i++){
			;
			if(i%2 != 0){
				JButton topShift = new JButton("Shift");
				JButton bottomShift = new JButton("Shift");
				top.add(topShift);
				bottom.add(bottomShift);
				topShift.addActionListener(new ShiftButtonListener(_board,i,true,false));
				bottomShift.addActionListener(new ShiftButtonListener(_board,i,false,false));
			}
			
			else{
				top.add(new JPanel());
				bottom.add(new JPanel());
			}	
		}
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		left.setLayout(new GridLayout(7,1));
		right.setLayout(new GridLayout(7,1));
		for(int i=0;i<7;i++){
			if(i%2 != 0){
				JButton rightShift = new JButton("Shift");
				JButton leftShift = new JButton("Shift");
				right.add(rightShift);
				left.add(leftShift);
				rightShift.addActionListener(new ShiftButtonListener(_board,6-i,true,true));
				leftShift.addActionListener(new ShiftButtonListener(_board,6-i,false,true));
			}
			
			else{
				right.add(new JPanel());
				left.add(new JPanel());
			}
				
		}

		_gamePanel.add(top, BorderLayout.NORTH);
		_gamePanel.add(bottom, BorderLayout.SOUTH);
		_gamePanel.add(right, BorderLayout.EAST);
		_gamePanel.add(left, BorderLayout.WEST);
	}
	
	
	/**
	 * Sets up a 7x7 grid of JPanels which will hold visual representations of
	 * Tiles. Each JPanel is a 3x3 grid which will hold visual representation of
	 * a Tile's NEWS values. These sub-panels are labeled 0-8 as shown: 0 1 2 3
	 * 4 5 6 7 8 where sub-panel 1 should represent the Tile's North value, 5
	 * represents the Tile's East value, etc... sub-panel 4 will always be black
	 */
	private void initializeBoard() {
		_boardPanel = new JPanel();
		_boardPanel.setLayout(new GridLayout(Board.WIDTH, Board.HEIGHT));
		_boardPanel.setFocusable(false);

		for (int c = 0; c < Board.WIDTH; c++) {
			for (int r = 0; r < Board.HEIGHT; r++) {
				JPanel p = new JPanel();
				p.setBorder(new LineBorder(Color.BLACK));
				p.setLayout(new GridLayout(3, 3));
				p.setOpaque(true);
				p.setFocusable(false);
				p.setPreferredSize(new Dimension(60, 60));

				for (int i = 0; i < 3; i++) {// 3x3 grid on face of tile
					for (int j = 0; j < 3; j++) {
						JPanel pan = new JPanel();
						pan.setOpaque(true);
						pan.setPreferredSize(new Dimension(20, 20));
						pan.setBackground(Color.WHITE);
						pan.add(new JLabel());//each subpanel has a JLabel on it. Access it via (JLabel)getComponent(0) in update()
						p.add(pan);
					}

				}
				_boardPanel.add(p);
			}
		}
	}

	/**
	 * Method to initilialize the data panel
	 */
	public void initializeData() {
		JButton rotateTile = new JButton("Rotate tile");
		_dataPanel.add(rotateTile);
		rotateTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.rotateFreeTile();

			}
		});

		_dataPanel.add(_freeTilePanel);

		_freeTilePanel.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {// 3x3 grid on face of tile
			for (int j = 0; j < 3; j++) {
				JPanel pan = new JPanel();
				pan.setOpaque(true);
				pan.setPreferredSize(new Dimension(20, 20));
				pan.setBackground(Color.WHITE);

				_freeTilePanel.add(pan);
			}
		}

		// setting center sub-panel to black (component 4 is center sub-panel)
		JPanel pan = (JPanel) _freeTilePanel.getComponent(4);
		pan.setBackground(Color.BLACK);

		// setting north color based on tile's getNorth() value
		pan = (JPanel) _freeTilePanel.getComponent(1);// see javadoc for why
		// these are 1,5,7,3
		if (_freeTile.getNorth() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting east color based on tile's getEast() value
		pan = (JPanel) _freeTilePanel.getComponent(5);
		if (_freeTile.getEast() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting south color based on tile's getSouth() value
		pan = (JPanel) _freeTilePanel.getComponent(7);
		if (_freeTile.getSouth() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting west color based on tile's getWest() value
		pan = (JPanel) _freeTilePanel.getComponent(3);
		if (_freeTile.getWest() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}

		_playerButtons = new ArrayList<>();
		for (int i = 0; i < _board.numberOfPlayers(); i++) {
			_playerButtons.add(new JButton(_board.getPlayer(i).getPlayerName()));
			_dataPanel.add(_playerButtons.get(i));
		}

	}

	private void redrawTile() {
		JPanel tile = _freeTilePanel;
		_freeTile = _board.getFreeTile();

		// setting center sub-panel to black (component 4 is center sub-panel)
		JPanel pan = (JPanel) tile.getComponent(4);
		pan.setBackground(Color.BLACK);

		// setting north color based on tile's getNorth() value
		pan = (JPanel) tile.getComponent(1);// see javadoc for why these are
		// 1,5,7,3
		if (_freeTile.getNorth() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting east color based on tile's getEast() value
		pan = (JPanel) tile.getComponent(5);
		if (_freeTile.getEast() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting south color based on tile's getSouth() value
		pan = (JPanel) tile.getComponent(7);
		if (_freeTile.getSouth() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}
		// setting west color based on tile's getWest() value
		pan = (JPanel) tile.getComponent(3);
		if (_freeTile.getWest() == true) {
			pan.setBackground(Color.BLACK);
		} else {
			pan.setBackground(Color.WHITE);
		}

		System.out.println("East " + _freeTile.getEast());
		System.out.println("West " + _freeTile.getWest());
		System.out.println("North " + _freeTile.getNorth());
		System.out.println("South " + _freeTile.getSouth());
		_freeTilePanel.repaint();
		_dataPanel.repaint();
		_window.repaint();

	}

	/**
	 * The Update method is required as this implements the Observer interface
	 */

	@Override
	public void update() {
		for (int c = 0; c < Board.WIDTH; c++) {
			for (int r = 0; r < Board.HEIGHT; r++) {
				JPanel p = (JPanel) _boardPanel.getComponent(r * Board.WIDTH + c);

				//JLabel pos = new JLabel("" + c + "," + (6 - r));
				//pos.setForeground(Color.WHITE);

				Tile t = _board.getTile(c, 6 - r);// 6-r because our 0,0 is at
				// bottom left instead of
				// top-left

				// setting center sub-panel to black (component 4 is center
				// sub-panel)
				JPanel pan = (JPanel) p.getComponent(4);
				pan.setBackground(Color.BLACK);

				//pan.add(pos);

				// setting north color based on tile's getNorth() value
				pan = (JPanel) p.getComponent(1);// see javadoc for why these
				// are 1,5,7,3
				if (t.getNorth() == true) {
					pan.setBackground(Color.BLACK);
				} else {
					pan.setBackground(Color.WHITE);
				}
				// setting east color based on tile's getEast() value
				pan = (JPanel) p.getComponent(5);
				if (t.getEast() == true) {
					pan.setBackground(Color.BLACK);
				} else {
					pan.setBackground(Color.WHITE);
				}
				// setting south color based on tile's getSouth() value
				pan = (JPanel) p.getComponent(7);
				if (t.getSouth() == true) {
					pan.setBackground(Color.BLACK);
				} else {
					pan.setBackground(Color.WHITE);
				}
				// setting west color based on tile's getWest() value
				pan = (JPanel) p.getComponent(3);
				if (t.getWest() == true) {
					pan.setBackground(Color.BLACK);
				} else {
					pan.setBackground(Color.WHITE);
				}


				if(t.hasToken()){//if this tile has a token on it
					Token token = t.getToken();
					pan = (JPanel) p.getComponent(4);
					pan.setBackground(Color.PINK);
					if(token.isFacedDown()){
						System.out.println("??");
						JLabel j = (JLabel)pan.getComponent(0);
						j.setText("??");
					}
					else{
						JLabel j = (JLabel)pan.getComponent(0);
						j.setText(token.getTokenNumber()+"");
					}
				}
				if(t.hasPlayer()){//if this tile has at least 1 player on it
					if(t.getPlayerList().size()>=1){//tile has at least one player
						pan = (JPanel) p.getComponent(0);
						pan.setBackground(t.getPlayerList().get(0).getColor());
					}
					if(t.getPlayerList().size()>=2){//tile has at least two players on it
						pan = (JPanel)p.getComponent(2);
						pan.setBackground(t.getPlayerList().get(1).getColor());
					}
					if(t.getPlayerList().size()>=3){//tile has at least three players on it
						pan = (JPanel)p.getComponent(6);
						pan.setBackground(t.getPlayerList().get(2).getColor());
					}
					if(t.getPlayerList().size()==4){//tile has 4 players on it
						pan = (JPanel)p.getComponent(8);
						pan.setBackground(t.getPlayerList().get(3).getColor());
					}


				}
			}
		}
		redrawTile();
	}

}
