/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Edge {
    private boolean hasroad;
    private Player owner;
    private ArrayList<Corner> connectingcorners = new ArrayList<>();
    
    public Edge(Corner c1, Corner c2){
        hasroad = false;
        connectingcorners.add(c1);
        connectingcorners.add(c2);
    }
    
    public ArrayList<Corner> getConnectingCorners(){
        return connectingcorners;
    }
    
    public void setRoad(Player p){
        hasroad = true;
        owner = p;
    }
    
    public Player getOwner(){
        return owner;
    }
    
}
