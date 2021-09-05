package command;

import server.WorkInformation;

/**
 * the FTP command for upload. This command will use the excute() function to
 * start the DataPort(20) and start a DataThread for client to upload. Before
 * doing the RETR, you need login first.
 * 
 * @author Encore
 *
 */
public class StorCommand implements Command {
	private StringBuffer command = null;
	private static StorCommand fc = null;

	private StorCommand(String command) {
		this.setCommand(command);
	}

	public static StorCommand getInstance(String command) {
		if (fc == null) {
			fc = new StorCommand(command);
		} else {
			fc.setCommand(command);
		}
		return fc;
	}

	@Override
	public void setCommand(String command) {
		// TODO Auto-generated method stub
		this.command = new StringBuffer(command);
	}

	@Override
	public boolean judgeCommand() {
		// TODO Auto-generated method stub
		if (command.substring(0, 4).equals("STOR")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String excute() {
		// TODO Auto-generated method stub
		if (WorkInformation.getInstance().getUSER_STATUS() != 1 && WorkInformation.getInstance().getPWD_STATUS() != 1)
			return "unauthorized operation.";
		if (this.judgeCommand()) {
			String tmp = command.toString().split(" ")[1];
			String filename = tmp.substring(0, tmp.length() - 4);
			WorkInformation.getInstance().setFilename(filename);
			WorkInformation.getInstance().setType(0);
			return "upload will begin.";
		} else {
			return "grammer error";
		}
	}

	public static void main(String[] args) {
		StorCommand sc = getInstance("STOR test.png\r\n");
		System.out.println(sc.excute());
	}
}
