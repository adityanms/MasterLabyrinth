package masterlabyrinth;

public class Board {
	public Board() {
		Tiles[][] board = new Tiles[7][7];
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (x % 2 == 1 || y % 2 == 1) {
					board[x][y] = new Tiles(x, y, true);
				} else {
					board[x][y] = new Tiles(x, y, false);
				}
			}
		}
		Players player1 = new Players(2, 2);
		Players player2 = new Players(2, 4);
		Players player3 = new Players(4, 2);
		Players player4 = new Players(4, 4);
		for (int n = 1; n < 21; n++) {
			Tokens tokens = new Tokens(n);
			if (board[tokens.getX()][tokens.getY()].isToken()) {
				board[tokens.getX()][tokens.getY()].setT(tokens);
				board[tokens.getX()][tokens.getY()].setToken(true);
			} else {
				n--;
			}

		}
	}
}