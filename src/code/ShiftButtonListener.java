package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftButtonListener implements ActionListener {
	
	int number;
	boolean direction;
	boolean isRow;
	Board board;
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
