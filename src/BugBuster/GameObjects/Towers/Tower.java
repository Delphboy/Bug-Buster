package BugBuster.GameObjects.Towers;

import BugBuster.GameObjects.GameObject;
import BugBuster.GameObjects.Pathogens.Pathogen;
import javafx.scene.image.Image;

public class Tower extends GameObject implements TowerIF
{
	private String type;
	private int tileLocX;
	private int tileLocY;
	private int radius;
	private int damage;

	public Tower(String type, int radius, int damage)
	{
		super(null, 0 ,0);
		this.type = type;
		this.radius = radius;
		this.damage = damage;
	}

	public String getType()
	{
		return type;
	}

	public int getTileLocX()
	{
		return tileLocX;
	}

	public int getTileLocY()
	{
		return tileLocY;
	}

	public int getRadius()
	{
		return radius;
	}

	public int getDamage()
	{
		return damage;
	}

	public Image getImage()
	{
		return img;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public void setImage(Image image)
	{
		this.img = image;
	}

	@Override
	public String toString()
	{
		return "Tower{" +
				"tileLocX=" + tileLocX +
				", tileLocY=" + tileLocY +
				", radius=" + radius +
				", damage=" + damage +
				'}';
	}

	@Override
	public void shoot(Pathogen enemy)
	{

	}

	@Override
	public Pathogen getTarget()
	{
		return null;
	}

	@Override
	public void upgradeTowerRange()
	{

	}

	@Override
	public void upgradeTowerDamage()
	{

	}
}
