package server;

import java.io.IOException;

/**
 * This Thread is using for receive the command from the client
 * 
 * @author Encore
 *
 */
public class CommandThread extends Thread {
	private CommandReceiver cr = null;

	public CommandThread() {
		this.cr = new CommandReceiver();
	}

	public void run() {
		try {
			cr.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("FTP CONNECTION INTERRUPTED.");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		CommandThread ct = new CommandThread();
		ct.start();
	}
}
