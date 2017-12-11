package BugBuster;

import BugBuster.Screens.UIComponents.HeaderBarComponent;

public class Player
{
	private static Player instance = null;

	int health; // 1 hp per heart, max 10
	int wavesComplete, currency;
	String currentVaccine = "flu";

	private Player()
	{
		health = 10;
		wavesComplete = 0;
		currency = 1000;
	}

	public static Player getInstance()
	{
		if (instance == null)
		{
			instance = new Player();
		}
		return instance;
	}

	public static void setInstanceToNull()
	{
		instance = null;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getWavesComplete()
	{
		return wavesComplete;
	}

	public void setWavesComplete(int wavesComplete)
	{
		this.wavesComplete = wavesComplete;
	}

	public int getCurrency()
	{
		return currency;
	}

	public void setCurrency(int currency)
	{
		this.currency = currency;
	}

	public String getCurrentVaccine()
	{
		return currentVaccine;
	}

	public void setCurrentVaccine(String currentVaccine)
	{
		this.currentVaccine = currentVaccine;
	}
}
