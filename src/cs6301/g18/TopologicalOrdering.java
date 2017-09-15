package cs6301.g18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;

public class TopologicalOrdering {
	/** Algorithm 1. Remove vertices with no incoming edges, one at a
	    *  time, along with their incident edges, and add them to a list.
	 * @throws Exception 
	    */
	List<Graph.Vertex> toplogicalOrder1(Graph g) throws Exception {
		int topNum = 0;
		Queue<Vertex> q = new LinkedList<>();
		List<Vertex> topList = new ArrayList<>();
		for(Vertex ver: g.v) {
			ver.inDegree = ver.revAdj.size();
			if(ver.inDegree == 0) q.add(ver);
		}
		while(!q.isEmpty()) {
			Vertex u = q.remove();
			u.top = topNum++;
			topList.add(u);
			for(Edge e: u.adj) {
				e.otherEnd(u).inDegree--;
				if(e.otherEnd(u).inDegree == 0) q.add(e.otherEnd(u));
			}
		}
		if(topNum != g.size()) {
			throw new Exception();
		}
		return topList;
	}

	   /** Algorithm 2. Run DFS on g and add nodes to the front of the output list,
	    *  in the order in which they finish.  Try to write code without using global variables.
	    */
	List<Graph.Vertex> toplogicalOrder2(Graph g) {
		Iterator it = g.iterator();
		List<Vertex> decFinList = new LinkedList<>();
		DFS(g, it, decFinList);
		return decFinList;
	}
	
	void DFS(Graph g, Iterator<Vertex> it, List<Vertex> decFinList) {
		int topNum = g.size();
		int time = 0;
		int cno = 0;
		for(Vertex ver: g.v) {
			ver.seen = false;
		}
		while(it.hasNext()) {
			Vertex u = it.next();
			if(!u.seen) {
				cno++;
				DFSVisit(g, u, time, cno, topNum, decFinList);
			}
		}
	}
	
	void DFSVisit(Graph g, Vertex u, int time, int cno, int topNum, List<Vertex> decFinList) {
		u.seen = true;
		u.dis = ++time;
		u.cno = cno;
		for(Edge e: u.adj) {
			if(!e.otherEnd(u).seen) {
				e.otherEnd(u).parent = u;
				DFSVisit(g, e.otherEnd(u), time, cno, topNum, decFinList);
			}
		}
		u.fin = ++time;
		u.top = topNum--;
		decFinList.add(u);
	}
}
