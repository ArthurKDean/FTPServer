package command;

import server.WorkInformation;

public class RestCommand implements Command{
	private StringBuffer command = null;
	private static RestCommand rc = null;
	private WorkInformation winfo = null;
	
	public RestCommand(String command) {
		// TODO Auto-generated constructor stub
		this.setCommand(command);
	}
	public static RestCommand getInstance(String command) {
		if (rc == null) {
			rc = new RestCommand(command);
		} else {
			rc.setCommand(command);
		}
		return rc;
	}


	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("REST")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
