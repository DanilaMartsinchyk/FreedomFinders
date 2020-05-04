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
    /**
     * creates an Obstacles object
     * @param i a PImage of the obstacle
     * @param x x coordinate of the top left corner of the obstacle
     * @param y y coordinate of the top left corner of the obstacle
     * @param w width of the obstacle
     * @param h height of the obstacle
     */
    public Obstacles(PImage i, int x, int y, int w, int h) {
        super(x,y,30,35);
        image = i;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    /**
     * moves the obstacle
     */
    public void move() {
        this.x -= 3;
    }
    /**
     * returns the x value of the obstacle
     */
    public double getX() {
        return x;
    }
    /**
     * draws the obstacle
     * @param drawer used to help draw the obstacle
     */
    public void draw(PApplet drawer) {
        drawer.image(image, (int) x, (int) y, (int) width, (int) height);
    }
    
}