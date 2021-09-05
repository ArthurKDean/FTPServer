package command;

import java.io.File;

import server.WorkInformation;

public class SizeCommand implements Command {
	private StringBuffer command = null;
	private static SizeCommand sc = null;
	private File file = null;
	private WorkInformation winfo = null;
	static long filesize;

	public SizeCommand(String command) {
		// TODO Auto-generated constructor stub
		this.setCommand(command);
	}

	public static SizeCommand getInstance(String command) {
		if (sc == null) {
			sc = new SizeCommand(command);
		} else {
			sc.setCommand(command);
		}
		return sc;
	}

	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("SIZE")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		if (this.judgeCommand()) {
			String tmp = command.toString().split(" ")[1];
			String filename = tmp.substring(0, tmp.length() - 2);
			file = new File(winfo.getWorkdir() + filename);
			if (file.exists()) {
				filesize = file.length();
				return "file's size " + filesize + ".";
			} else {
				return "file isn't exist.";
			}
		} else {
			return "grammer error.";
		}
	}

	
}
