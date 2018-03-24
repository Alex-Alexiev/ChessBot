import java.util.ArrayList;

public class Board {

	private Piece[][] board = new Piece[8][8];
	private final String DEFAULT_BOARD_STATE = "" + "br,bn,bb,bq,bk,bb,bn,br," + "bp,bp,bp,bp,bp,bp,bp,bp,"
			+ "n,n,n,n,n,n,n,n," + "n,n,n,n,n,n,n,n," + "n,n,n,n,n,n,n,n," + "n,n,n,n,n,n,n,n,"
			+ "wp,wp,wp,wp,wp,wp,wp,wp," + "wr,wn,wb,wq,wk,wb,wn,wr";

	public Board() {
		String[] positions = DEFAULT_BOARD_STATE.split(",");
		String s;
		int i = 0;
		for (int r = 0; r < board.length; r++) {
			for (int f = 0; f < board[r].length; f++) {
				s = positions[i];
				if (!s.equals("n")) {
					String colour = s.substring(0, 1);
					if (colour.equals("w"))
						colour = "white";
					else
						colour = "black";
					String piece = s.substring(1);
					if (piece.equals("r"))
						board[r][f] = new Rook(colour);
					else if (piece.equals("n"))
						board[r][f] = new Knight(colour);
					else if (piece.equals("b"))
						board[r][f] = new Bishop(colour);
					else if (piece.equals("q"))
						board[r][f] = new Queen(colour);
					else if (piece.equals("k"))
						board[r][f] = new King(colour);
					else if (piece.equals("p"))
						board[r][f] = new Pawn(colour);
				}
				i++;
			}
		}
	}

	public int evaluate(String colour, int spotValue, int captureValue, int lossValue) {
		int value = 0;
		if (isCheckMate(colour)) { 
			return Integer.MAX_VALUE;
		}
		for (Move m : this.AllPossibleMoves(colour)) {
			value+= spotValue;
			Piece piece = board[m.getR2()][m.getF2()];
			if (piece != null) {
				value += captureValue * piece.getValue();
			}
		}
		if (colour.equals("black"))
			colour = "white";
		else
			colour = "black";
		for (Move m : this.allAttackingMoves(colour)) {
			//king stackOverFlow
			Piece piece = board[m.getR2()][m.getF2()];
			if (piece != null) {
				value -= lossValue* piece.getValue();
			}
		}
		return value;
	}

	public Piece move(int pieceR, int pieceF, int spotR, int spotF) {
		// clean up this sketchy stuff a bit
		if (board[pieceR][pieceF] == null) {
			return null;
		}
		if (board[spotR][spotF] == null) {
			board[spotR][spotF] = board[pieceR][pieceF];
			board[pieceR][pieceF] = null;
			return null;
		} else {
			Piece ret = board[spotR][spotF];
			board[spotR][spotF] = board[pieceR][pieceF];
			board[pieceR][pieceF] = null;
			return ret;
		}
	}

	public ArrayList<Move> AllPossibleMoves(String colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int r = 0; r < board.length; r++) {
			for (int f = 0; f < board[r].length; f++) {
				if (board[r][f] != null && board[r][f].getColour().equals(colour)) {
					for (Move m : board[r][f].getMoves(this, r, f)) {
						moves.add(m);
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> allAttackingMoves(String colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int r = 0; r < board.length; r++) {
			for (int f = 0; f < board[r].length; f++) {
				if (board[r][f] != null && board[r][f].getColour().equals(colour)) {
					for (Move m : board[r][f].getAttackingMoves(this, r, f)) {
						moves.add(m);
					}
				}
			}
		}
		return moves;
	}

	public Piece getPiece(int r, int f) {
		return board[r][f];
	}

	public Board copyBoard() {
		Board b = new Board();
		for (int r = 0; r < board.length; r++) {
			for (int f = 0; f < board[r].length; f++) {
				b.board[r][f] = board[r][f];
			}
		}
		return b;
	}
	
	
	public boolean isCheckMate(String colour) {
		String kingColour;
		if (colour.equals("black"))
			kingColour = "white";
		else
			kingColour = "black";
		if (!kingUnderAttack(kingColour)) return false;
		for (Move m : this.AllPossibleMoves(kingColour)) {
			Board temp = this.copyBoard();
			temp.move(m.getR1(), m.getF1(), m.getR2(), m.getF2());
			if (!temp.kingUnderAttack(kingColour)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean kingUnderAttack(String kingColour) {
		String otherColour;
		if (kingColour.equals("black"))
			otherColour = "white";
		else
			otherColour = "black";
		for (Move m : this.allAttackingMoves(otherColour)) {
			Piece curr = board[m.getR2()][m.getF2()];
			if (curr != null && curr.getColour().equals(kingColour) && curr instanceof King)
				return true;
		}
		return false;
	}

	public void printCoolBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int f = 0; f < board[r].length; f++) {
				if (board[r][f] == null) {
					System.out.print("\u2001  ");
				} else {
					System.out.print(board[r][f] + "  ");
				}
			}
			System.out.println("");
		}
	}
	
	public void print() {
		boolean white = true;
		int rank = 8;
		for (int r = 0; r < board.length; r++) {
			System.out.print(rank+ " |");
			for (int f = 0; f < board[r].length; f++) {
				if (board[r][f] == null) {
					if (white) {
						System.out.print("  |");
					} else {
						System.out.print("##|");
					}
				} else {
					System.out.print(board[r][f] + "|");
				}
				white = !white;
			}
			rank--;
			white = !white;
			System.out.println("");
		}
		System.out.println("   a  b  c  d  e  f  g  h");
	}

}
