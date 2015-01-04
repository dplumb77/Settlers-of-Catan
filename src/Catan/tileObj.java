/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Alex
 */
public class tileObj extends JComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    boolean clicked;
    Color color;
    Tile t;
    
    public tileObj(Tile tile) {
        setOpaque(false);
        t = tile;
        width = 70;
        height = 80;
        x = (int) (t.getCoords().getx()*61-(t.getCoords().gety()*64/2.08) + 123);
        y = t.getCoords().gety()*53 + 50;
        clicked = false;
        
        //test Drawing settlements
        /*
        if(t.getResource()==Resource.ROCK) {
            t.getAdjacentCorners().get(5).createsettlement(Settlement.SMALL, new Player());
        }
        if(t.getResource()==Resource.SHEEP) {
            t.getAdjacentCorners().get(3).createsettlement(Settlement.LARGE, new Player());
        }
        */
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;    
        
        //Draw tiles
           g.drawImage(getTileImage(t),
                    x, 
                    y, 
                    null); 
        
        //Draw tokens
        if(t.getResource()!=Resource.DESERT) {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(x+15, y+20, 31, 31);
            g2.setColor(Color.WHITE);
            g2.fillOval(x+15, y+20, 30, 30);
            g2.setFont(new Font("Consolas", Font.BOLD, 16));
            g2.setColor(Color.BLACK);
            if(t.getToken() < 10) {
              if(t.getToken()==6 || t.getToken()==8)
                  g2.setColor(Color.RED);
              
              g2.drawString("" + t.getToken(), x+26, y+40);
            }
            else
              g2.drawString("" + t.getToken(), x+22, y+40);
        }
        
        //Draw Robber
        ImageIcon robber = new ImageIcon("images/robber.png");
        Image Robber = robber.getImage();
        
        if(t.getRobber()) {
            g.drawImage(Robber,
                    x+17, 
                    y+9, 
                    null); 
        }
    }
    
    private Image getTileImage(Tile t) {
       ImageIcon brick = new ImageIcon("images/brick.png");
        Image Brick = brick.getImage();
       ImageIcon wood = new ImageIcon("images/wood.png");
        Image Wood = wood.getImage();
       ImageIcon sheep = new ImageIcon("images/sheep.png");
        Image Sheep = sheep.getImage();
       ImageIcon rock = new ImageIcon("images/rock.png");
        Image Rock = rock.getImage();
       ImageIcon wheat = new ImageIcon("images/wheat.png");
        Image Wheat = wheat.getImage();
       ImageIcon desert = new ImageIcon("images/desert.png");
        Image Desert = desert.getImage();
        
       if(t.getResource()==Resource.BRICK) {
           return Brick;
       }
       else if(t.getResource()==Resource.WOOD) {
           return Wood;
       }
       else if(t.getResource()==Resource.SHEEP) {
           return Sheep;
       }
       else if(t.getResource()==Resource.ROCK) {
           return Rock;
       }
       else if(t.getResource()==Resource.WHEAT) {
           return Wheat;
       }
       else if(t.getResource()==Resource.DESERT) {
           return Desert;
       }
       
       return null;      
   }  
    
    @Override
    public boolean contains(int x, int y) {
        x -= this.x;
          x -= 9;
        y -= this.y;
          y -= 29;
                  
        GeneralPath bounds = new GeneralPath();
        bounds.moveTo(29, 0);
        bounds.lineTo(0, 14);
        bounds.lineTo(0, 52);
        bounds.lineTo(29, 70);
        bounds.lineTo(59, 52);
        bounds.lineTo(59, 15);
        bounds.lineTo(29, 0);
        bounds.closePath();
        return bounds.contains(x, y);
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
