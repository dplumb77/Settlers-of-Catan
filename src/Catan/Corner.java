
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
        int i;
        
        //adjacent corner coordinates
        adjacentcornercoords.add(new Coordinate(x,y+1));
        adjacentcornercoords.add(new Coordinate(x,y-1));
        if((x%2)==(y%2)){
            adjacentcornercoords.add(new Coordinate(x+1,y));
        }
        else{
            adjacentcornercoords.add(new Coordinate(x-1,y));
        }
        
        //removes coordinates that dont correspond to valid corners
        Iterator<Coordinate> iter = adjacentcornercoords.iterator();
        while (iter.hasNext()) {
          Coordinate c = iter.next();
          if(validcorners.contains(c)==false)
            iter.remove();
        }
        
        //adjacent tiles
        if(y%2 == x%2){
            i = ((x+y)/2)-2;
            adjacenttilecoords.add(new Coordinate(x-1,i));
            adjacenttilecoords.add(new Coordinate(x,i));
            adjacenttilecoords.add(new Coordinate(x,i+1));
        }
        else{
           i = ((x+y)/2)-1;
            adjacenttilecoords.add(new Coordinate(x,i));
            adjacenttilecoords.add(new Coordinate(x-1,i));
            adjacenttilecoords.add(new Coordinate(x-1,i-1));
        }  

        iter = adjacenttilecoords.iterator();
        while (iter.hasNext()) {
          Coordinate c = iter.next();
          if(validtiles.contains(c)==false)
            iter.remove();
        }
    }
    
    public void filladjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist){
        for(Tile t : tilelist){
            if(adjacenttilecoords.contains(t.getCoords())==true) adjacenttiles.add(t);
        }
        for(Corner c : cornerlist){
            if(adjacentcornercoords.contains(c.getCoords())==true) adjacentcorners.add(c);
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
    


      
