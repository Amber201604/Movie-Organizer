//name:Qianyu Zhang


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.net.*;


public class main {

	public static void main(String[] args) throws Exception{

		//read the .txt website into 'data' array
		ArrayList<String[]> data  = getData();
		
		//Organize the String array produced above, into their movies
		ArrayList<ArrayList<String[]>> theMovies = getMovies(data);
		
		//graph structure
		ArrayList<Graph> graphs = getGraphs(theMovies);
		
		
		/*  test data
		Vertex one = new Vertex("1");
		Vertex two = new Vertex("2");
		Vertex three = new Vertex("3");
		Vertex four = new Vertex("4");
		Vertex five = new Vertex("5");
		Vertex six = new Vertex("6");

		one.addNeighbour(two, 15);
		one.addNeighbour(four, 7);
		one.addNeighbour(five, 10);


		two.addNeighbour(one, 15);
		two.addNeighbour(three, 9);
		two.addNeighbour(four, 11);
		two.addNeighbour(six, 9);


		three.addNeighbour(two, 9);
		three.addNeighbour(five, 12);
		three.addNeighbour(six, 7);


		four.addNeighbour(one, 7);
		four.addNeighbour(two, 11);
		four.addNeighbour(five, 8);
		four.addNeighbour(six, 14);


		five.addNeighbour(one, 10);
		five.addNeighbour(three, 12);
		five.addNeighbour(four, 8);
		five.addNeighbour(six, 8);


		six.addNeighbour(two, 9);
		six.addNeighbour(three, 7);
		six.addNeighbour(four, 14);
		six.addNeighbour(five, 8);

*/

		
		int[] BFSWeights = new int[graphs.size()];
		int[] primsWeights = new int[graphs.size()];

		Random random = new Random();
		for (int i = 0; i < graphs.size(); i++){
			
			int randomNum = random.nextInt((graphs.get(i).R.size()-1) + 1);
			
			BFSWeights[i] = BFS(graphs.get(i).R.get(randomNum));
			System.out.println("In BFS Algorithm, the weight of graph " + (i+1) + ": " + BFSWeights[i] );
			
			primsWeights[i] = Prim(graphs.get(i), randomNum);
			System.out.println("In Prim's Algorithm, the weight of graph " + (i+1) + ": " + primsWeights[i] );
			
		}
		
		int[] graphSizes = {100, 200, 400, 600, 800, 1000, 1200, 1400, 1600};
		

		//9 size for the 9 sizes (100, 200, 400, 600, 800, 1000, 1200, 1400, 1600)
		double[] computeDiff = new double[9];
		
			for (int i = 0; i < graphs.size(); i ++){
				if (i <= 24){
					computeDiff[0] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 49){
					computeDiff[1] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 74){
					computeDiff[2] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 99){
					computeDiff[3] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 124){
					computeDiff[4] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 149){
					computeDiff[5] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 174){
					computeDiff[6] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 199){
					computeDiff[7] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
				else if (i <= 224){
					computeDiff[8] += (((double) BFSWeights[i] / primsWeights[i] - 1)) * 100;
				}
			}

			for (int i = 0; i < computeDiff.length; i++){
				System.out.println("Graph size " + graphSizes[i] + " , differnece percentage: " + Math.round(computeDiff[i] / 25.0) + "%");
			}
	}

	public static int BFS(Vertex vertex){
		int total = 0;
		BQueue Q = new BQueue();
		Q.add(vertex);
		vertex.addedToQueue = true;
		while (!Q.isEmpty()){
			Vertex x = Q.pop();
			x.visited = true;
			for (int i = 0; i < x.neighbourList.size(); i++){
				Vertex y = x.neighbourList.get(i).v;
				int yWeight = x.neighbourList.get(i).weight;
				if (!y.visited && !y.addedToQueue){
					Q.add(y);
					y.addedToQueue = true;
					total += yWeight;
				}
			}
		}
		return total;
	}

	
	public static int Prim(Graph graph, int randomNum){
		int numVertices = graph.R.size();
		
		int anyVertexIndex = randomNum;
		Vertex v = graph.R.get(anyVertexIndex);
		graph.T.add(v);
		graph.R.remove(anyVertexIndex);
		
		
		int totalWeight = 0;
		while (graph.T.size() < numVertices){
			int min = 0;
			Vertex minVertex = null;
			for (int i = 0; i < graph.T.size(); i++){
				for (int j = 0; j < graph.T.get(i).neighbourList.size(); j++){
					if ( inR(graph.T.get(i).neighbourList.get(j).v.vertexName,graph) && (graph.T.get(i).neighbourList.get(j).weight < min || min == 0 )){
						min = graph.T.get(i).neighbourList.get(j).weight;
						minVertex = graph.T.get(i).neighbourList.get(j).v;
					}
				}
			}
			totalWeight += min;
			graph.T.add(minVertex);
			removeFromR(minVertex.vertexName, graph);			
		}
		return totalWeight;
	}
	
	public static void removeFromR(String vertexName, Graph g){
		for (int i = 0; i < g.R.size(); i++){
			if (g.R.get(i).vertexName.equals(vertexName)){
				g.R.remove(i);
			}
		}
	}
	public static boolean inR (String vertexName, Graph g){
		for (int i = 0; i< g.R.size();i++){
			if (g.R.get(i).vertexName.equals(vertexName)){
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String[]> getData() throws Exception{
		URL movies = new URL("http://sites.cs.queensu.ca/courses/cisc235/Assignments/Assignment%204/Test_Cases.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(movies.openStream()));
		String inputLine;
		ArrayList<String[]> data = new ArrayList<String[]>();
		while((inputLine = in.readLine())!=null){
			data.add(inputLine.trim().split("	|	"));
		}
		return data;
	}

	
	public static ArrayList<Graph> getGraphs(ArrayList<ArrayList<String[]>> theMovies){
		ArrayList<Graph> graphsList = new ArrayList<Graph>();
		
		//225 graphs with 8 different sizes
		for (int i = 0; i<theMovies.size();i++) {
			
			Graph g = new Graph();
			Map<String, Integer> dictionary = new HashMap<String, Integer>();
			
			for (int j = 0; j<theMovies.get(i).size();j++) {
				
				Vertex tempVertex = new Vertex(theMovies.get(i).get(j)[0]);
				g.R.add(tempVertex);
				dictionary.put(tempVertex.vertexName, g.R.size()-1);
				
			}


			for (int j = 0; j< g.R.size();j++) {
				
				int numVertex = Integer.parseInt(theMovies.get(i).get(j)[1]);
				for (int k = 2; k <= numVertex*2; k+=2){					
					String currentVertexName = theMovies.get(i).get(j)[k];
					int index = dictionary.get(currentVertexName);
					int weight = Integer.parseInt(theMovies.get(i).get(j)[k + 1]);					
					//System.out.println("Current vertex = " + g.R.get(j).vertexName + " Attaching " +g.R.get(index).vertexName + " with weight " + weight); 					
					g.R.get(j).addNeighbour(g.R.get(index), weight);
				}
			}
			graphsList.add(g);
		}

		return graphsList;
	}

	 
	public static ArrayList<ArrayList<String[]>> getMovies(ArrayList<String[]> data){
		ArrayList<ArrayList<String[]>> theMovies = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> tempList = new ArrayList<String[]>();
		for (int i = 0; i < data.size(); i++){
			if (data.get(i).length == 2){
				theMovies.add(tempList);
				tempList = new ArrayList<String[]>();
			}
			else{
				tempList.add(data.get(i));
			}
		}
		theMovies.add(tempList);
		theMovies.remove(0);
		return theMovies;
	}

}
