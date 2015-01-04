/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class DCDeck {
    private ArrayList<DC> deck = new ArrayList<>();
    
    public DCDeck(){
        
        //add cards to deck
        for(int i = 0; i<14; i++){
            deck.add(DC.ARMY);
        }
        for(int i=0;i<5;i++){
            deck.add(DC.VP);
        }
        deck.add(DC.MONOPOLY);
        deck.add(DC.YoP);
        deck.add(DC.YoP);
        deck.add(DC.YoP);
        deck.add(DC.YoP);
        deck.add(DC.YoP);
        
        //shuffle deck
        Random r = new Random();
        int n = deck.size();
        int r1;
        int r2;
        DC tmp;
        for(int i=0;i<50;i++){
            r1 = r.nextInt(n);
            r2 = r.nextInt(n);
            tmp = deck.get(r1);
            deck.set(r1,deck.get(r2));
            deck.set(r2,tmp);
        }
    }
    
    public DC drawCard(){
        DC dc = deck.get(0);
        deck.remove(0);
        return dc;
    }
}
