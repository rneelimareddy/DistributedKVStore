package com.neel.kvstore.data;

import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

public interface IDataAccessObject {
	public void put(String key, JSONObject json, long timestamp);
	public JSONObject get(String key);
	public void remove(String key);
	public Set<Entry<String, JSONObject>> getAll();
}
