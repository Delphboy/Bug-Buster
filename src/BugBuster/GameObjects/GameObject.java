package BugBuster.GameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class provided by the assignment brief
 */
class GameObject
{
	protected Image img;
	protected double x, y;
	protected GraphicsContext gc;

	public GameObject(GraphicsContext gc, double x, double y)
	{
		this.gc=gc;
		this.x=x;
		this.y=y;
	}

	public void update()
	{
		if(img!=null)
			gc.drawImage(img, x, y, 30, 30);
	}
}


