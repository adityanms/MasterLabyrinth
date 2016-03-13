package masterlabyrinth;

public class Board {
	public Board() {
		Tiles[][] board = new Tiles[7][7];
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if(x%2==1||y%2==1){
					board[x][y] = new Tiles(x, y, true);
				}else{
					board[x][y] = new Tiles(x,y,false);
				}
			}
		}

	}

}
