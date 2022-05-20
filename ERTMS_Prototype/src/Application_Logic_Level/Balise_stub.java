package Application_Logic_Level;

import java.util.ArrayList;

import Middleware.BTM_Connector;
import Middleware.EB_Connector;

public class Balise_stub extends Thread{
	
	
	private BTM_Connector bt = new BTM_Connector();
	
	ArrayList<String> position= new ArrayList<String>();
	private static boolean request = false ;
	private EB_Connector connector_btm;
	

	public Balise_stub (EB_Connector conn){
		
		connector_btm = conn;
	}
	
	public String getPosition(int i) {
		return position.get(i);
	}

	public static boolean isRequest() {
		return request;
	}

	public static void setRequest(boolean request) {
		Balise_stub.request = request;
	}

	public void run() {
		position.add("Roma");
		position.add("Napoli");
		while (true) {
			bt.request_position_balise(connector_btm);
			bt.send_position(connector_btm, position.get((int) ((Math.random() * 2))));
		
		}
	}
	
	
	

}
