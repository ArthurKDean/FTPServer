package server;

import java.io.IOException;

public class DataThread extends Thread {
	private DataReceiver dr = null;

	public void run() {
		dr = new DataReceiver();// 创建dr对象之后需要指定工作目录和操作文件对象
		while (true) {
			if (WorkInformation.getInstance().getType() == 0) {
				dr.upload();
				System.out.println("upload done");
			} else if (WorkInformation.getInstance().getType() == 1) {
				try {
					dr.download();
					System.out.println("download done");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		DataThread dt = new DataThread();
		dt.start();
	}
}
