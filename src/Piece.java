import java.util.ArrayList;

public abstract class Piece {
	
	public abstract ArrayList<Move> getMoves(Board board, int r, int f);
	
	public abstract String getColour();
	
	public abstract int getValue();
	
	public boolean canFly() {
		return false;
	}
	
	public boolean isValidMove(Board board, int pieceR, int pieceF, int spotR, int spotF) {
		Piece curr = board.getPiece(pieceR, pieceF);
		String colour = curr.getColour();
		if (!(spotR < 8 && spotR >= 0 && spotF < 8 && spotF >= 0))
			return false;
		if (board.getPiece(spotR,  spotF) != null && board.getPiece(spotR,  spotF).getColour().equals(colour))
			return false;
		if (!board.getPiece(pieceR,  pieceF).canFly()) {
			int backStepR = pieceR - spotR;
			if (backStepR != 0) backStepR = backStepR / (Math.abs(backStepR));
			int backStepF = pieceF - spotF;
			if (backStepF != 0) backStepF = backStepF / (Math.abs(backStepF));
			Piece prev = board.getPiece(spotR + backStepR, spotF + backStepF);
			if (prev != null && prev != board.getPiece(pieceR, pieceF)) {
				return false;
			}
		}
		return true;
	};
	
	public ArrayList<Move> getAttackingMoves(Board board, int r, int f){
		return getMoves(board, r, f);
	}

}
