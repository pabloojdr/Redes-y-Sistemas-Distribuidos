/**
 *
 * @author <tu nombre aqui>
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        // DATOS DEL SERVIDOR:
        //* FIJOS: comÃ©ntelos si los lee de la lÃ­nea de comandos
         String serverName = "127.0.0.1"; // direccion local
         int serverPort = 12345;
        //* VARIABLES: descomÃ©ntelos si los lee de la lÃ­nea de comandos
        //String serverName = args[0];
        //int serverPort = Integer.parseInt(args[1]);

        // SOCKET
        Socket serviceSocket = null;

        // FLUJOS PARA EL ENVÃ�O Y RECEPCIÃ“N
        PrintWriter out = null;
        BufferedReader in = null;

        //* COMPLETAR: Crear socket y conectar con servidor
        serviceSocket = new Socket(serverName, serverPort);

        //* COMPLETAR: Inicializar los flujos de entrada/salida del socket conectado en las variables PrintWriter y BufferedReader
        in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
		out = new PrintWriter(serviceSocket.getOutputStream(), true);
        

        // Obtener texto por teclado
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Introduzca un texto a enviar (para acabar introduzca un texto sin dígito inicial)");
        userInput = stdIn.readLine();

        //* COMPLETAR: Comprobar si el usuario ha iniciado el fin de la interacciÃ³n
        while (Character.isDigit(userInput.charAt(0))) { // bucle del servicio
            //* COMPLETAR: Enviar texto en userInput al servidor a travÃ©s del flujo de salida del socket conectado
        	out.println(userInput);
            //* COMPLETAR: Recibir texto enviado por el servidor a travÃ©s del flujo de entrada del socket conectado
            String line = in.readLine();
            

            // Leer texto de usuario por teclado
            System.out.println("Introduzca un texto a enviar (para acabar introduzca un texto sin dígito inicial)");
            userInput = stdIn.readLine();
        } // Fin del bucle de servicio en cliente

        // Salimos porque el cliente quiere terminar la interaccion, ha introducido TERMINAR
        //* COMPLETAR: Enviar FINISH al servidor para indicar el fin deL Servicio
        	out.println("FINISH");
        //* COMPLETAR: Recibir el OK del Servidor
        	if(in.readLine().equals("OK")) {
        		System.out.println("Servicio finalizado por parte del servidor");
        	} else {
        		System.out.println("Error: respuesta inesperada del servidor");
        	}
        //* COMPLETAR Cerrar flujos y socket
        	in.close();
        	out.close();
        	serviceSocket.close();
    }
}
