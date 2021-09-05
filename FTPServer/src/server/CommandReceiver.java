package server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

import command.*;

/**
 * Using for get command form the client and analyzer the command then doing
 * what the command will do.
 * 
 * @DataPort 20
 * @author Encore
 *
 */
public class CommandReceiver implements Receiver {
	private ServerSocket SERVER_SOCKET = null;
	private Socket SOCKET = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private String ip = null;
	private String username = "not logged in";
	private Command command = null;

	public void createSocket() {
		try {
			this.SERVER_SOCKET = new ServerSocket(21);
			System.out.println("SERVER START.");
			if ((SOCKET = SERVER_SOCKET.accept()) != null) {
				System.out.println("ONE CONNECTION HAS ACCPETED.");
				this.write("CONNECTION LEVEL STABLE!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void read() throws IOException {
		br = new BufferedReader(new InputStreamReader(this.SOCKET.getInputStream()));
		String msg = null;
		if ((msg = br.readLine()) != null) {
			System.out.println(username + "(" + ip + "): " + msg);
			CommandFactory cf = CommandFactory.getInstance(msg);
			Command command = cf.select();
			System.out.println(username + "(" + ip + "): " + command.excute());
			this.write("COPY THAT.");
		}
	}

	public void write(String msg) throws IOException {
		OutputStream out = SOCKET.getOutputStream();
		pw = new PrintWriter(out, true);
		pw.println("SERVER:" + msg);
	}

	public void run() throws IOException {
		this.createSocket();
		ip = this.SOCKET.getInetAddress().toString();
		Scanner input = new Scanner(System.in);
		this.read();
		while (true) {
			this.read();
		}
	}
}
