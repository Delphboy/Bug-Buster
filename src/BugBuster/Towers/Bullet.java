package BugBuster.Towers;

import BugBuster.GameObject;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends GameObject
{
    protected double endX, endY;
    public Bullet(GraphicsContext gc, double startX, double startY, double
            endX, double endY)
    {
        super(gc, startX, startY);
        this.endX = endX;
        this.endY = endY;
        img = new Image("resources/test-tile.png");
        update();
    }

    @Override
    public void update()
    {
        if(x < endX)
            x += 1;
        else if(x > endX)
            x -= 1;

        if(y < endY)
            y += 1;
        else if(y > endY)
            y -= 1;

        System.out.println("DEBUG1:" + x + " : " + y);
        System.out.println("DEBUG2:" + endX + " : " + endY);
        super.update();
    }

}
