package server;

/**
 * This class is using for storage DataReceiver's working file system
 * information. this class has only one instance.
 * 
 * @author Encore
 *
 */
public class WorkInformation {
	private int USER_STATUS = 0;
	private int PWD_STATUS = 0;
	private String workdir = "D:\\FTP server\\";
	private String filename = "test.png";
	private static WorkInformation winfo = null;
	private int type = -1;

	private WorkInformation() {

	}

	public static WorkInformation getInstance() {
		if (winfo == null) {
			return winfo = new WorkInformation();
		} else {
			return winfo;
		}
	}

	public synchronized int getUSER_STATUS() {
		return USER_STATUS;
	}

	public synchronized void setUSER_STATUS(int uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}

	public synchronized int getPWD_STATUS() {
		return PWD_STATUS;
	}

	public synchronized void setPWD_STATUS(int pWD_STATUS) {
		PWD_STATUS = pWD_STATUS;
	}

	public synchronized void setWorkdir(String workdir) {
		this.workdir = workdir;
	}

	public synchronized void setFilename(String filename) {
		this.filename = filename;
	}

	public synchronized String getWorkdir() {
		return this.workdir;
	}

	public synchronized String getFilename() {
		return this.filename;
	}

	public synchronized int getType() {
		return type;
	}

	public synchronized void setType(int type) {
		this.type = type;
	}
}
