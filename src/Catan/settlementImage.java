/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JComponent;

/**
 *
 * @author Elliot
 */
public class settlementImage extends JComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    Color color;
    int kind;
    
    public settlementImage(Settlement type, int X, int Y) {
        x = X;
        y = Y;
        width = 70;
        height = 70;
        color = Color.RED;
        if(type==Settlement.SMALL)
            kind = 0;
        else if(type==Settlement.LARGE)
            kind = 1;
            
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;       
        ImageIcon settlement = new ImageIcon("images/pieces/settlement.png");
        Image Settlement = settlement.getImage();
        ImageIcon city = new ImageIcon("images/pieces/city.png");
        Image City = city.getImage();
        g2.setColor(Color.RED);
        if(kind==0) {
          g2.drawImage(Settlement, 0, 0, this);    
        }
        else if(kind==1) {
          g2.drawImage(City, 0, 0, this);    
        }
        
    }
    
   public int getX() {
       return x;
   }
   
   public int getY() {
       return y;
   }
   
   public void setX(int theX) {
       x = theX;
   }
   
   public void setY(int theY) {
       y = theY;
   }
   
   public int getHeight() {
       return height;
   }
   
   public void setHeight(int h) {
       height = h;
   }
   
   public int getWidth() {
       return width;
   }
   
   public void setWidth(int w) {
       width = w;
   }
   
   public Color getColor() {
       return color;
   }
   
   public void setColor(Color color1) {
       color = color1;
   }
}
