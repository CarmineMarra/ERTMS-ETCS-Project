package Middleware;

import java.util.ArrayList;

import Application_Logic_Level.Application_Logic;
import Boundary.GUI;
import Monitoring_Infrastructure.Event_Prototype;

public class ED_Connector {
	
	static Application_Logic a = new Application_Logic();
	private static ArrayList<Event_Prototype> event_arr = new ArrayList<Event_Prototype>();
	static GUI g = new GUI();
	static String id_Driver;
	static String Running_number;
	static String level;
	static int choice_request;
	static String repchoice_request;
	static boolean report_request;	
	static boolean check_request;
	static boolean start_request;
	static boolean ackstart_request;
	static String idtrain;
	static String model;
	static String mode;
	static String running;
	private int driver_available;
	private int arr_available;
	private int choice_available;
	private int repchoice_available;	
	private int report_available;
	private int check_available;
	private int train_available;
	private int start_available;
	private int ackstart_available;
	private int mode_available;
	
	
	
	public ED_Connector(){
		id_Driver=("");
		Running_number = new String ("");
		level = new String("");
		driver_available = 0;
		arr_available= 0;
		choice_available= 0;
		repchoice_available= 0;	
		report_available= 0;
		check_available= 0;
		train_available= 0;
		start_available= 0;
		ackstart_available= 0;
		mode_available= 0;
	}
	
	
	
	public static String getID_Driver() {
		return id_Driver;
	}


	public synchronized void put_Driver (String driver, String lev) {
		
		while (driver_available >= 1){
					try{
						System.out.println( "ID_Driver space full !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		id_Driver = driver;
		level = lev;
		driver_available= driver_available+1;
	notifyAll();
	}

	
	public synchronized String[] take_Driver () {
		
		while (driver_available == 0){
					try{
						System.out.println( "ID_Driver not ready");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}



		driver_available= driver_available-1;
		

	notifyAll();
	return new String [] {id_Driver, level};

	}
	
	
	public synchronized void put_choice (int c) {
		
		while (choice_available >= 1){
					try{
						System.out.println( "choise request not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		choice_request = c;

		choice_available= choice_available+1;

	notifyAll();
	}

	
	
	public  synchronized int take_choice() {
		
		while (choice_available == 0){
			try{
				System.out.println( "choice request not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		choice_available= choice_available-1;


		notifyAll();
		return choice_request;

}
	
	
	
	public synchronized void put_repchoice (String c) {
		
		while (repchoice_available >= 1){
					try{
						System.out.println( "choise request not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		repchoice_request = c;

		repchoice_available= repchoice_available+1;

	notifyAll();
	}

	
	
	public  synchronized String take_repchoice() {
		
		while (repchoice_available == 0){
			try{
				System.out.println( "choice request not take DMI");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		repchoice_available= repchoice_available-1;


		notifyAll();
		return repchoice_request;


}
	
	
	
	public synchronized void put_report (boolean c) {
		
		while (report_available >= 1){
					try{
						System.out.println( "report request not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		report_request = c;

		report_available= report_available+1;

	notifyAll();
	}

	
	
	public  synchronized boolean take_report() {
		
		while (report_available == 0){
			try{
				System.out.println( "report request not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		report_available= report_available-1;


		notifyAll();
		return report_request;


}
	
	
	public synchronized void put_check (boolean c) {
		
		while (check_available >= 1){
					try{
						System.out.println( "check request not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		check_request = c;

		check_available= check_available+1;

	notifyAll();
	}

	
	
	public  synchronized boolean take_check() {
		
		while (check_available == 0){
			try{
				System.out.println( "check request not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		check_available= check_available-1;


		notifyAll();
		return check_request;


}
	
	
	public synchronized void put_train (String id, String m, String r) {
		
		while (train_available >= 1){
					try{
						System.out.println( "train not put DMI !");
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
				System.out.println( "train not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		
		train_available= train_available-1;


		notifyAll();
		return new String [] {idtrain, model, running};
	}
	
	public synchronized void put_start (boolean c) {
		
		while (start_available >= 1){
					try{
						System.out.println( "start request not put !");
						wait();
					}catch (InterruptedException e ){
						e.printStackTrace();
					}
		}
		start_request = c;

		start_available= start_available+1;

	notifyAll();
	}

	
	
	public  synchronized boolean take_start() {
		
		while (start_available == 0){
			try{
				System.out.println( "start request not take");
				wait();
			}catch (InterruptedException e ){
				e.printStackTrace();
			}
		}

		start_available= start_available-1;


		notifyAll();
		return start_request;


}
	


public synchronized void put_ackstart (boolean c) {
	
	while (ackstart_available >= 1){
				try{
					System.out.println( "ackstart request not put !");
					wait();
				}catch (InterruptedException e ){
					e.printStackTrace();
				}
	}
	ackstart_request = c;

	ackstart_available= ackstart_available+1;

notifyAll();
}



public  synchronized boolean take_ackstart() {
	
	while (ackstart_available == 0){
		try{
			System.out.println( "ackstart request not take");
			wait();
		}catch (InterruptedException e ){
			e.printStackTrace();
		}
	}

	ackstart_available= ackstart_available-1;


	notifyAll();
	return ackstart_request;


}


public synchronized void send_mode (String m) {
	System.out.println( "mode take");
	while (mode_available >= 1){
				try{
					System.out.println( "mode not put !");
					wait();
				}catch (InterruptedException e ){
					e.printStackTrace();
				}
	}
	mode = m;

	
	
	mode_available= mode_available+1;

	notifyAll();
	}



	public  synchronized String receive_mode() {

	while (mode_available == 0){
		try{
			System.out.println( "mode not take DMI");
			wait();
		}catch (InterruptedException e ){
			e.printStackTrace();
		}
	}

	GUI.setEvent_start(System.nanoTime());
	try {
		GUI.randprob();
	} catch (InterruptedException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	mode_available= mode_available-1;

	
	
	GUI.setEvent_end(System.nanoTime());
	
	if (GUI.isMode()== true)
		GUI.getEvent_temp().set_Event(GUI.getEvent_start(), GUI.getEvent_end(),  GUI.getEvent_temp().getCase_id(), "S22", "start", "start_of_mission_carmine", "end", 22);
	else
		GUI.getEvent_temp().set_Event(GUI.getEvent_start(), GUI.getEvent_end(),  GUI.getEvent_temp().getCase_id(), "S14", "start", "start_of_mission_carmine", "end", 14);
	notifyAll(); 
	return mode;

}
	
	public  synchronized void charge_arr() {

	while (arr_available >=1){
		try{
			System.out.println( "arr not charge");
			wait();
		}catch (InterruptedException e ){
			e.printStackTrace();
		}	
	}
	
	for (int i= 0; i< Application_Logic.getEvent_arr().size(); i++) {
		event_arr.add(Application_Logic.getEvent_arr().get(i));
	}
		
	arr_available= arr_available+1;
	notifyAll(); 
	
	}
	
	public  synchronized void discharge_arr() {

	while (arr_available == 0){
		try{
			System.out.println( "arr not discharge");
			wait();
		}catch (InterruptedException e ){
			e.printStackTrace();
		}	
	}
	
	for (int i= 0; i< event_arr.size(); i++){
		GUI.getEvent_arr().add(event_arr.get(i));
	}
	arr_available= arr_available -1;
	notifyAll(); 
	
	}
	
}
	
