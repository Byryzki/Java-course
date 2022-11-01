import java.util.*;
public class Date {
    
    private int year = 0;
    private int month = 0;
    private int day = 0;

    Date(int year, int month, int day) throws DateException{
        
        if(tarkastaja(month,day)){
            throw new DateException(String.format("Illegal date %d.%d.%d",day,month,year));
        }
        
        else if(month >= 13 || day >= 32 || day <= 0 || month <= 0 ){
            throw new DateException(String.format("Illegal date %d.%d.%d",day,month,year));
        }
        else{
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
        

        
        
        
            
    private boolean tarkastaja(int month,int day){
        if(month == 11 && day == 31){
            return true;
        }
        else{
            return false;
        }
    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public String toString(){
        String paiva;
        String kuukausi;
        if(day < 10){
            paiva = String.format("0%d", day);
        }
        else{
            paiva = Integer.toString(day);
        }
        
        if(month < 10){
            kuukausi = String.format("0%d", month);
        }
        else{
            kuukausi = Integer.toString(month);
        }
        return String.format("%s.%s.%d", paiva,kuukausi,year);
    }
}