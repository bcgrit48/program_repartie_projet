package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Controller {
    // Nombre aléatoire que le client A doit envoyer au serveur
    private int numberToGuess;

    // xOffset and yOffset are used to calculate the frame's position relative to mouse movement (used for titleBar)
    private double xOffset, yOffset;

    @FXML
    private Button minBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private GridPane titleBar;

    @FXML
    private GridPane mainMenu;

    @FXML
    private ImageView backgroundImg;

    @FXML
    private Button startGameBtn;

    @FXML
    private Label hintLabel;

    public void initialize() {
        // Rien à initialiser pour le moment
    }

    // Méthode pour démarrer le jeu et générer un nombre
    public void handleStartAction() {
        mainMenu.setVisible(false);

        // Génération du nombre aléatoire
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;  // Génère un nombre entre 1 et 100
        System.out.println("Nombre généré par Client A: " + numberToGuess);

        // Connexion au serveur pour envoyer le nombre
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
            // Envoi du nombre au serveur
            out.println(numberToGuess);
            hintLabel.setText("Le nombre a été envoyé au serveur");
        } catch (Exception e) {
            e.printStackTrace();
            hintLabel.setText("Erreur lors de l'envoi au serveur");
        }
    }

    public void handleExitAction() {
        Platform.exit();
    }

    public void handleCloseAction() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void handleMinimizedAction() {
        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleTitleBarClickAction(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        event.consume();
    }

    public void handleTitleBarMovementAction(MouseEvent event) {
        Scene scene = titleBar.getScene();
        Window window = scene.getWindow();
        window.setX(event.getScreenX() - xOffset);
        window.setY(event.getScreenY() - yOffset);
        event.consume();
    }
}
