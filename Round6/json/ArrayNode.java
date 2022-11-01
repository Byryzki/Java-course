import java.io.*;
import java.lang.*;
import java.util.*;
        
public class ArrayNode extends Node implements Iterable<Node>{
    
    private ArrayList<Node> nodes;
    
    ArrayNode(){
        this.nodes = new ArrayList<Node>();
    }
    public void add(Node node){
        nodes.add(node);
    }
    public int size(){
        return nodes.size();
    }
    
    @Override
    public Iterator<Node> iterator() {
      return nodes.iterator();
    }

}