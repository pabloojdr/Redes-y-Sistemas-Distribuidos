import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <su nombre aquÃ­>
 */
public class ServerUDP {
	public static String extraerTexto(String texto) {
		String resultado = "";
		int salto = Character.getNumericValue(texto.charAt(0));

		for (int i = 1; i < texto.length(); i += salto + 1) {
			resultado += texto.charAt(i);
		}

		return resultado;
	}

	public static void main(String[] args) throws IOException {
		// DATOS DEL SERVIDOR
		// * FIJO: Si se lee de línea de comando debe comentarse
		int port = 54322; // puerto del servidor
		// * VARIABLE: Si se lee de línea de comando debe descomentarse
		//int port = Integer.parseInt(args[0]); // puerto del servidor

		// SOCKET
		DatagramSocket server = null;

		// * COMPLETAR Crear e inicalizar el socket del servidor
		server = new DatagramSocket(port);

		// Funcion PRINCIPAL del servidor
		while (true) {
			// * COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta
			// de máximo 400 bytes
			byte[] datosRecibidos = new byte[400];
			DatagramPacket packet = new DatagramPacket(datosRecibidos, datosRecibidos.length);

			// * COMPLETAR: Recibir datagrama
			System.out.println("Esperando para recibir");
			server.receive(packet);

			// * COMPLETAR: Obtener texto recibido
			String line = new String(packet.getData(), packet.getOffset(), packet.getLength(),
					StandardCharsets.UTF_8);

			// * COMPLETAR: Mostrar por pantalla la direccion socket (IP y puerto) del
			// cliente y su texto
			System.out.println(packet.getAddress().toString() + ": " + packet.getPort() + "-> " + line);

			// Capitalizamos la linea
			line = extraerTexto(line);

			// * COMPLETAR: crear datagrama de respuesta
			DatagramPacket reply = new DatagramPacket(line.getBytes(), line.length(), packet.getAddress(),
					packet.getPort());

			// * COMPLETAR: Enviar datagrama de respuesta
			server.send(reply);
			System.out.println("Paquete recibido");

		} // Fin del bucle del servicio
	}

}
