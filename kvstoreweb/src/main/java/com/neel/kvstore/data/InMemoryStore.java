package com.neel.kvstore.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

public class InMemoryStore implements IDataAccessObject {
	
	private Map<String, JSONObject> map = new HashMap<String, JSONObject>();
	private Map<String, Long> lastUpdatedTime = new HashMap<String, Long>();

	public synchronized void put(String key, JSONObject json, long timestamp) {
		if(!lastUpdatedTime.containsKey(key) || lastUpdatedTime.get(key) < timestamp){
			map.put(key,json);
			lastUpdatedTime.put(key, timestamp);
			System.out.println("Added Values: "+ map.get(key) + ", json : " + json.hashCode());
		}else{
			System.out.println("Not the lastest. Hence skipping");
		}
	}

	public JSONObject get(String key) {
		System.out.println("Get Values: "+ map.get(key) + ", json : " + map.get(key).hashCode());  
		return map.get(key);  
	}

	public synchronized void remove(String key) {
		 map.remove(key);
		 lastUpdatedTime.remove(key);
	}
	
	public Set<Entry<String, JSONObject>> getAll() {
		return map.entrySet();
	}

}
