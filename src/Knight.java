import java.util.ArrayList;

public class Knight extends Piece {

	private String colour;
	private final int VALUE = 3;

	public Knight(String colour) {
		this.colour = colour;
	}

	public String toString() {
		if (colour.equals("black")) {
			return "bN";
			//return "\u265E";
		}
		return "wN"; 
		//return "\u2658";
	}

	public int getValue() {
		return VALUE;
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int[][] MOVES = new int[][] { { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 },
				{ -2, -1 } };
		for (int[] spot : MOVES) {
			if (isValidMove(board, r, f, r + spot[0], f + spot[1])) {
				moves.add(new Move(r, f, r + spot[0], f + spot[1]));
			}
		}
		return moves;
	}

	public String getColour() {
		return colour;
	}

	public boolean canFly() {
		return true;
	}

}
