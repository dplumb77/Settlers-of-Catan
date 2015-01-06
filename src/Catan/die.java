package Catan;
/**
 *
 * @author Elliot
 * 
 * HELLO THIS IS ELLIOT THIS IS A TEST PLEASE IGNOR POST LE LOL
 */
public class die {
    
    private int value;
    private int number;
    
    public die(int val, int num) {
        value = val;
        number = num;
    }
    
    public void setValue(int val) {
        value = val;
    }
    
    public int getValue() {
        return value;
    }
    
    public String toString() {
        return "Die " + number + " Value " + value;
    }
}
