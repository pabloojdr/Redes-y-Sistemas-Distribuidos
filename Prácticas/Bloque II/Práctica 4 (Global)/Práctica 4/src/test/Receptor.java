package test;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

//Primero se ejecuta el receptor y luego ejecutamos el Emisor

public class Receptor {

	public static void main(String args[]) {
		DatagramSocket serverSocket = null;

		try {
			serverSocket = new DatagramSocket(54321);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		DatagramPacket packet = new DatagramPacket(new byte[800], 800);

		try {
			serverSocket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String message = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
		System.out.println(packet.getAddress().toString() + ": " + packet.getPort() + "-> " + message);
		serverSocket.close();
	}
}
