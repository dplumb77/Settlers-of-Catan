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
public enum DC {
    ARMY, YoP, MONOPOLY, ROADBUILDER, VP;
    
    public String toString() {
        switch(this) {
            case ARMY : return "army";
                case YoP : return "YoP";
                    case MONOPOLY : return "monopoly";
                        case ROADBUILDER : return "roadbuilder";
                            case VP : return "vp";
        }
        return "FUCK";
    }
}
