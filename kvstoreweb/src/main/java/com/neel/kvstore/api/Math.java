package com.neel.kvstore.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/operation")
public class Math {

	@GET
	@Path("/add/{param1}/{param2}")
	public Response getMsg(@PathParam("param1") String msg1,@PathParam("param2") String msg2) {
		int x = Integer.parseInt(msg1);
		int y = Integer.parseInt(msg2);
		int z = x + y; 
		String output = "Z: " + Integer.toString(z);
		return Response.status(200).entity(output).build();

	}
	
}