/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Alex
 */
public class Player {
    private String name;
    private int victorypoints;
    private boolean haslongestroad;
    private boolean haslargestarmy;
    private int remainingroads;
    private int remaininglargesettlements;
    private int remainingsmallsettlements;
    private int smallsettlementcount;
    private int largesettlementcount;
    private int roadcount;
    private int armycount;
    private int vpcards;
    private ArrayList<Port> portlist = new ArrayList<>();
    private ArrayList<DC> DChand = new ArrayList<>();
    private ArrayList<Resource> resourcehand = new ArrayList<>();
    
    
    public Player(){
        smallsettlementcount = 0;
        largesettlementcount = 0;
        roadcount = 0;
        armycount = 0;
        vpcards = 0;
        remaininglargesettlements = 5;
        remainingsmallsettlements = 5;
        remainingroads = 30;     
        victorypoints = 0;
        haslongestroad = false;
        haslargestarmy = false;              
    }

    /*
        General methods:
            int calculateScore() - returns player's victory points.
    `       String getName() - returns player's name
    */
    
    public int calculateScore(){
        victorypoints = 0;
        victorypoints += smallsettlementcount;
        victorypoints += 2*largesettlementcount;
        victorypoints += vpcards;
        if(haslongestroad==true) victorypoints+=2;
        if(haslargestarmy==true) victorypoints+=2;
        return victorypoints;       
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String s){
        name = s;
    }

    /*
        Resource methods:
            void addResource(Resource r, int i) 
                - adds given resource to player's resource hand.
            void removeResource(Resource r, int i) 
                - removes given resource from player's resource hand.
            int getTotalResourceCount() 
                - returns size of player's resource hand.
            Resource takeRandomResource() 
                - removes and returns a random resource from player's hand.
            -getters for each resource type
    */    
    
    public void addResource(Resource r, int i){
        if(i>0){
            for(int n = 0; n<i; n++){
                resourcehand.add(r);
            }
        }
    }
    
    public void removeResource(Resource r, int i){
        Iterator<Resource> iter = resourcehand.iterator();
        while (iter.hasNext()) {
          Resource resource = iter.next();
            if(resource==r && i>0){
                iter.remove();
                i--;
            }
        }
    }

    public int getTotalResourceCount(){
        return resourcehand.size();
    }
    
    public Resource takeRandomResource(){
    Random r = new Random();
    int i = resourcehand.size();
    int r1;
    int r2;
    int n;
    Resource tmp;
    for(n=0;n<30;n++){
        r1 = r.nextInt(i);
        r2 = r.nextInt(i);
        tmp = resourcehand.get(r1);
        resourcehand.set(r1,resourcehand.get(r2));
        resourcehand.set(r2,tmp);
    }
    tmp= resourcehand.get(0);
    resourcehand.remove(0);
    return tmp;
    }

    public int getResource(Resource r){
        int i = 0;
        for(Resource resource: resourcehand){
            if(resource==r) i++;
        }
        return i;
    }
    
    public int getRocks(){
        int i = 0;
        for(Resource r : resourcehand){
            if(r==Resource.ROCK) i++;
        }
        return i;
    }
    
    public int getWood(){
        int i = 0;
        for(Resource r : resourcehand){
            if(r==Resource.WOOD) i++;
        }
        return i;
    }
    
    public int getSheep(){
        int i = 0;
        for(Resource r : resourcehand){
            if(r==Resource.SHEEP) i++;
        }
        return i;
    }
    
    public int getClay(){
        int i = 0;
        for(Resource r : resourcehand){
            if(r==Resource.CLAY) i++;
        }
        return i;
    }
    
    public int getWheat(){
        int i = 0;
        for(Resource r : resourcehand){
            if(r==Resource.WHEAT) i++;
        }
        return i;
    }
       

    
    /*
        Settlement methods:
            void makeSmallSettlement() 
                -removes required resources from player's resource hand and adjusts remaining settlemnt counts for a small settlement.
            void makeLargeSettlement() 
                - removes required resources from player's resource hand and adjusts remaining settlemnt counts for a large settlement.
            int getSmallSettlementCount() 
            int getLargeSettlementCount() 
            boolean canMakeSmallSettlement()
            boolean canMakeLargeSettlement()
                - both check resource hand for required resources
    */      
     
    public void makeSmallSettlement(){
        smallsettlementcount +=1;
        remainingsmallsettlements -= 1;
        this.removeResource(Resource.SHEEP, 1);
        this.removeResource(Resource.WHEAT, 1);
        this.removeResource(Resource.CLAY, 1);
        this.removeResource(Resource.WOOD, 1);
    }
    
    public void makeLargeSettlement(){
        smallsettlementcount -=1;
        largesettlementcount += 1;
        remainingsmallsettlements +=1;
        remaininglargesettlements -=1;
        this.removeResource(Resource.ROCK, 3);
        this.removeResource(Resource.WHEAT, 2);
    }
    
    public int getSmallSettlementCount(){
        return smallsettlementcount;
    }
    
    public int getLargeSettlementCount(){
        return largesettlementcount;
    }    
    
    public boolean canMakeSmallSettlement(){
        if(this.getClay()>0 && this.getWood()>0 && this.getSheep()>0 && this.getWheat()>0 && remainingsmallsettlements >0) return true;
        return false;
    }
    
    public boolean canMakeLargeSettlement(){
        if(this.getRocks()>2 && this.getWheat() >1 && remaininglargesettlements >0) return true;
        return false;
    }
    
    
    /*
    Road methods:
        void setHasLongestRoad(boolean b) 
        void makeRoad() 
            - removes required resources from player's resource hand and decrements remaining roads.
        boolean canMakeRoad()
            - checks resource hand for required resources
        int getRoadCount()
        void setRoadCount(int i)
    */
    
    public void setHasLongestRoad(boolean b){
        haslongestroad=b; 
    }

    public void makeRoad(){
        remainingroads--;
        this.removeResource(Resource.WOOD, 1);
        this.removeResource(Resource.CLAY, 1);
    }
    
    public boolean canMakeRoad(){
        if(this.getWood()>0 && this.getClay()>0 && remainingroads >0) return true;
        return false;
    }

    public int getRoadCount(){
        return roadcount;
    }
    
    public void setRoadCount(int i){
        roadcount = i;
    }   
   
    
    /*   
    Army methods:
        void addArmy(int i)
        void setHasLargestArmy(boolean b)
        int getArmyCount()
    */

    public void addArmy(int i){
        armycount += i;
    }    
    
    public void setHasLargestArmy(boolean b){
        haslargestarmy = b;
        this.calculateScore();
    }
    
    public int getArmyCount(){
        return armycount;
    }
    
    
    
    /*   
    DC methods:
        void makeDC(DC dc) 
            - takes resources out of player's resource hand and adds a DC to the player's DC hand and increments vpcount if it is a VP card.
        List<DC> getDCHand()
        boolean canMakeDC()
            - checks resource hand for required resources
    */
    
    public void makeDC(DC dc){
        //take card from dc deck
        //if the card is a vp point card increment vp card score
        this.removeResource(Resource.ROCK, 1);
        this.removeResource(Resource.SHEEP, 1);
        this.removeResource(Resource.WHEAT, 1);
        DChand.add(dc);
        if(dc==DC.VP) vpcards++;
    }
    
    public List<DC> getDCHand(){
        return DChand;
    }

    public boolean canMakeDC(){
        if(this.getSheep()>0 && this.getWheat() >0 && this.getRocks()>0) return true;
        return false;
    }
    
    public ArrayList<Port> getPorts(){
        return portlist;                
    }
    
    public void addPort(Port p){
        portlist.add(p);
    }
    
}
