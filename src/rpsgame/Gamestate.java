package rpsgame;
import java.util.*;

public class GameState {
	
	int l;
	int r;
	int l1;
	int r1;
//	boolean loop = false;
	HashSet<GameState> adjacent = new HashSet<>();
	int limit;
	int turn;
	
	public GameState(int l, int r, int l1, int r1, int turn, int limit){
		this.l = Math.max(l, r);
		this.r = Math.min(l, r);
		this.l1 = Math.max(l1, r1);
		this.r1 = Math.min(l1, r1);
		this.limit = limit;
		this.turn = turn;
	}

	public void fillNeighbors() {
		int nTurn = turn + 1;
		adjacent.add(this);
		if(turn % 2 == 0) {
			int l2 = (l1 != 0) ? (l1 + l) % limit : 0;
			int l3 = (l1 != 0) ? (l1 + r) % limit : 0;
			int r2 = (r1 != 0) ? (r1 + l) % limit: 0;
			int r3 = (r1 != 0) ? (r1 + r) % limit: 0;
			adjacent.add(new GameState(l, r, l2, r1, nTurn, limit));
			adjacent.add(new GameState(l, r, l3, r1, nTurn, limit));
			adjacent.add(new GameState(l, r, l1, r2, nTurn, limit));
			adjacent.add(new GameState(l, r, l1, r3, nTurn, limit));
			int total = l + r;
			int counter = 0;
			while (counter <= total / 2) {
				GameState temp = new GameState(total - counter, counter, l1, r1, nTurn, limit);
				if (counter < limit && total - counter < limit && !temp.equals(this)) {
					adjacent.add(temp);
				}
				counter += 1;
			}
		} else {
			int l2 = (l != 0) ? (l + l1) % limit: 0;
			int l3 = (l != 0) ? (l + r1) % limit: 0;
			int r2 = (r != 0) ? (r + l1) % limit: 0;
			int r3 = (r != 0) ? (r + r1) % limit: 0;
            adjacent.add(new GameState(l2, r, l1, r1, nTurn, limit));
			adjacent.add(new GameState(l3, r, l1, r1, nTurn, limit));
			adjacent.add(new GameState(l, r2, l1, r1, nTurn, limit));
			adjacent.add(new GameState(l, r3, l1, r1, nTurn, limit));
			int total = l1 + r1;
			int counter = 0;
			while(counter <= total / 2) {
				GameState temp = new GameState(l, r, total - counter, counter, nTurn, limit);
				if (counter < limit && total - counter < limit && !temp.equals(this)) {
					adjacent.add(temp);
				}
				counter += 1;
			}
		}
		adjacent.remove(this);
	}
	
	public int checkWin(){
		if(l == 0 && r == 0){
			return 0;
		} else if (l1 == 0 && r1 == 0){
			return 1;
		} else {
			return -1;
		}
	}

	
	public String toString(){
//		return String.valueOf(l) + String.valueOf(r) + String.valueOf(l1) + String.valueOf(r1);
		StringBuilder result = new StringBuilder();
		int space1 = 9 - (l + r);
		for(int i = 0; i < l; i++) {
			result.append("|");
		}
		for(int i = 0; i < space1; i++) {
			result.append(" ");
		}
		for(int i = 0; i < r; i++) {
			result.append("|");
		}
		result.append("\n");
		int space2 = 9 - (l1 + r1);

		for(int i = 0; i < l1; i++){
			result.append("|");
		}
		for(int i = 0; i < space2; i++) {
			result.append(" ");
		}
		for(int i = 0; i < r1; i++) {
			result.append("|");
		}
		result.append("\n");
		result.append(String.valueOf(this.turn % 2));
		return result.toString();
	}
	
	public boolean equals(Object obj){
		return (this.getClass() == obj.getClass()) && ((GameState) obj).l == this.l && ((GameState) obj).r == this.r
				&& ((GameState) obj).l1 == this.l1 && ((GameState) obj).r1 == this.r1 && (((GameState) obj).turn % 2) == (this.turn % 2);
	}
	
	public int hashCode(){
		return l * 10000 + r * 1000 + l1 * 100 + r1 * 10 + (turn % 2);
	}
}
