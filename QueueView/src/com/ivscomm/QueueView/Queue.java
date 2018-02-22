package com.ivscomm.QueueView;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class Queue {

	private String name;

	private int holdTime;

	// private String[] Callers;

	private int numCalls;

	private int numAgentsLog;

	private int numAgentsAva;

	private String[] Agents;

	public Queue(){

	}

	public Queue(@JsonProperty("name") String name,
			@JsonProperty("holdTime") int holdTime,
			// @JsonProperty("Callers") String[] Callers,
			@JsonProperty("numCalls") int numCalls,
			@JsonProperty("numAgentsLog") int numAgentsLog,
			@JsonProperty("numAgentsAva") int numAgentsAva,
			@JsonProperty("Agents") String[] Agents
			) {
		super();
		this.name = name;
		this.holdTime = holdTime;
		// this.Callers = Callers;
		this.numCalls = numCalls;
		this.numAgentsLog = numAgentsLog;
		this.numAgentsAva = numAgentsAva;
		this.Agents = Agents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(int holdTime) {
		this.holdTime = holdTime;
	}

	/*
	 * public String[] getCallers() { return Callers; }
	 * 
	 * public void setCallers(String[] callers) { Callers = callers; }
	 */

	public int getNumCalls() {
		return numCalls;
	}

	public void setNumCalls(int numCalls) {
		this.numCalls = numCalls;
	}

	public int getNumAgentsLog() {
		return numAgentsLog;
	}

	public void setNumAgentsLog(int numAgentsLog) {
		this.numAgentsLog = numAgentsLog;
	}

	public int getNumAgentsAva() {
		return numAgentsAva;
	}

	public void setNumAgentsAva(int numAgentsAva) {
		this.numAgentsAva = numAgentsAva;
	}

	
	  public String[] getAgents() { return Agents; }
	  
	  public void setAgents(String[] agents) { Agents = agents; }
	 

}
