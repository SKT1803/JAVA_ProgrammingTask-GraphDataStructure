//-----------------------------------------------------
// Title: Main Class
// Author: Serdar Kemal Topkaya
// ID: 71200082370
// Section: 3
// Assignment: 1
// Description: This class reads the city numbers, bidirectional roads between them, loading and running time from the console.
// Constructs the graph by building the edges between the cities (by calculating the number of cities and edges that it should contain) 
// and calls the method that will find the lexicographically smallest path which will take the minimum amount 
// of time (in minutes) required to move from city X to city Y. 
//-----------------------------------------------------
package Q1;

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
		Graph graph = new Graph(scan.nextInt()); // Graph object with the city number that are created by processing the
													// input that is passed on
		int routesNum = scan.nextInt(); // integer that represents the int value of undirected flight routes

		int loadingTime = scan.nextInt(); // integer that represents the int value of loading time
		int runningTime = scan.nextInt(); // integer that represents the int value of running time

		for (int i = 0; i < routesNum; i++) {
			graph.addEdge(scan.nextInt() - 1, scan.nextInt() - 1); // adds the given edges to the graph
		}

		int start = scan.nextInt(); // integer that represents the int value of start city
		int finish = scan.nextInt(); // integer that represents the int value of end city

		graph.findSmallestPath(start - 1, finish - 1, loadingTime, runningTime); // calling method to find
		// lexicographically smallest path which will take the minimum amount of time
		// (in minutes) required to move from city X to city Y

	}

}