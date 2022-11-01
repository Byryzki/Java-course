package fi.tuni.prog3.json;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * A class for representing a JSON value. The value can be either a double, a boolean, a String or null.
 */
public final class ValueNode extends Node {
    
    Double valued;
    Boolean valueb;
    String values;

    /**
     * Constructs a JSON value node that stores the given double value.
     * @param value The double value to store in the new JSON value node.
     */
    public ValueNode(double value){
        this.valued = value;
    }

    /**
     * Constructs a JSON value node that stores the given boolean value.
     * @param value The boolean value to store in the new JSON value node.
     */
    public ValueNode(boolean value){
        this.valueb = value;
    }

    /**
     * Constructs a JSON value node that stores the given string or null.
     * @param value The string or null to store in the new JSON value node.
     */
    public ValueNode(String value){
        this.values = value;
    }
    ValueNode(){
        
    }
    /**
     * Checks whether this value node stores a number (double).
     * @return true if this node stores a double value, otherwise false.
     */
    public boolean isNumber(){
        return valued != null;
    }
    /**
     * Checks whether this value node stores a boolean value.
     * @return true if this node stores a boolean value, otherwise false.
     */
    public boolean isBoolean(){
        return valueb != null;
    }
    /**
     * Checks whether this value node stores a string.
     * @return true if this node stores a string, otherwise false.
     */
    public boolean isString(){
        return values != null;
    }
    /**
     * Checks whether this value node stores null.
     * @return true if this node stores a string, otherwise false.
     */
    public boolean isNull(){
        return isNumber() || isBoolean() || isString();
    }
    /**
     * Returns the stored value as a number (double).
     * @return the stored number as a double value.
     * @throws IllegalStateException if the stored value is not a number.
     */
    public double getNumber(){
        return valued;
    }
    /**
     * Returns the stored value as a boolean value.
     * @return the stored number as a double value.
     * @throws IllegalStateException if the stored value is not a boolean value.
     */
    public boolean getBoolean(){
        return valueb;
    }
    /**
     * Returns the stored value as a string.
     * @return the stored string.
     * @throws IllegalStateException if the stored value is not a string.
     */
    public String getString(){
        return values;
    }
    /**
     * Returns the stored value as null.
     * @return null.
     * @throws IllegalStateException if the stored value is not null.
     */
    public Object getNull(){
        return null;
    }

}
