/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farkle;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Elliot
 */
public class FarkleCanvas extends JComponent {
    
    private farkle problem;
    BufferedImage img;
    private int x;
    private int y;
    private int width;
    private int height;
    Color color;
    
    public FarkleCanvas(farkle fproblem) {
        problem = fproblem;
        setPreferredSize(new Dimension(400, 200));
        x=0;
        y=0;
        width=400;
        height=200;
        color = Color.BLACK;
        img = null;
        try {
            img = ImageIO.read(new File("d1.png")); }
        catch (IOException e) {
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
    
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(10, 10, 375, 175);
        
        
        g.drawImage(getImg(problem.one), 30, 50, null);
        g.drawImage(getImg(problem.two), 90, 50, null);
        g.drawImage(getImg(problem.three), 150, 50, null);
        g.drawImage(getImg(problem.four), 210, 50, null);
        g.drawImage(getImg(problem.five), 270, 50, null);
        g.drawImage(getImg(problem.six), 330, 50, null);
    }
    
    private Image getImg(die d) {
        ImageIcon d1 = new ImageIcon("d1.png");
        Image imaged1 = d1.getImage();
        ImageIcon d2 = new ImageIcon("d2.png");
        Image imaged2 = d2.getImage();
        ImageIcon d3 = new ImageIcon("d3.png");
        Image imaged3 = d3.getImage();
        ImageIcon d4 = new ImageIcon("d4.png");
        Image imaged4 = d4.getImage();
        ImageIcon d5 = new ImageIcon("d5.png");
        Image imaged5 = d5.getImage();
        ImageIcon d6 = new ImageIcon("d6.png");
        Image imaged6 = d6.getImage();
        ImageIcon dq = new ImageIcon("dq.png");
        Image imagedq = dq.getImage();
        
        ImageIcon d1hold = new ImageIcon("d1hold.png");
        Image imaged1hold = d1hold.getImage();
        ImageIcon d2hold = new ImageIcon("d2hold.png");
        Image imaged2hold = d2hold.getImage();
        ImageIcon d3hold = new ImageIcon("d3hold.png");
        Image imaged3hold = d3hold.getImage();
        ImageIcon d4hold = new ImageIcon("d4hold.png");
        Image imaged4hold = d4hold.getImage();
        ImageIcon d5hold = new ImageIcon("d5hold.png");
        Image imaged5hold = d5hold.getImage();
        ImageIcon d6hold = new ImageIcon("d6hold.png");
        Image imaged6hold = d6hold.getImage();
        
        ImageIcon d1perm = new ImageIcon("d1perm.png");
        Image imaged1perm = d1perm.getImage();
        ImageIcon d2perm = new ImageIcon("d2perm.png");
        Image imaged2perm = d2perm.getImage();
        ImageIcon d3perm = new ImageIcon("d3perm.png");
        Image imaged3perm = d3perm.getImage();
        ImageIcon d4perm = new ImageIcon("d4perm.png");
        Image imaged4perm = d4perm.getImage();
        ImageIcon d5perm = new ImageIcon("d5perm.png");
        Image imaged5perm = d5perm.getImage();
        ImageIcon d6perm = new ImageIcon("d6perm.png");
        Image imaged6perm = d6perm.getImage();
        
        if(d.question) {
            return imagedq;
        }
        else {
        switch(d.getValue()) {
            case 1 : 
               if(d.perm) return imaged1perm;
               else if(d.hold) return imaged1hold;
               else return imaged1;
            case 2 : 
               if(d.perm) return imaged2perm;
               else if(d.hold) return imaged2hold;
               else return imaged2;
            case 3 : 
               if(d.perm) return imaged3perm;
               else if(d.hold) return imaged3hold;
               else return imaged3;
            case 4 : 
               if(d.perm) return imaged4perm;
               else if(d.hold) return imaged4hold;
               else return imaged4;
            case 5 : 
               if(d.perm) return imaged5perm;
               else if(d.hold) return imaged5hold;
               else return imaged5;
            case 6 : 
               if(d.perm) return imaged6perm;
               else if(d.hold) return imaged6hold;
               else return imaged6;            
            default : return null;
        }
        }
        
    }
}
