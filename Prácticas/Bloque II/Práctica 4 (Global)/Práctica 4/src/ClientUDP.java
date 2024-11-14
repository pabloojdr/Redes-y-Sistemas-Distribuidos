import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <su nombre aquí>
 */

public class ClientUDP {
	public static void main(String[] args) throws IOException {
		// DATOS DEL SERVIDOR:
		// * FIJOS: com�ntelos si los lee de la l�nea de comandos
		String serverName = "127.0.0.1"; // direccion local
		int serverPort = 54322;
		// * VARIABLES: descom�ntelos si los lee de la l�nea de comandos
		// String serverName = args[0];
		// int serverPort = Integer.parseInt(args[1]);

		DatagramSocket serviceSocket = null;

		// * COMPLETAR: crear socket
		serviceSocket = new DatagramSocket(); // No a�adimos puerto por ser cliente

		// INICIALIZA ENTRADA POR TECLADO
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		System.out.println("Introduzca un texto a enviar que empiece con digito (sin digito inicial para acabar): ");
		userInput = stdIn.readLine(); /* CADENA ALMACENADA EN userInput */

		// * COMPLETAR: Comprobar si el usuario quiere terminar servicio
		char firstChar = userInput.charAt(0);
		while (userInput != null && Character.isDigit(firstChar)) {
			// * COMPLETAR: Crear datagrama con la cadena escrito en el cuerpo

			byte[] datosEnvio = userInput.getBytes();
			InetAddress serverAddress = InetAddress.getByName(serverName);

			DatagramPacket envioInfo = null;

			envioInfo = new DatagramPacket(datosEnvio, datosEnvio.length, serverAddress, serverPort);
			
			// * COMPLETAR: Enviar datagrama a traves del socket
			serviceSocket.send(envioInfo);
			System.out.println("Mensaje enviado");
			System.out.println("STATUS: Waiting for the reply");
			
			// * COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta
			// de m�ximo 400 bytes
			byte[] datosRecibidos = new byte[400];
			DatagramPacket recepcion = new DatagramPacket(datosRecibidos, datosRecibidos.length);
			
			// * COMPLETAR: Recibir datagrama de respuesta
			serviceSocket.receive(recepcion);

			// * COMPLETAR: Extraer contenido del cuerpo del datagrama en variable line

			String line = new String(recepcion.getData(), recepcion.getOffset(), recepcion.getLength(),
					StandardCharsets.UTF_8);

			System.out.println("Respuesta del servidor: " + line);
			System.out.println("Introduzca un texto a enviar que empiece con digito (sin digito inicial para acabar): ");
			userInput = stdIn.readLine();
		}

		System.out.println("STATUS: Closing client");

		// * COMPLETAR Cerrar socket cliente
		serviceSocket.close();

		System.out.println("STATUS: closed");
	}
}
