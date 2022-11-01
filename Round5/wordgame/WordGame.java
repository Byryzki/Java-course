import java.util.*;
import java.io.*;
import java.lang.*;

public class WordGame 
{
    private ArrayList<WordGameState> peli = new ArrayList<>();
    private ArrayList<String> listOfLines = new ArrayList<>();
    private String theword;

    public WordGame(String wordFilename)
    {
        try
        {
            Scanner s = new Scanner(new File(wordFilename));
            while (s.hasNext()){
                listOfLines.add(s.next());
            }
            s.close();
        }
        catch(Exception e){System.out.println(e);}

        
    }

    public void initGame(int wordIndex, int mistakeLimit)
    {
        int n = listOfLines.size();
        theword = listOfLines.get(wordIndex % n);
        WordGameState newgame = new WordGameState("_".repeat(theword.length()), 0, mistakeLimit, theword.length());

        if(peli.size() != 0){peli.clear();}
        peli.add(newgame);
    }

    public boolean isGameActive()
    {
        if(peli.size() == 0) {return false;}
        
        else if(peli.get(0).missingchars > 0 && peli.get(0).mistakes <= peli.get(0).mistakelimit){return true;}

        return false;
    }

    public WordGameState getGameState() throws GameStateException
    {
        if(!isGameActive()){throw new GameStateException("There is currently no active word game!");}
        
        return peli.get(0);
    }

    public WordGameState guess(char c) throws GameStateException
    {
        if(!isGameActive()){throw new GameStateException("There is currently no active word game!");}

        char[] viivach = peli.get(0).word.toCharArray();
        boolean cng=false;

        for(int i=0; i < theword.length(); i++)
        {   
            if(Character.toLowerCase(c) == theword.charAt(i)) // is char correct?
            {
 
                if(Character.toLowerCase(c) == peli.get(0).word.charAt(i)) // arvattu jo
                {
                    break;
                }
                else // right quess!!!
                {
                    viivach[i] = Character.toLowerCase(c);
                    peli.get(0).word = String.valueOf(viivach);
                    peli.get(0).missingchars -= 1;
                    cng = true;
                }
            }
            
        }

        if(!cng)
        {
            peli.get(0).mistakes += 1;
            if(peli.get(0).mistakelimit < peli.get(0).mistakes){peli.get(0).word = theword;}
        }

        return peli.get(0);
    }

    public WordGameState guess(String word) throws GameStateException
    {
        if(!isGameActive()){throw new GameStateException("There is currently no active word game!");}

        String str = theword;
        String rts = word.toLowerCase();

        if(str.equals(rts))
        {
            peli.get(0).missingchars = 0;
            peli.get(0).word = theword;
        }
        else 
        {
            peli.get(0).mistakes += 1;
            if(peli.get(0).mistakelimit < peli.get(0).mistakes){peli.get(0).word = theword;}
        }
    
        return peli.get(0);
    }
    
    static class WordGameState
    {
        private String word;
        private int mistakes;
        private int mistakelimit;
        private int missingchars;

        private WordGameState(String word, int mistakes, int mistakelimit, int missingchars)
        {
            this.word = word;
            this.mistakes = mistakes;
            this.mistakelimit = mistakelimit;
            this.missingchars = missingchars;
        }

        public String getWord(){return word;}
        public int getMistakes(){return mistakes;}
        public int getMistakeLimit(){return mistakelimit;}
        public int getMissingChars(){return missingchars;}

    }

    public static void main(String args[]) throws IOException {
    String wordFilename = args[0];
    String cmdFilename = args[1];

    WordGame game = new WordGame(wordFilename);
    try(var cmdFile = new BufferedReader(new FileReader(cmdFilename))) {
      String cmd;
      while((cmd = cmdFile.readLine()) != null) {
        String[] parts = cmd.split("\\s+");
        try {
          WordGame.WordGameState state = null;
          switch(parts[0]) {
            case "init":
              System.out.format("Initializing a new game with %d and %d.%n",
                      Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
              game.initGame(Integer.parseInt(parts[1]), Integer.parseInt(
                      parts[2]));
              break;
            case "check":
              System.out.println("Checking game state.");
              System.out.format("  A game %s currently active.%n",
                      game.isGameActive() ? "is" : "is NOT");
              break;
            case "char":
              System.out.format("Guessing '%c'.%n", parts[1].charAt(0));
              state = game.guess(parts[1].charAt(0));
              break;
            case "word":
              System.out.format("Guessing \"%s\".%n", parts[1]);
              state = game.guess(parts[1]);
              break;
            case "state":
              System.out.println("Retrieving game status.");
              state = game.getGameState();
          }
          if(state != null) {
            System.out.format(
                    "  Word: %s (%d/%d wrong guesses, %d missing chars left)%n",
                    state.getWord(), state.getMistakes(), state
                    .getMistakeLimit(), state.getMissingChars());
            if(state.getMissingChars() == 0) {
              System.out.println("  The player won!");
            }
            else if(state.getMistakes() > state.getMistakeLimit()) {
              System.out.println("  The player lost!");
            }
          }
        }
        catch(GameStateException e) {
          System.out.println(e);
        }
      }
    }
  }
}

