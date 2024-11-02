import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                try {
                    // Attente de la connexion du client
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connecté");

                    // Générer un nombre aléatoire entre 0 et 100
                    Random random = new Random();
                    int randomNumber = random.nextInt(101);

                    // Envoyer le nombre au client
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println(randomNumber);

                    // Fermer la connexion avec le client
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Erreur lors de la communication avec le client.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du serveur.");
            e.printStackTrace();
        }
    }
}