package command;

public class QuitCommand implements Command {

	private StringBuffer command = null;
	private static QuitCommand qc = null;

	public QuitCommand(String command) {
		// TODO Auto-generated constructor stub
		this.setCommand(command);
	}

	public static QuitCommand getInstance(String command) {
		if (qc == null) {
			qc = new QuitCommand(command);
		} else {
			qc.setCommand(command);
		}
		return qc;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCommand(String str) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(str);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("QUIT")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		System.exit(0);
		return "Close the connection to the server.";
	}

}
