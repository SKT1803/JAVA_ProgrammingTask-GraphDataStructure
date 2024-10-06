//-----------------------------------------------------
// Title: Graph Class
// Author: Serdar Kemal Topkaya
// ID: 71200082370
// Section: 3
// Assignment: 1
// Description: This class represents our Graph structure and contains the customly created methods that are going to help us find,
// the number of city you need to go through to reach city Y from the city X, path which will take the minimum amount of time (in minutes) required by to move from city X
// to city Y, a total time through the path from city X to city Y.
//-----------------------------------------------------
//-----------------------------------------------------
// Title: Graph Class
// Author: Serdar Kemal Topkaya
// ID: 71200082370
// Section: 3
// Assignment: 1
// Description: This class represents our Graph structure and contains the customly created methods that are going to help us find,
// sort, filter and print the paths that goes from the given starting island and end to this city island again.
// So the path will be a cycle and it contains the island that we should include.
//-----------------------------------------------------
package Q2;

import java.util.LinkedList;

public class Graph {

	LinkedList<LinkedList<Integer>> paths = new LinkedList<LinkedList<Integer>>(); // linked list object that contains
	// linked lists of integers - it stores the paths that we found during the path-finding process

	public int islandsNum; // int variable that stores the number of islands (vertices) in our graph
	public LinkedList<Integer>[] adj; // linked list of integers that stores the adjacent vertices of a vertex

	@SuppressWarnings("unchecked")
	public Graph(int islandsNum)
	// --------------------------------------------------------
	// Summary: Initializes the variable islandNum with the given vertex number islandNum and
	// creates an adjacency list for every vertex in the graph
	// Precondition: islandNum is an integer
	// Postcondition: The value of islandNum is set and adjacency lists for every vertex are
	// created
	// --------------------------------------------------------
	{
		this.islandsNum = islandsNum;
		adj = (LinkedList<Integer>[]) new LinkedList[islandsNum];
		for (int v = 0; v < islandsNum; v++)
			adj[v] = new LinkedList<Integer>();
	}

	public void addEdge(int v, int w)
	// --------------------------------------------------------
	// Summary: Creates an edge between the given vertices v and w by adding them to
	// each others' adjacency list.
	// Precondition: v and w are integers
	// Postcondition: And edge between vertex v and vertex w is created
	// --------------------------------------------------------
	{
		adj[v].add(w);
		adj[w].add(v);
	}

	public Iterable<Integer> adj(int v)
	// --------------------------------------------------------
	// Summary: Returns the iterable adjacency list of the specified vertex for
	// further use
	// Precondition: v is an integer
	// Postcondition: Adjacency list of vertex v is returned
	// --------------------------------------------------------
	{
		return adj[v];
	}

	public int islandsNum()
	// --------------------------------------------------------
	// Summary: Returns the island number of the graph
	// --------------------------------------------------------
	{
		return islandsNum;
	}

	public void findPaths(int start, int finish, int include) 
	// --------------------------------------------------------
	// Summary: Creates isVisited array and pathList list to keep track of the visit
	// information and the current path, respectively. Then saves the possible paths
	// that are from starting island to finish island which is the start again (cycle) 
	// into the path list (our linked list of paths), by passing the arguments into the findPathsDFS method (with
	// the start island being already added to the current pathList). Finally,
	// prints all the paths that are fit to the given criteria in the question, with
	// the help of printPaths method.
	// Precondition: start, finish and include are integers
	// --------------------------------------------------------
	{
		boolean[] isVisited = new boolean[islandsNum()]; // boolean array that is keeping track of the information about which
		// island is visited
		LinkedList<Integer> currentPath = new LinkedList<>(); // linked list of integers that stores the current path
		currentPath.add(start);
		int parentOfTheFirstVertex = -1;
		findPathsDFS(start, finish, parentOfTheFirstVertex, isVisited, currentPath);
		printShortestPaths(paths, include);

	}

	@SuppressWarnings("unchecked")
	private void findPathsDFS(Integer v, Integer finish, Integer parentVertex, boolean[] isVisited, LinkedList<Integer> currentPath)
	// --------------------------------------------------------
	// Summary: By applying a Depth-First Search approach, we find all the paths
	// that start with vertex v and ends with vertex finish. We start from the starting
	// island and keep storing the visited islands in our path. If the cycle is
	// completed (we determine that by checking whether the starting island is equal to
	// finish island), we save the path that goes from starting island and become a cycle into the paths list.
	// Precondition:  v, finish, parentVertex is an integer, isVisited is a boolean array, currentPath is an int LinkedList
	// --------------------------------------------------------
	{

		isVisited[v] = true;
		for (Integer i : adj(v)) {
			if (!isVisited[i]) {
				currentPath.add(i);
				findPathsDFS(i, finish, v, isVisited, currentPath);
				currentPath.remove(i);
			} else if (!i.equals(parentVertex) && i.equals(finish)) 
				// In here we are looking for a cycle
			{
				paths.add((LinkedList<Integer>) currentPath.clone());
			}
		}
		isVisited[v] = false;

	}

	private void printShortestPaths(LinkedList<LinkedList<Integer>> paths, int include) 
	// --------------------------------------------------------
	// Summary: Prints the paths that fit to some specific criteria after the paths
	// are sorted and filtered by the sortPaths method
	// Precondition: paths is a linked list that contains linked lists of integers, include is an integer
	// --------------------------------------------------------
	{
		LinkedList<LinkedList<Integer>> pathsToPrint = sortPaths(paths, include); // linked list that contains
		// the paths after the sorting operations

		if (pathsToPrint.size() == 0) 
			// That means there are no cycle
		{
			return;
		} else {

			for (int i = 0; i < pathsToPrint.get(pathsToPrint.size() - 1).size() - 1; i++) {
				System.out.print((pathsToPrint.get(pathsToPrint.size() - 1).get(i) + 1) + " ");
			}
			System.out.println(((pathsToPrint.get(pathsToPrint.size() - 1).getLast() + 1)));
		}

	}

	private LinkedList<LinkedList<Integer>> sortPaths(LinkedList<LinkedList<Integer>> paths, int include)
	// --------------------------------------------------------
	// Summary: First finds the paths that have include island while sorting the
	// contents of each path  lexicographically at the same time. Then, sorts the paths
	// according to their length in an ascending order. 
	// Precondition: paths is a linked list that contains linked lists of integers, include is an integer
	// Postcondition: paths that are going to be printed are returned to the calling
	// method after sorted and filtered out
	// --------------------------------------------------------
	{
		LinkedList<LinkedList<Integer>> pathsToPrint = new LinkedList<LinkedList<Integer>>(); // linked list that
		// contains the paths that are going to be printed
         
		for (int i = 0; i < paths.size(); i++)
		// First outer for for sorting the contents of the paths lexicographically
	    // and checks the paths has include island, if has it add the island to pathsToPrint
		{
			
			for (int k = 0; k < paths.size(); k++) {
				if (paths.get(k).contains(include)) {
					paths.get(k).sort(null);
					pathsToPrint.add(paths.get(k));
				
				}
			}

		}
 
		for (int i = 0; i < pathsToPrint.size(); i++)
		// Second outer for sorting the paths with respect to their sizes
		{
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
}