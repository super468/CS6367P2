package edu.utd;

import edu.utd.object.Variable;
import org.objectweb.asm.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataTraceCollection {
    public static HashMap<String, List<Variable>> map = new HashMap<String, List<Variable>>();
    public static String localName;
    public static Type type;
    public static String methodName;

    public static void addVariable(String key, Type type, String name, Object value){
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<Variable>());
        }
        boolean flag = false;
        for(Variable v : map.get(key)){
            if(v.getName().equals(name)){
                v.addValue(value);
                flag = true;
                break;
            }
        }
        if(!flag) map.get(key).add(new Variable(type, name, value));
    }

    public static void addVariableValue(Object value){
        System.out.println("xxxxxxxxxxxxxxxxxxxxx");
        System.out.println(type.toString() + " " + localName + " " + value);
        addVariable(methodName, type, localName, value);
    }

    public static void test(){
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
    }

    protected void finalize() {
        System.out.println("Test Run Finished\n");
        try {
            FileWriter fw = new FileWriter("datatrace.txt",true);
            StringBuffer sb = new StringBuffer();
            sb.append("test test");
            for(String methodName : DataTraceCollection.map.keySet()) {
                // write [Test] + Name
                sb.append(methodName + "\n");
                // write className + line
                List<Variable> variables = DataTraceCollection.map.get(methodName);

                for(Variable variable : variables){
                    sb.append(variable.getType().toString() + " " + variable.getName() + " ");
                    for(Object obj : variable.getValues()){
                        sb.append(obj + " ");
                    }
                    sb.append("\n");
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ex) {
            System.err.println("Couldn't log this");
        }
    }
}
