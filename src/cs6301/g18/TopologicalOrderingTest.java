package cs6301.g18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import cs6301.g00.Graph;

public class TopologicalOrderingTest {
public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(new File("graph.in"));
		Graph g = Graph.readDirectedGraph(in);
		TopologicalOrdering topo = new TopologicalOrdering();
		List<Graph.Vertex> res1 = topo.toplogicalOrder1(g);
		System.out.println("Result of Algorithm 1: " + res1.toString());
		List<Graph.Vertex> res2 = topo.toplogicalOrder2(g);
		System.out.println("Result of Algorithm 2: " + res2.toString());
		
	}
}
