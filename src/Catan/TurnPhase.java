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
public enum TurnPhase {
    START,ROLL,BUILD, TRADE;
    
    public String toString() {
        switch(this) {
            case START : return "start";
                case BUILD : return "build";
                    case ROLL : return "roll";
                        case TRADE : return "trade";
        }
        return "FUCK";
    }
}
