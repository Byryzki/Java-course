/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.util.TreeMap;
import java.io.*;
/**
 *
 * @author laine
 */
public class Currencies {
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws IOException {
        TreeMap<String, Double> map = new TreeMap<>();
       while(true) {
           BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

           System.out.print("Enter command: ");

           final String input = user.readLine();

           String arrinput[] = input.split(" ");

           if("quit".equalsIgnoreCase(arrinput[0])) {
             System.out.println("Quit-command received, exiting...");
             break;
           }
           if("rate".equalsIgnoreCase(arrinput[0])) {
             double numero = Double.parseDouble(arrinput[2]);
             map.put(arrinput[1],numero);
             
             double maara = Double.parseDouble(arrinput[2]);
             String valuutta = arrinput[1].toUpperCase();
             
             System.out.format("Stored the rate 1 EUR = %.3f %s%n", maara, valuutta);
           }
           if("convert".equalsIgnoreCase(arrinput[0])) {
             try{
                double numero = Double.parseDouble(arrinput[1]);
                double vastine = numero/map.get(arrinput[2]);
                System.out.format("%.3f %s = %.3f EUR%n", numero, arrinput[2], vastine);
             }
             catch(Exception e){
                 System.out.printf("No rate for %s has been stored!%n", arrinput[2].toUpperCase());
             }
             
           }
           if("rates".equalsIgnoreCase(arrinput[0])) {
             if("rates".equalsIgnoreCase(arrinput[0])){
               System.out.println("Stored euro rates:");
               map.forEach((k, v) -> System.out.format("  %s %.3f%n", k.toUpperCase(), v));

           }

        }
    }
    }
}
