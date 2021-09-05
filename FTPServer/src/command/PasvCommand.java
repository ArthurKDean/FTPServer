package command;

import server.WorkInformation;

public class PasvCommand implements Command{
	private StringBuffer command = null;
	private static PasvCommand pc = null;
	
	
	
	public PasvCommand(String command) {
		// TODO Auto-generated constructor stub
		this.setCommand(command);
	}
	public static PasvCommand getInstance(String command) {
		if (pc == null) {
			pc = new PasvCommand(command);
		} else {
			pc.setCommand(command);
		}
		return pc;
	}
	

	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("PASV")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		return "Into passive mode.";
	}

}
