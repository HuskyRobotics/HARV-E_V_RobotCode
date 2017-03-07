package org.usfirst.frc.team4585.robot.harve.model.intranet;

import java.net.*;
import java.io.*;

public class NetPortListener implements Runnable {
	
	private String ip;
	private int port;
	Socket clientSocket;
	DataOutputStream outToServer;
	DataInputStream inFromServer;
	Exception mostRecentError;
	
	boolean connected=false;

	public NetPortListener(String ipAddress, int portNumber) throws Exception {
		ip = ipAddress;
		port = portNumber;
	}
	
	@Override
	public void run() {
		try {
			while (!this.reConnect(ip, port)); // try to connect until it does
		} catch (Exception e) {
			mostRecentError=e;
		}
	}

	public boolean send(String message) {

		try {
			String toServer = message;

			outToServer.writeUTF(toServer);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			connected=false;
			return false;
		}
	}

	public String recieve() {

		try {
			String Sentence;

			Sentence = inFromServer.readUTF();

			return Sentence;

		} catch (IOException e) {
			connected=false;
			return "";

		}
	}

	public boolean close() {
		try {
			clientSocket.close();
			outToServer.close();
			inFromServer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean reConnect(String ip, int port){
		try {
			while (!this.connect(ip, port)); // try to connect until it does

			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new DataInputStream(clientSocket.getInputStream());
			
			connected=true;
			return connected;

			// sets a timeout of read()
		} catch (Exception e) {
			mostRecentError=e;
			return false;
		}
	}

	private boolean connect(String ipAddress, int portNumber) {
		try {
			clientSocket = new Socket(ip, port); // try to connect
			clientSocket.setSoTimeout(8000); // 8 Seconds
			return true;
		} catch (Exception e) {
			mostRecentError=e;
			return false;
		}
	}
	Exception getError(){
		return mostRecentError;
	}
	public boolean isConnected(){
		return connected;
	}



}
