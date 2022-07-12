/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dice;

import java.util.Random;

/**
 *
 * @author byrdh
 */
public class BlackDice extends Dice {

    public String[] faces;
public int rerolls = 3;
    public String face;
    
    public BlackDice() {
        faces = new String[]{"Whiskey", "Dynamite", "Arrow", "Gatling\nGun", "Duel", "Duel"};
        face = "Begin";
    }

    @Override
    public void roll() {
        rerolls -= 1;
        Random r = new Random();
        this.face = faces[r.nextInt(6)];
    }

    @Override
    public String getFace() {
        return this.face;
    }

    @Override
    public int getRerolls() {
        return rerolls;
    }

    @Override
    public void setRerolls(int n){
        rerolls = n;
    }

}
