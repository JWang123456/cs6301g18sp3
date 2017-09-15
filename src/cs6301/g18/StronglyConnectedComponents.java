package cs6301.g18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;


public class StronglyConnectedComponents {
	
	List<List<Vertex>> ans = new LinkedList<>();
	List<Vertex> temp;
	
	int stronglyConnectedComponents(Graph g) {
		
		Iterator it = g.iterator();
		List<Vertex> decFinList = new LinkedList<>();
		DFS(g, it, decFinList);
		ans = new LinkedList<>();
		
		Iterator rit = decFinList.iterator();
		for(Vertex ver: decFinList) {
			ver.seen = false;
			ver.adj = new LinkedList<>(ver.revAdj);
			ver.revAdj = new LinkedList<>(ver.adj);
		}
		List<Vertex> rdecFinList = new LinkedList<>();
		
		DFS(g, rit, rdecFinList);
		//System.out.println(ans);
		
		return ans.size();
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
				temp = new LinkedList<>();
				DFSVisit(g, u, time, cno, topNum, decFinList);
				ans.add(temp);
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
		temp.add(u);
	}
}
