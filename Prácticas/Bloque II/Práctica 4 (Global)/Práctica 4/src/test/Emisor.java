package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emisor {
	public static void main(String args[]) throws IOException {
		int serverPto = 54321;
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

		DatagramSocket socket = new DatagramSocket();
		String line = "Hola buenos dias";

		DatagramPacket packet = new DatagramPacket(line.getBytes(), line.length(), serverAddress, serverPto);
		socket.send(packet);
		System.out.println("Paquete enviado");

		socket.close(); // Cuando terminemos de trabajar, es muy importante cerrar el socket
	}
}
