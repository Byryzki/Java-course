/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.util.*;
/**
 *
 * @author laine
 */
public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
 
        double[] dA = Arrays.stream(args).mapToDouble(Double::parseDouble).toArray();
        Arrays.sort(dA);
        int size = dA.length;

        if (size % 2 == 0){
            double median = ((dA[size/2] + dA[(size/2)-1]) /2);
            System.out.println("Median: " + median);
        } else {
            System.out.println("Median: " + dA[size/2]);
        }
    }
}
