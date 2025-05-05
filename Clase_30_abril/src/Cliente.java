import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static  void main(String[] args) {
        try {
            int portServidor = 5000;
            InetAddress ip_server = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            // Enviar mensaje al servidor
            Scanner teclado = new Scanner(System.in);
            System.out.println("Ingresa un mensaje: ");
            String mensaje_ingresado = teclado.nextLine();

            String mensaje = mensaje_ingresado;
            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferSalida, bufferSalida.length, ip_server, portServidor);
            socket.send(paqueteEnvio);

            // Preparar buffer para recibir respuesta del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteRecepcion);

            // Mostrar la respuesta del servidor
            String mensajeRespuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("Respuesta del servidor: " + mensajeRespuesta);

            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}