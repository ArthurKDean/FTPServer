package server;

import java.io.IOException;

/**
 * ·þÎñÆ÷¶Ë
 * 
 * @author Encore
 *
 */
public class Server {
	private CommandThread ct = null;
	private DataThread dt = null;

	public Server() {
		this.ct = new CommandThread();
		this.dt = new DataThread();
	}

	public void start() throws IOException {
		ct.start();
		dt.start();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.start();
	}

}
