import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloCliente extends Thread{
    private DatagramSocket socket;
    private DatagramPacket pack;

    public HiloCliente(DatagramSocket socket, DatagramPacket pack){
        this.pack = pack;
        this.socket = socket;
    }

    public void  run(){
        InetAddress ip_cliente = pack.getAddress();
        int port_cliente = pack.getPort();
        // Extraer información
        String mensajeRecibido = new String(pack.getData(), 0, pack.getLength());
        System.out.println("Mensaje recibido del cliente: " + mensajeRecibido);

        // Respuesta
        String mensajeRespuesta = "Server OK";
        byte[] bufferSalida = mensajeRespuesta.getBytes();
        DatagramPacket packRespuesta = new DatagramPacket(bufferSalida, bufferSalida.length, ip_cliente, port_cliente);
        try {
            socket.send(packRespuesta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}