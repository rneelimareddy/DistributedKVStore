package com.neel.kvstore.api;

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

import net.sf.json.JSONObject;

@Path("/project")
public class KVStore {
	
	static IDataAccessObject dao = new InMemoryStore();
	
	@POST
	@Path("/add/{key}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(@PathParam(value = "key") String key, String str){
		JSONObject json = JSONObject.fromObject(str);
		dao.put(key, json);
		String output = "Added Successfully..!";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/retrieve/{key}")
	public Response get(@PathParam(value = "key") String key){
		
		JSONObject jsonGet = dao.get(key);
		System.out.println("Get Values: "+ jsonGet);
		String message = jsonGet != null ? jsonGet.toString() : "None";
		return Response.status(200).entity(message).build();		
	}
	
	@DELETE
	@Path("/delete/{key}")
	public Response delete(@PathParam(value = "key") String key){
	
	dao.remove(key);
	String output = "Deleted Successfully..!";
	return Response.status(200).entity(output).build();
}
	
}
