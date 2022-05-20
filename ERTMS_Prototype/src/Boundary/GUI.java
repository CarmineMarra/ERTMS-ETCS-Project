package Boundary;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.Color;


import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Application_Logic_Level.Application_Logic;
import Application_Logic_Level.Balise_stub;
import Application_Logic_Level.RBC_stub;
import DatabaseAccess.DBFacade;
import Entity.Running_Data;
import Middleware.EB_Connector;
import Middleware.ED_Connector;
import Middleware.ER_Connector;
import Monitoring_Infrastructure.Event_Prototype;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import javax.swing.JSpinner;

@SuppressWarnings("unused")
public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBFacade DBF = new DBFacade();
	ED_Connector syncD = new ED_Connector();
	EB_Connector syncB = new EB_Connector();
	ER_Connector syncR = new ER_Connector();
	static Random rand = new Random();
	static long event_start;
	
	public static ArrayList<Event_Prototype> getEvent_arr() {
		return event_arr;
	}

	public static void setEvent_arr(ArrayList<Event_Prototype> event_arr) {
		GUI.event_arr = event_arr;
	}

	public static long getEvent_start() {
		return event_start;
	}

	public static void setEvent_start(long event_start) {
		GUI.event_start = event_start;
	}

	public static long getEvent_end() {
		return event_end;
	}

	public static void setEvent_end(long event_end) {
		GUI.event_end = event_end;
	}


	public static Event_Prototype getEvent_temp() {
		return event_temp;
	}

	public void setEvent_temp(Event_Prototype event_temp) {
		GUI.event_temp = event_temp;
	}
	
	public static boolean isMode() {
		return mode;
	}

	public static void setMode(boolean mode) {
		GUI.mode = mode;
	}
	
	public static boolean isFault() {
		return fault;
	}

	public static void setFault(boolean fault) {
		GUI.fault = fault;
	}

	
	static long event_end;

	private static Event_Prototype event_temp = new Event_Prototype();
	private static ArrayList<Event_Prototype> event_arr = new ArrayList<Event_Prototype>();
	Running_Data train_data = new Running_Data();
	Thread E = new Application_Logic(syncD, syncB, syncR, event_temp);
	Thread B = new Balise_stub(syncB);
	Thread R = new RBC_stub(syncR);
	private long start_time;
	private long end_time;
	private static boolean conn_report;
	private static int choice;
	private static int number_id;
	private static String test_mode;
	private static String level_temp;
	private static String driver_temp;
	private static String select_mode;
	private static String input_mode;
	private static String definitive_mode;
	private static String train_temp;
	private static String model_temp;
	private static String run_temp;
	private static int test_number;
	private static int count_test;
	private static int fault_test;
	private static boolean pos_report;
	private static boolean checkd_temp;
	private static boolean count = false;
	private static boolean mode = false;
	private static boolean fault = false;
	


	
	ArrayList<String> driver_arr= new ArrayList<String>();
	ArrayList<String> driver_arr1= new ArrayList<String>();
	ArrayList<String> level_arr = new ArrayList<String>();
	ArrayList<String> train_arr = new ArrayList<String>();
	ArrayList<String> model_arr = new ArrayList<String>();
	ArrayList<String> run_arr = new ArrayList<String>();
	ArrayList<String> mode1_arr = new ArrayList<String>();
	ArrayList<String> mode2_arr = new ArrayList<String>();
	static ArrayList<Boolean> process = new ArrayList<Boolean>(Arrays.asList(new Boolean[24]));
	
	

	private JPanel contentPane;
	private JTextField Driver;
	private JTextField Runn;
	private JTextField Modeltrain;
	private JTextField Trainid;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	JPanel Tachi_1 = new JPanel();
	JPanel Tachi = new JPanel();
	JPanel Test_number = new JPanel();
	JPanel Drive_enter = new JPanel();
	JLabel Console_text = new JLabel("");
	JPanel Test = new JPanel();
	JButton btnNewButton_2 = new JButton("");
	

	public static Boolean getProcess(int i) {
		return process.get(i);
	}

	public static void setProcess(int j, Boolean i) {
		GUI.process.set(j,i);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("ERTMS/ETCS Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 744);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Tachi_1.setLayout(null);
		Tachi_1.setForeground(Color.WHITE);
		Tachi_1.setBorder(UIManager.getBorder("PopupMenu.border"));
		Tachi_1.setBackground(Color.WHITE);
		Tachi_1.setBounds(175, 27, 793, 371);
		//contentPane.add(Tachi_1);
		
		JLabel Mode_lable = new JLabel("MODE:   S  H");
		Mode_lable.setHorizontalAlignment(SwingConstants.LEFT);
		Mode_lable.setForeground(new Color(50, 205, 50));
		Mode_lable.setFont(new Font("Tahoma", Font.BOLD, 35));
		Mode_lable.setBounds(27, 317, 252, 51);
		Tachi_1.add(Mode_lable);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\carmi\\Desktop\\Tesi_Magistrale\\Project_ERTMS\\Images\\Immagine 2022-04-08 1500021.png"));
		lblNewLabel_4.setBounds(3, 3, 787, 365);
		Tachi_1.add(lblNewLabel_4);
		
		
		Tachi.setForeground(Color.WHITE);
		Tachi.setBorder(UIManager.getBorder("PopupMenu.border"));
		Tachi.setBackground(Color.WHITE);
		Tachi.setBounds(175, 27, 793, 371);
		contentPane.add(Tachi);
		Tachi.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(3, 3, 787, 365);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\carmi\\Desktop\\Tesi_Magistrale\\Project_ERTMS\\Images\\Immagine 2022-04-08 150002.png"));
		Tachi.add(lblNewLabel);
		
		
		Test_number.setLayout(null);
		Test_number.setFocusCycleRoot(true);
		Test_number.setEnabled(false);
		Test_number.setBackground(Color.DARK_GRAY);
		Test_number.setBounds(59, 427, 1048, 96);
		//contentPane.add(Test_number);
		
		JLabel lblNewLabel_1_3 = new JLabel("Test number");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(380, 2, 87, 21);
		Test_number.add(lblNewLabel_1_3);

		
		JSpinner Test_field = new JSpinner();
		Test_field.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Test_field.setBounds(390, 33, 55, 20);
		Test_number.add(Test_field);
		
	
		
		Drive_enter.setFocusCycleRoot(true);
		Drive_enter.setEnabled(false);
		Drive_enter.setBackground(Color.DARK_GRAY);
		Drive_enter.setBounds(59, 427, 1048, 96);
		//contentPane.add(Drive_enter);
		Drive_enter.setLayout(null);
		
		Driver = new JTextField();
		Driver.setBackground(Color.LIGHT_GRAY);
		Driver.setColumns(10);
		Driver.setBounds(374, 33, 156, 33);
		Drive_enter.add(Driver);
		
		JLabel lblNewLabel_1 = new JLabel("ID Driver");
		lblNewLabel_1.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(374, 2, 87, 21);
		Drive_enter.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Level");
		lblNewLabel_1_1.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(674, 2, 122, 21);
		Drive_enter.add(lblNewLabel_1_1);
		
		JPanel Train_data = new JPanel();
		Train_data.setBounds(59, 427, 1048, 96);
		//contentPane.add(Train_data);
		Train_data.setLayout(null);
		Train_data.setBackground(Color.DARK_GRAY);
		
		
		Runn = new JTextField();
		Runn.setBackground(Color.LIGHT_GRAY);
		Runn.setColumns(10);
		Runn.setBounds(649, 33, 156, 33);
		Train_data.add(Runn);
		
		Modeltrain = new JTextField();
		Modeltrain.setBackground(Color.LIGHT_GRAY);
		Modeltrain.setColumns(10);
		Modeltrain.setBounds(374, 33, 156, 33);
		Train_data.add(Modeltrain);
		
		Trainid = new JTextField();
		Trainid.setBackground(Color.LIGHT_GRAY);
		Trainid.setColumns(10);
		Trainid.setBounds(105, 34, 156, 33);
		Train_data.add(Trainid);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ID Treno");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(104, 2, 87, 21);
		Train_data.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Model");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(374, 2, 87, 21);
		Train_data.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Running Number");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(649, 2, 122, 21);
		Train_data.add(lblNewLabel_1_1_1);
		


		
		JPanel Console = new JPanel();
		Console.setBackground(Color.LIGHT_GRAY);
		Console.setBounds(67, 552, 1040, 109);
		contentPane.add(Console);
		Console.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Console:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 0, 110, 20);
		Console.add(lblNewLabel_2);
		
		
		Console_text.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		Console_text.setVerticalAlignment(SwingConstants.TOP);
		Console_text.setBounds(20, 21, 844, 78);
		Console.add(Console_text);
		
		
		
		JRadioButton Three = new JRadioButton("");
		buttonGroup.add(Three);
		Three.setActionCommand("3");
		Three.setBackground(Color.DARK_GRAY);
		Three.setBounds(750, 39, 21, 21);
		Drive_enter.add(Three);
		
		
		JRadioButton NTC = new JRadioButton("");
		buttonGroup.add(NTC);
		NTC.setActionCommand("NTC");
		NTC.setBackground(Color.DARK_GRAY);
		NTC.setBounds(611, 39, 21, 21);
		Drive_enter.add(NTC);
		
		JRadioButton Zero = new JRadioButton("");
		buttonGroup.add(Zero);
		Zero.setActionCommand("0");
		Zero.setBackground(Color.DARK_GRAY);
		Zero.setBounds(649, 39, 21, 21);
		Drive_enter.add(Zero);
		
		JRadioButton One = new JRadioButton("");
		buttonGroup.add(One);
		One.setActionCommand("1");
		One.setBackground(Color.DARK_GRAY);
		One.setBounds(682, 39, 21, 21);
		Drive_enter.add(One);
		
		JRadioButton Two = new JRadioButton("");
		buttonGroup.add(Two);
		Two.setActionCommand("2");
		Two.setBackground(Color.DARK_GRAY);
		Two.setBounds(717, 39, 21, 21);
		Drive_enter.add(Two);
		
		JLabel lblNewLabel_3 = new JLabel("NTC");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(611, 66, 31, 13);
		Drive_enter.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("0");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(649, 66, 21, 13);
		Drive_enter.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("1");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1.setBounds(682, 66, 21, 13);
		Drive_enter.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("2");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1_1.setBounds(717, 66, 21, 13);
		Drive_enter.add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("3");
		lblNewLabel_3_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1_1_1.setBounds(750, 67, 21, 13);
		Drive_enter.add(lblNewLabel_3_1_1_1_1);
		
		

		
		JPanel NL_SH_TRAIN = new JPanel();
		NL_SH_TRAIN.setBounds(59, 427, 1048, 96);
		//contentPane.add(NL_SH_TRAIN);
		NL_SH_TRAIN.setLayout(null);
		NL_SH_TRAIN.setBackground(Color.DARK_GRAY);
		
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("NL");
		lblNewLabel_1_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_1.setBounds(374, 10, 87, 21);
		NL_SH_TRAIN.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("SH");
		lblNewLabel_1_2_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1.setBounds(495, 10, 35, 21);
		NL_SH_TRAIN.add(lblNewLabel_1_2_2_1);
		
		JRadioButton NL_2 = new JRadioButton("");
		NL_2.setActionCommand("NL");
		buttonGroup_1.add(NL_2);
		NL_2.setBackground(Color.DARK_GRAY);
		NL_2.setBounds(370, 33, 21, 21);
		NL_SH_TRAIN.add(NL_2);
		
		JRadioButton Train = new JRadioButton("");
		Train.setActionCommand("TRAIN");
		buttonGroup_1.add(Train);
		Train.setBackground(Color.DARK_GRAY);
		Train.setBounds(607, 33, 21, 21);
		NL_SH_TRAIN.add(Train);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Chose between NL, SH mode or train data enter");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(343, 60, 393, 20);
		NL_SH_TRAIN.add(lblNewLabel_2_1_1);
		
		JRadioButton SH_2 = new JRadioButton("");
		SH_2.setActionCommand("SH");
		buttonGroup_1.add(SH_2);
		SH_2.setBackground(Color.DARK_GRAY);
		SH_2.setBounds(495, 33, 21, 21);
		NL_SH_TRAIN.add(SH_2);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Train data enter");
		lblNewLabel_1_2_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_1.setBounds(569, 10, 152, 21);
		NL_SH_TRAIN.add(lblNewLabel_1_2_2_1_1);
		
		JPanel NL_SH = new JPanel();
		NL_SH.setBounds(59, 427, 1048, 96);
		//contentPane.add(NL_SH);
		NL_SH.setLayout(null);
		NL_SH.setBackground(Color.DARK_GRAY);
		

		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("NL");
		lblNewLabel_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(374, 10, 87, 21);
		NL_SH.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("SH");
		lblNewLabel_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2.setBounds(607, 10, 87, 21);
		NL_SH.add(lblNewLabel_1_2_2);
		
		JRadioButton NL1 = new JRadioButton("");
		NL1.setActionCommand("NL");
		buttonGroup_2.add(NL1);
		
		NL1.setBackground(Color.DARK_GRAY);
		NL1.setBounds(370, 33, 21, 21);
		NL_SH.add(NL1);
		
		JRadioButton SH1 = new JRadioButton("");
		SH1.setActionCommand("SH");
		buttonGroup_2.add(SH1);
		
		SH1.setBackground(Color.DARK_GRAY);
		SH1.setBounds(607, 33, 21, 21);
		NL_SH.add(SH1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Chose between NL or SH mode");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(391, 60, 393, 20);
		NL_SH.add(lblNewLabel_2_1);
		
		// BUTTON OF DRIVER AND LEVEL CONFIRM
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            
				//test_mode = buttonGroup_3.getSelection().getActionCommand();	
				/*if (test_mode.contentEquals("1")== true )	{			
					level_temp = level_arr.get((int) ((Math.random() * 4)));
					driver_temp = driver_arr.get((int) ((Math.random() * 4)));
				}*/
				
				level_temp = buttonGroup.getSelection().getActionCommand();
					driver_temp = Driver.getText();

				checkd_temp= false;
				for (int i = 0; i < driver_arr.size(); i++) {
					

					if (driver_temp.contentEquals(driver_arr.get(i))== true)
						checkd_temp= true;
					
				}
				
				if (checkd_temp == true){
				
					syncD.put_Driver(driver_temp, level_temp);
					
					if ((level_temp.contentEquals("2")== true)|| (level_temp.contentEquals("3")== true)) {
						conn_report =syncD.take_report();
						if (conn_report == true) {
							Console_text.setText("Session with RBC opened! ");
							pos_report = syncD.take_check();
							if (pos_report== true )
								Console_text.setText("<html>" + Console_text.getText() + "<br>"  + "Position confirmed by RBC");
							else
								Console_text.setText("<html>" + Console_text.getText() + "<br>" + "Position not confirmed by RBC");
						}
						else {
							Console_text.setForeground(Color.RED);
							Console_text.setText("Session with RBC rejected! ");
							Console_text.setForeground(Color.BLACK);
						}
					}
			
					else 
						Console_text.setText("This level does not allow connection with RBC! ");
				
					choice = syncD.take_choice();
				
					if (choice == 1) {
						getContentPane().remove(Drive_enter);
						getContentPane().add(NL_SH);
					}
					else {
						getContentPane().remove(Drive_enter);
						getContentPane().add(NL_SH_TRAIN);
					
					}
					repaint();
				}
				else {
					Console_text.setText("<html>" + Console_text.getText() + "<br>" + "Re-enter VALID Driver ID");
					repaint();
				}
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(916, 33, 132, 33);
		Drive_enter.add(btnNewButton);

		
		
		Test.setBackground(Color.DARK_GRAY);
		Test.setBounds(978, 165, 137, 63);
		contentPane.add(Test);
		Test.setLayout(null);
		
		JRadioButton Manual = new JRadioButton("");
		buttonGroup_3.add(Manual);
		Manual.setBounds(6, 21, 21, 21);
		
		Manual.setBackground(Color.DARK_GRAY);
		Manual.setActionCommand("0");
		Test.add(Manual);
		
		JRadioButton Test_but = new JRadioButton("");
		buttonGroup_3.add(Test_but);
		Test_but.setBackground(Color.DARK_GRAY);
		Test_but.setActionCommand("1");
		Test_but.setBounds(58, 21, 21, 21);
		Test.add(Test_but);
		
		JRadioButton Fault_inj = new JRadioButton("");
		buttonGroup_3.add(Fault_inj);
		Fault_inj.setBackground(Color.DARK_GRAY);
		Fault_inj.setActionCommand("2");
		Fault_inj.setBounds(110, 21, 21, 21);
		Test.add(Fault_inj);
		
		JLabel lblNewLabel_3_2 = new JLabel("Man");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_2.setBounds(6, 48, 31, 13);
		Test.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Test");
		lblNewLabel_3_2_1.setForeground(Color.WHITE);
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_2_1.setBounds(58, 49, 31, 13);
		Test.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Fault");
		lblNewLabel_3_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_2_1_1.setBounds(110, 48, 31, 13);
		Test.add(lblNewLabel_3_2_1_1);
		
		
		
		
		// BUTTON POWER ON
		
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			input_mode =buttonGroup_3.getSelection().getActionCommand();
			
			try {
				S0method();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	 	
	 		}
		});
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\carmi\\Desktop\\Tesi_Magistrale\\Project_ERTMS\\Images\\pngegg.png").getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
		btnNewButton_2.setIcon(imageIcon);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setBounds(979, 27, 128, 128);
		contentPane.add(btnNewButton_2);
		
		//TEST BUTTON 
		JButton btnNewButton_3 = new JButton("Confirm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				test_number = (int) Test_field.getValue();
				count_test= 0;
				 
				 while (count_test < test_number) {
					 
			            //
					 	if (count== false)
					 		count= true;
						else {
							try {
								S0method();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					 	
					 	event_start = System.nanoTime();
					 	try {
							randprob();
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						level_temp = level_arr.get((int) ((Math.random() * 4)));
						driver_temp = driver_arr.get((int) ((Math.random() * 5)));
						event_end = System.nanoTime();
						
						event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S1", "start", "start_of_mission_carmine", "end", 1);
						
						checkd_temp= false;
						
						event_start = System.nanoTime();
						try {
							randprob();
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						for (int i = 0; i < driver_arr1.size(); i++) {
							

							if (driver_temp.contentEquals(driver_arr1.get(i))== true)
								checkd_temp= true;
							
						}
						event_end = System.nanoTime();
						
						event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S2", "start", "start_of_mission_carmine", "end", 2);

						
						if (checkd_temp == true){
							syncD.put_Driver(driver_temp, level_temp);
						
							if ((level_temp.contentEquals("2")== true)|| (level_temp.contentEquals("3")== true)) {
								conn_report =syncD.take_report();
								if (conn_report == true) 
									pos_report = syncD.take_check();
								}

							else {
								event_start = System.nanoTime();
								try {
									randprob();
								} catch (InterruptedException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								Console_text.setText("This level does not allow connection with RBC! ");
								
								 
								 event_end= System.nanoTime();
								 
								 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S12", "start", "start_of_mission_carmine", "end", 12);
								 
							}
							choice = syncD.take_choice();
							
								if (choice == 1) {
									select_mode =mode2_arr.get((int) ((Math.random() * 4)));
									syncD.put_repchoice(select_mode);
									mode= false;
									definitive_mode = syncD.receive_mode();
								}
								else {
									
									event_start = System.nanoTime();
									try {
										randprob();
									} catch (InterruptedException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									select_mode =mode1_arr.get((int) ((Math.random() * 4)));
									// In this case the time can be monitored here and not into the monitor because the GUI is the first producer
									syncD.put_repchoice(select_mode);
									 
									 event_end= System.nanoTime();
									 
									 event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S13", "start", "start_of_mission_carmine", "end",13);
									 
									 
									if ((select_mode.contentEquals("SH") == true) || (select_mode.contentEquals("NL") == true)){
										
										event_start = System.nanoTime();
										mode = false;
										definitive_mode = syncD.receive_mode();


									}
									else {
										
										event_start = System.nanoTime();
										try {
											randprob();
										} catch (InterruptedException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
										train_temp =train_arr.get((int) ((Math.random() * 4)));
										model_temp = model_arr.get((int) ((Math.random() * 4)));
										run_temp = run_arr.get((int) ((Math.random() * 5)));
										// In this case the time can be monitored here and not into the monitor because the GUI is the first producer

										syncD.put_train(train_temp, model_temp , run_temp );
										
										event_end= System.nanoTime();
										 
										event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S15", "start", "start_of_mission_carmine", "end", 15);
										 
										
										mode = true;
										
										definitive_mode = syncD.receive_mode();
										
										 
										
										 
										

							}
							
						}

						count_test = count_test +1;
						Console_text.setText("<html>" + Console_text.getText() + "<br>"  +definitive_mode);
					}
					else {
							event_start = System.nanoTime();
							try {
								randprob();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							Console_text.setText("<html>" + Console_text.getText() + "<br>" + "Re-enter VALID Driver ID");
							repaint();
							event_end = System.nanoTime();
							event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S3", "start", "start_of_mission_carmine", "end", 3);
							count = false;
							Collections.fill(process, Boolean.FALSE);
						}
						

					if (count == true) {
						
						event_start = System.nanoTime();
						System.out.println("Cycle end");
						try {
							randprob();
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						event_end = System.nanoTime();
						event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S23", "start", "start_of_mission_carmine", "end",  23);
						event_temp.setCase_id(event_temp.getCase_id()+1);
					}
						////////////////
					
					Console_text.setText("TEST END ");

				 }
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(536, 32, 132, 33);
		Test_number.add(btnNewButton_3);
		
		
		
		
		
		
		//BUTTON OF SH/NL MODE CONFIRMATION
		JButton btnNewButton_1_1 = new JButton("Confirm");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*if (test_mode.contentEquals("1")== true )	{			
					select_mode =mode2_arr.get((int) ((Math.random() * 4)));
				}
			*/
					select_mode =buttonGroup_2.getSelection().getActionCommand();
				
				
				
				syncD.put_repchoice(select_mode);
				definitive_mode = syncD.receive_mode();
				Console_text.setText("");
				Mode_lable.setText("Mode:     "+ definitive_mode);
				getContentPane().remove(NL_SH);
				btnNewButton_2.setEnabled(true);
				getContentPane().add(Test);
				getContentPane().add(Tachi_1);
				getContentPane().remove(Tachi);
	            repaint();
				
				
			}
		});
		
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1_1.setBounds(916, 33, 132, 33);
		NL_SH.add(btnNewButton_1_1);
		
		
		// BUTTON OF SH/NL/TRAIN MODE CONFIRMATION
		JButton btnNewButton_1_1_1 = new JButton("Confirm");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				/*if (test_mode.contentEquals("1")== true )	{			
					select_mode =mode1_arr.get((int) ((Math.random() * 4)));
				}
				else {*/
					select_mode =buttonGroup_1.getSelection().getActionCommand();
				
				

				syncD.put_repchoice(select_mode);
				
				if ((select_mode.contentEquals("SH") == true) || (select_mode.contentEquals("NL") == true)){
					definitive_mode = syncD.receive_mode();
					Console_text.setText("");
					Mode_lable.setText("Mode:     "+ definitive_mode);
					getContentPane().remove(NL_SH_TRAIN);
					btnNewButton_2.setEnabled(true);
					getContentPane().add(Tachi_1);
					getContentPane().remove(Tachi);
					getContentPane().add(Test);

				}
				else {
					getContentPane().remove(NL_SH_TRAIN);
		            getContentPane().add(Train_data);
		            					
				}
				
				
				repaint();
				
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1_1_1.setBounds(916, 33, 132, 33);
		NL_SH_TRAIN.add(btnNewButton_1_1_1);
		
		// BUTTON OF DATA TRAIN CONFIRMATION		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				/*if (test_mode.contentEquals("1")== true )	{			
					train_temp =train_arr.get((int) ((Math.random() * 4)));
					model_temp = model_arr.get((int) ((Math.random() * 4)));
					run_temp = run_arr.get((int) ((Math.random() * 5)));
					
				}
				else {*/
					train_temp = Trainid.getText();
					model_temp = Modeltrain.getText();
					run_temp = Runn.getText();
				
				syncD.put_train(train_temp, model_temp , run_temp );
				
				definitive_mode = syncD.receive_mode();
				Console_text.setText("");
				Mode_lable.setText("Mode:     "+ definitive_mode);
				getContentPane().remove(Train_data);
				btnNewButton_2.setEnabled(true);
				getContentPane().add(Test);
				getContentPane().remove(Tachi);
				getContentPane().add(Tachi_1);
	            repaint();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(916, 33, 132, 33);
		Train_data.add(btnNewButton_1);
		
		
		
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
		
	
	
	
	public void S0method() throws InterruptedException {
		
		event_start = System.nanoTime();
				
		Collections.fill(process, Boolean.FALSE);
		
		if (input_mode.contentEquals("2")== true)
			Application_Logic.setFault(true);
		
		if (count == false) {
					
			
					try {
					number_id = DBF.takeEventIdMax();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					System.out.println(number_id);
					event_temp.setCase_id(number_id +1);
					driver_arr.add("123");
					driver_arr.add("100");
					driver_arr.add("789");
					driver_arr.add("147");
					driver_arr.add("258");
					driver_arr.add("456");
					
					driver_arr1.add("123");
					driver_arr1.add("456");
					driver_arr1.add("789");
					driver_arr1.add("147");
					driver_arr1.add("258");
					
					level_arr.add("NTC");
					level_arr.add("0");
					level_arr.add("1");
					level_arr.add("2");
					level_arr.add("3");
					
					train_arr.add("321");
					train_arr.add("654");
					train_arr.add("987");
					train_arr.add("741");
					train_arr.add("852");
					
					run_arr.add("369");
					run_arr.add("759");
					run_arr.add("153");
					run_arr.add("684");
					run_arr.add("024");
					
					model_arr.add("white");
					model_arr.add("red");
					model_arr.add("blue");
					model_arr.add("black");
					model_arr.add("green");
					
					mode1_arr.add("NL");
					mode1_arr.add("SH");
					mode1_arr.add("Train");
					mode1_arr.add("Train");
					mode1_arr.add("SH");
					
					mode2_arr.add("NL");
					mode2_arr.add("SH");
					mode2_arr.add("NL");
					mode2_arr.add("SH");
					mode2_arr.add("SH");
					
							E.start();	
							B.start();
							R.start();
				           }
					
					
					getContentPane().remove(Test);
					getContentPane().remove(Tachi_1);
					getContentPane().add(Tachi);
					if (input_mode.contentEquals("1")== true) {
						
						getContentPane().add(Test_number);
					}
					else if (input_mode.contentEquals("0")== true)
						getContentPane().add(Drive_enter);
					else { 
						fault = true;
						getContentPane().add(Test_number);
					}
					randprob();
		          
					Console_text.setText("Desk opened." );
					btnNewButton_2.setEnabled(false);
					
					event_end = System.nanoTime();
					
					System.out.println("Sono1");
		            repaint();
		                        
	
		            event_temp.set_Event(event_start, event_end,  event_temp.getCase_id(), "S0", "start", "start_of_mission_carmine", "end", 0);
		            
			}

}
