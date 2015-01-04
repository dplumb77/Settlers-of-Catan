/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catan;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Elliot
 */
public class clickRefresh implements MouseListener {
    
    JFrame frame;
    boardCanvas canvas;
    
    public clickRefresh(JFrame f, boardCanvas c) {
        frame = f;
        canvas = c;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.err.println("General click");
        canvas.repaint();
        frame.repaint();
        for(tileObj t : canvas.tileobjs) {
            if(t.clicked) {
                System.err.println("t is clicked");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
