import java.util.*;
public class DateTime extends Date{
    
    private int hour;
    private int minute;
    private int second;
    
    DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException{
        
        super(year,month,day);
        
        if(hour >= 24 || minute >= 60 || second >= 60 || hour <= 0 || minute <= 0 || second <= 0){
            throw new DateException(String.format("Illegal time %d:%d:%d",hour,minute,second));
        }else{
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
    public int getSecond(){
        return second;
    }
    
    public String toString(){
        String tunti;
        String minuutti;
        String sekunti;
        
        if(hour < 10){
            tunti = String.format("0%d", hour);
        }
        else{
            tunti = Integer.toString(hour);
        }
        
        if(minute < 10){
            minuutti = String.format("0%d", minute);
        }
        else{
            minuutti = Integer.toString(minute);
        }
        if(second < 10){
            sekunti = String.format("0%d", second);
        }
        else{
            sekunti = Integer.toString(second);
        }
        
        super.toString();
        return String.format("%s %s:%s:%s",super.toString(), tunti,minuutti,sekunti);
    }
    
    
}