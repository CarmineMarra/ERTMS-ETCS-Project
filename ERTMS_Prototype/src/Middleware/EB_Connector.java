package Middleware;

import Application_Logic_Level.Application_Logic;

public class EB_Connector {
	

	static private String position;
	static Application_Logic a = new Application_Logic();
	private int position_available;
	static private boolean reqpos_request;

	private int reqpos_available;


	public EB_Connector(){
		position = new String("");
		position_available = 0;
		reqpos_available = 0;
	};
	
	

public synchronized void put_reqpos () {
	
	while (reqpos_available >= 1){
				try{
					System.out.println( "position request not put !");
					wait();
				}catch (InterruptedException e ){
					e.printStackTrace();
				}
	}
	reqpos_request = true;

	reqpos_available= reqpos_available+1;

notifyAll();
}



public  synchronized boolean take_reqpos() {
	
	while (reqpos_available == 0){
		try{
			System.out.println( "position request not take");
			wait();
			
		}catch (InterruptedException e ){
			e.printStackTrace();
		}
	}

	reqpos_available= reqpos_available-1;


	notifyAll();
	return reqpos_request;


}

	
	public synchronized void put_Position (String pos) {
		
		while (position_available >= 1){
					try{
						System.out.println( "position space full !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		position = pos;
		System.out.println( "position ok");
		position_available= position_available+1;
	notifyAll();
	}

	
	public synchronized String take_Position () {
		
		while (position_available == 0){
					try{
						System.out.println( "position not ready");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		

		Application_Logic.setEvent_start(System.nanoTime());

		position_available= position_available-1;
		
		try {
			Application_Logic.randprob();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Application_Logic.setEvent_end(System.nanoTime());
	
		notifyAll();
		
		Application_Logic.getEvent_temp().set_Event(Application_Logic.getEvent_start(), Application_Logic.getEvent_end(),  Application_Logic.getEvent_temp().getCase_id(), "S5", "start", "start_of_mission_carmine", "end", 5);

	
	return position;
	}


}
