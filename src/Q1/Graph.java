//-----------------------------------------------------
// Title: Graph Class
// Author: Serdar Kemal Topkaya
// Description: This class represents our Graph structure and contains the customly created methods that are going to help us find,
// the number of city you need to go through to reach city Y from the city X, path which will take the minimum amount of time (in minutes) required by to move from city X
// to city Y, a total time through the path from city X to city Y.
//-----------------------------------------------------
package Q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	public int citiesNum; // // The number of cities in the graph.
	public LinkedList<Integer>[] adj; // Linked list of integers that stores the adjacent vertices of a vertex
	public LinkedList<LinkedList<Integer>> allPaths = new LinkedList<>(); // LinkedList that contains every possible paths in
																			// the graph

	@SuppressWarnings("unchecked")
	public Graph(int citiesNum)
	// --------------------------------------------------------
	// Summary: Initializes the variable citiesNum with the given vertex number
	// citiesNum and creates an adjacency list for every vertex in the graph
	// Precondition: citiesNum is an integer
	// Postcondition: The value of citiesNum is set and adjacency lists for every
	// vertex are created
	// --------------------------------------------------------

	{
		this.citiesNum = citiesNum;
		this.adj = new LinkedList[citiesNum];
		for (int i = 0; i < citiesNum; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int v, int w)

	// --------------------------------------------------------
	// Summary: Creates an edge between the given vertices v and w by adding them to
	// each others' adjacency list
	// Precondition: v and w are integers
	// Postcondition: And edge between vertex v and vertex w is created
	// --------------------------------------------------------
	{
		adj[v].add(w);
		adj[w].add(v);
	}

	public void findAllPathsRecursive(int start, int finish, boolean[] isVisited, LinkedList<Integer> paths)
	// --------------------------------------------------------
	// Summary: In this method helping findSmallestPath method, it add the all the
	// paths that found before to an one LinkedList, so we have one big List that have list of paths. Also, in
	// this method, we create the path recursively.
	// Precondition: start and finish are an integer, isVisited is a boolean array,
	// paths is an integer LinkedList
	// --------------------------------------------------------

	{

		if (start == finish) {
			LinkedList<Integer> path = new LinkedList<>(paths); // LinkedList of integers that stores the current path
			allPaths.add(path); // adds the path to one big List that have list of paths
			return;
		}

		isVisited[start] = true; // an array that maintains whether a city has been visited or not.

		for (Integer cityPoint : adj[start])
		// in this for if city is not visited before, we add this city to the path and with
		// findAllPathsRecursive method it will be visited.
		{

			if (!isVisited[cityPoint]) {
				paths.add(cityPoint);
				findAllPathsRecursive(cityPoint, finish, isVisited, paths);
				paths.remove(cityPoint); // if the two city has not connection it removes the city from the path
			}

		}
		isVisited[start] = false;
	}

	public void findSmallestPath(int start, int finish, int runningTime, int loadingTime)
	// --------------------------------------------------------
	// Summary: In this method, using the findAllPathsRecursive method, it finds all
	// the paths that using given start and finish point. After that it sorts the list to find the
	// lexicographically smallest path. Finally, to prints the result, it calls the printSmallestPath method.
	// Precondition: start, finish, runningTime and loadingTime are an integer
	// --------------------------------------------------------

	{

		boolean[] isVisited = new boolean[citiesNum]; // array that to know which point is visited or not
		// Such situations are called Cycles. To avoid forming a cycle in our graph, we
		// maintain isVisited.

		LinkedList<Integer> onePath = new LinkedList<>(); // adds the point to create one path

		onePath.add(start);

		findAllPathsRecursive(start, finish, isVisited, onePath); // calling method that add to paths that find to one List

		sortAndFilterPaths(allPaths);

		printSmallestPath(allPaths, runningTime, loadingTime); // calling method that prints the result
	}

	
	public LinkedList<LinkedList<Integer>> sortAndFilterPaths(LinkedList<LinkedList<Integer>> paths) 
	// --------------------------------------------------------
	// Summary: First finds the paths while sorting the  contents of each path lexicographically at the same time. 
	// Then, sorts the paths according to their length in an ascending order.
	// Precondition: paths is a linked list that contains linked lists of integers,
	// include is an integer
	// Postcondition: paths that are going to be printed are returned to the calling
	// method after sorted and filtered out
	{
		LinkedList<LinkedList<Integer>> pathsToPrint = new LinkedList<LinkedList<Integer>>(); // linked list that
		// contains the paths that are going to be printed

		for (int i = 0; i < paths.size(); i++) {
			// First outer for for sorting the contents of the paths lexicographically
			for (int k = 0; k < paths.size(); k++) {
				paths.get(k).sort(null);
				pathsToPrint.add(paths.get(k));

			}

		}

		for (int i = 0; i < pathsToPrint.size(); i++) {
			// Second outer for sorting the paths with respect to their sizes
			for (int k = 0; k < pathsToPrint.size(); k++) {
				if (pathsToPrint.get(i).size() > pathsToPrint.get(k).size()) {
					LinkedList<Integer> temp = pathsToPrint.get(i);
					pathsToPrint.set(i, pathsToPrint.get(k));
					pathsToPrint.set(k, temp);
				}
			}
		}

		return pathsToPrint;
	}

	public void printSmallestPath(LinkedList<LinkedList<Integer>> paths, int loadingTime, int runningTime)
	// --------------------------------------------------------
	// Summary: This method prints the number of city you need to go through to
	// reach city Y from the city X.
	// Prints the path which will take the minimum amount of time (in minutes)
	// required by to move from city X to city Y.
	// Prints the total time through the path from city X to city Y.
	// Precondition: paths is a LinkedList of integer Lists, loadingTime is an integer,
	// runningTime is an integer
	// --------------------------------------------------------

	{

		System.out.println(paths.get(0).size()); // number of city you need to go through to reach city Y from the city
													// X.

		for (int i = 0; i < paths.get(0).size(); i++) {
			System.out.print((paths.get(0).get(i) + 1) + " "); // smallest path from city X to city Y.
		}
		System.out.println();
		timeCalculator(paths.get(0), loadingTime, runningTime); // total time through the path from city X to city Y.

	}

	public void timeCalculator(LinkedList<Integer> path, int loadingTime, int runningTime)
	// --------------------------------------------------------
	// Summary: This method finds the total time through the path from city X to
	// city Y.
	// In this method, I created my own calculation to find the time.
	// Precondition: path is an integer LinkedList, loadingTime is an integer, runningTime
	// is an integer
	// --------------------------------------------------------
	{

		int constant1 = 0; // constant value that I will use for the calculation.
		int constant2 = 0; // constant value that I will use for the calculation.
		if (loadingTime < runningTime) {
			// to use to find loading time in general, I find this constant.
			constant1 = Math.abs((loadingTime * 2) - runningTime);
		}

		if (path.size() > 3) {
			// if path's size greater than I divide the size of path to 2. and if its double
			// I take the ceil value of it.
			constant2 = (int) (Math.ceil(path.size() / 2.0));

		}

		else {
			// if it is not greater than 3, I just divide the size of path to 2 and take the
			// integer value of it.
			constant2 = (path.size() / 2);

		}

		int totalTime = ((path.size() - 1) * runningTime) + (constant1 * constant2); // this calculation gives the
		// total time (in minutes) required to move from city X to city Y.
		System.out.println(totalTime);

	}

}
