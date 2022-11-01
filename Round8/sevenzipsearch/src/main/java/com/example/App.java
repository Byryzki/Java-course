package com.example;

import org.apache.commons.compress.archivers.sevenz.*;
import org.tukaani.xz.*;
import java.lang.Exception;
import java.util.*;
import java.io.*;

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException 
    {
        try (SevenZFile sevenZFile = new SevenZFile(new File(args[0]))) 
        {
            
            SevenZArchiveEntry entry;
		    while ((entry = sevenZFile.getNextEntry()) != null) 
            {
 
                File file = new File(entry.getName());
                boolean istxt = entry.getName().contains(".txt");

                if(istxt)
                {
                    System.out.println(file);
                    int cnt = 1;
                    try {
                        Scanner myReader = new Scanner(file);
                        while (myReader.hasNextLine()) 
                        {
                          String rivi = myReader.nextLine();
                            if(rivi.contains(args[1]))
                            {
                                rivi.substring(rivi.indexOf(args[1]), rivi.indexOf(args[1])+args[1].length()).toUpperCase();

                                System.out.format("%d: %s", cnt, rivi);
                                ++cnt;
                            }
                        }
                        System.out.println();
                        myReader.close();
                        } 
                      
                      catch (FileNotFoundException e) 
                      {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                }
            }

        }
    }
}
