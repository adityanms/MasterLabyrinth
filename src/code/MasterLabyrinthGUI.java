package code;

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
	private JPanel _boardPanel, _dataPanel;
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
		initializeData();
		update();
		_window.setLayout(new GridLayout(1, 2));
		_window.add(_boardPanel);
		_window.add(_dataPanel);
		_window.pack();
		_window.setVisible(true);

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
		JPanel _shiftPanel = new JPanel(new GridLayout(6,2));
		_shiftPanel.setBorder(new LineBorder(Color.BLACK));
		JButton shift = new JButton("Shift Down 2");
		_shiftPanel.add(shift);
		shift.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(1, true);
			}
		});
		JButton shift0 = new JButton("Shift Up 2");
		_shiftPanel.add(shift0);
		shift0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(1, false);
			}
		});
		JButton shift1 = new JButton("Shift Down 4");
		_shiftPanel.add(shift1);
		shift1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(3, true);
			}
		});
		JButton shift2 = new JButton("Shift Up 4");
		_shiftPanel.add(shift2);
		shift2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(3, false);
			}
		});

		JButton shift3 = new JButton("Shift Down 6");
		_shiftPanel.add(shift3);
		shift3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(5, true);
			}
		});
		JButton shift4 = new JButton("Shift Up 6");
		_shiftPanel.add(shift4);
		shift4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftColumn(5, false);
			}
		});
		JButton shift5 = new JButton("Shift Left 6");
		_shiftPanel.add(shift5);
		shift5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(1, true);
			}
		});
		JButton shift6 = new JButton("Shift Right 6");
		_shiftPanel.add(shift6);
		shift6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(1, false);
			}
		});
		JButton shift7 = new JButton("Shift Left 4");
		_shiftPanel.add(shift7);
		shift7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(3, true);
			}
		});
		JButton shift8 = new JButton("Shift Right 4");
		_shiftPanel.add(shift8);
		shift8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(3, false);
			}
		});
		JButton shift9 = new JButton("Shift Left 2");
		_shiftPanel.add(shift9);
		shift9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(5, true);
			}
		});
		JButton shift10 = new JButton("Shift Right 2");
		_shiftPanel.add(shift10);
		shift10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_board.shiftRow(5, false);
			}
		});

		_dataPanel.add(_freeTilePanel);
		_dataPanel.add(_shiftPanel);

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
					pan = (JPanel) p.getComponent(4);
					pan.setBackground(Color.RED);
				}
				if(t.hasPlayer()){
					if(t.getPlayerList().size()>=1){
						pan = (JPanel) p.getComponent(0);
						pan.setBackground(t.getPlayerList().get(0).getColor());
						};
						
				}
			}
		}
		redrawTile();
	}

}
