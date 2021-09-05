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
		System.out.println(SOCKET.getInetAddress().getHostAddress() + "...发送消息来");

		// 读取客户端上传过来的文本文件
		// 源 ---socket(字节流)---额外：需要转换成字符流 ，缓存流
		InputStream in = SOCKET.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		// 目的 ---硬盘字符流 FileWriter---额外：打印流
		PrintWriter pw = new PrintWriter(new FileWriter("D:\\360Downloads\\YYSetup.txt"), true);
		String line = null;
		while ((line = br.readLine()) != null) {
			// if("over#$@#@$".equals(line)){//自己定义的结束标志
			// break;
			// }
			pw.println(line);
		}

		pw.close();

		// 上传成功，给客户端一个提示信息

		OutputStream out = SOCKET.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		dout.writeUTF("文件上传成功！");
		SOCKET.close();
		dout.close();
	}

}
