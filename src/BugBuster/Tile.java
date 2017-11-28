package BugBuster;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Created by stc765 on 28/11/17.
 */
public class Tile
{
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;

    Rectangle tileBoundary;
    Image tileImg;
    int posX;
    int posY;

    public Tile(int posX, int posY, String imgLoc)
    {
        this.posX = posX;
        this.posY = posY;
        tileBoundary = new Rectangle(posX, posY, TILE_WIDTH, TILE_HEIGHT);
        tileImg = new Image(imgLoc);
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public Image getTileImg()
    {
        return tileImg;
    }
}
