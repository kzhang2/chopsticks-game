package rpsgame;

public class Test {
	final int limit = 3;


	public static void main (String[] args) {
		Gamestate start = new Gamestate(1, 1, 1, 1);
		System.out.println("test");
		System.out.println(start.checkWin());
		System.out.println(start);
	}
	
}
