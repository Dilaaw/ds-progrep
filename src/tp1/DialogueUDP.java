package tp1;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class DialogueUDP {
    private String nomDestinataire; // Le nom utilisateur du destinataire (l’autre utilisateur)
    private int portDestinataire; // Le numéro du port auquel le destinataire est à l'écoute
    private InetAddress adresseDestinataire; // L'adresse du destinataire
    private String nomSource; // Notre nom d'utilisateur
    private DatagramSocket socketReception; // Le socket permettant d'écouter portSource

    static private Scanner scanner=new Scanner(System.in);

    public DialogueUDP(String monNomUtilisateur, int monPort, int portDestinataire, InetAddress adresseDestinataire) {
        this.nomSource = nomDestinataire;
        try{
            this.socketReception = new DatagramSocket(monPort);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossible de créer socketReception lié au port"+monPort);
            System.exit(1);
        }
        this.adresseDestinataire = adresseDestinataire;
        this.portDestinataire = portDestinataire;
    }

    public void envoiMessage(boolean premierEnvoi){
        byte[] data=new byte[1000];
        String message = null;
        System.out.println(this.nomSource+">");
        if(premierEnvoi){
            message = nomSource;
            System.out.println(message);
        }
        else{
            message=scanner.nextLine();
        }
        DatagramPacket paquet = null;
        paquet = new DatagramPacket(data,data.length,adresseDestinataire,portDestinataire);

        DatagramSocket socket = null;
//        try{
//            socket = new DatagramSocket();
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
        try{
            socketReception.send(paquet);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossible d'envoyer le diagramme UDP !");
            System.exit(1);
        }
    }

    public void receptionMessage(boolean premierReception){
        byte[] data=new byte[1000];
        DatagramPacket paquet = null;
        paquet = new DatagramPacket(data,data.length);
        try{
            socketReception.receive(paquet);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problème lors de la réception d'un datagramme UDP");
            System.exit(1);
        }
        String message = null;
        message = new String(paquet.getData(),paquet.getLength());

        if(premierReception){
            this.nomDestinataire=message;
        }
        System.out.println(nomDestinataire+">"+message);
    }
    void dialogue(boolean premier){
        System.out.println("*** Dialogue ***");
        if(premier){
            envoiMessage(true);
            receptionMessage(true);
        }
        else{
            receptionMessage(true);
            envoiMessage(true);
        }
    }
    public static void main(String[] args) {
        if(args.length!=5){
            System.err.println("Le programme nécessite 5 arguments !");
            System.exit(1);
        }
        System.out.println("Arguments :");
        for(int i=0;i<5;i++){
            System.out.println(args[i]);
        }
        String monNom = args[0];
        int monPort = Integer.parseInt(args[1]);
        InetAddress adressDest = null;

        try{
            adressDest = InetAddress.getByName(args[2]);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
