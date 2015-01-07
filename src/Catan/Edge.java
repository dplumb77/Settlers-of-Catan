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
    
    //used for removing duplicate edges during edge initialization
    public boolean sharesCorners(Edge e){
        ArrayList<Corner> coordlist = e.getConnectingCorners();
        if(connectingcorners.contains(coordlist.get(0))&& connectingcorners.contains(coordlist.get(1))) return true;
        return false;
    }

    public boolean canPlaceRoad(Player p){
        for(Corner c: connectingcorners){
            if(c.getOwner()==p) return true;
            for(Edge e: c.getRoads()){
                if(e.getOwner()==p) return true;
            }
        }
        return false;
    }
    public void placeRoad(Player p){
        hasroad = true;
        owner = p;
    }
    
    public Player getOwner(){
        return owner;
    }
    
}
