package Characters;

import Main.*;
import javax.management.relation.Role;
import java.util.ArrayList;

public class NPC {
/*
    public Role role;

    public void one_shot(int i, ArrayList<Player> players){ //When they roll a (1)
        //i is the index of the player who's turn it is. The player currently rolling die. You don't have to write the dice rolling parts, I got that.


        Player shooter = players.get(i);

        //These are the indexes of players in the ArrayList players who can be hit.
        int p1 = i-1;
        int p2 = i+1;

        //Checks to see if the index is outside of the bounds and corrects it if it is. The players play in the order of the array.
        if(p1<0){
            p1 = players.size();
        }else if(p1> players.size()){
            p1 = 0;
        }

        if(p2<0){
            p2 = players.size();
        }else if(p2> players.size()){
            p2 = 0;
        }
        Player victim1 = players.get(p1);
        Player victim2 = players.get(p2);

        //Deputy will never shoot the sheriff
        if(shooter.role == "Deputy"){
            if(victim1.role == "Sheriff"){
                victim2.one();//This isnt a method yet, but I will be adding it my player class soon. It means that player gets shot by the (1) die
            }
            if(victim2.role == "Sheriff"){
                victim1.one();
            }
        }

        //Outlaws and Renegades will always attack the sheriff, unless the sheriff is dead.
        if(shooter.role == "Outlaw"){
            if(victim1.role == "Sheriff"){
                victim1.one();
            }
            if(victim2.role == "Sheriff"){
                victim2.one();
            }
        }if(shooter.role == "Renegade"){
            if(victim1.role == "Sheriff"){
                victim1.one();
            }
            if(victim2.role == "Sheriff"){
                victim2.one();
            }
        }

    }




*/





}
