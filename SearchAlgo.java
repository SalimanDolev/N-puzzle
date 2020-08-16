import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class SearchAlgo {
	static int itertation = 1;
	static String path;
	static int numOfVert;
	static int cost;
	static double runTime;
	static int Degree = 1;
	/**
	 * function to Write to file when there is no solution
	 */
	public void WritingNoPath(int NumberOfVer) {
		try {
			FileWriter writer = new FileWriter("output.txt", true);
			writer.write("no path");
			writer.write("\r\n");   // write new line
			writer.write("Num: " + NumberOfVer );
			writer.write("\r\n");   // write new line
			//calculate the cost
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write to file function
	 * first line writes the path
	 * second line writes the number of vertices
	 * third line writes the cost of the algo
	 * if "with time" than on the fifth line write the Run time
	 */
	public void writeToFile(Vertex ans, int NumOfV, boolean withtime ,double endTime ) {
		try {
			FileWriter writer = new FileWriter("output.txt", true);
			writer.write(ans.getPath().substring(0,ans.getPath().length()-1 ));
			writer.write("\r\n");   // write new line
			writer.write("Num: " + NumOfV);
			writer.write("\r\n");   // write new line
			//calculate the cost

			writer.write("Cost: " + ans.getCost());
			if(withtime) {
				writer.write("\r\n");   // write new line
				writer.write( endTime+ " seconds");
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * print the open list
	 * 
	 */
	public void printOpenList(Hashtable<String, Vertex> openList, int iteration,int n,int m) {
		System.out.println("The open list in the " + iteration + " iteration");
		Enumeration<Vertex> s = openList.elements();
		while (s.hasMoreElements()) {
			Vertex v = s.nextElement();
			String temp[][] = v.getMat();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {

					System.out.print(temp[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println(v.getF());
			System.out.println();
		}
	}

	/**
	 * 	
	 * @param The new state to check
	 * @param The goal state
	 * @return true if the matrix is equal false if not
	 */
	public static boolean ChecksMat(String[][] mat , String [][] mat2) {
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2[0].length; j++) {
				if(!mat[i][j].contains(mat2[i][j]))
					return false;
			}
		}
		return true;
	}

	//The order of the creation of the child's is : left up right down


	/**
	 * BFS - Breadth First Search
	 * Going over the all vertices in each degree
	 *  
	 * This function is BFS search to find the answer to 8-puzzle 
	 * @param StartMat - the start vertex
	 * @param finalMat - the end vertex
	 */
	public void BFS(String [][] StartMat,String [][]finalMat,boolean open,boolean Time) {

		Vertex v = new Vertex(StartMat);numOfVert++;
		long startTime = System.currentTimeMillis();
		double endtime;
		Hashtable<String, Vertex> openList = new Hashtable<String, Vertex>();
		Hashtable<String, Vertex> closedList = new Hashtable<String, Vertex>();
		openList.put(v.getMatToString(), v);// enter first vertex to the open list
		Queue<Vertex> states = new LinkedList<Vertex>(); 
		states.add(v);// enter the root to the queue

		// add checking that the start position is not the end position

		
		while(!states.isEmpty()) {

			if(open) {//print the open list to the screen
				printOpenList(openList, itertation, StartMat.length, StartMat[0].length);
				itertation++;
			}//end of printing
			Vertex checkVertex = states.poll();
			closedList.put(checkVertex.getMatToString(), checkVertex);
			openList.remove(checkVertex.getMatToString(), checkVertex);
			// if with open than print the openlist in each iteration

			//operators on the vertices
			Operators o = new Operators();

			//Left operator
			if(o.checkifLeft(checkVertex.getMat())) {
				Vertex left = o.operatorLeft(checkVertex);
				if(!openList.containsKey(left.getMatToString())  && !closedList.containsKey(left.getMatToString())) {
					numOfVert++;
					if(ChecksMat(left.getMat(), finalMat)) {// checks if the vertex equal to the final
						endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
						writeToFile(left,numOfVert, Time,endtime) ; // write to file function
						break;
					}else {
						openList.put(left.getMatToString(), left);
						states.add(left);
					}
				}
			}
			//Up operator
			if(o.checkifUp(checkVertex.getMat())) {
				Vertex up = o.operatorUp(checkVertex);
				if(!openList.containsKey(up.getMatToString()) && !closedList.containsKey(up.getMatToString())) {
					numOfVert++;
					if(ChecksMat(up.getMat(), finalMat)) {// checks if the vertex equal to the final
						endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
						writeToFile(up,numOfVert, Time,endtime) ; // write to file function
						break;
					}else {
						openList.put(up.getMatToString(), up);
						states.add(up);
					}
				}
			}
			//Right operator
			if(o.checkifRight(checkVertex.getMat())) {
				Vertex right = o.operatorRight(checkVertex);
				if(!openList.containsKey(right.getMatToString()) && !closedList.containsKey(right.getMatToString())) {
					numOfVert++;
					if(ChecksMat(right.getMat(), finalMat)) {// checks if the vertex equal to the final
						endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
						writeToFile(right,numOfVert, Time,endtime) ; // write to file function
						break;
					}else {
						openList.put(right.getMatToString(), right);
						states.add(right);
					}
				}
			}
			//Down operator
			if(o.checkifDown(checkVertex.getMat())) {
				Vertex down = o.operatorDown(checkVertex);
				if(!openList.containsKey(down.getMatToString()) && !closedList.containsKey(down.getMatToString())) {
					numOfVert++;
					if(ChecksMat(down.getMat(), finalMat)) {// checks if the vertex equal to the final
						endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
						writeToFile(down,numOfVert, Time,endtime) ; // write to file function
						break;
					}else {
						openList.put(down.getMatToString(), down);
						states.add(down);
					}
				}
			}
		}//end while
		if (states.size() == 0) {WritingNoPath(numOfVert);};


	}

	

	/**
	 *	D F I D - depth first iterative deepening 
	 * Recursive search, doing DFS with limit in each iteration
	 * 
	 * @param Start - the start vertex
	 * @param Final - the final vertex
	 * @param limit - the limit of each iteration
	 * @param openlist - the list of vertex that need to be evaluate
	 * @return 1) if found the Mat that is the answer write to file
	 * 		   2) if limit = 0 return the vertex and check if it got to cutoff
	 * 		   3) return fail if there is no solution
	 */

	static int cutOff = 10;
	
	public Vertex Limited_DFS(Vertex Start,Vertex Final,int limit,Hashtable<String, Vertex> openList,boolean open,boolean Time,long startTime) {
		if(ChecksMat(Start.getMat(), Final.getMat())) {// check if the start vertex equal to final
			double endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
			writeToFile(Start,numOfVert, Time,endtime);// write to file function
			return Start;// return the final vertex
		}
		
		else if (limit == 0) { // checks if the limit for this iteration is bigger then 0
		
			Vertex v = new Vertex(Start);
			v.setDepth(cutOff);
			return v;
		
		}else{
			
			if(open) {//print the open list to the screen
				printOpenList(openList, itertation, Start.getMat().length, Start.getMat()[0].length);
				itertation++;
			}//end of printing
			
			openList.put(Start.getMatToString(), Start); // add to the openlist the start vertex
			boolean isCutoff = false;
			Operators o = new Operators();
			//operators on start
			//left operator
			if(o.checkifLeft(Start.getMat())) { // checks if left operator can be made
				Vertex left = o.operatorLeft(Start);
				if(!openList.containsKey(left.getMatToString())) { //checks if the left vertex already in open list
					numOfVert++;
					Vertex result = new Vertex(Limited_DFS(left, Final, limit-1, openList,open,Time,startTime)); // recursive call for DFS
					if (result.getDepth() == cutOff) {// check if the depth of the vertex is equal to cutoff
						isCutoff = true;
					}else if (result.getDepth() != -1) {// checks if fail
						return result;
					}
				}
			}



			//Up operator
			if(o.checkifUp(Start.getMat())) {
				Vertex up = o.operatorUp(Start);
				if(!openList.containsKey(up.getMatToString())) {
					numOfVert++;
					Vertex result = new Vertex (Limited_DFS(up, Final, limit-1, openList,open,Time,startTime));
					if (result.getDepth() == cutOff) {
						isCutoff = true;
					}else if (result.getDepth() != -1) {
						return result;
					}
				}
			}


			//Right operator
			if(o.checkifRight(Start.getMat())) {
				Vertex right = o.operatorRight(Start);
				if(!openList.containsKey(right.getMatToString())) {
					numOfVert++;
					Vertex result = new Vertex(Limited_DFS(right, Final, limit-1, openList,open,Time,startTime));
					if (result.getDepth() == cutOff) {
						isCutoff = true;
					}else if (result.getDepth() != -1) { // fail return
						return result;
					}
				}
			}

			//Down operator
			if(o.checkifDown(Start.getMat())) {
				Vertex down = o.operatorDown(Start);
				if(!openList.containsKey(down.getMatToString())) {
					numOfVert++;
					Vertex result = new Vertex( Limited_DFS(down, Final, limit-1, openList,open,Time,startTime));
					if (result.getDepth() == cutOff) {
						isCutoff = true;
					}else if (result.getDepth() != -1) {
						return result;
					}
				}
			}//end all operators

			openList.remove(Start.getMatToString());
			if(isCutoff) {
				Vertex v = new Vertex(Start);
				v.setDepth(cutOff);
				return v;
			}else{
				Vertex v = new Vertex(Start);
				v.setDepth(-1);
				return v;

			}
		}
	}

	public void FIDF(String [][] startMat,String [][]finalMat,boolean open,boolean Time) {
		Vertex start = new Vertex(startMat);//create the start vertex
		numOfVert++; // first vertex
		Vertex enswer = new Vertex(finalMat);// create the final vertex
		long startTime = System.currentTimeMillis();
		for (int depth = 1; depth < 100; depth++) {
			Hashtable<String, Vertex> openList = new Hashtable<String, Vertex>();
			Vertex result = new Vertex(Limited_DFS(start,enswer,depth,openList,open,Time,startTime));
			if(result.getDepth() != cutOff && result.getDepth() <= cutOff && result.getDepth() >= 0) {
				break;
			}else if (result.getDepth() == -1) {
				System.out.println("here");
				WritingNoPath(numOfVert);
				break;
			}
		}
	}

	/**
	 * A* - Uniform Cost Search with f(v)
	 * 
	 * Heuristic function -  Using Manhattan distance to compute the distance of each number from his final place
	 *          /\
	 *          || 
	 *  f(v) = h(v) + g(v)
	 * 				   ||
	 * 				   \/
	 * 				The cost of the vertex until this move.
	 */

	public void aStar(String [][] StartMat,String [][]finalMat,boolean open,boolean Time) {
		Vertex v = new Vertex(StartMat);numOfVert++;
		long startTime = System.currentTimeMillis();
		double endtime;
		Hashtable<String, Vertex> openList = new Hashtable<String, Vertex>();
		Hashtable<String, Vertex> closedList = new Hashtable<String, Vertex>();
		openList.put(v.getMatToString(), v);// enter first vertex to the open list
		PriorityQueue<Vertex> states = new PriorityQueue<Vertex>(); 
		states.add(v);// enter the root to the queue
		H h = new H(finalMat); // **build the heuristic matrix to compare**
		v.setF(h.Heuricstic(v.getMat()) + v.getCost());
		int itertation = 1;
		while(!states.isEmpty()) {

			//if with open than print the openlist in each iteration
			if(open) {//print the open list to the screen
				printOpenList(openList, itertation, StartMat.length, StartMat[0].length);
				itertation++;
			}//end of printing

			///////////////////////////////////////////////////////////////////////////////////
			////////checking the first in priority queue is the goal///////////////////////////
			Vertex checkVertex = states.poll(); // poll from queue
			openList.remove(checkVertex.getMatToString(), checkVertex); // remove front

			if(ChecksMat(checkVertex.getMat(), finalMat)) {// checks if the vertex equal to the final
				endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
				writeToFile(checkVertex,numOfVert, Time,endtime) ; // write to file function
				break;
			}
			closedList.put(checkVertex.getMatToString(), checkVertex); // enter ther vertex to the closed list
			////////////////////////////////////////////////////////////////////////////////////////////
			///////start of operators///////////////////////////////////////////////////////////////////


			//operators on the vertices
			Operators o = new Operators();

			//Left operator
			if(o.checkifLeft(checkVertex.getMat())) {
				Vertex left = o.operatorLeft(checkVertex);
				
				if(!openList.containsKey(left.getMatToString())  && !closedList.containsKey(left.getMatToString())) {
					left.setF(h.Heuricstic(left.getMat()) + left.getCost());
					numOfVert++;
					openList.put(left.getMatToString(), left);
					states.add(left);
				}else if(openList.containsKey(left.getMatToString()) &&
						left.getF() < h.Heuricstic(openList.get(left.getKey()).getMat()) + openList.get(left.getKey()).getCost() ) { // if in open list and higher path cost replace him
					openList.replace(left.getMatToString(), left);
					states.add(left);
				}
			}
			//Up operator
			if(o.checkifUp(checkVertex.getMat())) { // checks if operator up is optional
				Vertex up = o.operatorUp(checkVertex);
				if(!openList.containsKey(up.getMatToString())  && !closedList.containsKey(up.getMatToString())) {
					up.setF(h.Heuricstic(up.getMat()) + up.getCost()); // set the f(v) of the vertex
					numOfVert++;
					openList.put(up.getMatToString(), up);
					states.add(up);
				}else if(openList.containsKey(up.getMatToString()) &&
						up.getF() < h.Heuricstic(openList.get(up.getKey()).getMat()) + openList.get(up.getKey()).getCost() ) { // if in open list and higher path cost replace him
					openList.replace(up.getMatToString(), up);
					states.add(up);
				}
			}
			//Right operator
			if(o.checkifRight(checkVertex.getMat())) {
				Vertex right = o.operatorRight(checkVertex);
				if(!openList.containsKey(right.getMatToString())  && !closedList.containsKey(right.getMatToString())) {
					right.setF(h.Heuricstic(right.getMat()) + right.getCost());
					numOfVert++;
					openList.put(right.getMatToString(), right);
					states.add(right);
				}else if(openList.containsKey(right.getMatToString()) &&
						right.getF() < h.Heuricstic(openList.get(right.getKey()).getMat()) + openList.get(right.getKey()).getCost() ) { // if in open list and higher path cost replace him
					openList.replace(right.getMatToString(), right);
					states.add(right);
				}
			}
			//Down operator
			if(o.checkifDown(checkVertex.getMat())) {
				Vertex down = o.operatorDown(checkVertex);
				if(!openList.containsKey(down.getMatToString())  && !closedList.containsKey(down.getMatToString())) {
					down.setF(h.Heuricstic(down.getMat()) + down.getCost());
					numOfVert++;
					openList.put(down.getMatToString(), down);
					states.add(down);
				}else if(openList.containsKey(down.getMatToString()) &&
						down.getF() < (h.Heuricstic(openList.get(down.getKey()).getMat()) + openList.get(down.getKey()).getCost()) ) { // if in open list and higher path cost replace him
					openList.replace(down.getMatToString(), down);
					states.add(down);
				}
			}

		}//end while
		if (states.size() == 0) {WritingNoPath(numOfVert);};

	}


	/**
	 * 
	 * @param stack - the Vertex stack from IDA* 
	 * @param v - the vertex that needs to be removed
	 */
	public Stack<Vertex> removeFromStack(Stack<Vertex> stack , Vertex v) {
		Stack<Vertex> newStack = new Stack<Vertex>();
		while(!stack.isEmpty()) {
			if(ChecksMat(stack.peek().getMat(), v.getMat()))
				break;
			else
				newStack.push(stack.pop());
		}
		stack.pop();
		while(!newStack.isEmpty()) {
			stack.push(newStack.pop());
		}
		
		return stack;
	}

	/**
	 * IDA* - Each iteration is a Depth-First-Search, keeps track of f(v) = g(v) + h(v),
	 * The cost threshold is initialized to the heuristic of the initial state. 
	 */
	public void IDAstar(String [][] StartMat,String [][]finalMat,boolean open,boolean Time) {
		
		long startTime = System.currentTimeMillis();//start time of algorithm
		double endtime;// Initialize the end time of the algorithm
		boolean Continue = false;//Continue for Continue with next operator
		boolean End = false;
		
		Stack<Vertex> vertexStack = new Stack<Vertex>(); // stack of the vertices
		Hashtable<String, Vertex> vertexHash = new Hashtable<String, Vertex>();
		H h =new H(finalMat); // compute the final heuristic to compare
		int Temp = h.Heuricstic(StartMat); // The starting heuristic value
		Operators o = new Operators();
		Vertex v = new Vertex(StartMat);numOfVert++;
		v.setF(h.Heuricstic(v.getMat()) + v.getCost());//compute the F value for the start
		while (Temp != Integer.MAX_VALUE) {
			int minF = Integer.MAX_VALUE;
			vertexStack.add(v); // add to stack
			vertexHash.put(v.getMatToString(),v); // add to hash table

			while(!vertexStack.isEmpty()) {
				if(open) {//print the open list to the screen
					printOpenList(vertexHash, itertation, StartMat.length, StartMat[0].length);
					itertation++;
				}//end of printing
				
				Vertex checkVertex =new Vertex(vertexStack.pop()); // pop the first from the stack
				// check if vertex marked as stack
				
						//if marked as "out"
				if( checkVertex.getOut() ){
					vertexHash.remove(checkVertex.getMatToString());
				}else { // if not marked as "out"
					checkVertex.setOut(true);
					vertexStack.push(checkVertex);

								//left operator//
					////////////////////////////////////////////////////////////
					if(o.checkifLeft(checkVertex.getMat()) && !checkVertex.getMove().equals("R") ) { // **needs to add loop avoidance for father of checksVertex
						Vertex left = o.operatorLeft(checkVertex);numOfVert++;
						left.setF(h.Heuricstic(left.getMat()) + left.getCost());
						if(left.getF() > Temp) {
							minF = Math.min(minF,left.getF());
							Continue = true;
						}
						if(vertexHash.contains(left.getMatToString()) && vertexHash.get(left.getMatToString()).getOut() && !Continue) {
							//Continue with next operator
							Continue = true;
						}
						if (vertexHash.contains(left.getMatToString()) && !vertexHash.get(left.getMatToString()).getOut() && !Continue) {
							if(vertexHash.get(left.getMatToString()).getF() > left.getF()) {
								vertexHash.remove(left.getMatToString());
								// remove from stack ** need to add//
								vertexStack = removeFromStack(vertexStack,left);
							}else {
								//Continue with next operator
								Continue = true;
							}
						} 
						if(ChecksMat(left.getMat(), finalMat) && !Continue) {
							// return path by taking all "out" vertex in stack
							endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
							writeToFile(left,numOfVert, Time,endtime) ; // write to file function
							End = true;
							break;

						}
						if (Continue == false) {
							vertexStack.add(left);
							vertexHash.put(left.getMatToString(), left);
						}
					}
					Continue = false;// restart the Continue for next operator
								
									//Up operator//
					////////////////////////////////////////////////////////////
					if(o.checkifUp(checkVertex.getMat()) && !checkVertex.getMove().equals("D")) {
						Vertex up = o.operatorUp(checkVertex);numOfVert++;
						up.setF(h.Heuricstic(up.getMat()) + up.getCost());
						if(up.getF() > Temp) {
							minF = Math.min(minF,up.getF());
							Continue = true;
						}
						else if(vertexHash.contains(up.getMatToString()) && vertexHash.get(up.getMatToString()).getOut() && !Continue) {
							//Continue with next operator
							Continue = true;
						}
						else if (vertexHash.contains(up.getMatToString()) && !vertexHash.get(up.getMatToString()).getOut() && !Continue) {
							if(vertexHash.get(up.getMatToString()).getF() > up.getF()) {
								vertexHash.remove(up.getMatToString());
								// remove from stack ** need to add//
								vertexStack = removeFromStack(vertexStack,up);
							}else {
								//Continue with next operator
								Continue = true;
							}
						}else if(ChecksMat(up.getMat(), finalMat) && !Continue) {
							// return path by taking all "out" vertex in stack
							endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
							writeToFile(up,numOfVert, Time,endtime) ; // write to file function
							End = true;
							break;

						}
						if (Continue == false) {
							vertexStack.add(up);
							vertexHash.put(up.getMatToString(), up);
						}
					}
					Continue = false;// restart the Continue for next operator

										//Right operator//
					////////////////////////////////////////////////////////////
					if(o.checkifRight(checkVertex.getMat()) && !checkVertex.getMove().equals("L")) {
						Vertex right = o.operatorRight(checkVertex);numOfVert++;
						right.setF(h.Heuricstic(right.getMat()) + right.getCost());
						if(right.getF() > Temp) {
							minF = Math.min(minF,right.getF());
							Continue = true;
						}
						else if(vertexHash.contains(right.getMatToString()) && vertexHash.get(right.getMatToString()).getOut() && !Continue ) {
							//Continue with next operator
							Continue = true;
						}
						else if (vertexHash.contains(right.getMatToString()) && !vertexHash.get(right.getMatToString()).getOut() && !Continue) {
							if(vertexHash.get(right.getMatToString()).getF() > right.getF()) {
								vertexHash.remove(right.getMatToString());
								// remove from stack ** need to add//
								vertexStack = removeFromStack(vertexStack,right);
							}else {
								//Continue with next operator
								Continue = true;
							}
						}else if(ChecksMat(right.getMat(), finalMat) && !Continue) {
							// return path by taking all "out" vertex in stack
							endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
							writeToFile(right,numOfVert, Time,endtime) ; // write to file function
							End = true;
							break;

						}
						if (Continue == false) {
							vertexStack.add(right);
							vertexHash.put(right.getMatToString(), right);
						}
					}
					Continue = false;// restart the Continue for next operator

									//Down operator//
					////////////////////////////////////////////////////////////
					if(o.checkifDown(checkVertex.getMat()) && !checkVertex.getMove().equals("U")) {
						Vertex down = o.operatorDown(checkVertex);numOfVert++;
						down.setF(h.Heuricstic(down.getMat()) + down.getCost());
						if(down.getF() > Temp) {
							minF = Math.min(minF,down.getF());
							Continue = true;
						}
						else if(vertexHash.contains(down.getMatToString()) && vertexHash.get(down.getMatToString()).getOut() && !Continue ) {
							//Continue with next operator
							Continue = true;
						}
						else if (vertexHash.contains(down.getMatToString()) && !vertexHash.get(down.getMatToString()).getOut() && !Continue) {
							if( vertexHash.get(down.getMatToString()).getF() > down.getF()) {
								vertexHash.remove(down.getMatToString());
								// remove from stack ** need to add//
								vertexStack = removeFromStack(vertexStack,down);
							}else {
								//Continue with next operator
								Continue = true;
							}
						}else if(ChecksMat(down.getMat(), finalMat) && !Continue) {
							// return path by taking all "out" vertex in stack
							endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
							writeToFile(down,numOfVert, Time,endtime) ; // write to file function
							End = true;
							break;

						}
						if (Continue == false) {
							vertexStack.add(down);
							vertexHash.put(down.getMatToString(), down);
						}
					}
					Continue = false;// restart the Continue for next operator


				}

			}
			if(!End)
				Temp = minF;
			else 
				break;
		}
		// if End = false there is no path
		if(!End)
			WritingNoPath(numOfVert);

	}
	
	
	/**
	 * Function for the factorial O bound of the DFBnB algorithm
	 */
	 public static int factorial(int number) {
	        int result = 1;

	        for (int factor = 2; factor <= number; factor++) {
	            result *= factor;
	        }

	        return result;
	    }

	/**
	 * DFBnB - Depth First Branch and Bound , DFBnB assumes a cost function that can be applied
	 * to a partial solution, and is a lower bound on the
	 * cost of all completions of that partial solution 
	 */
	public void DFBnB(String [][] StartMat,String [][]finalMat,boolean open,boolean Time) {
		
		long startTime = System.currentTimeMillis();//start time of algorithm
		
		double endtime;// Initialize the end time of the algorithm
		
		Vertex v = new Vertex(StartMat); numOfVert++;//create the first vertex
		
		Stack<Vertex> stackVertex = new Stack<Vertex>();
		
		stackVertex.push(v); // push vertex to the stack
		
		Hashtable<String, Vertex> hashVertex = new Hashtable<String, Vertex>(); // open list
		
		hashVertex.put(v.getMatToString(),v);
		Operators o = new Operators();
		Vertex result = null;
		// compute the upper bound of the algorithm
		int Temp = StartMat.length * StartMat[0].length > 12 ? Integer.MAX_VALUE : factorial(StartMat.length * StartMat[0].length) ;
		H h = new H(finalMat); // Heuristic function for the algorithm
		v.setF(h.Heuricstic(v.getMat()));
		while(!stackVertex.isEmpty()) {
		
			if(open) {//print the open list to the screen
				printOpenList(hashVertex, itertation, StartMat.length, StartMat[0].length);
				itertation++;
			}//end of printing
			
			Vertex checkVertex = stackVertex.pop(); // pop to the first vertex in the stack
			
			if(checkVertex.getOut()){
				
				hashVertex.remove(checkVertex.getMatToString()); // if the vertex marked as out remove him from the stack
				
			}else {
				
				checkVertex.setOut(true); // if not marked as out, mark it and push it back to the stack
				stackVertex.push(checkVertex);
				
				ArrayList<Vertex> listVertex = new ArrayList<Vertex>(); //create list of the created vertexes
				
				// create the alowed operators for the vertex - branching factor of 3 (check last move) 
				if(o.checkifLeft(checkVertex.getMat()) && !checkVertex.getMove().equals("R") ) {
					Vertex left = new Vertex(o.operatorLeft(checkVertex));numOfVert++;
					left.setF(h.Heuricstic(left.getMat()) + left.getCost());
					listVertex.add(left);
				}
				if(o.checkifUp(checkVertex.getMat()) && !checkVertex.getMove().equals("D") ) {
					Vertex up = new Vertex(o.operatorUp(checkVertex));numOfVert++;
					up.setF(h.Heuricstic(up.getMat()) + up.getCost());
					listVertex.add(up);
				}
				if(o.checkifRight(checkVertex.getMat()) && !checkVertex.getMove().equals("L") ) {
					Vertex right = new Vertex(o.operatorRight(checkVertex));numOfVert++;
					right.setF(h.Heuricstic(right.getMat()) + right.getCost());
					listVertex.add(right);
				}
				if(o.checkifDown(checkVertex.getMat()) && !checkVertex.getMove().equals("U") ) {
					Vertex down = new Vertex(o.operatorDown(checkVertex));numOfVert++;
					down.setF(h.Heuricstic(down.getMat()) + down.getCost());
					listVertex.add(down);
				}
					
				Collections.sort(listVertex); // sort the vertexes in the stack by theire F value in descending order
				// for every vertex in the lost check if he is in the open list, if he is marked as out and then check if he is the goal
				for (int i = 0; i < listVertex.size(); i++) {
					if(listVertex.get(i).getF() > Temp) {
						listVertex.clear();
						break;
					}
					else if (hashVertex.contains(listVertex.get(i).getMatToString()) && hashVertex.get(listVertex.get(i).getMatToString()).getOut()) {
						listVertex.remove(i);
					}
					else if(hashVertex.contains(listVertex.get(i).getMatToString()) && !hashVertex.get(listVertex.get(i).getMatToString()).getOut()) {
						if(hashVertex.get(listVertex.get(i).getMatToString()).getF() <= listVertex.get(i).getF()) {
							listVertex.remove(i);
						}else {
							hashVertex.remove(listVertex.get(i).getMatToString());
							stackVertex = removeFromStack(stackVertex, listVertex.get(i));
						}
					}
					else if(ChecksMat(listVertex.get(i).getMat(), finalMat)) {
						Temp = listVertex.get(i).getF();
						result = listVertex.get(i);
						for (int j = i; j < listVertex.size(); j++) {
							listVertex.remove(j);
						}
						
					}
					
				}
				// insert in reverse order to the stack and the hash table
				for (int i = listVertex.size() -1 ; i >=0 ; i--) {
					stackVertex.push(listVertex.get(i));
					hashVertex.put(listVertex.get(i).getMatToString(), listVertex.get(i));
					listVertex.remove(i);
				}
				
				
			}
		}//return result
		if (result != null) {
			endtime = (double)(System.currentTimeMillis() - startTime)/1_000.0;
			writeToFile(result,numOfVert, Time,endtime) ; // write to file function
			
		}else{
			WritingNoPath(numOfVert);
		}
	}
	
}//end of class

