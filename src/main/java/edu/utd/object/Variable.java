package edu.utd.object;

import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private Type type;
    private String name;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    private List<Object> values;

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Variable(Type type, String name, int index) {
        this.type = type;
        this.name = name;
        this.values = new ArrayList<Object>();
        this.index = index;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public List<Object> getValues() {
        return values;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void addValue(Object obj){
        this.values.add(obj);
    }
}
