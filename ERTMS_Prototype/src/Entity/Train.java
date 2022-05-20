package Entity;

public class Train {
	

	private static String ID_Train;
	private static String Model;
	private static String level;

	
	
	
	
	public Train() {
		
		ID_Train = new String ("");
		Model = new String ("");
		level = new String ("");
	}
	
	
	
	public static String getModel() {
		return Model;
	}

	static public void setModel(String model) {
		Model = model;
	}

	
	

	public static String get_ID_Train() {
		return ID_Train;
	}

	static public void set_ID_Train(String iD_Train) {
		ID_Train = iD_Train;
	}
	
	
	

	public static String get_Level() {
		return level;
	}

	static public void set_Level(String levels) {
		level = levels;
	}





}
