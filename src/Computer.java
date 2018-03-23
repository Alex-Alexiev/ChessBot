public class Computer {
	
	private Board board;
	private String colour;
	
	public Computer (Board board, String colour) {
		this.board = board;
		this.colour = colour;
	}
	
	public void move() {
		BoardTree tree = new BoardTree(board, 1, colour);
		Move best = tree.bestMove();
		board.move(best.getR1(), best.getF1(), best.getR2(), best.getF2());
	}

}
