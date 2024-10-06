//-----------------------------------------------------
// Title: Main Class
// Author: Serdar Kemal Topkaya
// Description: This class reads the city numbers, bidirectional roads between them, loading and running time from the console.
// Constructs the graph by building the edges between the cities (by calculating the number of cities and edges that it should contain) 
// and calls the method that will find the lexicographically smallest path which will take the minimum amount 
// of time (in minutes) required to move from city X to city Y. 
//-----------------------------------------------------

//-----------------------------------------------------
//Title: Main Class
//Author: Serdar Kemal Topkaya
//ID: 71200082370
//Section: 3
//Assignment: 1
//Description: This class reads the islands number around the city, undirected paths between the islands 
//from the console. Constructs the graph by building the edges between the islands (by calculating the number of islands and edges that it should contain) 
//and calls the method that will find the lexicographically smallest path which will have a cycle and have included island.
//-----------------------------------------------------
package Q2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	// --------------------------------------------------------
	// Summary: Reads the input integer from the console and constructs the graph
	// object by processing the input and finds the path that goes through the
	// provided inputs.
	// --------------------------------------------------------
	{

		Scanner scan = new Scanner(System.in);

		Graph graph = new Graph(scan.nextInt()); // Graph object with the islands number that are created by processing
													// the
		// input that is passed on
		int pathsNum = scan.nextInt(); // integer that represents the int value of undirected paths between the islands

		for (int i = 0; i < pathsNum; i++) {
			graph.addEdge(scan.nextInt() - 1, scan.nextInt() - 1); // adds the given edges to the graph
		}

		int start = scan.nextInt(); // integer that represents the int value of start island
		int include = scan.nextInt(); // // integer that represents the int value of island that we should include in
										// the path

		graph.findPaths(start - 1, start - 1, include - 1); // calling method to print the Print the lexicographically
															// smallest path

	}

}
