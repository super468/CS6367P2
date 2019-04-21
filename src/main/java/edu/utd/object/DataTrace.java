package edu.utd.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataTrace {
    private HashMap<String, List<Status>> map = new HashMap<String, List<Status>>();
    public boolean addStatus(String key, Status status){
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<Status>());
        }
        return map.get(key).add(status);
    }

    public HashMap<String, List<Status>> getMap() {
        return map;
    }
}
