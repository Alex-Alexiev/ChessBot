
public class Human {
	
	private Board board;
	private String colour;
	
	public Human(Board board, String colour) {
		this.board = board;
		this.colour = colour;
	}
	
	public boolean move(int r1, int f1, int r2, int f2) {
		boolean legalMove = false;
		for (Move m : board.getPiece(r1,f1).getMoves(board, r1,f1)) {
			if (m.getR2() == r2 && m.getF2() == f2){
				legalMove = true;
				break;
			}
		}
		if (legalMove) {
			board.move(r1, f1, r2, f2);
			return true;
		}
		else {
			return false;
		}
	}

}
