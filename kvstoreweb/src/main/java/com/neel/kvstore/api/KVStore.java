package com.neel.kvstore.api;


import java.time.Instant;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.neel.kvstore.data.IDataAccessObject;
import com.neel.kvstore.data.InMemoryStore;
import com.neel.kvstore.kafka.KafkaPublisher;

import net.sf.json.JSONObject;

@Path("/project")
public class KVStore {
	
	static IDataAccessObject dao = new InMemoryStore();
	static KafkaPublisher kp = new KafkaPublisher();
	
	@POST
	@Path("/add/{key}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(@PathParam(value = "key") String key, String str){
		JSONObject json = JSONObject.fromObject(str);
		Instant now = Instant.now();
		dao.put(key, json, now.toEpochMilli());
		System.out.println("Before Publish..!");
		kp.publish(key,json, now.toEpochMilli());
		String output = "Added Successfully..!";
		return Response.status(200).entity(output).build();
	}	
	
	@GET
	@Path("/retrieve/{key}")
	public Response get(@PathParam(value = "key") String key){
		
		JSONObject jsonGet = dao.get(key);
		System.out.println("Get Values: "+ jsonGet);
		String message = jsonGet != null ? jsonGet.toString() : "Requested Key not found..!";
		return Response.status(200).entity(message).build();		
	}
	
	@DELETE
	@Path("/delete/{key}")
	public Response delete(@PathParam(value = "key") String key){
	dao.remove(key);
	kp.publish(key,null, Instant.now().toEpochMilli());
	String output = "Deleted Successfully..!";
	return Response.status(200).entity(output).build();
}
	@GET
	@Path("/getall")
	public Response get(){	
		Set<Entry<String,JSONObject>> jsonGetAll = dao.getAll();
		System.out.println("Get All Values: "+ jsonGetAll);
		String message =(String) (jsonGetAll != null ? jsonGetAll.toString() : "Requested Key not found..!");
		return Response.status(200).entity(message).build();		
	}
	
}
