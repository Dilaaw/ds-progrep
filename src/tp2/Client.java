package tp2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;

    public void connexion(InetAddress adresseServeur, int port){
        try{
            socket = new Socket(adresseServeur,port);
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Problème lors de la demande de connexion au serveur: "+adresseServeur+" "+port);
        }
    }

    public static void main(String[] args){
        if(args.length!=2){
            System.err.println("Le programme nécessite 2 arguments !");
        }
        System.out.println("Arguments: ");
        for (int i=0;i<2;i++){
            System.out.println(args[i]);
        }
        InetAddress adressServeur = null;

        try{
            adressServeur=InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Problème avec l'adresse IP : "+args[0]);
        }
        int portServeur = Integer.parseInt(args[1]);

        Client client=new Client();
        client.connexion(adressServeur,portServeur);
    }
}
