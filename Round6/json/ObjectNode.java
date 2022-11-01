import java.util.*;
import java.lang.*;

public class ObjectNode extends Node implements Iterable<String>{

    private HashMap<String,Node> mapp;


    ObjectNode(){
        this.mapp = new HashMap<>();
    }


    public Node get(String key){
        return mapp.get(key);
    }
    public void set(String key, Node node){

        mapp.put(key,node);
    }
    public int size(){
        return mapp.size();
    }

    @Override
    public Iterator<String> iterator() {

        Set<String> keyset = mapp.keySet();

        List<String> palautus = new ArrayList<>(keyset);
        Collections.sort(palautus);



        return palautus.iterator();
    }

}