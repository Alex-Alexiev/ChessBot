
public class Move {
	
	private int r1,f1,r2,f2;
	
	public Move(int r1, int f1, int r2, int f2) {
		this.r1 = r1;
		this.r2 = r2;
		this.f1 = f1;
		this.f2 = f2;
		
	}
	
	public int getR1() {
		return r1;
	}
	
	public int getR2() {
		return r2;
	}
	
	public int getF1() {
		return f1;
	}
	
	public int getF2() {
		return f2;
	}
	
	public String toString() {
		return "{("+r1+", "+f1+") -> ("+r2+", "+f2+")}";
	}
	
	public void print() {
		System.out.println(this);
	}

}
