package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements an ActionListener for the shift buttons across the board
 * @author Adi
 *
 */

public class ShiftButtonListener implements ActionListener {
	
	int number;
	boolean direction;
	boolean isRow;
	Board board;
	
	/**
	 * Constructor for the class
	 * @param board the board calling this constructor
	 * @param number the index of the button
	 * @param direction direction of shifting. Set true for up and false for down. 
	 * @param isRow Boolean parameter to know if shift is row shift or column shift
	 */
	public ShiftButtonListener(Board board, int number, boolean direction, boolean isRow) {
		this.number = number;
		this.direction = direction;
		this.isRow = isRow;
		this.board = board;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isRow){
			board.shiftRow(number, direction);
		}
		else
			board.shiftColumn(number, direction);
		
		
	}

}
