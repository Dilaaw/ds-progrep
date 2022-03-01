package tp0;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RecevoirUDP {
    public static void main(String[] args) {
        assert args.length == 1;
        DatagramPacket paquet = null;
        DatagramSocket socket = null;
        byte[] buffer = new byte[1024];
        try {
            socket = new DatagramSocket(Integer.parseInt(args[0]));
        }
        catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
        paquet = new DatagramPacket(buffer,buffer.length);
        try {
            System.out.println("Attente d'un paquet sur le port : " +args[0]);
            socket.receive(paquet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
        System.out.printf("Données : %s", new String(buffer,0, paquet.getLength()));
    }
}
