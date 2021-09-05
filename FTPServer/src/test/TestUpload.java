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

		OutputStream out = SOCKET.getOutputStream();// 这里的输出流 对应的是服务器端的输入流
		PrintWriter pw = new PrintWriter(out, true);// 建议不要用BufferedWriter
		// !!!!!!!!!!!!!!!!!这个true不要忘了！---自动刷新
		// 现在大家写网络传输文件，一般是用PrintWriter

		String str = null;
		while ((str = br.readLine()) != null) {
			pw.println(str);
		}

		// 给服务器发送结束标记---上传结束，要加结束标记，
		// 否则服务器在数据接收完毕时再调用read()或readLine()时会出异常
		// 法1：pw.println("over#$@#@$");//不能出现文件中存在的结束关键字---搞特殊一点
		// 法2---建议采用该种方式---由socket内部来指定结束标记
		SOCKET.shutdownOutput();
		br.close();

		// 接收服务器端反馈

		InputStream in = SOCKET.getInputStream();
		DataInputStream din = new DataInputStream(in);
		System.out.println("server应答:" + din.readUTF());
		SOCKET.close();
		din.close();
	}

}
