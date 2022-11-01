import java.io.*;
import java.lang.*;
import java.util.*;

public class ValueNode extends Node {
    
    Double valued;
    Boolean valueb;
    String values;

    
    ValueNode(double value){
        this.valued = value;
    }
    ValueNode(boolean value){
        this.valueb = value;
    }
    ValueNode(String value){
        this.values = value;
    }
    ValueNode(){
        
    }
    public boolean isNumber(){
        return valued != null;
    }
    public boolean isBoolean(){
        return valueb != null;
    }
    public boolean isString(){
        return values != null;
    }
    public boolean isNull(){
        return isNumber() || isBoolean() || isString();
    }
    public double getNumber(){
        return valued;
    }
    public boolean getBoolean(){
        return valueb;
    }
    public String getString(){
        return values;
    }
}
