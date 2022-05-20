package Application_Logic_Level;


import java.util.ArrayList;
import java.util.Random;

import Entity.Driver;
import Entity.Running_Data;
import Entity.Train;
import Middleware.EB_Connector;
import Middleware.ED_Connector;
import Middleware.ER_Connector;
import Monitoring_Infrastructure.Event_Prototype;

public class Application_Logic extends Thread{
	
	
	static Random rand = new Random();
	private ED_Connector connector_dmi;
	private static Event_Prototype event_temp;
	static long event_start;
	static long event_end;
	private EB_Connector connector_btm;
	private ER_Connector connector_rtm;
	private static String driver_temp;
	private static String level_temp;
	private static String train_temp;
	private static String run_temp;
	private static String model_temp;
	private static String[] driver_level;

	private static String[] train_arr;
	
	private static boolean report;
	private static boolean check_pos;
	private static boolean fault= false;
	private static int choice;
	private static int fault_test;
	private static String choice_reply;

	
	
	public Application_Logic () {
		driver_temp = new String ("");
		level_temp= new String ("");
		train_temp= new String ("");
		run_temp= new String ("");
		model_temp= new String ("");
		report = false;
		check_pos = false;
		choice= 0;
		
	}
	
	public static Event_Prototype getEvent_temp() {
		return event_temp;
	}

	public void setEvent_temp(Event_Prototype event_temp) {
		Application_Logic.event_temp = event_temp;
	}



	private static ArrayList<Event_Prototype> event_arr = new ArrayList<Event_Prototype>();
	public static ArrayList<Event_Prototype> getEvent_arr() {
		return event_arr;
	}

	public static void setEvent_arr(ArrayList<Event_Prototype> event_arr) {
		Application_Logic.event_arr = event_arr;
	}

	public static long getEvent_end() {
		return event_end;
	}

	public static void setEvent_end(long event_end) {
		Application_Logic.event_end = event_end;
	}
	
	
	public static long getEvent_start() {
		return event_start;
	}


	public static void setEvent_start(long event_start) {
		Application_Logic.event_start = event_start;
	}
	
	
	public static boolean isCheck_pos() {
		return check_pos;
	}



	public static void setCheck_pos(boolean check_pos) {
		Application_Logic.check_pos = check_pos;
	}



	public static boolean isReport() {
		return report;
	}



	public static void setReport(boolean report) {
		Application_Logic.report = report;
	}


	public static boolean isFault() {
		return fault;
	}

	public static void setFault(boolean fault) {
		Application_Logic.fault = fault;
	}

	public static void randprob() throws InterruptedException {
		

		
		fault_test = 1 + (int)(Math.random() * 7) ;
		if (fault== true) {
			
			
			
			fault_test = 1 + (int)(Math.random() * 10) ;
			if (fault_test > 7 ) {

				Thread.sleep(fault_test);

				}
			
			else
				Thread.sleep(fault_test);
			}
		
		else 
			Thread.sleep(fault_test);
		
	}
		
	
		

	public Application_Logic(ED_Connector connD, EB_Connector connB, ER_Connector connR, Event_Prototype even){
		

		driver_temp = new String("");
		report = false;
		
		
		
		connector_dmi = connD;
		connector_btm = connB;
		connector_rtm = connR;
		event_temp = even;

		
	}
	
	
	
	public void run() {
		
		
	while(true){

		driver_level= connector_dmi.take_Driver();
		event_start= System.nanoTime();
		try {
			randprob();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		driver_temp = driver_level [0];
		level_temp = driver_level [1];
		
		Driver.setID_Driver(driver_temp);
		Train.set_Level(level_temp);
		event_end= System.nanoTime();
		
		event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S4", "start", "start_of_mission_carmine", "end", 4);

		System.out.println(Train.get_Level()+ Driver.getID_Driver());
		

		connector_btm.put_reqpos();
		
		Running_Data.setPosition(connector_btm.take_Position());
	
		
		if ((Train.get_Level().contentEquals("2")== true) || (Train.get_Level().contentEquals("3")== true)) {
			
			event_start = System.nanoTime();
			connector_rtm.put_req(true);
			
			try {
				randprob();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			 
			 event_end= System.nanoTime();
			 
			 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S6", "start", "start_of_mission_carmine", "end", 6);
		
			 report = connector_rtm.take_connection();

			// inviare il report alla gui
		
			 if ( report == true) {
				 connector_dmi.put_report(true);
			 }
			 else {
				 event_start = System.nanoTime();
				 connector_dmi.put_report(false);
				 
				 try {
						randprob();
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				 
				 event_end= System.nanoTime();
				 
				 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S11", "start", "start_of_mission_carmine", "end", 11);
				 
			 }
		

			
			if (report == true){
				// in questo caso la connessione è stabilita ma deve essere controllata la posizione da parte di RBC
				// quindi mandare posizione a RBC per la verifica
				event_start = System.nanoTime();
				connector_rtm.put_pos(Running_Data.getPosition(), Train.get_Level()); 
				
				
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				event_end= System.nanoTime();
				 
				 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S8", "start", "start_of_mission_carmine", "end", 8);
				 
				check_pos = connector_rtm.take_check();
				
				 
				
				/*if (check_pos == true)
				// Notificare al sistema di bordo che il treno è stato rifiutato;
				System.out.println("Il treno è accettato da RBC!!! Contento? Adesso potrai viaggiare in tranquillità!!");
			
			else 
				System.out.println("Il treno non è stato accettato da RBC, quindi macchinista di merda togliti dai coglioni!!");
			*/
			
			//Notificare la Gui di check pos
				 
				if ( check_pos == false) {
					event_start = System.nanoTime();
					connector_dmi.put_check(false);
					
					try {
						randprob();
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					event_end= System.nanoTime();
				 
					event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S9", "start", "start_of_mission_carmine", "end", 9);
				}
				else {
					event_start = System.nanoTime();
					connector_dmi.put_check(true);
					
					try {
						randprob();
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					 event_end= System.nanoTime();
					 
					 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S10", "start", "start_of_mission_carmine", "end", 10);
					 
				}
			}
		}
		
		/*if (report == false || check_pos == false) {
			// permetti di effettuare la scelta tra NL e SH solo
			event_start = System.nanoTime();
			choice = 1;
			connector_dmi.put_choice (choice);
			 
			 event_end= System.nanoTime();
			 
			 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), " S9 report Onboard for invalid position ", " start ", " Start of mission ", " end ", event_arr);
			 
		}*/
		
		choice = 2;
		connector_dmi.put_choice (choice);
		
		// adesso in base a quello che è stato scelto dall'interfaccia mostrata allora si mette in choice_reply
		
		choice_reply = connector_dmi.take_repchoice();
		
		
		if ( (choice_reply.contentEquals("SH") == true) || (choice_reply.contentEquals("NL") == true)) {
			// Treno in SH o NL mode e fine
			if(Train.get_Level().contentEquals("2") == true  || Train.get_Level().contentEquals("3") == true)
				connector_rtm.put_nlsh (false);	
			Running_Data.setMA_mode(choice_reply);
			

		}
		else {
			// inseriemento dati treno da partre del driver	
			train_arr =connector_dmi.take_train();
			train_temp = train_arr[0];
			model_temp = train_arr[1];
			run_temp =  train_arr[2];
			Train.set_ID_Train(train_temp);
			Train.setModel(model_temp);
			Running_Data.setRunning_numeber(run_temp);
			
			if (Train.get_Level().contentEquals("2") == true  || Train.get_Level().contentEquals("3") == true) {
				connector_rtm.put_nlsh (true);
				event_start = System.nanoTime();
				connector_rtm.put_train(Train.get_ID_Train(), Train.getModel(), Running_Data.getRunning_numeber()); 
				
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				event_end= System.nanoTime();
				
				
				 
				 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S16", "start", "start_of_mission_carmine", "end", 16);
				 
				connector_rtm.take_ack();
				 
				
			}
			
	
			switch (Train.get_Level()) {
			
			case "NTC":
				event_start = System.nanoTime();
				Running_Data.setMA_mode("SN");
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				event_end= System.nanoTime();
				 
				event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S18", "start", "start_of_mission_carmine", "end", 18);
				 
				break;
			case "0":
				event_start = System.nanoTime();
				Running_Data.setMA_mode("UN");
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				event_end= System.nanoTime();
				 
				event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S17", "start", "start_of_mission_carmine", "end", 17);
				 
				break;
			case "1":
				event_start = System.nanoTime();
				Running_Data.setMA_mode("SR");
				
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 event_end= System.nanoTime();
				 
				 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S21", "start", "start_of_mission_carmine", "end", 21);
				 
				break;
			default :
				event_start = System.nanoTime();
				connector_rtm.put_MA();
				try {
					randprob();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				event_end= System.nanoTime();
				 
				event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S20", "start", "start_of_mission_carmine", "end", 20);
				 

				Running_Data.setMA_mode(connector_rtm.take_MAdecision());
				 
				
			}
		}
		
	
		connector_dmi.send_mode (Running_Data.getMA_mode());

				
					

		
	}	
		
	}

}
