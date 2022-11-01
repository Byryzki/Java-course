/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author laine
 */

import java.io.*;
public class Mean {

    public static void main(String args[]) {
        double sum = 0;
        
        for(int i = 0; i < args.length; i++){
            sum += Double.parseDouble(args[i]);
        }
        System.out.println("Mean: " + sum/args.length);

    }
}
