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
    private ResourceDeck Rdeck;
    private int PlayerNum;
    private int playerpos;
    private int turn;
 
    
    public CatanGame(){
        board = new Board();
        players = new ArrayList<Player>();
        phase = TurnPhase.START; 
        board = new Board();
        DCdeck = new DCDeck();
        playerpos = 0;
        turn = 0;
    }
 
    //
    public void advancePhase(){
        if(phase == TurnPhase.START){
            phase = TurnPhase.ROLL;
            turn++;
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
        playerpos++;
        playerpos%=players.size();
        cp = players.get(playerpos);
        if(playerpos==0) turn++;
    }
    
    /*
        Start phase methods:
            void freeSettlement(Player p, Corner c) - checks for valid placement and places settlement if successful. if it's the second free settlement give resources.
            void freeRoad(Player p, Corner c) - checks for valid placement and places road if successful.
    */
    
        public void freeSettlement(Player p, Corner c, boolean b){
            if(c.canPlaceFreeSettlement()==true) c.placeSettlement(Settlement.SMALL, p);
            if(b==true) c.freePayout();
        }
        
        public void freeRoad(Player p, Edge e){
            if(e.canPlaceRoad(p)==true) e.placeRoad(p);
        }
    
    /*
        Roll phase methods:
            void boardPayout(int i) - has the board payout resources for the dice roll
            void moveRobber(Tile t) - moves the robber to the selected tile.
            void stealResource(Player p) - takes a random resource from the selected player's hand and adds it to the current player's hand. 
    */
    public void boardPayout(int i){
        board.tilePayout(i,Rdeck);
    }
    
    public void moveRobber(Tile t){
        board.moveRobber(t);
    }
    
   public void stealResource(Player p){
       cp.addResource(p.takeRandomResource(),1);
   }
   
   public void discardResource(Player p, Resource r){
       p.removeResource(r, 1);
       Rdeck.add(r, 1);
   }

   
   /*
       Build phase methods:
           boolean buildSmallSettlement(Corner c) - checks to see if the player can build on the given corner and does so if successful.
           boolean buildLargeSettlement(Corner c) - checks to see if the player can build on the given corner and does so if successful.
           boolean buildRoad(Edge e) - checks to see if the player can build on the given edge and does so if successful.
           boolean buildDC() - checks to see if the player can build a DC and adds a DC to the player's DC hand if successful.         
   */
    public boolean buildSmallSettlement(Corner c){
        if(cp.canMakeSmallSettlement()==true){
            if(board.canPlaceSettlement(cp,c)){
                board.placeSettlement(cp,c);
                cp.makeSmallSettlement();
                Rdeck.add(Resource.WOOD,1);
                Rdeck.add(Resource.CLAY,1);
                Rdeck.add(Resource.WHEAT,1);
                Rdeck.add(Resource.SHEEP,1);
                return true;
            }
        }
        return false;       
    }
    
    public boolean buildLargeSettlement(Corner c){
        if(cp.canMakeLargeSettlement()==true){
            if(c.getSettlement()==Settlement.SMALL && c.getOwner() == cp){
                board.placeSettlement(cp,c);
                cp.makeLargeSettlement();
                Rdeck.add(Resource.ROCK, 3);
                Rdeck.add(Resource.WHEAT,2);
                return true;
            }
        }       
        return false;
    }
    
    public boolean buildRoad(Edge e){
        if(cp.canMakeRoad()==true){
            board.placeRoad(cp,e);
            cp.makeRoad();
            Rdeck.add(Resource.CLAY,1);
            Rdeck.add(Resource.WOOD,1);
            return true;
        }
        return false;
    }
    
    public boolean buildDC(){
        if(cp.canMakeDC()==true){
           cp.makeDC(DCdeck.drawCard());
           Rdeck.add(Resource.ROCK, 1);
           Rdeck.add(Resource.SHEEP, 1);
           Rdeck.add(Resource.WHEAT,1);
        }
        return false;
    }
    
    /*
        TODO: Trade Phase methods:
            -trade resources between players
            -use ports to swap resources
            -probably can just use Player.addResource() and Player.removeResource()
    */

    /*
        DC methods:
            void ArmyDC(Tile t)
            void monopolyDC(Resource r)
            void YoPDC(Resource r1, Resource r2)
                -probably can just replace with two Player.addResource() calls
    */
    public void ArmyDC(Tile t){
        cp.addArmy(1);
        board.moveRobber(t);
    }
    
    public void monopolyDC(Resource r){
        for(Player p: players){
            if(p!=cp){
                cp.addResource(r, p.getResource(r));
                p.removeResource(r, p.getResource(r));
            }
        }
    }
    
    public void YoPDC(Resource r1, Resource r2){
        cp.addResource(r1,1);
        cp.addResource(r2,1);
    }

    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public Player getCurrentPlayer(){
        return cp;
    }
    
    public int getTurn(){
        return turn;
    }
    
    public ArrayList<Tile> getTiles(){
        return board.getTiles();
    }
    
    public ArrayList<Edge> getEdges(){
        return board.getEdges();
    }
    
    public ArrayList<Corner> getCorners(){
        return board.getCorners();
    }
    
    public void setPlayerNum(int PlayerNum) {
        this.PlayerNum = PlayerNum;
        for(int i=0; i<PlayerNum; i++) {
            this.players.add(new Player());
        }
    }
    
    public Board getBoard(){
        return board;
    }
    
    public boolean hasWinner(){
        for(Player p: players){
            if(p.calculateScore()>=10) return true;
        }
        return false;
    }
    
    public Player getWinner(){
        Player player = new Player();
        for(Player p: players){
            if(p.calculateScore()>=10) player = p;
        }
        return player;
    }
}
