package BugBuster;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * This class represents a tile which makes up the world
 * @author Henry Senior
 * @version 1.0.0
 */
public class Tile
{
    // Provide easy access to the width and height for calculations elsewhere
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;

    Rectangle tileBoundary;
    Image tileImg;
    int posX;
    int posY;
    boolean isWalkable, isStartTile, isEndTile;

    /**
     * Create a generic tile
     * @param posX
     * @param posY
     * @param imgLoc
     * @param isWalkable
     */
    public Tile(int posX, int posY, String imgLoc, boolean isWalkable)
    {
        this.posX = posX;
        this.posY = posY;
        tileBoundary = new Rectangle(posX, posY, TILE_WIDTH, TILE_HEIGHT);
        tileImg = new Image(imgLoc);
        this.isWalkable = isWalkable;
        isStartTile = false;
        isEndTile = false;
    }

    /**
     * Create a tile which specifies whether or not it is either a start or end tile
     * @param posX
     * @param posY
     * @param imgLoc
     * @param isWalkable
     * @param isStartTile
     * @param isEndTile
     */
    public Tile(int posX, int posY, String imgLoc, boolean isWalkable, boolean isStartTile,
                boolean isEndTile)
    {
        this.posX = posX;
        this.posY = posY;
        tileBoundary = new Rectangle(posX, posY, TILE_WIDTH, TILE_HEIGHT);
        tileImg = new Image(imgLoc);
        this.isWalkable = isWalkable;
        this.isStartTile = isStartTile;
        this.isEndTile = isEndTile;
    }

    /**
     * Get the X position of the tile in the tile array
     * @return posX
     */
    public int getPosX()
    {
        return posX;
    }

    /**
     * Get the Y position of the tile in the tile array
     * @return posY
     */
    public int getPosY()
    {
        return posY;
    }

    /**
     * Get the image displayed on the tile
     * @return tileImg
     */
    public Image getTileImg()
    {
        return tileImg;
    }

    /**
     * Get whether or not a pathogen can walk on the tile
     * @return isWalkable
     */
    public boolean isWalkable()
    {
        return isWalkable;
    }

    /**
     * Get whether or not a tile is an end tile
     * @return isEndTile
     */
    public boolean isEndTile()
    {
        return isEndTile;
    }
}