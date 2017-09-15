package cs6301.g18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs6301.g00.Graph;

public class StronglyConnectedComponentsTest {
public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("graph.in"));
		Graph g = Graph.readDirectedGraph(in);
		
		StronglyConnectedComponents scc = new StronglyConnectedComponents();
		
		System.out.println("The number of strongly connected components are: " + scc.stronglyConnectedComponents(g));

	}
}
