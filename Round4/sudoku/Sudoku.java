/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author laine
 */
import java.io.*;
import java.util.*;
public class Sudoku {
    private char[][] array = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},       
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},   
        };


    public void set(int i, int j, char c){
        if (i < 0 || i > 8 || j < 0 || j > 8 ){
            System.out.format("Trying to access illegal cell (%d, %d)!\n",i,j);
        }
        else if(" 1234567890".indexOf(c) == -1){
            System.out.format("Trying to set illegal character %c to (%d, %d)!\n",c,i,j);
        }
        else{
            this.array[i][j] = c;
        }
    }
    private static char checkrow(String s) {
        for (int i = 0; i < (s.length() - 1); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return s.charAt(i);
                }
            }
        }
        return ' ';
    }
    private static char[][] transpose(char[][] original){
        char[][] transpose = {
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},       
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},   
        };
        for(int i=0;i<9;i++){    
            for(int j=0;j<9;j++){    
                transpose[i][j]=original[j][i];  
            }    
        }
        
        return transpose;
    }
    private char box(char[][] arr,int row,int col){
        HashSet<Character> st = new HashSet<>();
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                char curr = arr[i+row][j+col];

                // If already encountered before, return
                // false
                if (st.contains(curr))
                    return curr;

                // If it is not an empty cell,
                // insert value at current cell in set
                if (curr != ' ')
                    st.add(curr);
            }
        }
        return ' ';
    }
    public boolean check(){
        int i = 0;
        int j = 0;
        char c = ' ';
        char a = 'x';
                
        boolean row = false;
        boolean column = false;
        boolean block = false;
        
        for (char[] x : this.array)
        {   
            String str = new String(x);
            
            if(checkrow(str) != ' '){
                c = checkrow(str);
                row = true;
                break;
            }
            
            i++;
        }
        if(row == false){
            for (char[] z : transpose(this.array))
            {
                
                String str2 = new String(z);
            
                if(checkrow(str2) != ' '){
                    c = checkrow(str2);
                    column = true;
                    break;
                }
            
                j++;
            }
        }
        if(column == false && row == false){
            i = 0;
            j = 0;
            for(int roww = 0; roww < 9; roww += 3)
            {
                for(int col = 0; col < 9; col += 3)
                {
                    c = box(this.array,roww,col);
                    if(c != ' '){
                        a = box(this.array,roww,col);
                        i = roww;
                        j = col;
                        block = true;
                        break;
                    }
                }
            }
            
            
        
        }
        
        
            
        if(row == true){
            System.out.format("Row %d has multiple %c's!\n",i,c);
            return false;
        }else if(column == true){
            System.out.format("Column %d has multiple %c's!\n",j,c);
            return false;
        }else if(block == true){
            System.out.format("Block at (%d, %d) has multiple %c's!\n",i,j,a);
            return false;
        } else {
            return true;
        }
    }
    public void print(){
        System.out.println("#####################################");
        int counter = 0;
        int hastag = 0;
        for (char[] x : this.array)
        {   
           System.out.print("#");
           for (char y : x)
           {
                System.out.print(" "+ y + " ");
                counter++;
                if(counter == 3){
                    System.out.print("#");
                    counter = 0;
                }
                else{
                    System.out.print("|");
                }
                
                
           }
           System.out.println();
           hastag++;
           
           if(hastag == 9){
               System.out.println("#####################################");
           }else if(hastag == 3 || hastag == 6){
               System.out.println("#####################################");
           }else{
               System.out.println("#---+---+---#---+---+---#---+---+---#");
           }
           
        }
        

    }
    
}