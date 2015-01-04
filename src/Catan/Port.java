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
public enum Port {
    NONE, BRICK, ROCK, SHEEP, WHEAT, WOOD, GENERAL;
    
    public String toString() {
        switch(this) {
            case NONE : return "none";
            case BRICK : return "brick";
            case ROCK : return "rock";
            case SHEEP : return "sheep";
            case WHEAT : return "wheat";
            case WOOD : return "wood";
            case GENERAL : return "general";
        }
        return "FUCK";
    }
}
