import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            DatagramSocket socketCliente = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getByName("172.29.59.56");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Tú: ");
                String mensaje = scanner.nextLine();

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Adios....");
                    return;
                }

                byte[] buffer = mensaje.getBytes();

                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, ipServidor, 5000);
                socketCliente.send(paquete);

                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                socketCliente.receive(paqueteRespuesta);

                String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                System.out.println("Servidor: " + respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
