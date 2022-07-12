/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Characters.Player;
import Dice.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

import javax.management.timer.Timer;
import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author byrdh
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane base;
    @FXML
    private Label diceLabel1;
    @FXML
    private Label diceLabel2;
    @FXML
    private Label diceLabel3;
    @FXML
    private Label diceLabel4;
    @FXML
    private Label diceLabel5;
    @FXML
    private CheckBox diceCheck1;
    @FXML
    private Label rerollLabel1;
    @FXML
    private CheckBox diceCheck2;
    @FXML
    private Label rerollLabel2;
    @FXML
    private CheckBox diceCheck3;
    @FXML
    private Label rerollLabel3;
    @FXML
    private CheckBox diceCheck4;
    @FXML
    private Label rerollLabel4;
    @FXML
    private CheckBox diceCheck5;
    @FXML
    private Label rerollLabel5;
    @FXML
    private HBox characterPane;
    @FXML
    private Label rollsLabel;

    @FXML
    private Button npcButton1;
    @FXML
    private Button npcButton2;
    @FXML
    private Button npcButton3;
    @FXML
    private Button npcButton4;
    @FXML
    private Button npcButton5;
    @FXML
    private Button npcButton6;
    @FXML
    private CheckBox loudmouthCheck;
    @FXML
    private CheckBox cowardCheck;
    @FXML
    private Button playerButton;
    @FXML
    private Label playerHelpBox;
    @FXML
    private Button resolveButton;

    public ArrayList<Integer> choicesIndex = new ArrayList<Integer>();
    public static ArrayList<Dice> diceHand = new ArrayList<>();
    public ArrayList<Label> diceLabels = new ArrayList<>();
    public ArrayList<Label> rerollLabels = new ArrayList<>();
    public ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    public ArrayList<Button> infoButtons = new ArrayList<>();
    public int arrowPile = 9;

    public ArrayList<Player> players = new ArrayList<Player>();

    public int playerChoice = -1;
    public boolean choiceAvailable = false;

    public int turn = 0; //Index of player who's turn it is

    public boolean[] diceToReroll = new boolean[5];

    public boolean loudMouth, coward, undead;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        players = Player.generatePlayers(Main.numPlayers, Main.saloon, Main.undead);


        diceLabels.add(diceLabel1);
        diceLabels.add(diceLabel2);
        diceLabels.add(diceLabel3);
        diceLabels.add(diceLabel4);
        diceLabels.add(diceLabel5);

        rerollLabels.add(rerollLabel1);
        rerollLabels.add(rerollLabel2);
        rerollLabels.add(rerollLabel3);
        rerollLabels.add(rerollLabel4);
        rerollLabels.add(rerollLabel5);

        checkBoxes.add(diceCheck1);
        checkBoxes.add(diceCheck2);
        checkBoxes.add(diceCheck3);
        checkBoxes.add(diceCheck4);
        checkBoxes.add(diceCheck5);

        Arrays.fill(diceToReroll, false);
        Button[] test = {playerButton ,npcButton1, npcButton2, npcButton3, npcButton4, npcButton5, npcButton6};
        for(int i = 0;i<players.size();i++){
            infoButtons.add(test[i]);
        }
        if(undead){

        }
        updatePlayerInfo();

        playerTurn();
    }

    public void playerTurn(){
        playerHelpBox.setText("It's your turn, please begin rolling the die.");
        rollsLabel.setText("Total Rolls Left: "+players.get(0).rolls);
    }

    public void updatePlayerInfo() {
        for (int i = players.size()-1; i >=0; i--) {
            if(players.get(i).getHp()<=0){
                infoButtons.get(i).setText(players.get(i).getName() + " the " + players.get(i).role + " is dead.");
                players.remove(i);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            infoButtons.get(i).setText(players.get(i).playerInfo());
        }
    }

    @FXML
    private void checkMark(ActionEvent ac) {
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (ac.getSource() == checkBoxes.get(i)) {

                diceToReroll[i] = !diceToReroll[i];
                break;
            }
        }

    }

    @FXML
    private void chooseDice(ActionEvent ac) {
        if (ac.getSource().equals(loudmouthCheck)) {
            if (loudmouthCheck.isSelected()) {
                loudMouth = true;
                coward = false;
                cowardCheck.setSelected(false);
            } else {
                loudMouth = false;
            }
        } else if (ac.getSource().equals(loudmouthCheck)) {
            if (cowardCheck.isSelected()) {
                coward = true;
                loudMouth = false;
                loudmouthCheck.setSelected(false);
            } else {
                coward = false;
            }

        }

    }


    @FXML
    void resolveDie(ActionEvent event) {
        int one = 0;
        int two = 0;
        int beer = 0;

        for(int i = 0;i<diceHand.size();i++){
            if(diceHand.get(i).getFace().equals("One")){
                one ++;
            }
            if(diceHand.get(i).getFace().equals("Two")){
                two ++;
            }
            if(diceHand.get(i).getFace().equals("Beer")){
                beer ++;
            }
        }

        for(int i = 0;i<one;i++){
            chooseOne();
        }
        for(int i = 0;i<two;i++){
            chooseTwo();
        }
        for(int i = 0;i<beer;i++){
            chooseBeer();
        }

        diceHand.clear();
        players.get(0).rolls = 3;
    }

    private void checkDice(){
        //ArrowCheck
        int arrowNum = faceNum("Arrow");
        for(int i = 0;i<arrowNum;i++) {
            players.get(turn).arrows ++;
            if(arrowPile==0){
                for(int j = 0; j < players.size(); j++){
                    players.get(j).indianAttack();
                    arrowPile = 9;
                }
            }
        }

        //Dynamite Check
        int dynamiteNum = faceNum("Dynamite");
        for(int i = 0;i<diceHand.size();i++){
            if(diceHand.get(i).getFace() == "Dynamite") {
                diceHand.get(i).setRerolls(0);
            }
        }
        if(dynamiteNum>=3){
            players.get(turn).dynamite();
                    if(turn == players.size()-1){
                        turn = 0;
                    }else{
                        turn++;
                    }
        }


    }

    public int faceNum(String face){
        int num = 0;

        for(int i = 0;i<diceHand.size();i++) {
            if (diceHand.get(i).getFace() == face) {
                num++;
            }
        }
            return num;
    }

    public void chooseOne() {
        choicesIndex.clear();
        playerHelpBox.setText("Choose one of the highlighted players to shoot.");
        int index1 = players.size()-1;
        int index2 = 1;

        if(index1<0){
            index1 = players.size();
        }else if(index1> players.size()){
            index1 = 0;
        }
        choicesIndex.add(index1);

        if(index2<0){
            index2 = players.size();
        }else if(index2> players.size()){
            index2 = 0;
        }
        Player victim1 = players.get(index1);
        Player victim2 = players.get(index2);

        for(int i = 0;i<players.size();i++){
            if(i != index1 && i != index2){
                infoButtons.get(i).disarm();
            }
        }
        choicesIndex.add(index2);

        JFrame f= new JFrame("Choose");
        JLabel label = new JLabel("Choose a player to shoot");
        f.add(label);
        JPopupMenu popupmenu = new JPopupMenu();
        label.add(popupmenu);
        popupmenu.setLayout(new FlowLayout(FlowLayout.CENTER));
        ArrayList<JMenuItem> choices = new ArrayList<JMenuItem>();
        choices.add(new JMenuItem((players.get(index1).getName())));
        choices.add(new JMenuItem((players.get(index2).getName())));

        for(int i = 0;i<choices.size();i++) {
            choices.get(0).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    playerChoice = choicesIndex.get(choices.indexOf(e.getSource()));
                    f.setVisible(false);
                }
            });
        }

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                popupmenu.show(f,e.getX(), e.getY());
            }
        });
        f.add(popupmenu);
        f.setLocationRelativeTo(null);
        popupmenu.setVisible(true);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);

        Thread waitingThread = new Thread()
        {
            public void run()
            {
                while (f.isVisible())
                {
                    try {
                        Thread.sleep(250);
                        System.out.println("Check");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        waitingThread.start();
        try {
            waitingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        players.get(playerChoice).one();
        playerChoice = -1;
        infoButtons.get(index1).arm();
        infoButtons.get(index2).arm();

        updatePlayerInfo();
    }

    public void chooseTwo(){
        playerHelpBox.setText("Choose one of the highlighted players to shoot.");
        int index1 = players.size()-2;
        int index2 = 2;

        if(index1<0){
            index1 = players.size()+index1;
        }else if(index1> players.size()){
            index1 = 0+index1-players.size();
        }

        if(index2<0){
            index2 = players.size();
        }else if(index2> players.size()){
            index2 = 0;
        }
        Player victim1 = players.get(index1);
        Player victim2 = players.get(index2);

        for(int i = 0;i<players.size();i++){
            if(i != index1 && i != index2){
                infoButtons.get(i).disarm();
            }
        }
        choiceAvailable = true;
        while(playerChoice==-1){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        players.get(playerChoice).one();
        playerChoice = -1;
        infoButtons.get(index1).arm();
        infoButtons.get(index2).arm();

        updatePlayerInfo();
    }

    public void chooseBeer(){
            playerHelpBox.setText("Choose a player to heal.");
            choiceAvailable = true;
            while(playerChoice==-1){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }
            players.get(playerChoice).beer();
            playerChoice = -1;

            updatePlayerInfo();
    }

    @FXML
    private void rollButton(ActionEvent ac) {
        if(turn == 0 && players.get(0).rolls>0) {
            if (diceHand.isEmpty()) {
                diceHand.add(new WhiteDice());
                diceHand.add(new WhiteDice());

                if (loudMouth) {
                    diceHand.add(new LoudMouth());
                } else if (coward) {
                    diceHand.add(new Coward());
                } else {
                    diceHand.add(new WhiteDice());
                }

                if (undead) {
                    diceHand.add(new BlackDice());
                    diceHand.add(new BlackDice());
                } else {
                    diceHand.add(new WhiteDice());
                    diceHand.add(new WhiteDice());
                }
                checkDice();
                for (int i = 0; i < diceHand.size(); i++) {
                    diceHand.get(i).roll();
                    diceLabels.get(i).setText(diceHand.get(i).getFace());
                    rerollLabels.get(i).setText("Rerolls Left: " + diceHand.get(i).getRerolls());
                }

                players.get(0).rolls --;
                rollsLabel.setText("Total Rolls Left: "+players.get(0).rolls);
                updatePlayerInfo();
            } else {

                for (int i = 0; i < diceHand.size(); i++) {
                    if (diceToReroll[i] && diceHand.get(i).getRerolls() > 0) {
                        diceHand.get(i).roll();
                        diceLabels.get(i).setText(diceHand.get(i).getFace());
                        rerollLabels.get(i).setText("Rerolls Left: " + diceHand.get(i).getRerolls());
                    }
                }
                checkDice();
                players.get(0).rolls --;
                rollsLabel.setText("Total Rolls Left: "+players.get(0).rolls);
                updatePlayerInfo();
            }
        }


    }

    @FXML
    private void playerPick(ActionEvent ac){
        if(choiceAvailable){
                for(int i = 0;i<infoButtons.size();i++){
                    if(ac.getSource().equals(infoButtons.get(i))){
                        playerChoice = i;
                    }
                }
            }
        }



}
