package command;

import java.io.File;

import server.DataThread;
import server.WorkInformation;

/**
 * the FTP command for download. This command will use the excute() function to
 * start the DataPort(20) and start a DataThread for client to download. Before
 * doing the RETR, you need login first.
 * 
 * @author Encore
 *
 */
public class RetrCommand implements Command {
	private StringBuffer command = null;
	private static RetrCommand rc = null;
	private File file = null;
	private DataThread dt = null;

	private RetrCommand(String command) {
		this.setCommand(command);
	}

	public static RetrCommand getInstance(String command) {
		if (rc == null) {
			rc = new RetrCommand(command);
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
		if (command.substring(0, 4).equals("RETR")) {
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
			file = new File(WorkInformation.getInstance().getWorkdir() + filename);
			if (file.exists()) {
				WorkInformation.getInstance().setFilename(filename);
				WorkInformation.getInstance().setType(1);
				return "download will begin.";
			} else {
				return "file isn't exist.";
			}
		} else {
			return "grammer error.";
		}
	}
}
