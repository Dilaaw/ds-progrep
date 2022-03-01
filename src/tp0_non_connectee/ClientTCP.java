package tp0_non_connectee;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {

    public static void main(String[] args) {

        System.out.println("--- client ---");

        //demande de connexion et connexion au serveur

        System.out.println("--- connexion au serveur... ---");

        Socket socket = null;
        InetAddress adresseServeur = null;

        try {
            adresseServeur = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            socket = new Socket(adresseServeur, 6000);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("la connexion est Ã©tablie !");

        //on prepare flux d'entree et sortie

        InputStream input = null;

        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        BufferedReader entree = new BufferedReader(new InputStreamReader(input));
        OutputStream output = null;

        try {
            output = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        PrintWriter sortie = new PrintWriter(output);

        // echange de donne

        sortie.println("je suis le client !");
        sortie.flush();

        try {
            System.out.println(entree.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //fermeture de la connexion

        sortie.close();

        try {
            entree.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}