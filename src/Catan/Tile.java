
package Catan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tile {
    private int x;
    private int y;
    private Resource resource;
    private int token;
    private boolean robber;
    private Coordinate coords;
    private ArrayList<Coordinate> adjacentcornercoords = new ArrayList<>();
    private ArrayList<Coordinate> adjacenttilecoords = new ArrayList<>();
    private List<Tile> adjacenttiles = new ArrayList<>();
    private List<Corner> adjacentcorners = new ArrayList<>();
    
    public Tile(Coordinate coordinates, ArrayList<Coordinate> validtiles){
        x = coordinates.getx();
        y = coordinates.gety();
        coords = coordinates;
        robber = false;
        
        int n;
        int m;
        int i;
        
        //generates list of adjacent tile coordinates
        for(n=0;n<3;n++){
            for(m=0;m<3;m++){
                if(!((n==0&&m==2)||(n==2&&m==0))){
                    adjacenttilecoords.add(new Coordinate(x-1+m, y-1+n));
                }
            }
        }

        Iterator<Coordinate> iter = adjacenttilecoords.iterator();
        while (iter.hasNext()) {
          Coordinate c = iter.next();
          if(validtiles.contains(c)==false)
            iter.remove();
        }
        
        //adjacent corners
        i = ((y+2)*2)-x; // makes sense
        for(n=0;n<2;n++){
            for(m=0;m<3;m++){
                adjacentcornercoords.add(new Coordinate(x+n,i-m));
            }
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
    
    public void setResource(Resource r){
        resource = r;
    }
    
    public void setToken(int i){
        token = i;
    }
    
    public void setRobber(boolean b){
        robber = b;
    }
    
    public void setCoords(Coordinate c){
        coords = c;
    }
    
    public ArrayList<Coordinate> getAdjacentCorners(){
        return adjacentcornercoords;
    }
    
    public ArrayList<Coordinate> getAdjacenttiles(){
        return adjacenttilecoords;
    }
    
    public Coordinate getCoords(){
        return coords;
    }
    
    public boolean getRobber(){
        return robber;
    }
    
    public int getToken(){
        return token;
    }
    
    public Resource getResource(){
        return resource;
    }
    
    public ArrayList<Coordinate> getadjacentcornercoords(){
        return adjacentcornercoords;
    }
    
    public void payout(){
        if(robber==false){
            for(Corner c : adjacentcorners){
                if(c.getSettlement()== Settlement.LARGE){
                    c.getOwner().addResource(resource,2);
                }
                if(c.getSettlement() == Settlement.SMALL){
                    c.getOwner().addResource(resource, 1);
                }
            }
        }
    }
    
    public List<Corner> getAdjacentcorners() {
        return adjacentcorners;
    }
    
    
   
    
}
