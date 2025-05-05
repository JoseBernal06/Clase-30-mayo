import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        try {
            int port = 5000;
            DatagramSocket socket = new DatagramSocket(port);

            System.out.println("Empezando la conexion");

            while (true){
                // Arreglo de bytes para recibir los datos
                byte[] bufferEntrada = new byte[1024];

                // Crear el paquete para recibir información
                DatagramPacket pack = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // El servidor recibe el mensaje del cliente
                socket.receive(pack);

                HiloCliente hilo = new HiloCliente(socket, pack);
                hilo.start();
            }

        } catch (SocketException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
