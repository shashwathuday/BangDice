package Characters;

import java.util.ArrayList;
import Main.*;

public class Player {

    public String role;
    private int hp;
    private String name;
    public int arrows;
    public int indianArrow;
    public int rolls;
    public static String[] characters = {"Calamity Janet", "Jesse Jones", "Jourdonnais",
        "Paul Regret", "Rose Doolan", "Suzy Lafayette", "Vulture Sam",
        "Apache Kid", "Bill Noface",
        "Belle Star", "Greg Digger"};
    public static int[] allBaseHp = {8, 9, 7, 9, 9, 8, 9, 9, 9, 8, 7};

    public static ArrayList<Player> generatePlayers(int n, boolean saloon, boolean undead){
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> roleList = new ArrayList<>();

        for(int i = 0;i<7;i++){
            nameList.add(characters[i]);
        }
        if(saloon){
            nameList.add(characters[7]);
            nameList.add(characters[8]);
        }
        if(undead){
            nameList.add(characters[9]);
            nameList.add(characters[10]);
        }

        if(n >= 3){
            roleList.add("Sheriff");
            roleList.add("Renegade");
            roleList.add("Outlaw");
        }
        if(n >= 4){
            roleList.add("Outlaw");
        }
        if(n >= 5){
            roleList.add("Deputy");
        }
        if(n >= 6){
            roleList.add("Outlaw");
        }
        if(n >= 7){
            roleList.add("Deputy");
        }
        if(n==8){
            roleList.add("Renegade");
        }


        while(roleList.size()>0){
            int characterInd = (int)(Math.random()*nameList.size());
            int roleInd = (int)(Math.random()*roleList.size());
            players.add(new Player(nameList.get(characterInd), roleList.get(roleInd)));
            nameList.remove(characterInd);
            roleList.remove(roleInd);
        }

        return players;
    }

    public String playerInfo(){
        String s = "";
            if (this.role == "Sheriff") {
                s += "Sheriff\n" + this.getName() + "\n";
            } else {
                s += this.getName() + "\n";
            }
        s += "Bullets: "+this.getHp() + "\n";
        s += "Arrows: "+this.arrows + "\n";
        if(Main.saloon){
            s += "Indian Chief Arrow: "+this.arrows + "\n";
        }
        return s;
    }

    public Player(String name, String role) {
        this.role = role;
        arrows = 0;
        rolls = 3;
        indianArrow = 0;
        for (int i = 0; i < characters.length; i++) {
            if (name.equals(characters[i])) {
                if (role.equals("Sheriff")) {
                    this.name = name;
                    hp = allBaseHp[i] + 2;
                } else {
                    this.name = name;
                    hp = allBaseHp[i];
                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void one(){
        hp -= 1;
    }
    public void two(){

    }
    public void beer(){

    }

    public void setHp(int hp) {
        int maxHp = 0;
        for(int i = 0;i<characters.length;i++){
            if (this.getName() == characters[i]) {
                maxHp = allBaseHp[i];
            }
        }
        if(hp<=maxHp){
            this.hp = hp;
        }

    }

    public void dynamite() {
        hp -= 1;
    }

    public void gatling() {
        if (name.equals("Paul Regret")) {

        } else {
            hp -= 1;
        }
    }

    public void indianAttack() {
        if (role.equals("Jourdonnais")) {
            if (arrows > 0) {
                hp -= arrows-1;
            }
        }else{
            hp -= arrows;
        }
        arrows = 0;
    }
}