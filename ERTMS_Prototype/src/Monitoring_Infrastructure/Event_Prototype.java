package Monitoring_Infrastructure;

import java.sql.SQLException;
import java.util.ArrayList;

import Boundary.GUI;
import DatabaseAccess.DBFacade;


public class Event_Prototype {
	
	private String timestamp;
	private String lifecycle;
	private String activity;
	private String process;
	//private boolean process_available;
	private int case_id = 0;
	private static int queue = 0;
	DBFacade DBF = new DBFacade();
	
	
	public Event_Prototype () {
		
		
	}
	
	
	public Event_Prototype ( long t, String l, String a, String p, int c) {
		
		
		timestamp = Long.toString(t);
		lifecycle = l;
		activity = a;
		process = p;
		case_id = c;
		//process_available = false;
		
	}
	
	
	public int getCase_id() {
		return case_id;
	}
	
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getLifecycle() {
		return lifecycle;
	}
	
	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getProcess() {
		return process;
	}
	
	public void setProcess(String process) {
		this.process = process;
	}
	
	public synchronized void set_Event (long timeA, long timeB, int caseid, String act, String lifeA, String proc, String lifeB, int i){

		
		
		queue = queue +1;
			while (queue > 1){
				try{
					System.out.println( "mode not take DMI");
					wait();
				}catch (InterruptedException e ){
					e.printStackTrace();
				}
			}
		
		if (GUI.getProcess(i)== false) {

		
		
		///s.add(new Event_Prototype (timeA, lifeA, act, proc, caseid ));
		try {
			DBF.insertEvent(Long.toString(timeA),lifeA, caseid, act, proc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DBF.insertEvent(Long.toString(timeB),lifeB, caseid, act, proc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//s.add(new Event_Prototype (timeB, lifeB, act, proc, caseid ));
		System.out.println("processo : " + i);
			GUI.setProcess(i, true);
		}
		queue= queue -1;
		notifyAll(); 
	}
	
	
	
	/*public String get_Event_arr (ArrayList<Event_Prototype> s){

		
		return this s.getActivity()+ Long.toString(timestamp) + this.getLifecycle()  + Long.toString(case_id) + this.getProcess();
	}*/
	
	public String get_Event (){

		
		return this.getActivity()+ this.timestamp + this.getLifecycle()  + Long.toString(case_id) + this.getProcess();
	}
	
}
/*
 event_start = System.nanoTime();
 
 event_end= System.nanoTime();
 
 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), " S0 open desk ", " start ", " Start of mission ", " end ", event_arr);
 
 
 
 event_arr.add(event_temp);

long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.

*/