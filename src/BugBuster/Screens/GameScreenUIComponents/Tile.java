package BugBuster.Screens.GameScreenUIComponents;

import BugBuster.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents a tile which makes up the world
 * @author Henry Senior
 * @version 1.0.0
 */
public class Tile extends GameObject
{
    // Provide easy access to the width and height for calculations elsewhere
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;

    int posX;
    int posY;
    boolean isWalkable, isEndTile;

    /**
     * Create a generic tile
     * @param posX
     * @param posY
     * @param imgLoc
     * @param isWalkable
     */
    public Tile(GraphicsContext gc, int posX, int posY, String imgLoc, boolean isWalkable)
    {
        super(gc, posX * Tile.TILE_WIDTH, posY * TILE_HEIGHT);
        this.posX = posX;
        this.posY = posY;
        img = new Image(imgLoc);
        this.isWalkable = isWalkable;
        isEndTile = false;
    }

    /**
     * Create a tile which specifies whether or not it is either a start or end tile
     * @param posX
     * @param posY
     * @param imgLoc
     * @param isWalkable
     * @param isEndTile
     */
    public Tile(GraphicsContext gc, int posX, int posY, String imgLoc, boolean isWalkable,
                boolean isEndTile)
    {
        this(gc, posX, posY, imgLoc, isWalkable);
        this.isEndTile = isEndTile;
    }

    @Override
    public void update()
    {
        if(img != null)
            gc.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT);
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