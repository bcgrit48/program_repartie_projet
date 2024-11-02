import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                // Attente de la connexion du Client A (celui qui soumet le nombre)
                Socket clientASocket = serverSocket.accept();
                System.out.println("Client A connecté");

                // Lecture du nombre soumis par Client A
                BufferedReader inputA = new BufferedReader(new InputStreamReader(clientASocket.getInputStream()));
                int numberToGuess = Integer.parseInt(inputA.readLine()); // Lire le nombre en tant que chaîne de caractères
                System.out.println("Nombre reçu de Client A: " + numberToGuess);

                // Attente de la connexion du Client B (celui qui devine le nombre)
                Socket clientBSocket = serverSocket.accept();
                System.out.println("Client B connecté");

                // Envoyer le nombre au Client B
                PrintWriter outB = new PrintWriter(clientBSocket.getOutputStream(), true);
                outB.println(numberToGuess);
                System.out.println("Nombre envoyé à Client B");

                // Fermeture des connexions pour cette session
                clientASocket.close();
                clientBSocket.close();
                System.out.println("Session terminée. En attente d'une nouvelle session...");

                // Le serveur continue à accepter de nouvelles connexions pour d'autres sessions
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la création du serveur ou de la communication avec les clients.");
            e.printStackTrace();
        }
    }
}
