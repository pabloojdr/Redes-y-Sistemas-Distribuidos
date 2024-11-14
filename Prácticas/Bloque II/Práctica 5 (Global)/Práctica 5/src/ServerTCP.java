import java.io.*;
import java.net.*;
import java.util.Scanner;

class ServerTCP {
	public static String extract_text(String s) {
		String res = "";
		int salto = Character.getNumericValue(s.charAt(0));
		for (int i = 1; i < s.length(); i += salto + 1)
			res += s.charAt(i);
		return res;
	}

	public static void main(String[] args) {
		// DATOS DEL SERVIDOR
		// * FIJO: Si se lee de lÃ­nea de comando debe comentarse
		//int port = 12345; // puerto del servidor
		// * VARIABLE: Si se lee de lÃ­nea de comando debe descomentarse
		int port = Integer.parseInt(args[0]);

		// SOCKETS
		ServerSocket server = null; // Pasivo (recepciÃ³n de peticiones)
		Socket client = null; // Activo (atenciÃ³n al cliente)

		// FLUJOS PARA EL ENVÃ�O Y RECEPCIÃ“N
		BufferedReader in = null;
		PrintWriter out = null;

		// * COMPLETAR: Crear e inicalizar el socket del servidor (socket pasivo)(Abre
		// un puerto de escucha (12345))
		try {
			server = new ServerSocket(port, 1); //el 1 indica el tamaño de cola
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(port);
		}

		while (true) // Bucle de recepción de conexiones entrantes (en el bucle externo vamos a
						// esperar al cliente)
		{
			System.out.println("Esperando para recibir");
			// * COMPLETAR: Esperar conexiones entrantes
			try {
				client = server.accept();

				// * COMPLETAR: Una vez aceptada una conexion, inicializar flujos de
				// entrada/salida del socket conectado
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true); // Es necesario escribir el True, si no, el
																		// servidor puede fastidiar todo

				boolean salir = false;
				while (!salir) // Inicio bucle del servicio de un cliente (Esperamos por petición de cliente)
				{
					// * COMPLETAR: Recibir texto en line enviado por el cliente a travÃ©s del flujo
					// de entrada del socket conectado
					String line = in.readLine(); // Leo lo que me ha mandado el cliente

					// * COMPLETAR: Comprueba si es fin de conexion - SUSTITUIR POR LA CADENA DE FIN
					// enunciado
					if (line.compareTo("FINISH") != 0) {
						line = extract_text(line);
						System.out.println("Le envío al cliente: " + line);

						out.println(line); // Le envio la linea al cliente a través de mi flujo de salida

						// * COMPLETAR: Enviar texto al cliente a traves del flujo de salida del socket
						// conectado
					} else { // El cliente quiere cerrar conexión
						salir = true;
					}
				} // fin del servicio
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(-1);
			} // esperamos por los clientes

			// * COMPLETAR: Cerrar flujos y socket
			try {
				out.println("OK");
				in.close();
				out.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // fin del bucle
	} // fin del metodo
}
