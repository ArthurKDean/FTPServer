package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestUpload {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket SOCKET = new Socket("localhost",20);
		BufferedReader br = new BufferedReader(new FileReader("D:\\FTP client\\test.png"));

		OutputStream out = SOCKET.getOutputStream();// ���������� ��Ӧ���Ƿ������˵�������
		PrintWriter pw = new PrintWriter(out, true);// ���鲻Ҫ��BufferedWriter
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
		DataInputStream din = new DataInputStream(in);
		System.out.println("serverӦ��:" + din.readUTF());
		SOCKET.close();
		din.close();
	}

}
