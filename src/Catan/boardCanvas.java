/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author Alex
 */
public class boardCanvas extends JPanel {
    
    Board board;
    List<tileObj> tileobjs;
    
    private int x;
    private int y;
    private int width;
    private int height; 
    private List<Corner> allCorners;
    private List<settlementImage> settlements;
    Color color; 
       
    public boardCanvas(Board theBoard) {       
        board = theBoard;
        allCorners = board.corners;
        tileobjs = new ArrayList<tileObj>();
        
        x = 0;
        y = 0;
        height = 400;
        width = 380;
        color = Color.BLACK;
        
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

   public void paintComponent(Graphics g) {
       Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.BLACK);
        
        for(Tile t : board.tiles) {
          tileObj z = new tileObj(t);
          tileobjs.add(z);
        }
        
        for(tileObj t : tileobjs) {
            t.paintComponent(g);
        }
        
        //draw dice
        g.drawImage(getDieImage(board.getd1()), 0, 0, this);
        g.drawImage(getDieImage(board.getd2()), 50, 0, this);
        
        //draw settlements
        for(tileObj t : tileobjs) {
            int k = 0;
           for(Corner c : t.t.getAdjacentcorners()) {
               if(c.getSettlement() != Settlement.NONE) {
                  if(k==0) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()-10,t.getY()+4));
                        c.setDrawnSettlement(true);
                    }
                  }
                  if(k==1) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()+17,t.getY()-11));
                        c.setDrawnSettlement(true);
                    }
                  }
                  if(k==2) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()+50,t.getY()+4));
                        c.setDrawnSettlement(true);
                    }
                  }
                  if(k==3) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()+46,t.getY()+41));
                        c.setDrawnSettlement(true);
                    }  
                  }
                  if(k==4) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()+18,t.getY()+58));
                        c.setDrawnSettlement(true);
                    }  
                  }
                  if(k==5) {
                    if(!c.getDrawnSettlement()) {
                        add(new settlementImage(c.getSettlement(), t.getX()-15,t.getY()+41));
                        c.setDrawnSettlement(true);
                    }  
                  }                 
               }
               else {
                   c.setDrawnSettlement(false);
               }
               k++;
           }
        }
   }
   
   public Image getDieImage(die d) {
       
        ImageIcon d1 = new ImageIcon("images/dice/d1.png");
        Image D1 = d1.getImage();
        ImageIcon d2 = new ImageIcon("images/dice/d2.png");
        Image D2 = d2.getImage();
        ImageIcon d3 = new ImageIcon("images/dice/d3.png");
        Image D3 = d3.getImage();
        ImageIcon d4 = new ImageIcon("images/dice/d4.png");
        Image D4 = d4.getImage();
        ImageIcon d5 = new ImageIcon("images/dice/d5.png");
        Image D5 = d5.getImage();
        ImageIcon d6 = new ImageIcon("images/dice/d6.png");
        Image D6 = d6.getImage();
       
       switch(d.getValue()) {
           case 1 : return D1;
           case 2 : return D2;
           case 3 : return D3;
           case 4 : return D4;
           case 5 : return D5;
           case 6 : return D6;
           default : return null;
       }
   }
}


