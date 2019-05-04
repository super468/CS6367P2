package edu.utd;

import edu.utd.object.Variable;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class JUnitExecutionListener extends RunListener {

    // When test suite started to run
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Test Run Started\n");
        //CoverageCollection.testSuite = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
    }

    // When test suite finished running
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Test Run Finished\n");
        try {
            FileWriter fw = new FileWriter("/Users/wangtaishan/Desktop/datatrace.txt",true);
            StringBuffer sb = new StringBuffer();
            //sb.append("test test");
            for(String methodName : DataTraceCollection.map.keySet()) {
                // write [Test] + Name
                sb.append("Method Name: " + methodName + "\n");
                // write className + line
                List<Variable> variables = DataTraceCollection.map.get(methodName);
                for(Variable variable : variables){
                    sb.append("Variable: " + variable.getType().toString() + " " + variable.getName() + "\n");
                    for(Object obj : variable.getValues()){
                        sb.append("Value: " + obj + "\n");
                    }
                    //sb.append("\n");
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ex) {
            System.err.println("Couldn't log this");
        }
    }

    // When a single test started to run
    public void testStarted(Description description) throws Exception {
        //CoverageCollection.testName = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
        //CoverageCollection.testCase = new HashMap<String, LinkedHashSet<Integer>>();
        System.out.println(description.getClassName() + ":" + description.getMethodName() + " Started\n");
    }

    //When a single test finished running
    public void testFinished(Description description) throws Exception {
        //CoverageCollection.testSuite.put(CoverageCollection.testName, CoverageCollection.testCase);
        System.out.println(description.getClassName() + ":" + description.getMethodName() + " Started\n");
    }
}