package com.ivscomm.QueueView;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.script.*;

public class QueueDAO {
	
	private final ConcurrentMap<String, Queue> queueMap;
	
	String[] blank = {"IVS"};
	Queue IVSQueue = new Queue("IVSCOMM-QUEUE",0,0,0,0, blank);
	Queue AIRLIFTQueue = new Queue("AIRLIFTOFLANSING-QUEUE",0,0,0,0, blank);
	Queue KZOOLOAVESQueue = new Queue("KZOOLOAVES-QUEUE",0,0,0,0, blank);
	Queue SAVCOQueue = new Queue("SAVCO-QUEUE",0,0,0,0, blank);
	Queue EMMAUSQueue = new Queue("EMMAUS-QUEUE",0,0,0,0, blank);
	
	
	public QueueDAO(){
		this.queueMap = new ConcurrentHashMap<>();
		queueMap.putIfAbsent(IVSQueue.getName(), IVSQueue);
		queueMap.putIfAbsent(AIRLIFTQueue.getName(), AIRLIFTQueue);
		queueMap.putIfAbsent(KZOOLOAVESQueue.getName(), KZOOLOAVESQueue);
		queueMap.putIfAbsent(SAVCOQueue.getName(), SAVCOQueue);
		queueMap.putIfAbsent(EMMAUSQueue.getName(), EMMAUSQueue);
	}
	
	public Queue get(String name){
		return queueMap.get(name);
	}

	public Queue create(String name, int holdTime, int numCalls, int numAgentsLog, int numAgentsAva, String[] agents) throws ScriptException, NoSuchMethodException {
		Queue queue = new Queue(name, holdTime, numCalls, numAgentsLog, numAgentsAva, agents);
		if (null != queueMap.putIfAbsent(queue.getName(), queue)){
			return null;
		}
		return queue;
	}

	public Queue update(Queue queue) {
		if (queue.getHoldTime() == 0) {
			queue.setHoldTime(queueMap.get(queue.getName()).getHoldTime());
			
		}
		if (Arrays.equals(queue.getAgents(), null) | Arrays.equals(queue.getAgents(), blank)) {
			queue.setAgents(queueMap.get(queue.getName()).getAgents());
			
		}
		if (null == queueMap.replace(queue.getName(), queue)){
			return null;
		}
		return queue;
	}

}
