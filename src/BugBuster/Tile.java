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
    boolean isWalkable, isEndTile;

    public Tile(int posX, int posY, String imgLoc, boolean isWalkable)
    {
        this.posX = posX;
        this.posY = posY;
        tileBoundary = new Rectangle(posX, posY, TILE_WIDTH, TILE_HEIGHT);
        tileImg = new Image(imgLoc);
        this.isWalkable = isWalkable;
    }

    public Tile(int posX, int posY, String imgLoc, boolean isWalkable,
                boolean isEndTile)
    {
        this.posX = posX;
        this.posY = posY;
        tileBoundary = new Rectangle(posX, posY, TILE_WIDTH, TILE_HEIGHT);
        tileImg = new Image(imgLoc);
        this.isWalkable = isWalkable;
        this.isEndTile = isEndTile;
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

    public boolean isWalkable()
    {
        return isWalkable;
    }

    public boolean isEndTile()
    {
        return isEndTile;
    }
}
