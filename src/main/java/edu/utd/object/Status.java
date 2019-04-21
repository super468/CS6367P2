package edu.utd.object;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private List<Variables> variables = new ArrayList<Variables>();

    public boolean addVariables(Variables v){
        return this.variables.add(v);
    }

    public List<Variables> getVariables() {
        return variables;
    }
}



