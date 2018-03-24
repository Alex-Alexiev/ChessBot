import java.util.ArrayList;

public class BoardTree {
	
	public class Node{
		
		private ArrayList<Node> children = new ArrayList<Node>();
		private Board board;
		private Move move;
		private int value;
		
		public Node(Board board, Move move, String colour) {
			this.board = board;
			this.move = move;
			this.value = board.evaluate(colour, 0, 1, 2);
		}
		
		public void addChild(Node node) {
			children.add(node);
		}
		
		public Board getBoard() {
			return board;
		}
		
		public ArrayList<Node> getChildren(){
			return children;
		}
		
		public Move getMove() {
			return move;
		}
		
		public int getValue() {
			return value;
		}
		
		public Node bestChild() {
			int bestIndex = 0;
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).value > children.get(bestIndex).value) {
					bestIndex = i;
				}
			}
			return children.get(bestIndex);
		}
		
		public void print(Node n) {
			n.getBoard().print();
			System.out.println(n.value);
			System.out.println("");
			
			if (n.children.size() > 0) {
				for (Node  en: n.children) {
					print(en);
				}
			} 
		}
	}
	
	private int layers;
	private int intColour; //black is 1 
	private String initColour;
	private Node root;
	
	public BoardTree(Board board, int layers, String colour) {
		this.layers = layers;
		this.initColour = colour;
		if (colour.equals("black")) this.intColour = 1;
		else this.intColour = 0;
		root = new Node(board, null, colour);
		build(root, 0);
	}
	
	public Move bestMove() {
		maxValueTree(root, 0);
		return root.bestChild().getMove();
	}
	
	public int maxValueTree(Node n, int layer) {
		if (layer >= layers-1) {
			if (n.children.size() > 0) {
				return n.bestChild().value;
			}
			else {
				return 0;
			}
		}
		for (Node child : n.children) {
			child.value += maxValueTree(child, layer+1);
		}
		return 0;
	}
	
	public void build(Node r, int layer) {
		String colour;
		if ((layer+intColour)%2 == 0) {
			colour = "white";
		}
		else {
			colour = "black";
		}
		if (layer < layers) {
			for (Move m : r.getBoard().AllPossibleMoves(colour)) {
				Board b = r.getBoard().copyBoard();
				b.move(m.getR1(), m.getF1(), m.getR2(), m.getF2());
				Node child = new Node(b, m, initColour);
				r.addChild(child);
				//make the line below conditional if it evaluates above a certain threshold
				build(child, layer+1);	
			}
		}
	}
	
	public void print() {
		root.print(root);
	}
}
