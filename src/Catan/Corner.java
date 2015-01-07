
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
    
    private int x;
    private int y;
    private Coordinate coords;
    private List<Coordinate> adjacentcornercoords = new ArrayList<>();
    private List<Coordinate> adjacenttilecoords = new ArrayList<>();
    private List<Tile> adjacenttiles = new ArrayList<>();
    private List<Corner> adjacentcorners = new ArrayList<>();
    private Settlement settlement;
    private Port port;
    private Player owner;
    private List<Edge> roads = new ArrayList<>();
    boolean drawnSettlement;
    
    
    public Corner(Coordinate coordinates, ArrayList<Coordinate> validtiles, ArrayList<Coordinate> validcorners){
        coords = coordinates;
        x = coords.getx();
        y = coords.gety();
        settlement = Settlement.NONE;
    }
    
    public void filladjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist){
        //adjacent tiles
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
        
        //adjacent corners
        int i = ((y+2)*2)-x;
        for(Corner c: cornerlist){
            tmpcoord = c.getCoords();
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==i) adjacenttiles.add(t);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==(i-1)) adjacenttiles.add(t);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==(i-2)) adjacenttiles.add(t);            
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==i) adjacenttiles.add(t);           
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==(i-1)) adjacenttiles.add(t);                       
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==(i-2)) adjacenttiles.add(t); 
        }        
    }
    
    public Coordinate getCoords(){
        return coords;
    }
    
    public List<Corner> getAdjacentCorners(){
        return adjacentcorners;
    }
    
    public List<Tile> getAdjacenttiles(){
        return adjacenttiles;
    }
    
    public Settlement getSettlement(){
        return settlement;
    }
    
    public boolean isAdjacent(Corner c){
        if(adjacentcorners.contains(c) == true) return true;
        return false;
    }
    
    public Port getPort(){
        return port;
    }
    
    public Player getOwner(){
        return owner;
    }
    
    public void setOwner(Player p){
        owner = p;
    }
    
    public boolean canPlaceSettlement(Player p){
        
        //if the owner field is filled then there is already a settlement there, idk how to do that
        //if(this.getOwner()!=false) return false;
        
        //if there is a settlement within two tiles then the tile is not settleable
        for(Corner c : adjacentcorners){
            for(Corner corner : c.adjacentcorners){
                if(corner.getSettlement()!=Settlement.NONE) return false;
            }
        }
        
        //if there is a road leading to the corner owned by the player then it is settleable
        for(Edge e : roads){
            if(e.getOwner()==p) return true;
        }        
        return false;
    }
    
    public void createSettlement(Settlement s, Player p){
        owner = p;
        settlement = s;
    }
    
    public boolean getDrawnSettlement() {
        return drawnSettlement;
    }
    
    public void setDrawnSettlement(boolean b) {
        drawnSettlement = b;
    }
    
    public List<Corner> getAdjacentcorners() {
        return adjacentcorners;
    }
    
}
    


      
