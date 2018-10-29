// This class is to build the vertices of MST
import java.util.ArrayList;


public class Vertex {
	// an inner class for each node
	public class Node{		
		public Vertex v;
		public int weight = 0;		
		public Node(Vertex v, int weight){
			this.v = v;
			this.weight = weight;
		}       
	} 
	
	public String vertexName;
	public ArrayList<Node> neighbourList = new ArrayList<Node>();
	public boolean visited = false;
	public boolean addedToQueue = false;	
	public Vertex(String vertexName){	
		this.vertexName = vertexName;
	}

	public void addNeighbour(Vertex vertex, int newWeight){		
		neighbourList.add(new Node(vertex, newWeight));
		//System.out.println("From " + this.vertexName + " to node "+vertex.vertexName + " the weight is "+newWeight);
	}
	
}
