import java.util.ArrayList;

public class Rook extends Piece {

	private String colour;
	private final int VALUE = 5;
	
	public Rook (String colour) {
		this.colour = colour;
	}

	public String toString() {
		if (colour.equals("black")) {
			return "bR";
			//return "\u265C";
		}
		return "wR";
		//return "\u2656";
	}
	
	public int getValue() {
		return VALUE;
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int rm = -1;
		while (isValidMove(board,r, f, r+rm, f)) {
			moves.add(new Move(r,f, r+rm, f));
			rm--;
		}
		rm = 1;
		while (isValidMove(board,r, f, r+rm, f)) {
			moves.add(new Move(r,f,r+rm, f));
			rm++;
		}
		int fm = 1;
		while (isValidMove(board,r, f, r, f+fm)) {
			moves.add(new Move(r,f,r, f+fm));
			fm++;
		}
		fm = -1;
		while (isValidMove(board,r, f, r, f+fm)) {
			moves.add(new Move(r,f,r, f+fm));
			fm--;
		}
		return moves;
	}

	public String getColour() {
		return colour;
	}

}
