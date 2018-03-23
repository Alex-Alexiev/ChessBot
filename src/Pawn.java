import java.util.ArrayList;

public class Pawn extends Piece {
	
	private String colour;
	private final int VALUE = 1;
	
	public Pawn (String colour) {
		this.colour = colour;
	}

	public String toString() {
		if (colour.equals("black")) {
			return "\u265F";
		}
		return "\u2659";
	}
	
	public String getColour() {
		return colour;
	}

	public ArrayList<Move> getMoves(Board board, int r, int f) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (colour.equals("black")) {
			if (isValidMove(board,r, f, r+1, f)) {
				moves.add(new Move(r,f, r+1, f));
			}
			if (r <= 1) {
				if (isValidMove(board,r, f, r+2, f)) {
					moves.add(new Move(r,f, r+2, f));
				}
			}
		} else {
			if (isValidMove(board,r, f, r-1, f)) {
				moves.add(new Move(r,f, r-1,f));
			}
			if (r >= 6) {
				if (isValidMove(board,r, f, r-2, f)) {
					moves.add(new Move(r,f, r-2,f));
				}
			}
		}
		for (Move m : getAttackingMoves(board, r, f)) {
			if (isValidMove(board, m.getR1(), m.getF1(), m.getR2(),m.getF2())){
				moves.add(m);
			}
		}
		return moves;
	}
	
	public int getValue() {
		return VALUE;
	}
	
	public boolean isValidMove(Board board, int pieceR, int pieceF, int spotR, int spotF) {
		Piece curr = board.getPiece(pieceR, pieceF);
		String colour = curr.getColour();
		if (!(spotR < 8 && spotR >= 0 && spotF < 8 && spotF >= 0))
			return false;
		if (Math.abs(spotF-pieceF) > 0) {
			if (board.getPiece(spotR,  spotF) == null || board.getPiece(spotR,  spotF).getColour().equals(colour))
				return false;
		}
		if (board.getPiece(spotR,  spotF) != null)
			return false;
		return true;
	}
	
	public ArrayList<Move> getAttackingMoves(Board board, int r, int f){
		ArrayList<Move> moves = new ArrayList<Move>();
		if (colour.equals("black")) {
			if (isValidMove(board,r, f, r+1, f+1)) {
				moves.add(new Move(r,f, r+1, f+1));
			}
			if (isValidMove(board,r, f, r+1, f-1)) {
				moves.add(new Move(r,f, r+1, f-1));
			}
		} else {
			if (isValidMove(board,r, f, r-1, f+1)) {
				moves.add(new Move(r,f, r-1,f+1));
			}
			if (isValidMove(board,r, f, r-1, f-1)) {
				moves.add(new Move(r,f, r-1,f-1));
			} 
		}
		return moves;
	}

}
