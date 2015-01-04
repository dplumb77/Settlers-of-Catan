
package Catan;

public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int xparam, int yparam){
        x = xparam;
        y = yparam;
    }
    
    public Coordinate(){
        x = 0;
        y = 0;
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
    
    public void setx(int i){
        x = i;
    }
    
    public void sety(int i){
        y = i;
    }
}
