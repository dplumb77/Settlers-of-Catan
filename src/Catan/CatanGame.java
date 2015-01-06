;/*
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
public class CatanGame {
    private Board board;
    private ArrayList<Player> players;
    private TurnPhase phase;
    private Player cp;
    private DCDeck DCdeck;
    private int PlayerNum;
    //current player   
    public CatanGame(){
        board = new Board();
        players = new ArrayList<Player>();
        phase = TurnPhase.START; 
        board = new Board();
        DCdeck = new DCDeck();
    }
    
    public void advancePhase(){
        if(phase == TurnPhase.START){
            phase = TurnPhase.ROLL;
            return;
        }
        if(phase == TurnPhase.ROLL){
            phase = TurnPhase.BUILD;
            return;
        }
        if(phase == TurnPhase.BUILD){
            phase = TurnPhase.TRADE;
            return;
        }
        phase = TurnPhase.ROLL;
        //TODO change current player
    }
    
    //Roll Phase actions: roll the dice and distribute resources
    public void rollPhase(int i){
        if(i==7){
            for(Player p : players){
                if(p.getTotalResourceCount()>7); // do something until players with more than 7 resources discard half their resources then have the current player place the robber 
            }
        }
        else board.tilePayout(i);
    }
    
    public void placeRobber(Tile t){
        board.placeRobber(t);
        //have the player take a random card from a player who has a settlement on that tile
    }
    
    //take a random card from one player's hand and add it to the current player's hand
    
    //Build Phase actions: Roads Small/Large Settlements DCs
    public void buildSmallSettlement(Corner c){
        if(cp.canMakeSmallSettlement()==true){
            if(board.canPlaceSettlement(cp,c)){
                board.placeSettlement(cp,c);
            }
        }
        cp.makeSmallSettlement();       
    }
    
    public boolean buildLargeSettlement(Corner c){
        if(cp.canMakeLargeSettlement()==true){
            if(c.getSettlement()==Settlement.SMALL && c.getOwner() == cp){
                board.placeSettlement(cp,c);
                cp.makeLargeSettlement();
                return true;
            }
            else return false;
        }       
        return false;
    }
    
    public void buildRoad(Edge e){
        if(cp.canMakeRoad()==true){
            board.placeRoad(cp,e);
            cp.makeRoad();
            board.calculateLongestRoad();
        }
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public void setPlayerNum(int PlayerNum) {
        this.PlayerNum = PlayerNum;
        for(int i=0; i<PlayerNum; i++) {
            this.players.add(new Player());
        }
    }
    
    public Board getBoard() {
        return board;
    }
    
    //DC
    
    //Trade Phase actions: ports, interact with other players
    
    
    
}
