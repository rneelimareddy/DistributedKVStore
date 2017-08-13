package com.neel.kvstore.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InMemoryStore implements IDataAccessObject {
	
	private Map<String, JSONObject> map = new HashMap<String, JSONObject>(); 

	public void put(String key, JSONObject json) {
		// TODO Auto-generated method stub
		map.put(key,json);
		System.out.println("Added Values: "+ map.get(key)); 
	}

	public JSONObject get(String key) {
		//System.out.println("Get Values: "+ map.get(key));  
		return map.get(key);  
	}

	public void remove(String key) {
		// TODO Auto-generated method stub
		 map.remove(key);
	}
	
	public Set<Entry<String, JSONObject>> getAll() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

}
