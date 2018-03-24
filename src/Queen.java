import java.util.ArrayList;

public class Queen extends Piece {
	
	private String colour;
	private final int VALUE = 9;
	
	public Queen (String colour) {
		this.colour = colour;
	}

	public String toString() {
		if (colour.equals("black")) {
			return "bQ";
			//return "\u265B";
		}
		return "wQ";
		//return "\u2655";
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int rm = -1;
		while (isValidMove(board, r, f, r+rm, f)) {
			moves.add(new Move(r,f,r+rm, f));
			rm--;
		}
		rm = 1;
		while (isValidMove(board, r, f, r+rm, f)) {
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
		rm = -1;
		fm = -1;
		while (isValidMove(board,r, f, r+rm, f+fm)) {
			moves.add(new Move(r,f,r+rm, f+fm));
			rm--;
			fm--;
		}
		rm = 1;
		fm = -1;
		while (isValidMove(board,r, f, r+rm, f+fm)) {
			moves.add(new Move(r,f,r+rm, f+fm));
			rm++;
			fm--;
		}
		rm = 1;
		fm = 1;
		while (isValidMove(board,r, f, r+rm, f+fm)) {
			moves.add(new Move(r,f,r+rm, f+fm));
			rm++;
			fm++;
		}
		rm = -1;
		fm = 1;
		while (isValidMove(board,r, f, r+rm, f+fm)) {
			moves.add(new Move(r,f,r+rm, f+fm));
			rm--;
			fm++;
		}
		return moves;
	}
	
	public int getValue() {
		return VALUE;
	}

	public String getColour() {
		return colour;
	}
	
}
