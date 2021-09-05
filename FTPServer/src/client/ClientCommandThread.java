package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientCommandThread extends Thread {
	private Socket SOCKET = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private Match ma = null;

	public void read() throws IOException {
		br = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
		String msg = null;
		if ((msg = br.readLine()) != null) {
			System.out.println(msg);
		}
	}

	public void write(String msg) throws IOException {
		OutputStream out = SOCKET.getOutputStream();
		pw = new PrintWriter(out, true);
		pw.println(msg);
	}

	public void close() throws IOException {
		if (br != null) {
			br.close();
		}
		if (pw != null) {
			pw.close();
		}
		SOCKET.close();
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			this.SOCKET = new Socket("127.0.0.1", 21);
			ma = new Match();
			Scanner input = new Scanner(System.in);
			String msg = null;
			this.read();
			System.out.print(">>");
			while (!(msg = input.nextLine()).equals(null)) {
				if (ma.checkout(msg) == false) {
					System.out.println(">>");;
				} else {
					this.write(msg);
					this.read();
					ma.execute();

//					ClientWorkInfo.getInstance().setWorkdir("D:\\FTP client\\");
//					ClientWorkInfo.getInstance().setFilename("test.png");
//					ClientWorkInfo.getInstance().setType(0); 
//					System.out.println(ClientWorkInfo.getInstance().getFilename()
//							+ ClientWorkInfo.getInstance().getWorkdir() + ClientWorkInfo.getInstance().getType());
					System.out.print(">>");
				}
			}
			input.close();
			this.read();
			this.write(msg);
			this.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
