package Obstacles;

import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

public class Obstacles extends Rectangle2D.Double {
    private PImage image;
    private int x;
    private int y;
    private int w;
    private int h;
    public Obstacles(PImage i, int x, int y, int w, int h) {
        super(x,y,30,35);
        image = i;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void move() {
        this.x -= 3;
    }
    public double getX() {
        return x;
    }
    public void draw(PApplet drawer) {
        drawer.image(image, (int) x, (int) y, (int) width, (int) height);
    }
    
}
