package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is using for client's upload or download, the operation's type is
 * depend on Type from ClientWorkInfo
 * 
 * @type=-1 do nothing
 * @type=0 upload
 * @type=1 download
 * @author Encore
 *
 */
public class ClientDataThread extends Thread {
	private Socket SOCKET = null;
	private BufferedReader br = null;
	private OutputStream out = null;
	private PrintWriter pw = null;
	private InputStream in = null;
	private DataInputStream din = null;
	private DataOutputStream dout = null;

	public void createSocket() {
		try {
			this.SOCKET = new Socket("localhost", 20);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upload() throws IOException {
		this.createSocket();
		try {
			this.br = new BufferedReader(new FileReader(
					ClientWorkInfo.getInstance().getWorkdir() + ClientWorkInfo.getInstance().getFilename()));
			this.out = SOCKET.getOutputStream();// ���������� ��Ӧ���Ƿ������˵�������
			this.pw = new PrintWriter(out, true);// ���鲻Ҫ��BufferedWriter
			// !!!!!!!!!!!!!!!!!���true��Ҫ���ˣ�---�Զ�ˢ��
			// ���ڴ��д���紫���ļ���һ������PrintWriter

			String str = null;
			while ((str = br.readLine()) != null) {
				pw.println(str);
			}
			SOCKET.shutdownOutput();
			br.close();
			// ���շ������˷���
			this.in = SOCKET.getInputStream();
			this.din = new DataInputStream(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			SOCKET.close();
			din.close();
		}
	}

	public void download() throws UnknownHostException, IOException {
		this.createSocket();
		// ��ȡ�ͻ����ϴ��������ı��ļ�
		this.in = SOCKET.getInputStream();
		this.br = new BufferedReader(new InputStreamReader(in));
		this.pw = new PrintWriter(new FileWriter("D:\\FTP client\\test.png"), true);
		String line = null;
		while ((line = br.readLine()) != null) {
			// if("over#$@#@$".equals(line)){//�Լ�����Ľ�����־
			// break;
			// }
			pw.println(line);
		}
		pw.close();

		// �ϴ��ɹ������ͻ���һ����ʾ��Ϣ

		this.out = SOCKET.getOutputStream();
		this.dout = new DataOutputStream(out);
		dout.writeUTF("�ļ��ϴ��ɹ���");
		SOCKET.close();
		dout.close();
	}

	public void run() {
		try {
			while (true) {
				this.sleep(1000);
				if (ClientWorkInfo.getInstance().getFilename() != null
						&& ClientWorkInfo.getInstance().getWorkdir() != null) {
					if (ClientWorkInfo.getInstance().getType() == 0) {
						this.upload();
					} else if (ClientWorkInfo.getInstance().getType() == 1) {
						this.download();
					}
					ClientWorkInfo.getInstance().setFilename(null);
					ClientWorkInfo.getInstance().setWorkdir(null);
					ClientWorkInfo.getInstance().setType(-1);// ������������Ϊ-1
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
