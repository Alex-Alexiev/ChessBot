import java.util.ArrayList;

public class Bishop extends Piece{
	
	private String colour;
	private final int VALUE = 3;
	
	public Bishop (String colour) {
		this.colour = colour;
	}
	
	public String toString() {
		if (colour.equals("black")) {
			return "bB";
			//return "\u265D";
		}
		return "wB";
		//return "\u2657";
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int rm = -1, fm = -1;
		while(isValidMove(board, r, f, r+rm, f+fm)) {
			moves.add(new Move(r, f, r+rm, f+fm));
			rm--;
			fm--;
		}
		rm = 1;
		fm = -1;
		while (isValidMove(board, r, f, r+rm, f+fm)) {
			moves.add(new Move(r, f, r+rm, f+fm));
			rm++;
			fm--;
		}
		rm = 1;
		fm = 1;
		while (isValidMove(board, r, f, r+rm, f+fm)) {
			moves.add(new Move(r, f, r+rm, f+fm));
			rm++;
			fm++;
		}
		rm = -1;
		fm = 1;
		while (isValidMove(board, r, f, r+rm, f+fm)) {
			moves.add(new Move(r, f, r+rm, f+fm));
			rm--;
			fm++;
		}
		return moves;
	}

	public String getColour() {
		return colour;
	}
	
	public int getValue() {
		return VALUE;
	}
	

}
