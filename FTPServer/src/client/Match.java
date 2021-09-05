package client;

import java.io.IOException;

public class Match {
	private String command = null;
	private String filename = null;
	private String path = null;

	public void matchone(String str) throws IOException {
		String[] s = str.split(" ");
		switch (s[0]) {
		case "CWD":
			command = s[0];
			path = s[1].substring(0, s[1].length() - 4);
			break;
		case "STOR":
			command = s[0];
			filename = s[1].substring(0, s[1].length() - 4);
			break;
		case "RETR":
			command = s[0];
			filename = s[1].substring(0, s[1].length() - 4);
			break;
		case "USER":
			command = s[0];
			break;
		case "PASS":
			command = s[0];
			break;
		case "SIZE":
			command = s[0];
			break;
		case "PASV":
			command = s[0];
			break;
		case "QUIT":
			command = s[0];
			break;
		default:
			System.out.println("grammer is wrong.");
		}

	}

	public boolean checkout(String str) throws IOException {
		char[] arry = str.toCharArray();
		int a = arry.length;
		if (arry[a - 1] == 'n' && arry[a - 2] == '\\'&&arry[a - 3] == 'r' && arry[a - 4] == '\\') {
			matchone(str);
			return true;
		} else {
			System.out.println("grammer is wrong.");
			return false;
		}

	}

	public void execute() {
		if (command.equals("STOR")) {
			ClientWorkInfo.getInstance().setWorkdir("D:\\FTP client\\");
			ClientWorkInfo.getInstance().setFilename(filename);
			ClientWorkInfo.getInstance().setType(0);
		} else if (command.equals("RETR")) {
			ClientWorkInfo.getInstance().setWorkdir("D:\\FTP client\\");
			ClientWorkInfo.getInstance().setFilename(filename);
			ClientWorkInfo.getInstance().setType(1);
		} 
	}
	
	public static void main(String[] args) throws IOException{
		Match m = new Match();
		System.out.println(m.checkout("USER root\r\n"));
		m.execute();
		System.out.println(ClientWorkInfo.getInstance().getWorkdir());
		System.out.println(ClientWorkInfo.getInstance().getFilename());
		System.out.println(ClientWorkInfo.getInstance().getType());
	}
}
