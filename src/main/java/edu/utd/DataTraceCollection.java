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
    public static HashMap<String, Boolean> staticmap = new HashMap<String, Boolean>();
    public static String localName;
    public static Type type;
    public static String methodName;
    public static int count = 0;

//    public static void addVariable(String key, Type type, String name, int value){
//        if(!map.containsKey(key)){
//            map.put(key, new ArrayList<Variable>());
//        }
//        boolean flag = false;
//        for(Variable v : map.get(key)){
//            if(v.getName().equals(name)){
//                v.addValue(value);
//                flag = true;
//                break;
//            }
//        }
//        if(!flag) map.get(key).add(new Variable(type, name, value));
//    }

//    public static void addVariableValue(Object value){
////        System.out.println(type.toString() + " " + localName + " " + value);
////        addVariable(methodName, type, localName, value);
////    }

    public synchronized static void addVariable(Object obj, String className, String methodName, String signature){
        List<Variable> list = map.get(className + "/" + methodName + "/" + signature);
        if(count >= list.size()){
            count = 0;
        }
        Variable v = list.get(count);
        v.addValue(obj);
        count++;
        if(!methodName.equals("getMaxWidth"))
        System.out.println(className + "/" + methodName + ": " + v.getType().toString() + " " + v.getName() + " " + obj);
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
//        System.out.println("Test Run Finished\n");
//        try {
//            FileWriter fw = new FileWriter("datatrace.txt",true);
//            StringBuffer sb = new StringBuffer();
//            sb.append("");
////            for(String methodName : DataTraceCollection.map.keySet()) {
////                // write [Test] + Name
////                sb.append(methodName + "\n");
////                // write className + line
////                List<Variable> variables = DataTraceCollection.map.get(methodName);
////
////                for(Variable variable : variables){
////                    sb.append(variable.getType().toString() + " " + variable.getName() + " ");
////                    for(Object obj : variable.getValues()){
////                        sb.append(obj + " ");
////                    }
////                    sb.append("\n");
////                }
////            }
//            fw.write(sb.toString());
//            fw.close();
//        } catch (IOException ex) {
//            System.err.println("Couldn't log this");
//        }
    }

//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("Finalize Finished\n");
//        try {
//            FileWriter fw = new FileWriter("datatrace.txt",true);
//            StringBuffer sb = new StringBuffer();
//            sb.append("finalize");
//            for(String methodName : DataTraceCollection.map.keySet()) {
//                // write [Test] + Name
//                sb.append(methodName + "\n");
//                // write className + line
//                List<Variable> variables = DataTraceCollection.map.get(methodName);
//
//                for(Variable variable : variables){
//                    sb.append(variable.getType().toString() + " " + variable.getName() + " ");
//                    for(Object obj : variable.getValues()){
//                        sb.append(obj + " ");
//                    }
//                    sb.append("\n");
//                }
//            }
//            fw.write(sb.toString());
//            fw.close();
//        } catch (IOException ex) {
//            System.err.println("Couldn't log this");
//        }
//        super.finalize();
//    }
}
