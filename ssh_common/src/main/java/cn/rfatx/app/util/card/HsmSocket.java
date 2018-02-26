package cn.rfatx.app.util.card;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class HsmSocket {
	private Socket socket = null;
	private String ip = "";
	private int port = 0;

	private DataInputStream in = null;
	private DataOutputStream out = null;
	private byte[] receive = null;

	public HsmSocket(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public byte[] CommunicateHsm(byte[] cmdBytes) {
		byte cmdBuf[] = new byte[2+cmdBytes.length];
		byte countBuf[] = new byte[2];
		
		try {
			socket = new Socket(ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			int count = cmdBytes.length;
			
			cmdBuf[0] = (byte)((count&0xFF00)>>>8); //count hex
			cmdBuf[1] = (byte)count;
			System.arraycopy(cmdBytes, 0, cmdBuf, 2, count);
	
			//Send command to HSM
			out.write(cmdBuf, 0, cmdBuf.length);
			out.flush();

			//Read from HSM
			if(in.read(countBuf,0,2)>=2) {
				count = (countBuf[0]&0x00FF)<<8;
				count = count | countBuf[1];

				receive = new byte[count];
				in.readFully(receive);
			} else {
				System.out.println("Cannot read the reply message count.");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception ex) {
			}
		}
		
		return receive;
	}
}
