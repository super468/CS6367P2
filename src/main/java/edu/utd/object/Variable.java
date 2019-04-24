package edu.utd.object;

import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private Type type;
    private String name;
    private List<Object> values;

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Variable(Type type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.values = new ArrayList<Object>();
        this.values.add(value);
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
