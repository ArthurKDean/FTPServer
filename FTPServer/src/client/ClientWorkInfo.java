package client;

/**
 * This Class is using for client's upload or download, It has only one instance
 * for store working directory information.
 * 
 * @author Encore
 *
 */
public class ClientWorkInfo {
	private static ClientWorkInfo cwi = null;
	private String workdir = null;
	private String filename = null;
	private int type = -1;

	private ClientWorkInfo() {

	}

	public static ClientWorkInfo getInstance() {
		if (cwi == null)
			return cwi = new ClientWorkInfo();
		else
			return cwi;
	}

	public synchronized String getWorkdir() {
		return workdir;
	}

	public synchronized void setWorkdir(String workdir) {
		this.workdir = workdir;
	}

	public synchronized int getType() {
		return type;
	}

	public synchronized void setType(int type) {
		this.type = type;
	}

	public synchronized String getFilename() {
		return filename;
	}

	public synchronized void setFilename(String filename) {
		this.filename = filename;
	}

}
