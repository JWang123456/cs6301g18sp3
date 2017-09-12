package cs6301.g18.sp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.Iterator;

import cs6301.g18.sp3.Graph.Edge;
import cs6301.g18.sp3.Graph.Vertex;
public class Remove  {



	/** Algorithm 1. Remove vertices with no incoming edges, one at a
	    *  time, along with their incident edges, and add them to a list.
	    */
	
	LinkedList<Graph.Vertex> toplogicalOrder1(Graph g) {
		
		
		Vertex[] v = new Vertex[g.n];
		int[] indegree = new int[g.n]; 
		int[] outdegree = new int[g.n];
		LinkedList<Graph.Vertex> L = new LinkedList<Graph.Vertex>();
		
		Iterator<Vertex> vertex = g.iterator();
		
		while(vertex.hasNext()) {
	
			indegree[vertex.next().name] = vertex.next().revAdj.size();
			outdegree[vertex.next().name] = vertex.next().adj.size();	
			
			if(indegree[vertex.next().name] == 0) {
				for(Edge e: vertex.next().adj) {
					Vertex o = e.otherEnd(vertex.next());
					o.remove(e);
					
				};
				
				
				L.add(v[vertex.next().name]);
				v = Arrayutils.removeElement(v, vertex.next());
			}
		}

		return L;	
	}
	


	public static void main(String[] args)throws FileNotFoundException {
		
		Scanner in;
	        if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }
		
		//File inputFile = new File("input.in");
       // in = new Scanner(inputFile);
		
		Graph g = Graph.readDirectedGraph(in);
		
		System.out.println("start at " + g.n);
		Remove remove = new Remove();
		
		LinkedList<Graph.Vertex> list = remove.toplogicalOrder1(g);

		System.out.println(list.get(1));
		for(Vertex vi : list) {
			System.out.println(vi.name);
		}
		//Iterator<Vertex> it = list.iterator();
		///while(it.hasNext()) {
		//	System.out.println(it.next().name);
		//}
		
		
		System.out.println("done");

	}

}
