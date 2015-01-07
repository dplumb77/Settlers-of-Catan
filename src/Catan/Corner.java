
package Catan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ha
 * 
 */
public class Corner {
    
    private Coordinate coords;
    private List<Tile> adjacenttiles = new ArrayList<>();
    private List<Corner> adjacentcorners = new ArrayList<>();
    private Settlement settlement;
    private Port port;
    private Player owner;
    private List<Edge> roads = new ArrayList<>();
    boolean drawnSettlement;
    
    
    public Corner(Coordinate coordinates){
        coords = coordinates;
        settlement = Settlement.NONE;
        port = Port.NONE;
    }
    
    
    /*
        Adjacent stuff methods:
            void fillAdjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist)
                -called once during board creation to fill adjacent tiles/corners with tile/corner objects
                -maybe need to add edges too
            boolean isAdjacent(Corner c)
    */
    
    public void fillAdjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist){
        int x = coords.getx();
        int y = coords.gety();
        
        //tiles
        Coordinate tmpcoord;
        for(Tile t: tilelist){
            tmpcoord = t.getCoords();
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==y+1) adjacenttiles.add(t);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==y-1) adjacenttiles.add(t);
            if(x%2==y%2){
                if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==y) adjacenttiles.add(t);
            }
            else{
                if(tmpcoord.getx()==(x-1)&&tmpcoord.gety()==y) adjacenttiles.add(t);                
            }
                       
        }
        
        //corners
        int i;
        for(Corner c: cornerlist){
            tmpcoord = c.getCoords();
            if(x%2==y%2){
                i = ((x+y)/2)-2;
                if(tmpcoord.getx()==(x-1)&&tmpcoord.gety()==i) adjacentcorners.add(c);
                if(tmpcoord.getx()==(x)&&tmpcoord.gety()==i) adjacentcorners.add(c);
                if(tmpcoord.getx()==(x)&&tmpcoord.gety()==(i+1)) adjacentcorners.add(c); 
            }
            else{
                i = ((x+y)/2)-1;                
                if(tmpcoord.getx()==(x)&&tmpcoord.gety()==i) adjacentcorners.add(c);
                if(tmpcoord.getx()==(x-1)&&tmpcoord.gety()==(i)) adjacentcorners.add(c);
                if(tmpcoord.getx()==(x-1)&&tmpcoord.gety()==(i-1)) adjacentcorners.add(c); 
            }          
        }                
    }
    
    public boolean isAdjacent(Corner c){
        if(adjacentcorners.contains(c) == true) return true;
        return false;
    }

    
    /*
        Settlement methods:
            boolean canPlaceSettlement(Player p)
            boolean canPlaceFreeSettlement(Player p)
                -like canPlaceSettlement() but doesnt check for connecting roads
                -only called during start phase
            void placeSettlement(Settlement s, Player p)
                -sets owner and settlement type
                -can be used to place small or large settlements
                -also adds port to players portlist
    */
    
    public boolean canPlaceSettlement(Player p){
        
        //if there is a settlement on this corner or any adjacent corners return false
        if(settlement!= Settlement.NONE) return false;
        for(Corner c : adjacentcorners){
                if(c.getSettlement()!=Settlement.NONE) return false;
        }
        
        //if there is a road leading to the corner owned by the player return true
        for(Edge e : roads){
            if(e.getOwner()==p) return true;
        }        
        return false;
    }
    
    public boolean canPlaceFreeSettlement(){
        
        //if there is a settlement on this corner or any adjacent corners return false
        if(settlement!= Settlement.NONE) return false;
        for(Corner c : adjacentcorners){
                if(c.getSettlement()!=Settlement.NONE) return false;
        }       
        return true;
    }

    public void placeSettlement(Settlement s, Player p){
        owner = p;
        settlement = s;
        if(port!=Port.NONE) p.addPort(port);
    }    

    
    /*
        void freePayout()
            -only called during start phase to give player its starting resources
    */
    
    public void freePayout(){
        for(Tile t: adjacenttiles){
            owner.addResource(t.getResource(), 1);
        }
    }
    
    
    public boolean getDrawnSettlement() {
        return drawnSettlement;
    }
    
    public void setDrawnSettlement(boolean b) {
        drawnSettlement = b;
    }
    public Coordinate getCoords(){
        return coords;
    }
    
    public List<Corner> getAdjacentCorners(){
        return adjacentcorners;
    }
    
    public List<Tile> getAdjacentTiles(){
        return adjacenttiles;
    }
    
    public Settlement getSettlement(){
        return settlement;
    }

    public Port getPort(){
        return port;
    }
    
    public void setPort(Port p){
        port = p;
    }
    
    public Player getOwner(){
        return owner;
    }
    
    public List<Edge> getRoads(){
        return roads;
    }
    
    public void addRoad(Edge e){
        roads.add(e);
    }
}    


      
