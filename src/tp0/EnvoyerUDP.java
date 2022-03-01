package tp0;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class EnvoyerUDP {
    private String ipAddr;
    private int port;

    public EnvoyerUDP(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
    }

    public static void main(String[] args) {
        System.out.println(" *** Envoyer UDP ***");
        DatagramPacket paquet;
        DatagramSocket socket = null;
        InetAddress adrIP = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Veuillez saisir une chaîne de caractères : ");
        byte[] donnees = in.nextLine().getBytes();
        in.close();
        try{
            adrIP = InetAddress.getByName(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        paquet = new DatagramPacket(donnees,donnees.length,adrIP,Integer.parseInt(args[1]));
        try{
            socket = new DatagramSocket();
            socket.send(paquet);
        }
        catch (SocketException e){
            e.printStackTrace();
            System.exit(1);
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        System.out.printf("==> Chaîne de caractères envoyée à l'adresse : %s:%s ",args[0],args[1]);

    }
}
