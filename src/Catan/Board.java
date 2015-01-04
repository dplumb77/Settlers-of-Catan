
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
    ArrayList<Coordinate> tilecoords = new ArrayList<>();
    ArrayList<Coordinate> cornercoords = new ArrayList<>();
    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<Corner> corners = new ArrayList<>();
    ArrayList<Tile> randomtiles = new ArrayList<>();
    List<Resource> fillTiles;
    List<Integer> fillTokens;
    List<Edge> edges = new ArrayList<>();
    die d1, d2;
    
    public Board(){
        int n;
        int m;
        fillTilesInit();
        d1 = new die(1, 1);
        d2 = new die(1, 2);
        
        //generate list of tile coordinates
        tilecoords.add(new Coordinate(0,0));
        tilecoords.add(new Coordinate(0,1));
        tilecoords.add(new Coordinate(0,2));
        tilecoords.add(new Coordinate(1,0));
        tilecoords.add(new Coordinate(1,1));
        tilecoords.add(new Coordinate(1,2));
        tilecoords.add(new Coordinate(1,3));
        tilecoords.add(new Coordinate(2,0));
        tilecoords.add(new Coordinate(2,1));
        tilecoords.add(new Coordinate(2,2));
        tilecoords.add(new Coordinate(2,3));
        tilecoords.add(new Coordinate(2,4));
        tilecoords.add(new Coordinate(3,1));
        tilecoords.add(new Coordinate(3,2));
        tilecoords.add(new Coordinate(3,3));
        tilecoords.add(new Coordinate(3,4));
        tilecoords.add(new Coordinate(4,2));
        tilecoords.add(new Coordinate(4,3));
        tilecoords.add(new Coordinate(4,4));
        //fuck
        
        //generates a tile for each tile coordinate
        for(Coordinate c : tilecoords){
            tiles.add(new Tile(c,tilecoords));
        }
        
        //generates a list of coords for all corners
        for(Tile t : tiles){
            for(Coordinate c : t.getadjacentcornercoords()){
                if(cornercoords.contains(c)==false) cornercoords.add(c);
            }
        }
        
        //generates a corner for each corner coord
        for(Coordinate c : cornercoords){
            corners.add(new Corner(c,tilecoords,cornercoords));
        }
        
        //generates an edge between each corner and fills the list of edges
        for(Corner c: corners){
            for(Corner adjacent: c.getAdjacentCorners()){
                edges.add(new Edge(c,adjacent));
            }
        }
        
        Iterator<Edge> iter = edges.iterator();
        boolean b = false;
        while (iter.hasNext()) {
          Edge e = iter.next();
          for(Edge edge : edges){
              if(e.getConnectingCorners().contains(edge.getConnectingCorners().get(0))==true && e.getConnectingCorners().contains(edge.getConnectingCorners().get(1))==true && e!=edge ){
                  b = true;
              }
          }
            if(b==true) iter.remove();
            b = false;
        }
         
        //give each tile a token number and resource type        
        int k = 0;
        for(Tile t : tiles) {
            tiles.get(k).setResource(fillTiles.get(k));
            if(fillTiles.get(k)==Resource.DESERT) {
                tiles.get(k).setRobber(true);
            }
            k++;
        }
        int j = 0;
        k = 0;
        for(Tile t : tiles) {
            if(tiles.get(k).getResource()!=Resource.DESERT) {
              tiles.get(k).setToken(fillTokens.get(j));
              j++;
              k++;
            }
            else {
              k++;
            }
        }
        
        /*
        randomtiles = randomize(tiles);
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
                t.setResource(Resource.BRICK);
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
        } */
    }
    
private ArrayList<Tile> randomize(ArrayList<Tile> list){
    Random r = new Random();
    int i = list.size();
    int r1;
    int r2;
    int n;
    Tile tmp;
    for(n=0;n<30;n++){
        r1 = r.nextInt(i);
        r2 = r.nextInt(i);
        tmp = list.get(r1);
        list.set(r1,list.get(r2));
        list.set(r2,tmp);
    }
    return list;
}

    
public void fillTilesInit() {
        fillTiles = new ArrayList<Resource>();
        //tiles available
        int wheat = 4; //1, 2
        int wood = 4; //3, 4
        int rock = 3; //5, 6, 12
        int sheep = 4; //7, 8
        int brick = 3; //9, 10
        int desert = 1; //11
        
        while(wheat>0 || wood>0 || rock>0 || sheep>0 || brick>0 || desert>0) {
            
            int z = randInt(1, 12);
            if((z==1 || z==2) && wheat>0) {
                fillTiles.add(Resource.WHEAT);
                wheat--;
            }
            if((z==3 || z==4) && wood>0) {
                fillTiles.add(Resource.WOOD);
                wood--;
            }
            if((z==5 || z==6 || z==12) && rock>0) {
                fillTiles.add(Resource.ROCK);
                rock--;
            }
            if((z==7 || z==8) && sheep>0) {
                fillTiles.add(Resource.SHEEP);
                sheep--;
            }
            if((z==9 || z==10) && brick>0) {
                fillTiles.add(Resource.BRICK);
                brick--;
            }
            if(z==11 && desert>0) {
                fillTiles.add(Resource.DESERT);
                desert--;
            }
        }    
        
        int twos = 1;
        int threes = 2;
        int fours = 2;
        int fives = 2;
        int sixes = 2;
        int eights = 2;
        int nines = 2;
        int tens = 2;
        int elevens = 2;  
        int twelves = 1;
        fillTokens = new ArrayList<Integer>();
        
        while(twos>0 || threes>0 || fours>0 || fives>0 || sixes>0 || eights>0
              || nines>0 || tens>0 || elevens>0 || twelves>0) {
            
            int q = randInt(1, 18);
            if(q==1 && twos>0) {
                fillTokens.add(2);
                twos--;
            }
            if(((q==2)||(q==3)) && threes>0) {
                fillTokens.add(3);
                threes--;
            }
            if(((q==4)||(q==5)) && fours>0) {
                fillTokens.add(4);
                fours--;
            }
            if(((q==6)||(q==7)) && fives>0) {
                fillTokens.add(5);
                fives--;
            }
            if(((q==8)||(q==9)) && sixes>0) {
                fillTokens.add(6);
                sixes--;
            }
            if(((q==10)||(q==11)) && eights>0) {
                fillTokens.add(8);
                eights--;
            }
            if(((q==12)||(q==13)) && nines>0) {
                fillTokens.add(9);
                nines--;
            }
            if(((q==14)||(q==15)) && tens>0) {
                fillTokens.add(10);
                tens--;
            }
            if(((q==16)||(q==17)) && elevens>0) {
                fillTokens.add(11);
                elevens--;
            }
            if(q==18 && twelves>0) {
                fillTokens.add(12);
                twelves--;
            }
        }      
    }
    
   public static int randInt(int min, int max) {
      Random rand = new Random();
      int randomNum = rand.nextInt((max - min) + 1) + min;
      return randomNum;
    }
   
   public void placeSettlement(Player p, Corner c){
      if(c.getSettlement()==Settlement.NONE){
          c.createSettlement(Settlement.SMALL,p);
          p.makeSmallSettlement();
      }
      else{
          c.createSettlement(Settlement.LARGE, p);
          p.makeLargeSettlement();
      }
   }
   
   public boolean canPlaceSettlement(Player p, Corner c){
       if(c.canPlaceSettlement(p)==true) return true;
       return false;
   }
   
   public boolean canPlaceRoad(Player p, Edge e){
       for(Corner c: e.getConnectingCorners()){
           if(c.getOwner()==p) return true;
       }
       return false;
   }
   
   public void tilePayout(int i){
       for(Tile t: tiles){
           if(t.getToken()==i) t.payout();
       }
   }
   
   public void placeRoad(Player p, Edge e){
       e.setRoad(p);
       p.setRoadCount(p.getRoadCount()+1);
   }
   
   public void placeRobber(Tile t){
       for(Tile tile: tiles){
           tile.setRobber(false);
       }
       t.setRobber(true);
   }
   
   public void calculateLongestRoad(){
       //TODO
   }
   
   public die getd1() {
       return d1;
   }
   
   public die getd2() {
       return d2;
   }     
   
   public int roll() {
        d1.setValue(randInt(1,6));
        d2.setValue(randInt(1,6));
        
        return (d1.getValue() + d2.getValue());
    }
    
}
