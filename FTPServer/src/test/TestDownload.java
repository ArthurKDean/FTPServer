package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TestDownload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket SOCKET = new Socket("127.0.0.1", 20);
		System.out.println(SOCKET.getInetAddress().getHostAddress() + "...������Ϣ��");

		// ��ȡ�ͻ����ϴ��������ı��ļ�
		// Դ ---socket(�ֽ���)---���⣺��Ҫת�����ַ��� ��������
		InputStream in = SOCKET.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		// Ŀ�� ---Ӳ���ַ��� FileWriter---���⣺��ӡ��
		PrintWriter pw = new PrintWriter(new FileWriter("D:\\360Downloads\\YYSetup.txt"), true);
		String line = null;
		while ((line = br.readLine()) != null) {
			// if("over#$@#@$".equals(line)){//�Լ�����Ľ�����־
			// break;
			// }
			pw.println(line);
		}

		pw.close();

		// �ϴ��ɹ������ͻ���һ����ʾ��Ϣ

		OutputStream out = SOCKET.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		dout.writeUTF("�ļ��ϴ��ɹ���");
		SOCKET.close();
		dout.close();
	}

}
