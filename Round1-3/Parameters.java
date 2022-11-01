/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.util.*;
import java.io.*;
/**
 *
 * @author laine
 */
public class Parameters {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
      Arrays.sort(args);
        int count = 1;
        int longest = 0;
        for (int i = 0; i < args.length; i++) {
            if(args[i].length() > longest){
                longest = args[i].length();
            }
            count += 1;
        }
        int digts = String.valueOf(count).length();
        System.out.print("#".repeat(digts+longest+7));
        System.out.print("\n");
        for (int i = 0; i < args.length; i++) {
            System.out.print("#");

            System.out.print(" ".repeat((digts+1)-String.valueOf(i+1).length()));

            System.out.print(i+1);
            System.out.print(" ");
            System.out.print("|");
            System.out.print(" ");
            System.out.print(args[i]);
            for(int j = 0; j < (longest - args[i].length())+1; j++){
                System.out.print(" ");

            }
            if(i+2!=count){
                System.out.print("#");
                System.out.print("\n");
                System.out.print("#");
                System.out.print("-".repeat(digts+2));
                System.out.print("+");
                System.out.print("-".repeat(2+longest));
                System.out.print("#");
                System.out.print("\n");
            }
            else{
                System.out.print("#");
                System.out.print("\n");
            }


        }
        System.out.print("#".repeat(digts+longest+7));
    }
}
