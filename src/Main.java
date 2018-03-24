import java.util.Scanner;

public class Main {
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		
		Board board = new Board();
		Human human = new Human(board, "white");
		Computer computer = new Computer(board, "black");
		board.print();
		
		while(true) {
			int f1 = in.next().toLowerCase().charAt(0)-97;
			int r1 = 8-in.nextInt();
			int f2 = in.next().toLowerCase().charAt(0)-97;
			int r2 = 8-in.nextInt();
			if (!human.move(r1,f1,r2,f2)) {
				System.out.println("Invalid move. Try again.");
			}
			else {
				board.print();
				computer.move();
				System.out.println("");
				board.print();
			}
			if (board.isCheckMate("white")) {
				System.out.println("Human wins");
				break;
			}
			if (board.isCheckMate("black")) {
				System.out.println("Computer wins");
				break;
			}
		}
			
		/*
		Board board = new Board();
		Computer computer1 = new Computer(board, "black");
		Computer computer2 = new Computer(board, "white");
		
		while(true) {
			board.print();
			computer1.move();
			System.out.println("");
			board.print();
			computer2.move();
			System.out.println("");
			if (board.isCheckMate("white")) {
				System.out.println("Human wins");
				break;
			}
			if (board.isCheckMate("black")) {
				System.out.println("Computer wins");
				break;
			}
		}
		*/
		
		
		

	}

}
