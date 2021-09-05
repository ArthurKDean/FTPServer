package client;

public class Client {
	private ClientCommandThread cct = null;
	private ClientDataThread cdt = null;

	public Client() {
		this.cdt = new ClientDataThread();
		this.cct = new ClientCommandThread();
	}

	public void start() {
		this.cct.start();
		this.cdt.start();
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}

// USER root\r\n
// PASS 123\r\n
// CWD D:\\FTP client\\\r\n
// STOR test.png\r\n
// RETR test.png\r\n