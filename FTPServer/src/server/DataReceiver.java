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

			// 读取客户端上传过来的文本文件
			// 源 ---socket(字节流)---额外：需要转换成字符流 ，缓存流
			in = SOCKET.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			// 目的 ---硬盘字符流 FileWriter---额外：打印流
			pw = new PrintWriter(
					new FileWriter(
							WorkInformation.getInstance().getWorkdir() + WorkInformation.getInstance().getFilename()),
					true);
			String line = null;
			while ((line = br.readLine()) != null) {
				// if("over#$@#@$".equals(line)){//自己定义的结束标志
				// break;
				// }
				pw.println(line);
			}

			pw.close();

			// 上传成功，给客户端一个提示信息

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

		out = SOCKET.getOutputStream();// 这里的输出流 对应的是服务器端的输入流
		pw = new PrintWriter(out, true);// 建议不要用BufferedWriter
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
		din = new DataInputStream(in);
		SOCKET.close();
		SERVER_SOCKET.close();
		din.close();
	}

}
