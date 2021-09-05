package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

/**
 * 
 * @author Encore
 *
 */
public class DataReceiver implements Receiver {
	private ServerSocket SERVER_SOCKET = null;
	private Socket SOCKET = null;
	private InputStream in = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private OutputStream out = null;
	private DataOutputStream dout = null;
	private DataInputStream din = null;
	
	public void createSocket() {
		try {
			this.SERVER_SOCKET = new ServerSocket(20);
			SOCKET = SERVER_SOCKET.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void upload() {
		try {
			this.createSocket();
			System.out.println(SOCKET.getInetAddress().getHostAddress() + " perpare to upload...");

			// ��ȡ�ͻ����ϴ��������ı��ļ�
			// Դ ---socket(�ֽ���)---���⣺��Ҫת�����ַ��� ��������
			in = SOCKET.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			// Ŀ�� ---Ӳ���ַ��� FileWriter---���⣺��ӡ��
			pw = new PrintWriter(
					new FileWriter(
							WorkInformation.getInstance().getWorkdir() + WorkInformation.getInstance().getFilename()),
					true);
			String line = null;
			while ((line = br.readLine()) != null) {
				// if("over#$@#@$".equals(line)){//�Լ�����Ľ�����־
				// break;
				// }
				pw.println(line);
			}

			pw.close();

			// �ϴ��ɹ������ͻ���һ����ʾ��Ϣ

			out = SOCKET.getOutputStream();
			dout = new DataOutputStream(out);
			SOCKET.close();
			SERVER_SOCKET.close();
			dout.close();
			System.out.println("upload done.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void download() throws IOException {
		this.createSocket();
		System.out.println(SOCKET.getInetAddress().getHostAddress() + "prepare to download...");
		br = new BufferedReader(new FileReader(
				WorkInformation.getInstance().getWorkdir() + WorkInformation.getInstance().getFilename()));

		out = SOCKET.getOutputStream();// ���������� ��Ӧ���Ƿ������˵�������
		pw = new PrintWriter(out, true);// ���鲻Ҫ��BufferedWriter
		// !!!!!!!!!!!!!!!!!���true��Ҫ���ˣ�---�Զ�ˢ��
		// ���ڴ��д���紫���ļ���һ������PrintWriter

		String str = null;
		while ((str = br.readLine()) != null) {
			pw.println(str);
		}

		// �����������ͽ������---�ϴ�������Ҫ�ӽ�����ǣ�
		// ��������������ݽ������ʱ�ٵ���read()��readLine()ʱ����쳣
		// ��1��pw.println("over#$@#@$");//���ܳ����ļ��д��ڵĽ����ؼ���---������һ��
		// ��2---������ø��ַ�ʽ---��socket�ڲ���ָ���������
		SOCKET.shutdownOutput();
		br.close();

		// ���շ������˷���

		InputStream in = SOCKET.getInputStream();
		din = new DataInputStream(in);
		SOCKET.close();
		SERVER_SOCKET.close();
		din.close();
	}

}
