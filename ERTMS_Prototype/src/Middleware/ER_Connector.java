package Middleware;

import Application_Logic_Level.Application_Logic;

public class ER_Connector {
	
	Application_Logic a = new Application_Logic();
	private static int conn_available;
	private static int req_available;
	private static int pos_available;
	private static int check_available;
	private static int ma_available;
	private static int madecision_available;
	private static int ack_available;
	private static int nlsh_available;
	private static String position;
	private static String level;
	private static boolean check;
	private static boolean ack;
	private static boolean MA;
	private static boolean decision_nlsh;
	private static boolean conn_request = false;
	private static String idtrain;
	private static String model;
	private static String mode;
	private static String running;
	private int train_available;

	public ER_Connector(){
	
		conn_available = 0;
		nlsh_available =0;
		req_available = 0;
		pos_available = 0;
		check_available = 0;
		ma_available = 0;
		madecision_available = 0;
		ack_available = 0;
		position = new String ("");
		
	}
	
	public synchronized void put_conn (boolean b) {
		
		while (conn_available >=1){
					try{
						System.out.println( "conn request reply not ready !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		conn_request = b;

		conn_available= conn_available+1;
	notifyAll();
	}

	
	
	public  synchronized boolean take_connection() {
		
		while (conn_available == 0){
			try{
				System.out.println( "connection not ready");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		
		Application_Logic.setEvent_start(System.nanoTime());
		conn_available= conn_available-1;
		try {
			Application_Logic.randprob();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Application_Logic.setEvent_end(System.nanoTime());
	
		
		Application_Logic.getEvent_temp().set_Event(Application_Logic.getEvent_start(), Application_Logic.getEvent_end(),  Application_Logic.getEvent_temp().getCase_id(), "S7", "start", "start_of_mission_carmine", "end",  7);
		
		notifyAll();
		return conn_request;


}
	
	
	public synchronized void put_req (boolean b) {
		
		while (req_available >= 1){
					try{
						System.out.println( "conn request reply not ready !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}

		req_available= req_available+1;
	notifyAll();
	}
	
	
	public synchronized boolean take_req () {
	
		while (req_available == 0){
					try{
						System.out.println( "conn request reply not ready !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}

		req_available= req_available-1;
	notifyAll();
	return true;
	}
	

	public synchronized void put_pos (String p, String l) {
		
		while (pos_available >= 1){
					try{
						System.out.println( "position not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		position = p;
		level = l;

		pos_available= pos_available+1;
	notifyAll();
	}

	
	public synchronized String[] take_pos () {
		
		while (pos_available == 0){
					try{
						System.out.println( "waiting to position ");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		
		Application_Logic.setEvent_start(System.nanoTime());

		pos_available= pos_available-1;

	notifyAll();
	return new String [] {position, level};
	

	}
	
	

	public synchronized void put_check (boolean p) {
		
		while (check_available >= 1){
					try{
						System.out.println( "check not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		check = p;

		check_available= check_available+1;
	notifyAll();
	}

	
	public synchronized boolean take_check () {
		
		while (check_available == 0){
					try{
						System.out.println( "check not take ");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		


		check_available= check_available-1;
		

	notifyAll();
	return check;

	}
	
	
	
	public synchronized void put_train (String id, String m, String r) {
		
		while (train_available >= 1){
					try{
						System.out.println( "train not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		idtrain = id;
		model = m;
		running = r;

		train_available= train_available+1;

	notifyAll();
	}

	public  synchronized String[] take_train() {
		
		while (train_available == 0){
			try{
				System.out.println( "train not take RTM");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		

		train_available= train_available-1;


		notifyAll();
		return new String [] {idtrain, model, running};

	}
	
	
	public synchronized void put_ack () {
		
		while (ack_available >= 1){
					try{
						System.out.println( "ack not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		ack = true;

		ack_available= ack_available+1;

	notifyAll();
	}
	
	
	public  synchronized boolean take_ack() {
		
		while (ack_available == 0){
			try{
				System.out.println( "train not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		

		ack_available= ack_available-1;

		notifyAll();
		return ack;
	}
	
	
	public synchronized void put_MA () {
		
		while (ma_available >= 1){
					try{
						System.out.println( "MA not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		MA = true;

		ma_available= ma_available+1;

	notifyAll();
	}
	
	
	public  synchronized boolean take_MA() {
		
		while (ma_available == 0){
			try{
				System.out.println( "MA not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		

		ma_available= ma_available-1;

		notifyAll();
		return MA;
	}
	
	
	public synchronized void put_MAdecision(String m) {
		
		while (madecision_available >= 1){
					try{
						System.out.println( "mode not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}

		mode = m;
				
		madecision_available= madecision_available+1;

	notifyAll();
	}
		

	public  synchronized String take_MAdecision () {
		
		while (madecision_available == 0){
			try{
				System.out.println( "mode not take RTM");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}


		Application_Logic.setEvent_start(System.nanoTime());	
		madecision_available= madecision_available-1;
		try {
			Application_Logic.randprob();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Application_Logic.setEvent_end(System.nanoTime());
		
		notifyAll();
		
		Application_Logic.getEvent_temp().set_Event(Application_Logic.getEvent_start(), Application_Logic.getEvent_end(),  Application_Logic.getEvent_temp().getCase_id(), "S19", "start", "start_of_mission_carmine", "end", 19);


		notifyAll();
		return mode;

	}
	
	
	
public synchronized void put_nlsh (boolean b) {
		
		while (nlsh_available >= 1){
					try{
						System.out.println( "nlsh not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		decision_nlsh = b;

		nlsh_available= nlsh_available+1;

	notifyAll();
	}
	
public synchronized boolean take_nlsh () {
	
	while (nlsh_available == 0){
				try{
					System.out.println( "nlsh not take !");
					wait();
				}catch (InterruptedException e ){
					e.printStackTrace();
				}
	}


	nlsh_available= nlsh_available-1;

	notifyAll();
	return decision_nlsh;
}

}
