import java.util.*;
import java.util.function.Consumer;
import java.io.*;
import java.util.stream.Stream;

public class MovieAnalytics 
{
    List<Movie> lista;
    List<List<String>> records = new ArrayList<>();

    public MovieAnalytics()
    {
        this.lista = new ArrayList<>();
    }

    public static Consumer<Movie> showInfo()
    {
        Consumer<Movie> i = t -> System.out.format("%s (By %s, %d)%n", t.getTitle(), t.getDirector(), t.getReleaseYear());

        return i;
    }
    public void populateWithData(String fileName){
        try 
        {

            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                String match = myReader.nextLine();

                List<String> myList = new ArrayList<>(Arrays.asList(match.split(";")));

                Movie uusi = new Movie(myList.get(0),Integer.parseInt(myList.get(1)),Integer.parseInt((myList.get(2))),myList.get(3), Double.parseDouble(myList.get(4)),myList.get(5));

                lista.add(uusi);


            }
            myReader.close();
        } 
        catch (Exception e) 
        {
            System.out.println("ei0");
        }
    }

    Stream<Movie> moviesAfter(int year)
    {
        return lista.stream().filter(m -> m.getReleaseYear() >= year).sorted(Comparator.comparingInt(Movie::getReleaseYear).thenComparing(Comparator.comparing(Movie::getTitle)));
    }

    Stream<Movie> moviesBefore(int year)
    {
        return lista.stream().filter(m -> m.getReleaseYear() <= year).sorted(Comparator.comparingInt(Movie::getReleaseYear).thenComparing(Comparator.comparing(Movie::getTitle)));
    }

    Stream<Movie> moviesBetween(int yearA, int yearB)
    {
        return lista.stream().filter(m -> m.getReleaseYear() >= yearA && m.getReleaseYear() <= yearB).sorted(Comparator.comparingInt(Movie::getReleaseYear).thenComparing(Comparator.comparing(Movie::getTitle)));
    }

    Stream<Movie> moviesByDirector(String director)
    {
        return lista.stream().filter(m -> m.getDirector().equals(director)).sorted(Comparator.comparingInt(Movie::getReleaseYear).thenComparing(Comparator.comparing(Movie::getTitle)));
    }
}
