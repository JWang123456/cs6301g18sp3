package cs6301.g18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;

public class TopologicalOrdering {
	/** Algorithm 1. Remove vertices with no incoming edges, one at a
	    *  time, along with their incident edges, and add them to a list.
	    */
	List<Graph.Vertex> toplogicalOrder1(Graph g) {
		List<Graph.Vertex> res = new ArrayList<>();
		HashMap<Vertex, Boolean> visitedV = new HashMap<>();
		HashMap<Edge, Boolean> visitedE = new HashMap<>();
		Iterator<Vertex> itr = g.iterator();
		while(true) {
			//If all vertexes have been visited, break and return result
			int count = 0;
			for(int i = 0; i < g.size() - 1; i++) {
				if(visitedV.containsKey(g.getVertex(i + 1)) && visitedV.get(g.getVertex(i + 1))) {
					count++;
				}
			}
			if(count == g.size() - 1) break;
			
			//If not all vertexes have been visited and iterator is at end, go back to beginning
			if(!itr.hasNext()) itr = g.iterator();
			
			//Visited each vertex with no incoming edges, mark outgoing edges to true (delete) 
			Vertex v = itr.next();
			if(!visitedV.containsKey(v) || visitedV.get(v) == false) {
				//if one incoming edge is not deleted(marked true), flag is true, continue
				boolean flag = false;
				for(Edge ed: v.revAdj) {
					if(!(visitedE.containsKey(ed) && visitedE.get(ed) == true)) {
						flag = true;
					}
				}
				//If all incoming edges have been deleted(flag is false) or no incoming edges, add vertex to result, delete outgoing edges(mark true)
				if(v.revAdj.size() == 0 || !flag) {
					res.add(v);
					visitedV.put(v, true);
					Iterator<Edge> itrE = v.iterator();
					while(itrE.hasNext()) {
						Edge e = itrE.next();
						if(!visitedE.containsKey(e) || visitedE.get(e) == false) {
							visitedE.put(e, true);
						}
					}
				}
			}
		}
		return res; 
	}

	   /** Algorithm 2. Run DFS on g and add nodes to the front of the output list,
	    *  in the order in which they finish.  Try to write code without using global variables.
	    */
	List<Graph.Vertex> toplogicalOrder2(Graph g) {
		List<Graph.Vertex> res = new ArrayList<>();
		Iterator<Vertex> itr = g.iterator();
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		while(itr.hasNext()) {
			dfs(g, itr.next(), res, visited);
			System.out.println(res.toString());
		}
		return res;
	}
	
	void dfs(Graph g, Vertex v, List<Graph.Vertex> res, HashMap<Vertex, Boolean> visited) {
		if(visited.containsKey(v) && visited.get(v) == true) {
			return;
		}
		Iterator<Edge> itrE = v.iterator();
		Edge temp = null;
		while(itrE.hasNext()) {
			temp = itrE.next();
			if(!visited.containsKey(temp.otherEnd(v)) || visited.get(temp.otherEnd(v)) == false) {
				dfs(g, temp.otherEnd(v), res, visited);
				visited.put(temp.otherEnd(v), true);
				res.add(temp.otherEnd(v));
			}
		}
	}
}
