package rpsgame;
import java.util.*;

public class Gamestate {
	
	int[] hands = new int[4];
	boolean loop = false;
	ArrayList<Integer> fLink = new ArrayList<Integer>();
	ArrayList<Integer> bLink = new ArrayList<Integer>();
	
	public Gamestate(int r, int l, int r1, int l1){
		if(r >= l){
			hands[0] = r;
			hands[1] = l;
		} else {
			hands[0] = l;
			hands[1] = r;
		}
		if(r1 >= l1){
			hands[2] = r1;
			hands[3] = l1;
		} else {
			hands[2] = l1;
			hands[3] = r1;
		}
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
	
	public int add(int k, int n){
		if(k % 5 == 0){
			return -1;
		}
		String temp = Integer.toString(k, 2);
		if(temp.length()  < 4){
			int q = 4 - temp.length();
			String t = "";
			for(int i = 0; i < q; i++){
				t += "0";
			}
			temp += t;
		}
		String t1 = temp.substring(0, 2);
		String t2 = temp.substring(2);
		int i1 = Integer.parseInt(t1, 2);
		int i2 = Integer.parseInt(t2, 2);
		if(t1.substring(0, 1).equals(t2.substring(0, 1))){
			hands[i2] += n + 1;
			hands[i1] -= n + 1;
			return 0;
		} else {
			hands[i2] += hands[i1];
			return 1;
		}
	}
	
	public String toString(){
		return String.valueOf(hands[0]) + String.valueOf(hands[1]) + String.valueOf(hands[2]) + String.valueOf(hands[3]);
	}
	
	public boolean equals(Object obj){
		return ((Gamestate) obj).hands[0] == this.hands[0] && ((Gamestate) obj).hands[1] == this.hands[1] && ((Gamestate) obj).hands[2] == this.hands[2] && ((Gamestate) obj).hands[3] == this.hands[3];
	}
	
	public int hashCode(){
		return Integer.parseInt(String.valueOf(hands[0]) + String.valueOf(hands[1]) + String.valueOf(hands[2]) + String.valueOf(hands[3]));
	}
}
