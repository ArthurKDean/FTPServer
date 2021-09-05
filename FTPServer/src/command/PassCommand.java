package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.WorkInformation;

public class PassCommand implements Command {
	private StringBuffer command = null;
	private static PassCommand pc = null;

	private PassCommand(String str) {
		this.setCommand(str);
	}

	public static PassCommand getInstance(String command) {
		if (pc == null) {
			pc = new PassCommand(command);
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
		if (command.substring(0, 4).equals("PASS")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		if (WorkInformation.getInstance().getPWD_STATUS() == 1)
			return "wrong operation.";
		if (this.judgeCommand()) {
			Pattern p = Pattern.compile("[0-9]+");
			Matcher m = p.matcher(command);
			if (WorkInformation.getInstance().getUSER_STATUS() != 1)
				return "input username first.";
			if (m.find()) {
				String tmp = new String(m.group(0));
				if (tmp.equals("123")) {
					WorkInformation.getInstance().setPWD_STATUS(1);
					return "password correct.";
				} else
					return "password incorrect";
			} else
				return "password incorrect";
		} else {
			return "grammer error";
		}
	}

	public static void main(String[] args) {
		PassCommand pc = PassCommand.getInstance("PASS 123\r\n");
		System.out.println(pc.excute());
	}
}
