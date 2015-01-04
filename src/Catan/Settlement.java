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
public enum Settlement {
    NONE, SMALL, LARGE;
    
    public String toString() {
        switch(this) {
            case NONE: return "NONE";
            case SMALL: return "SMALL";
            case LARGE: return "LARGE";                
        }
        return "FUCK";
    }
}
