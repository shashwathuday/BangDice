package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class IntroFXMLController implements Initializable {


    public int playerNum;


    @FXML
    private Slider playerNumSlider;
    @FXML
    private TextField playerNumberSelection;
    @FXML
    private Label playerNumLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerNum = 3;
        playerNumSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            playerNum = (int) playerNumSlider.getValue();
            playerNumLabel.setText("Number of Players: " + playerNum);
            Main.numPlayers = playerNum;
        });
    }

    @FXML
    void saloonUpdate(ActionEvent event) {
        Main.saloon = !Main.saloon;
    }

    @FXML
    void undeadUpdate(ActionEvent event) {
        Main.undead = !Main.undead;
    }

    @FXML
    public void playButton() {

        Main.begin = true;
        try {
            Main.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
