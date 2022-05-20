package Application_Logic_Level;


import java.util.ArrayList;

import Middleware.ER_Connector;
import Middleware.RTM_Connector;

import java.util.*;

@SuppressWarnings("unused")
public class RBC_stub extends Thread{
	
	
	
	private static boolean conn_decision = true;
	private static boolean decision_train = true;
	private static boolean mode_nlsh = true;
	private ER_Connector connector_er;
	private static String pos_check;
	private static String level_check;
	private static String train_temp;
	private static String run_temp;
	private static String model_temp;
	private static String[] train_arr;
	private static String[] pos_arr;
	
	ArrayList<String> mode = new ArrayList<String>();
	

	

	
	
	public RBC_stub (ER_Connector conn){
		connector_er = conn;
		pos_check = new String ("");
		level_check = new String ("");
		train_temp = new String ("");
		run_temp = new String ("");
		model_temp = new String ("");

	}




	public void run() {
		
		mode.add("OS");
		mode.add("LS");
		mode.add("SH");
		mode.add("FS");
		mode.add("SR");

		while (true) {
		
		mode_nlsh= false;
		RTM_Connector.take_request_connection_rtm(connector_er);
		System.out.println( "RICHIESTA PRESA");		
		// inviare la risposta di connessione
		RTM_Connector.request_connection_rtm(connector_er, conn_decision);

		
		//ricevere la posizione ed il livello
		pos_arr = RTM_Connector.request_position_rtm(connector_er);
		pos_check = pos_arr[0];
		level_check= pos_arr [1];
		
		//controllare se la posizione è valida
		if (pos_check.contentEquals("Roma") == true )
			RTM_Connector.check_position_rtm(connector_er, true);
		else
			RTM_Connector.check_position_rtm(connector_er, false);
		System.out.println( "PRESO");	
		mode_nlsh= RTM_Connector.take_mode_nlsh(connector_er);
		System.out.println( "NON PRESO");	
		
		if (mode_nlsh == true && conn_decision == true) {
		
		// se la connessione è stabilita 

				train_arr = RTM_Connector.request_train(connector_er);
				train_temp = train_arr[0];
				model_temp = train_arr[1];
				run_temp = train_arr[2];
				RTM_Connector.send_ack (connector_er);
				RTM_Connector.receive_MA(connector_er);
				RTM_Connector.decision_MA(connector_er, mode.get((int) ((Math.random() * 4))));
				
			
			
			System.out.println( "STO DENTRO ");	
		}
		
		System.out.println( "SONO USCITO");	
	}
}

	}

		
		
		
	


