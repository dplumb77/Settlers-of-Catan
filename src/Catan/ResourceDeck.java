/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;

/**
 *
 * @author Alex
 */
public class ResourceDeck {
    private int rock;
    private int sheep;
    private int clay;
    private int wheat;
    private int wood;
    
    public ResourceDeck(){
        rock = 24;
        sheep = 24;
        clay = 24;
        wheat = 24;
    }
    
    public void take(Resource r, int i){
        if(r==Resource.ROCK) rock-=i;
        if(r==Resource.SHEEP) sheep-=i;
        if(r==Resource.CLAY) clay-=i;
        if(r==Resource.WHEAT) wheat-=i;
        if(r==Resource.WOOD) wood-=i;
    }

    public void add(Resource r, int i){
        if(r==Resource.ROCK) rock+=i;
        if(r==Resource.SHEEP) sheep+=i;
        if(r==Resource.CLAY) clay+=i;
        if(r==Resource.WHEAT) wheat+=i;
        if(r==Resource.WOOD) wood+=i;
    }
    
    public boolean hasResource(Resource r, int i){
        if(r==Resource.ROCK && rock >= i) return true;
        if(r==Resource.SHEEP && sheep >= i) return true;
        if(r==Resource.CLAY && clay >= i) return true;
        if(r==Resource.WHEAT && wheat >= i) return true;
        if(r==Resource.WOOD && wood >= i) return true;
        return false;
    }

    
}
