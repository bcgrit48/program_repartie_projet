package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Controller {
    private static final int NUM_CHANCES = 10;

    // numberGuess stores the randomly generated number that the user has to guess
    // chanceCounter counts the number of remaining chances the user has before the game is over
    private int numberGuess, chanceCounter;

    // xOffset and yOffset are used to calculate the frame's position relative to mouse movement (used for titleBar)
    private double xOffset, yOffset;

    @FXML
    private Button minBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private GridPane titleBar;

    @FXML
    private GridPane gameDisplay;

    @FXML
    private GridPane mainMenu;

    @FXML
    private Label chanceCounterLabel;

    @FXML
    private TextField userInputField;

    @FXML
    private ImageView backgroundImg;

    @FXML
    private Button tryAgainBtn;

    @FXML
    private Label hintLabel;

    public void initialize(){
        chanceCounter = NUM_CHANCES;
        chanceCounterLabel.setText("Tu as " + chanceCounter + " chances!");
    }

    // Méthode pour démarrer le jeu et recevoir un nombre du serveur
    public void handleStartAction(){
        mainMenu.setVisible(false);
        gameDisplay.setVisible(true);

        // Connexion au serveur pour récupérer le nombre à deviner
        try (Socket socket = new Socket("192.168.1.19", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String numberFromServer = in.readLine();
            numberGuess = Integer.parseInt(numberFromServer);

        } catch (Exception e) {
            e.printStackTrace();
            hintLabel.setText("Erreur lors de la connexion au serveur");
        }
    }

    public void handleSubmitAction(){
        int userGuess;

        try {
            // stores number from input field
            userGuess = Integer.parseInt(userInputField.getText().replace(" ", ""));

            if (userGuess == numberGuess) {
                // display success screen
                backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/success.png")));
                gameDisplay.setVisible(false);
                tryAgainBtn.setVisible(true);
            } else {
                // decrement chance counter and update label
                chanceCounter--;
                chanceCounterLabel.setText("Tu as " + chanceCounter + " chance" + (chanceCounter == 1 ? "" : "s") + " restantes");

                if (chanceCounter <= 0) {
                    // display game over screen
                    backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/gameover.png")));
                    gameDisplay.setVisible(false);
                    tryAgainBtn.setVisible(true);
                }
            }

            // update hint message
            if (userGuess < numberGuess) {
                hintLabel.setText("Trop Petit");
            } else {
                hintLabel.setText("Trop Grand");
            }

        } catch (Exception e) {
            hintLabel.setText("Ce n'est pas ça !");
        }
    }

    public void handleTryAgainAction(){
        backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/background.png")));
        gameDisplay.setVisible(true);

        // Connexion au serveur pour générer un nouveau nombre à deviner
        handleStartAction();

        chanceCounter = NUM_CHANCES;
        tryAgainBtn.setVisible(false);
        hintLabel.setText("");
        userInputField.setText("");
    }

    public void handleExitAction(){
        Platform.exit();
    }

    public void handleCloseAction(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void handleMinimizedAction(){
        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleTitleBarClickAction(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        event.consume();
    }

    public void handleTitleBarMovementAction(MouseEvent event){
        Scene scene = titleBar.getScene();
        Window window = scene.getWindow();
        window.setX(event.getScreenX() - xOffset);
        window.setY(event.getScreenY() - yOffset);
        event.consume();
    }
}
