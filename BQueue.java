// the operations(pop,add,set empty) of each vertex
import java.util.ArrayList;

public class BQueue {

	public ArrayList<Vertex> Q = new ArrayList<Vertex>();
	
	
	public Vertex pop(){
		Vertex temp = Q.get(0);
		Q.remove(0);
		return temp;
		
	}
	public void add(Vertex vertex){
		Q.add(vertex);
	}
	
	public boolean isEmpty(){
		return this.Q.size() == 0;
	}
	
}
