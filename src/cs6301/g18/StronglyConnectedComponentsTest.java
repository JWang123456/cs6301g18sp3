package cs6301.g18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import cs6301.g00.Graph;

public class StronglyConnectedComponentsTest {
public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("graph.in"));
		Graph g = Graph.readDirectedGraph(in);
		
		Scanner rin = new Scanner(new File("graph.in"));
		Graph rg = null;
		
		StronglyConnectedComponents scc = new StronglyConnectedComponents(rg);
		System.out.println(scc.stronglyConnectedComponents(g));
		System.out.println(scc.order);
		
	}
}
