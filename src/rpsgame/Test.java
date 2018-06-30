package rpsgame;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.Queue;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Test {


	public static void main (String[] args) {
	    HashSet<GameState> seen = new HashSet<>();
	    int limit = 5;
		GameState start = new GameState(1, 1, 1, 1, 0, limit);
        Graph rps = new SingleGraph("rps");
        rps.addAttribute("layout.quality", 4);
        rps.addAttribute("ui.quality");
        rps.addAttribute("ui.antialias");
        rps.addAttribute("ui.stylesheet", "url(file:///Users/kevinzhang/Documents/CS/java-experiments/rock-paper-scissors/src/rpsgame/styles.css)");

        bfs(seen, start, rps);
//        System.out.println(seen.size());
        rps.display();

    }


	public static void dfsRecurse(HashSet<GameState> s, GameState curr) {
	    if (curr.checkWin() != -1) {
	        s.add(curr);
	        return;
        }
        curr.fillNeighbors();
        s.add(curr);
        for (GameState g : curr.adjacent) {
            if (!s.contains(g)) {
                dfsRecurse(s, g);
            }
        }
    }

    public static void bfs(HashSet<GameState> s, GameState start, Graph g) {
	    Queue<GameState> toVisit = new ArrayDeque<>();
	    toVisit.offer(start);
	    g.addNode(start.toString());
	    Node st = g.getNode(start.toString());
//	    System.out.println(start.hashCode());
	    st.addAttribute("ui.label", start.hashCode());
	    while(!toVisit.isEmpty()) {
	        GameState curr = toVisit.remove();
	        if (curr.checkWin() != -1) {
	            s.add(curr);
	            continue;
            }
	        s.add(curr);
	        curr.fillNeighbors();
	        for (GameState gs : curr.adjacent) {
	            if(!s.contains(gs)) {
	                toVisit.offer(gs);
	                if (g.getNode(gs.toString()) == null) {
                        g.addNode(gs.toString());
                        Node n = g.getNode(gs.toString());
                        n.addAttribute("ui.label", gs.hashCode());
                    }
                    String edgeId = String.valueOf(curr.hashCode()) + String.valueOf(gs.hashCode());
                    if(g.getEdge(edgeId) == null) {
                        g.addEdge(edgeId, curr.toString(), gs.toString());
                    }
                }
            }
	    }
    }
}
