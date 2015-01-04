package Catan;
/**
 *
 * @author Elliot
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
