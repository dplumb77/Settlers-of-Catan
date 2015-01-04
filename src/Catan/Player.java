/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alex
 */
public class Player {
    private String name;
    private int rocks;
    private int sheep;
    private int wood;
    private int brick;
    private int wheat;
    private int victorypoints;
    private boolean haslongestroad;
    private boolean haslargestarmy;
    private int remainingroads;
    private int remaininglargesettlements;
    private int remainingsmallsettlements;
    private int roadsavailable;
    private int smallsettlementcount;
    private int largesettlementcount;
    private int roadcount;
    private int armycount;
    private int vpcards;
    private ArrayList<Port> portlist = new ArrayList<>();
    private ArrayList<DC> DChand = new ArrayList<>();
    
    public Player(){
        rocks = 0;
        sheep = 0;
        wood = 0;
        brick = 0;
        wheat = 0;
        smallsettlementcount = 0;
        largesettlementcount = 0;
        roadcount = 0;
        armycount = 0;
        roadsavailable = 1;
        vpcards = 0;
        remaininglargesettlements = 5;
        remainingsmallsettlements = 5;
        remainingroads = 30;     
        victorypoints = 0;
        haslongestroad = false;
        haslargestarmy = false;
                
    }
    
    public int calculateScore(){
        victorypoints = 0;
        victorypoints += smallsettlementcount;
        victorypoints += 2*largesettlementcount;
        victorypoints += vpcards;
        if(haslongestroad==true) victorypoints+=2;
        if(haslargestarmy==true) victorypoints+=2;
        return victorypoints;       
    }
    
    public void addResource(Resource r, int i){
        if(r==Resource.WOOD) wood+=i;
        if(r==Resource.BRICK) brick+=i;
        if(r==Resource.ROCK) rocks+=i;
        if(r==Resource.SHEEP) sheep+=i;
        if(r==Resource.WHEAT) wheat+=i;
    }
    
    public int getTotalResourceCount(){
        int i = 0;
        i +=wood;
        i += brick;
        i += rocks;
        i += sheep;
        i += wheat;
        return i;
    }
    
    public int getRocks(){
        return rocks;
    }
    
    public int getWood(){
        return wood;
    }
    
    public int getSheep(){
        return sheep;
    }
    
    public int getBrick(){
        return brick;
    }
    
    public int getWheat(){
        return wheat;
    }
    
    public void setRocks(int i){
        rocks = i;
    }
    
    public void setWood(int i){
        wood = i;
    }
    
    public void setSheep(int i){
        sheep = i ;
    }
    
    public void setBrick(int i){
        brick = i;
    }
    
    public void setWheat(int i){
        wheat = i;
    }
    
    public int getRoadCount(){
        return roadcount;
    }
    
    public void setRoadCount(int i){
        roadcount = i;
    }
    
    public int getSmallSettlementCount(){
        return smallsettlementcount;
    }
    
    public void setSmallSettlementCount(int i){
        smallsettlementcount = i;
    }
    
    public int getLargeSettlementCount(){
        return largesettlementcount;
    }
    
    public void setLargeSettlementCount(int i){
        largesettlementcount = i;
    }
    
    public void setRemainingSmallSettlement(int i){
        remainingsmallsettlements = i;
    }
    
    public boolean canMakeSmallSettlement(){
        if(wheat>0 && wood>0 && brick>0 && sheep>0 && remainingsmallsettlements >0) return true;
        return false;
    }
    
    public boolean canMakeLargeSettlement(){
        if(rocks>2 && wheat >1 && remaininglargesettlements >0) return true;
        return false;
    }
    
    public boolean canMakeRoad(){
        if(wood>0 && brick>0 && remainingroads >0) return true;
        return false;
    }
    
    public boolean canMakeDC(){
        if(sheep>0 && wheat >0 && rocks>0) return true;
        return false;
    }
    
    public void makeSmallSettlement(){
        smallsettlementcount +=1;
        remainingsmallsettlements -= 1;
        sheep -=1;
        wheat -=1;
        brick -=1;
        wood -=1;
        this.calculateScore();
    }
    
    public void makeLargeSettlement(){
        smallsettlementcount -=1;
        largesettlementcount += 1;
        remainingsmallsettlements +=1;
        remaininglargesettlements -=1;
        rocks -=3;
        wheat -=2;
        this.calculateScore();
    }
    
    public void makeRoad(){
        roadcount +=1;
        remainingroads -=1;
        wood -=1;
        brick -=1;
        this.calculateScore();
    }
    
    public void makeDC(){
        //take card from dc deck
        //if the card is a vp point card increment vp card score
        sheep -=1;
        rocks -=1;
        wheat -=1;
        this.calculateScore();
    }
    
    public void addArmy(){
        armycount++;
    }
    
    public void setHasLongestRoad(boolean b){
        haslongestroad=b; 
    }
    
    public void setHasLargestArmy(boolean b){
        haslargestarmy = b;
    }
    
    public List<DC> getDCHand(){
        return DChand;
    }
    
    public void giveDC(DC dc){
        DChand.add(dc);
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
