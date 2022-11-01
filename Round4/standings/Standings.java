import java.io.*;
import java.util.*;
public class Standings {
    
    private HashMap<String, Team> teams;
    private SortedMap<Double,Team> sort;
    private ArrayList<Team> standingslist;
    
    public static class Team{
        private int Wins;
        private int Ties;
        private int Losses;
        private int Scored;
        private int Allowed;
        private int Points;
        private String name;
        
        Team(String name){
            this.name = name;
        }
        
        int getWins(){
            return this.Wins;
        }
        int getTies(){
            return this.Ties;
        }
        int getLosses(){
            return this.Losses;
        }
        int getScored(){
            return this.Scored;
        }
        int getAllowed(){
            return this.Allowed;
        }
        int getPoints(){
            return this.Points;
        }
        int getEro(){
            return this.Scored-this.Allowed;
        }
        String getName(){
            return this.name;
        }
        
    }
    
    Standings(){
        this.teams = new HashMap<String, Team>();
        this.sort = new TreeMap<Double,Team>();
        this.standingslist = new ArrayList<Team>();
    }
    Standings(String filename){
        this.teams = new HashMap<String, Team>();
        this.sort = new TreeMap<Double,Team>();
        this.standingslist = new ArrayList<Team>();
        this.readMatchData(filename);
    }
    public void readMatchData(String filename){
        try 
        {   
            
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                String match = myReader.nextLine();
                
                List<String> myList = new ArrayList<String>(Arrays.asList(match.split("\\t")));
                List<String> myList2 = new ArrayList<String>(Arrays.asList(myList.get(1).split("-")));
                
                
                
                this.addMatchResult(myList.get(0),Integer.parseInt(myList2.get(0)),Integer.parseInt(myList2.get(1)),myList.get(2));
                
            }
            myReader.close();
        } 
        catch (Exception e) 
        {
            
        }
    }
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB){
        if(!teams.containsKey(teamNameA))  //conf teamA
        {
            Team newteamA = new Team(teamNameA);
            standingslist.add(newteamA);
            teams.put(teamNameA, newteamA);
        }
        if(!teams.containsKey(teamNameB)){
            Team newteamB = new Team(teamNameB);
            standingslist.add(newteamB);
            teams.put(teamNameB, newteamB);
        }
        
        if(goalsA > goalsB){
            
            teams.get(teamNameA).Scored += goalsA;
            teams.get(teamNameA).Allowed += goalsB;
            teams.get(teamNameB).Scored += goalsB;
            teams.get(teamNameB).Allowed += goalsA;
            
            teams.get(teamNameA).Points +=3;
            teams.get(teamNameA).Wins++;
            teams.get(teamNameB).Losses++;
            
        }else if(goalsA < goalsB){

            
            teams.get(teamNameA).Scored += goalsA;
            teams.get(teamNameA).Allowed += goalsB;
            teams.get(teamNameB).Scored += goalsB;
            teams.get(teamNameB).Allowed += goalsA;
            
            teams.get(teamNameB).Points +=3;
            teams.get(teamNameB).Wins++;
            teams.get(teamNameA).Losses++;
        }else{

            
            teams.get(teamNameA).Scored += goalsA;
            teams.get(teamNameA).Allowed += goalsB;
            teams.get(teamNameB).Scored += goalsB;
            teams.get(teamNameB).Allowed += goalsA;
            
            teams.get(teamNameA).Points +=1;
            teams.get(teamNameB).Points +=1;
            
            teams.get(teamNameA).Ties++;
            teams.get(teamNameB).Ties++;
        }
            

    }
    public ArrayList<Team> getTeams(){
        
        Collections.sort(standingslist, Comparator.comparing(Team::getPoints)
                .thenComparing(Team::getEro)
                .thenComparing(Team::getScored)
                .thenComparing(Team::getName)
                
        );
        Collections.reverse(standingslist);
        
        return standingslist;
    }
    public void printStandings(){
        
        int longest = 0;
        for (int i = 0; i < standingslist.size(); i++) {
            if(standingslist.get(i).name.length() > longest){
                longest = standingslist.get(i).name.length();
            }
        }

        for(Team team : this.getTeams())
        {
            String name = team.name;
            String space = " ";
            int games = team.Losses+team.Wins+team.Ties;
            int wins = team.Wins;
            int losses = team.Losses;
            int ties = team.Ties;
            int scored = team.Scored;
            int allowed = team.Allowed;
            int points = team.Points;
            
            System.out.print(name);
            System.out.print(space.repeat(longest-name.length()));
            System.out.print(space.repeat(4-Integer.toString(games).length()));
            System.out.format("%d",games);
            System.out.print(space.repeat(4-Integer.toString(wins).length()));
            System.out.format("%d",wins);
            System.out.print(space.repeat(4-Integer.toString(ties).length()));
            System.out.format("%d",ties);
            System.out.print(space.repeat(4-Integer.toString(losses).length()));
            System.out.format("%d",losses);
            
            System.out.print(space.repeat(6-Integer.toString(scored).length()-Integer.toString(allowed).length()));
            System.out.format("%d-%d",scored,allowed);
           
            System.out.print(space.repeat(4-Integer.toString(points).length()));
            System.out.format("%d%n",points);

        }
    }

    
    
}