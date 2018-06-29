package rpsgame;
import java.util.*;

public class Gamestate {
	
	int[] hands = new int[4];
//	boolean loop = false;
	ArrayList<Integer> fLink = new ArrayList<Integer>();
	ArrayList<Integer> bLink = new ArrayList<Integer>();
	int turn;
	
	public Gamestate(int l, int r, int l1, int r1){
		hands[0] = Math.max(l, r);
		hands[1] = Math.min(l, r);
		hands[2] = Math.max(l1, r1);
		hands[3] = Math.min(l1, r1);
		turn = 0;
	}
	
	public int checkWin(){
		if(hands[0] == 0 && hands[1] == 0){
			return 0;
		} else if (hands[2] == 0 && hands[3] == 0){
			return 1;
		} else {
			return -1;
		}
	}
	
	public void add(int k, String id){
		if (id.equals("firstleft")) {
			hands[0] = (hands[0] + 1) % 5;
		}
	}
	
	public String toString(){
//		return String.valueOf(hands[0]) + String.valueOf(hands[1]) + String.valueOf(hands[2]) + String.valueOf(hands[3]);
		String result = "";
		int space1 = 8 - (hands[0] + hands[1]);
		for(int i = 0; i < hands[0]; i++) {
			result += "|";
		}
		for(int i = 0; i < space1; i++) {
			result += " ";
		}
		for(int i = 0; i < hands[1]; i++) {
			result += "|";
		}
		result += "\n";
		int space2 = 8 - (hands[2] + hands[3]);
		for(int i = 0; i < hands[2]; i++) {
			result += "|";
		}
		for(int i = 0; i < space2; i++) {
			result += " ";
		}
		for(int i = 0; i < hands[3]; i++) {
			result += "|";
		}
		return result;
	}
	
	public boolean equals(Object obj){
		return ((Gamestate) obj).hands[0] == this.hands[0] && ((Gamestate) obj).hands[1] == this.hands[1] && ((Gamestate) obj).hands[2] == this.hands[2] && ((Gamestate) obj).hands[3] == this.hands[3];
	}
	
	public int hashCode(){
		return Integer.parseInt(String.valueOf(hands[0])) * 1000 + Integer.parseInt(String.valueOf(hands[1])) * 100
				+ Integer.parseInt(String.valueOf(hands[2])) * 10 + Integer.parseInt(String.valueOf(hands[3]));
	}
}
