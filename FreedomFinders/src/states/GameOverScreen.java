package states;

import processing.core.PApplet;
public class GameOverScreen {
    public void draw(PApplet surface)
    {
        surface.fill(255,255,255);
        surface.rect(75, 50, 125, 20);
        surface.rect(75, 75, 125, 20);
        surface.textSize(10);
        surface.fill(0,0,0);
        surface.text("To Bad, You Died :(", 80,65);
        surface.text("Restart By Clicking E", 80, 90);
    }
}