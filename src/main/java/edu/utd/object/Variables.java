package edu.utd.object;

public class Variables {
    private String accessModifier;
    private String type;
    private String name;
    private Object value;

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Variables(String accessModifier, String type, String name, Object value) {
        this.accessModifier = accessModifier;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
