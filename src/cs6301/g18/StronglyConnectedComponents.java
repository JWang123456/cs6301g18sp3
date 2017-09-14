package cs6301.g18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Vertex;


public class StronglyConnectedComponents {
	
	Graph rg;
	List<Integer> order = new ArrayList<>();
	List<List<Integer>> res= new ArrayList<>();
	
	HashMap<Vertex, Boolean> map = new HashMap<>();
	
	public StronglyConnectedComponents(Graph rg) {
		this.rg = rg;
	}
	
	int stronglyConnectedComponents(Graph g) {
		return stronglyConnectedComponents(g, rg);
	}

	int stronglyConnectedComponents(Graph g, Graph rg) {
		return 0;
		
	}
	
	void dfs(Graph g, Vertex v) {
		
	}
}
