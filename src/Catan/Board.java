
package Catan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class Board {
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Corner> corners = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    /*
        constructor:
            -fills list of tiles, corners and edges
            -fills each tile, corner and edge object's list of adjacent objects
            -randomizes resource and token placement
            -TODO: randomize port placement
    */
    
    public Board(){
        
        //fill tile list with tile objects
        tiles.add(new Tile(new Coordinate(0,0)));
        tiles.add(new Tile(new Coordinate(0,1)));
        tiles.add(new Tile(new Coordinate(0,2)));
        tiles.add(new Tile(new Coordinate(1,0)));
        tiles.add(new Tile(new Coordinate(1,1)));
        tiles.add(new Tile(new Coordinate(1,2)));
        tiles.add(new Tile(new Coordinate(1,3)));
        tiles.add(new Tile(new Coordinate(2,0)));
        tiles.add(new Tile(new Coordinate(2,1)));
        tiles.add(new Tile(new Coordinate(2,2)));
        tiles.add(new Tile(new Coordinate(2,3)));
        tiles.add(new Tile(new Coordinate(2,4)));
        tiles.add(new Tile(new Coordinate(3,1)));
        tiles.add(new Tile(new Coordinate(3,2)));
        tiles.add(new Tile(new Coordinate(3,3)));
        tiles.add(new Tile(new Coordinate(3,4)));
        tiles.add(new Tile(new Coordinate(4,2)));
        tiles.add(new Tile(new Coordinate(4,3)));
        tiles.add(new Tile(new Coordinate(4,4)));
      
        //fills corner list with corner objects
        List<Coordinate> cornercoords = new ArrayList<>();
        for(Tile t : tiles){
            for(Coordinate c : t.getAdjacentCornerCoords()){
                if(cornercoords.contains(c)==false) cornercoords.add(c);
            }
        }
        for(Coordinate c : cornercoords){
            corners.add(new Corner(c));
        }
        
        //fills adjacent corner/tile fields
        for(Tile t: tiles){
            t.fillAdjacents(tiles,corners);
        }
        for(Corner c: corners){
            c.fillAdjacents(tiles,corners);
        }
        
        //generates an edge between each corner and fills the list of edges
        //results in lots of duplicates
        for(Corner c: corners){
            for(Corner adjacent: c.getAdjacentCorners()){
                edges.add(new Edge(c,adjacent));
            }
        }
        
        //hopefully removes duplicates from the edge list
        Iterator<Edge> iter = edges.iterator();
        boolean b = false;
        while (iter.hasNext()) {
            Edge e = iter.next();
            for(Edge edge : edges){
                if(e.sharesCorners(edge)==true && edge!=e){
                    b = true;
                }
            }
            if(b==true) iter.remove();
            b = false;
        }
         
        //give each tile a token number and resource type                
        ArrayList<Tile> randomtiles = randomize(tiles);
        int sheep = 3;
        int wood = 3;
        int rock = 2;
        int brick = 2;
        int wheat = 3;
        for(Tile t : randomtiles){
            if(sheep>0){
                t.setResource(Resource.SHEEP);
                sheep--;
            }
            if(wood>0){
                t.setResource(Resource.WOOD);
                wood--;
            }
            if(brick>0){
                t.setResource(Resource.CLAY);
                brick--;
            }
            if(wheat>0){
                t.setResource(Resource.WHEAT);
                wheat--;
            }
            if(rock>0){
                t.setResource(Resource.ROCK);
                rock--;
            }
            else t.setResource(Resource.DESERT);        
        }            
        randomtiles = randomize(randomtiles);
        int twos = 1;
        int twelves = 1;
        int threes = 2;
        int fours = 2;
        int fives = 2;
        int sixes = 2;
        int sevens = 2;
        int eights = 2;
        int nines = 2;
        int tens = 2;
        int elevens = 2;        
        for(Tile t : randomtiles){
            if(t.getResource() != Resource.DESERT){
                if(twos != 0){
                    t.setToken(2);
                    twos--;
                }
                else if(threes !=0){
                    t.setToken(3);
                    threes--;
                }
                else if(fours !=0){
                    t.setToken(4);
                    fours--;
                }  
                else if(fives !=0){
                    t.setToken(5);
                    fives--;
                }  
                else if(sixes !=0){
                    t.setToken(6);
                    sixes--;
                }  
                else if(sevens !=0){
                    t.setToken(7);
                    sevens--;
                }  
                else if(eights !=0){
                    t.setToken(8);
                    eights--;
                }  
                else if(nines !=0){
                    t.setToken(9);
                    nines--;
                }  
                else if(tens !=0){
                     t.setToken(10);
                    tens--;
                }              
                else if(elevens !=0){
                    t.setToken(11);
                    elevens--;
                }  
                else if(twelves !=0){
                    t.setToken(12);
                    twelves--;
                }   
            }
        }
    }
    
    private ArrayList<Tile> randomize(ArrayList<Tile> list){
        Random r = new Random();
        int i = list.size();
        int r1;
        int r2;
        int n;
        Tile tmp;
        for(n=0;n<50;n++){
            r1 = r.nextInt(i);
            r2 = r.nextInt(i);
            tmp = list.get(r1);
            list.set(r1,list.get(r2));
            list.set(r2,tmp);
        }
        return list;
    }

    /*
        settlement methods:
            void placeSettlement(Player p, Corner c)
                -plates a settlement or upgrades the settlement for the given player on the given tile
            boolean canPlaceSettlemnt(Player p, Corner c)
                -just calls c.canPlaceSettlement(Player p)
                -probably unnecessary and could be deleted           
    */
    
    public void placeSettlement(Player p, Corner c){
        if(c.getSettlement()==Settlement.NONE){
            c.placeSettlement(Settlement.SMALL,p);
        }
        else{
            c.placeSettlement(Settlement.LARGE, p);
        }
    }
   
    public boolean canPlaceSettlement(Player p, Corner c){
        if(c.canPlaceSettlement(p)==true) return true;
        return false;
    }

    /*
        Road methods:
            boolean canPlaceRoad(Player p, Edge e)
                -probably unnecessary
            void placeRoad(Player p, Edge e)
                -also probably unnecessary
            TODO: calculate longest road
    */    
   
    public boolean canPlaceRoad(Player p, Edge e){
        for(Corner c: e.getConnectingCorners()){
            if(c.getOwner()==p) return true;
        }
        return false;
    }
 
    
    public void placeRoad(Player p, Edge e){
       e.placeRoad(p);
    }

    public void calculateLongestRoad(){
        
    }
    
    
    /*
        payout methods:
           void tilePayout(int i, ResourceDeck rd)
               -counts up how much of each resource would be distributed and only pays out a resource if the rd has enough
    */
   
    public void tilePayout(int i, ResourceDeck rd){
     
        //calculate the amount of resources that will be payed out
        ArrayList<Resource> resourcecount = new ArrayList<>();        
        for(Tile t: tiles){
            if(t.getToken()==i){
                for(Corner c: t.getAdjacentCorners()){
                   if(c.getSettlement()==Settlement.SMALL) resourcecount.add(t.getResource());
                   if(c.getSettlement()==Settlement.LARGE){
                       resourcecount.add(t.getResource());
                       resourcecount.add(t.getResource());
                   }
                }
            }
        }
        int rockcount = 0;
        int sheepcount = 0;
        int wheatcount = 0;
        int claycount = 0;
        int woodcount = 0;
        for(Resource r: resourcecount){
            if(r==Resource.ROCK) rockcount++;
            if(r==Resource.SHEEP) sheepcount++;
            if(r==Resource.CLAY) claycount++;
            if(r==Resource.WHEAT) wheatcount++;
            if(r==Resource.WOOD) woodcount++;
        }
        
        //payout only resources that can be distributed to all tiles
        if(rd.hasResource(Resource.ROCK,rockcount)==true) resourcePayout(Resource.ROCK,i,rd);
        if(rd.hasResource(Resource.SHEEP,sheepcount)==true) resourcePayout(Resource.SHEEP,i,rd);
        if(rd.hasResource(Resource.CLAY,claycount)==true) resourcePayout(Resource.CLAY,i,rd);
        if(rd.hasResource(Resource.WHEAT,wheatcount)==true) resourcePayout(Resource.WHEAT,i,rd);
        if(rd.hasResource(Resource.WOOD,woodcount)==true) resourcePayout(Resource.WOOD,i,rd);       
    }
    
    private void resourcePayout(Resource r, int i,ResourceDeck rd){
         for(Tile t: tiles){
            if(t.getToken()==i && t.getResource()==r){
                for(Corner c: t.getAdjacentCorners()){
                   if(c.getSettlement()==Settlement.SMALL){
                       rd.take(r, 1);
                       c.getOwner().addResource(r, 1);
                   }
                   if(c.getSettlement()==Settlement.LARGE){
                       rd.take(r, 2);
                       c.getOwner().addResource(r, 2);                       
                   }
                }
            }
        }       
    }

    /*
        Robber methods:
            void moveRobber(Tile t)
                -moves robber to given tile
    */
    
    public void moveRobber(Tile t){
        for(Tile tile: tiles){
            tile.setRobber(false);
        }
        t.setRobber(true);
    }

    /*
        getters for tile/edge/corner objects
    */
    
    public ArrayList<Tile> getTiles(){
        return tiles;
    }
   
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public ArrayList<Corner> getCorners(){
        return corners;
    }   
}
