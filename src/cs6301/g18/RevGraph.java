package cs6301.g18;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;

public class RevGraph extends Graph {

	public RevGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addEdge(Vertex from, Vertex to, int weight) {
		Edge e = new Edge(from, to, weight);
		if(this.directed) {
		    to.adj.add(e);
	        from.revAdj.add(e);
		} else {
		    to.adj.add(e);
		    from.adj.add(e);
		}
	}

}
