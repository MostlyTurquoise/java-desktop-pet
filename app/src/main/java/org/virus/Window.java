package org.virus;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    public int x = 0;
    public int y = 0;
    public int pixelWidth = 16;
    public int pixelHeight = 16;
    public int relClickX = -1;
    public int relClickY = -1;
    public int width = 100;
    public int height = 100;
    public int floor = 48;
    public Image baseImage = new ImageIcon(getClass().getClassLoader().getResource("test/amethyst_shard.png"))
            .getImage();
    public JLabel label = new JLabel();
    public MouseAdapter eventHandler = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked!");
        }

        public void mouseDragged(MouseEvent e) {
            x = e.getXOnScreen() - relClickX;
            y = e.getYOnScreen() - relClickY;
            updatePosition();
        }

        public void mouseEntered(MouseEvent e) {
            // System.out.println("Hi!");
        }

        public void mouseExited(MouseEvent e) {
            // System.out.println("Bye");
        }

        public void mousePressed(MouseEvent e) {
            relClickX = e.getXOnScreen() - x;
            relClickY = e.getYOnScreen() - y;
        }

        public void mouseReleased(MouseEvent e) {
            relClickX = -1;
            relClickY = -1;
        }
    };

    public Window() {
        setIconImage(baseImage);
        setSize(new Dimension(width, height));
        setResizable(false);
        setUndecorated(true);
        setType(JFrame.Type.UTILITY);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.addMouseListener(eventHandler);
        label.addMouseMotionListener(eventHandler);
        add(label);
        updateDisplay();
        setVisible(true);
        updatePosition();
    }

    @Override
    protected void finalize() {
        try {
            label.removeMouseListener(eventHandler);
            label.removeMouseMotionListener(eventHandler);
            eventHandler = null;
            label = null;
        } catch (Exception e) {
            // ...
        }
    }

    public void updatePosition() {
        setLocation(x - (x % (width/pixelWidth)), y - (y % (height / pixelHeight)));
    }

    public void updateDisplay() {
        label.setIcon(new ImageIcon(baseImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
    }
}