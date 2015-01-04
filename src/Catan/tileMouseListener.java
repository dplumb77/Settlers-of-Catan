/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Alex
 */
public class tileMouseListener implements MouseInputListener {
    
    Tile tile;
    tileObj tileobj;
    
    public tileMouseListener(tileObj t) {
        tileobj = t;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(tileobj.contains(e.getX(), e.getY())) {
            System.err.println("Tile Resource: " + tileobj.t.getResource().toString());
            tileobj.clicked = true;
        }
        else {
            tileobj.clicked = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
