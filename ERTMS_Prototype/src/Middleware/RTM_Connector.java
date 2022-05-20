package Middleware;

public class RTM_Connector {
	


	public static void take_request_connection_rtm(ER_Connector er) {
		
		
		er.take_req();
	}
	
	public static void request_connection_rtm(ER_Connector er, boolean c) {
		
		
		er.put_conn(c);
	}
	
	public static String [] request_position_rtm(ER_Connector er) {
		
		
		return er.take_pos();
	}	
	
	public static void check_position_rtm(ER_Connector er, boolean a) {
		
		
		er.put_check(a);
	}
	
	
	public static String [] request_train(ER_Connector er) {
		
		
		return er.take_train();
	}

	public static void send_ack(ER_Connector er) {
	
		
		er.put_ack();
		
		
	}	
	
	public static void receive_MA (ER_Connector er) {
	
		
		er.take_MA();
		
		
	}
	
	public static void decision_MA (ER_Connector er, String m) {
	
		
		er.put_MAdecision(m);
		
		
	}
	
	
	public static boolean take_mode_nlsh(ER_Connector er) {
		
		
		return er.take_nlsh();
	}
	

}
