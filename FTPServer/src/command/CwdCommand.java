package command;

import java.io.File;

import server.WorkInformation;

public class CwdCommand implements Command{

	private StringBuffer command = null;
	private static CwdCommand cc = null;
	private File file = null;
	private WorkInformation winfo = null;
	private String path;
		
	public CwdCommand(String command) {
		// TODO Auto-generated constructor stub
		this.setCommand(command);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static CwdCommand getInstance(String command) {
		if (cc == null) {
			cc = new CwdCommand(command);
		} else {
			cc.setCommand(command);
		}
		return cc;
	}
	
	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 3).equals("CWD")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		if (this.judgeCommand()) {
			path=command.substring(5,command.length()-3);
			winfo.setWorkdir(path);
			return "editor path sucess";
		} else {
			return "grammer error.";
		}
	}

}
