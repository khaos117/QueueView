package com.ivscomm.QueueView;


import javax.script.ScriptException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class QueueResource {

	private static QueueDAO dao = new QueueDAO();
	

	@POST
	@Path("queue")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void create(String data) throws NoSuchMethodException, ScriptException  {
		JSONTokener jsonParser = new JSONTokener(data);

		JSONObject jobject = null;
		String name = "";
		int holdTime = 0;
		int numCalls = 0;
		int numAgentsLog = 0;
		int numAgentsAva = 0;
		String agents = "";
		
		try{
			jobject = (JSONObject) jsonParser.nextValue();
			name = jobject.getString("name");
			holdTime = jobject.getInt("holdTime");
			numCalls = jobject.getInt("numCalls");
			numAgentsLog = jobject.getInt("numAgentsLog");
			numAgentsAva = jobject.getInt("numAgentsAva");
			agents = jobject.getString("Agents");

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		System.out.println(jobject);
		System.out.println(name + holdTime+numCalls+numAgentsLog+numAgentsAva);
		System.out.println("Creating Queue: " + name);
		int arrSize = StringUtils.countMatches(agents, ",");
		String[] agentsArr = new String[arrSize+1];
		agentsArr=agents.split("\\s*,\\s*");
		dao.create(name, holdTime, numCalls, numAgentsLog, numAgentsAva, agentsArr);
	}
	
	@GET
	@Path("index")
	@Produces("text/html")
	public Viewable index(){
		return new Viewable("/index.jsp", null);
	}
	
	@GET
	@Path("index/{name}")
	@Produces("text/html")
	public Viewable index(@Context HttpServletRequest request,@PathParam("name") String name) throws NoSuchMethodException, ScriptException { //, Queue queue
		
		//System.out.println("Name: "+ name);
		request.setAttribute("name", dao.get(name).getName());
        request.setAttribute("holdTime", dao.get(name).getHoldTime());
        request.setAttribute("numCalls", dao.get(name).getNumCalls());
        request.setAttribute("numAgentsLog", dao.get(name).getNumAgentsLog());
        request.setAttribute("numAgentsAva", dao.get(name).getNumAgentsAva());
        request.setAttribute("Agents", dao.get(name).getAgents());
        //System.out.println("TestQueue1 hold time: "+ dao.get(name).getHoldTime());
        //System.out.println("/INDEX called");
        return new Viewable("/index.jsp", null);
    }

	@PUT
	@Path("queue/{name}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void update(String data) throws NoSuchMethodException, ScriptException {
		
		JSONTokener jsonParser = new JSONTokener(data);

		JSONObject jobject = null;
		String name = "";
		int holdTime = 0;
		int numCalls = 0;
		int numAgentsLog = 0;
		int numAgentsAva = 0;
		String agents = "";
		try{
			jobject = (JSONObject) jsonParser.nextValue();
			name = jobject.getString("name");
			holdTime = jobject.getInt("holdTime");
			numCalls = jobject.getInt("numCalls");
			numAgentsLog = jobject.getInt("numAgentsLog");
			numAgentsAva = jobject.getInt("numAgentsAva");
			agents = jobject.getString("Agents");

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		System.out.println(jobject);
		System.out.println(name+" "+holdTime+" "+numCalls+" "+numAgentsLog+" "+numAgentsAva+" "+agents);
		System.out.println("Updating Queue: " + name);
		int arrSize = StringUtils.countMatches(agents, ",");
		String[] agentsArr = new String[arrSize+1];
		agentsArr=agents.split("\\s*,\\s*");
		Queue updated = new Queue(name, holdTime, numCalls, numAgentsLog, numAgentsAva, agentsArr);
		if(null==dao.update(updated)) {
			dao.create(name, holdTime, numCalls, numAgentsLog, numAgentsAva, agentsArr);
		}
		
	}

}
