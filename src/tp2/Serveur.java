package tp2;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private ServerSocket serverSocket;

    public void enregistrementService(int port) {
        try{
            serverSocket = new ServerSocket(port,4);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossible de créer le serveur socket !");
            System.exit(1);
        }
    }

    private Socket nouvelleConnexion(){
        Socket socket = null;
        try{
            socket = serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problème lors de l'attente d'une nouvelle connexion");
        }
        System.out.println("Nouvelle connexion réalisée !");
        return socket;
    }
    public static void main(String[] args) {
        if(args.length!=5){
            System.err.println("Le programme nécessite 5 arguments !");
            System.exit(1);
        }
        int nomPort = Integer.parseInt(args[0]);
        System.out.println("Numéro de port à utiliser "+nomPort);

        Serveur serveur = new Serveur();
        serveur.enregistrementService(nomPort);
        Socket socket = new Socket();
    }
}
