/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dice;

/**
 *
 * @author byrdh
 */
public abstract class Dice {

    public abstract void roll();

    public abstract String getFace();
    public abstract int getRerolls();
    public abstract void setRerolls(int n);

    public Dice() {

    }


}
