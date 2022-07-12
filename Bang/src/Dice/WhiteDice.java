package Dice;

import java.util.Random;

public class WhiteDice extends Dice {

    public String[] faces;
    public int rerolls = 3;
    public String face;

    
    public WhiteDice() {
        faces = new String[]{"One", "Two", "Dyna-\nmite", "Arrow", "Gatling\nGun", "Beer"};
        face = "Begin";
    }

    @Override
    public void roll() {
        Random r = new Random();
        rerolls -= 1;
        this.face = faces[r.nextInt(5)];
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
