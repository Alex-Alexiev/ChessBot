import java.util.ArrayList;

public class King extends Piece {

	private String colour;
	private final int VALUE = 0;

	public King(String colour) {
		this.colour = colour;
	}

	public String toString() {
		if (colour.equals("black")) {
			return "bK";
			//return "\u265A";
		}
		return "wK";
		//return "\u2654";
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int[][] MOVES = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
				{ 1, 1 } };
		for (int[] spot : MOVES) {
			if (isValidMove(board, r, f, r + spot[0], f + spot[1])) {
				moves.add(new Move(r, f, r + spot[0], f + spot[1]));
			}
		}
		return moves;
	}

	public boolean isValidMove(Board board, int pieceR, int pieceF, int spotR, int spotF) {
		Piece curr = board.getPiece(pieceR, pieceF);
		String colour = curr.getColour();
		if (!(spotR < 8 && spotR >= 0 && spotF < 8 && spotF >= 0))
			return false;
		if (board.getPiece(spotR, spotF) != null && board.getPiece(spotR, spotF).getColour().equals(colour))
			return false;
		String tempColour;
		if (colour.equals("black"))
			tempColour = "white";
		else
			tempColour = "black";
		for (Move m : board.allAttackingMoves(tempColour)) {
			if (m.getR2() == spotR && m.getF2() == spotF) {
				return false;
			}
		}
		return true;
	};

	public String getColour() {
		return colour;
	}

	public int getValue() {
		return VALUE;
	}

	public ArrayList<Move> getAttackingMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int[][] MOVES = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
				{ 1, 1 } };
		for (int[] spot : MOVES) {
			if (r + spot[0] < 8 && r + spot[0] >= 0 && f + spot[1] < 8 && f + spot[1] >= 0)
				moves.add(new Move(r, f, r + spot[0], f + spot[1]));
		}
		return moves;
	}

}
