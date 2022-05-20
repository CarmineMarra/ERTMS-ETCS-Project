package Middleware;

public class BTM_Connector {




	
	public BTM_Connector (){
		
	}

	
	public boolean request_position_balise(EB_Connector eb){
		
		return eb.take_reqpos();
	}

	public void send_position	(EB_Connector eb, String s) {
	
		eb.put_Position(s);
	
	
	
}
	
	
	
}
