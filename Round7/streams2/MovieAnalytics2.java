import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.stream.*;
import java.util.stream.Stream;




public class MovieAnalytics2 {

    private List<Movie> elokuvat;
    
    MovieAnalytics2(){
        this.elokuvat = new ArrayList<>();
    }
    
    void populateWithData(String fileName){
        
        try(Stream<String> sanat = Files.lines(Paths.get(fileName))) 
        {
            sanat.forEach(p -> elokuvat.add(new Movie(p.split(";")[0], Integer.parseInt(p.split(";")[1]), Integer.parseInt(p.split(";")[2]), p.split(";")[3], Double.parseDouble(p.split(";")[4]), p.split(";")[5])));       
        } 
        catch (Exception e) 
        {

        }
    }
    
    public void printCountByDirector(int n){
        SortedMap<String,Integer> map = new TreeMap<>();
        Stream<Movie> ohjaajaN = elokuvat.stream();
        ohjaajaN.forEach((a)-> {
            Integer count = map.get(a.getDirector());
            if(count == null){
                map.put(a.getDirector(),1);
            }else{
                map.put(a.getDirector(),count + 1);
            }
        });
        
        map.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .limit(n).forEach(k -> System.out.println(k.getKey() + ": " + k.getValue() + " movies"));

        
    }
    public void printAverageDurationByGenre(){
        SortedMap<String, ArrayList<Integer>> gen = new TreeMap<>();
        SortedMap<String,Double> map = new TreeMap<>();
        
        elokuvat.stream().forEach(p -> { 
        if(!gen.containsKey(p.getGenre()))
        {
            ArrayList<Integer> taa = new ArrayList<>();
            taa.add(p.getDuration());
            gen.put(p.getGenre(), taa);
        }
        else
        {                         
            gen.get(p.getGenre()).add(p.getDuration());
        }
        
        });
        
        gen.entrySet().stream().forEach(k -> {
            map.put(k.getKey(),k.getValue().stream().mapToDouble(a->a).average().getAsDouble());
        });
        
        map.entrySet().stream()
                .sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue()))
                .forEach(k -> System.out.format("%s: %.2f%n", k.getKey(), k.getValue()));
    }
    public void printAverageScoreByGenre(){
        SortedMap<String, ArrayList<Double>> gen = new TreeMap<>();
        SortedMap<String,Double> map = new TreeMap<>();

        elokuvat.stream().forEach(p -> { 
        if(!gen.containsKey(p.getGenre()))
        {
            ArrayList<Double> taa = new ArrayList<>();
            taa.add(p.getScore());
            gen.put(p.getGenre(), taa);
        }
        else
        {                         
            gen.get(p.getGenre()).add(p.getScore());
        }
        
        });
        gen.entrySet().stream().forEach(k -> {
            map.put(k.getKey(),k.getValue().stream().mapToDouble(a->a).average().getAsDouble());
        });
        
       
        
        map.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .forEach(k -> System.out.format("%s: %.2f%n", k.getKey(), k.getValue()));
    }
    
    
    
}