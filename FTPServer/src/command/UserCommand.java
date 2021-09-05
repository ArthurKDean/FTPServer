package command;

import java.util.regex.*;

import server.WorkInformation;

/**
 * USER命令，对Client传来的USER命令进行解析，获取其中用户名。
 * 
 * @author Encore
 *
 */
public class UserCommand implements Command {
	private StringBuffer command = null;
	private static UserCommand uc = null;

	private UserCommand(String str) {
		this.setCommand(str);
	}

	public static UserCommand getInstance(String command) {
		if (uc == null) {
			uc = new UserCommand(command);
		} else {
			uc.setCommand(command);
		}
		return uc;
	}

	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("USER")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		if (this.judgeCommand()) {
			WorkInformation.getInstance().setUSER_STATUS(0);
			WorkInformation.getInstance().setPWD_STATUS(0);
			Pattern p = Pattern.compile("[a-z]+");
			Matcher m = p.matcher(command);
			if (m.find()) {
				String tmp = new String(m.group(0));
				if (tmp.equals("root")) {
					WorkInformation.getInstance().setUSER_STATUS(1);
					return "username correct.";
				} else {
					return "username incorrect.";
				}
			} else
				return "can't find username.";
		} else {
			return "grammer error";
		}
	}
}
