
package Catan;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    private int x;
    private int y;
    private Resource resource;
    private int token;
    private boolean robber;
    private Coordinate coords;
    private List<Tile> adjacenttiles = new ArrayList<>();
    private List<Corner> adjacentcorners = new ArrayList<>();
    private List<Coordinate> adjacentcornercoords = new ArrayList<>();
    
    public Tile(Coordinate coordinates){
        x = coordinates.getx();
        y = coordinates.gety();
        coords = coordinates;
        robber = false;
              
        //still needed to generate corner objects
        int i = ((y+2)*2)-x;
        adjacentcornercoords.add(new Coordinate(x,i));
        adjacentcornercoords.add(new Coordinate(x,i-1));
        adjacentcornercoords.add(new Coordinate(x,i-2));
        adjacentcornercoords.add(new Coordinate(x+1,i));
        adjacentcornercoords.add(new Coordinate(x+1,i-1));
        adjacentcornercoords.add(new Coordinate(x+1,i-2)) ;           
        
    }

    /*
        Adjacent stuff methods:
            void fillAdjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist)
                -fills adjacent tiles/corners lists with tile/corner objects
                -maybe needs edges
            - getters for each list
    */
    
    public void fillAdjacents(ArrayList<Tile> tilelist, ArrayList<Corner> cornerlist){
        //adjacent tiles
        Coordinate tmpcoord;
        for(Tile t: tilelist){
            tmpcoord = t.getCoords();
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==y-1) adjacenttiles.add(t);
            if(tmpcoord.getx()==(x-1)&&tmpcoord.gety()==y) adjacenttiles.add(t);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==y+1) adjacenttiles.add(t);            
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==y+1) adjacenttiles.add(t);           
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==y) adjacenttiles.add(t);                       
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==y-1) adjacenttiles.add(t);            
        }
        
        //adjacent corners
        int i = ((y+2)*2)-x;
        for(Corner c: cornerlist){
            tmpcoord = c.getCoords();
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==i) adjacentcorners.add(c);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==(i-1)) adjacentcorners.add(c);
            if(tmpcoord.getx()==(x)&&tmpcoord.gety()==(i-2)) adjacentcorners.add(c);            
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==i) adjacentcorners.add(c);           
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==(i-1)) adjacentcorners.add(c);                       
            if(tmpcoord.getx()==(x+1)&&tmpcoord.gety()==(i-2)) adjacentcorners.add(c); 
        }
    }
    
    public List<Corner> getAdjacentCorners(){
        return adjacentcorners;
    }
    
    public List<Tile> getAdjacentTiles(){
        return adjacenttiles;
    }
    
    public List<Coordinate> getAdjacentCornerCoords(){
        return adjacentcornercoords;
    }    

    /*
        payout method
            void payout()
                -distrubutes resources to adjacent settlements
                -only called from Board class' tile payout method
    */
    
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
    
    public void setResource(Resource r){
        resource = r;
    }
    
    public void setToken(int i){
        token = i;
    }
    
    public Coordinate getCoords(){
        return coords;
    }
   
    public boolean getRobber(){
        return robber;
    }
    
    public void setRobber(boolean b){
        robber = b;
    }
    
    public int getToken(){
        return token;
    }
    
    public Resource getResource(){
        return resource;
    }    
}
