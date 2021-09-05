package command;

import server.DataThread;

/**
 * This factory is using for select which kind of Command subclass will be used.
 * This factory has only one instance, so if you want to use it, using
 * getInstance() function.Finally if you want to make sure which sub-class you
 * will be used, you must use the select() function.
 * 
 * @author Encore
 *
 */
public class CommandFactory {
	private String cmd = null;
	private static CommandFactory cf = null;
	private Command command = null;

	private CommandFactory(String cmd) {
		this.cmd = cmd;
	}

	private void setTitle(String cmd) {
		this.cmd = cmd;
	}

	public static CommandFactory getInstance(String cmd) {
		if (cf == null) {
			cf = new CommandFactory(cmd);
		} else {
			cf.setTitle(cmd);
		}
		return cf;
	}

	public Command select() {
		String title = this.cmd.split(" +")[0];
		if (title.equals("USER")) {
			return command = UserCommand.getInstance(cmd);
		} else if (title.equals("PASS")) {
			return command = PassCommand.getInstance(cmd);
		} else if (title.equals("SIZE")) {
			return command = SizeCommand.getInstance(cmd);
		} else if (title.equals("CWD")) {
			return command = CwdCommand.getInstance(cmd);
		} else if (title.equals("PASV")) {
			return command = PasvCommand.getInstance(cmd);
		} else if (title.equals("RETR")) {
			return command = RetrCommand.getInstance(cmd);
		} else if (title.equals("STOR")) {
			return command = StorCommand.getInstance(cmd);
			// } else if (title.equals("REST")) {
			// return command = RestCommand.getInstance(cmd);
			// } else if (title.equals("QUIT")) {
			// return command = QuitCommand.getInstance(cmd);
		} else {
			return null;
		}
	}
}
