/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;


public enum Resource {
    BRICK, DESERT, ROCK, SHEEP, WHEAT, WOOD;
    
    public String toString() {
        switch(this) {
            case BRICK : return "brick";
                case DESERT : return "desert";
                    case ROCK : return "rock";
                        case SHEEP : return "sheep";
                            case WHEAT : return "wheat";
                                case WOOD : return "wood";
        }
        return "FUCK";
    }
}
