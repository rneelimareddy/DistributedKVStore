package com.neel.kvstore.data;

import net.sf.json.JSONObject;

public interface IDataAccessObject {
	public void put(String key, JSONObject json);
	public JSONObject get(String key);
	public void remove(String key);
}
