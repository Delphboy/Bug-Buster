package BugBuster;

public class Player
{
	private static Player instance = null;

	int health; // 1 hp per heart, max 10
	int wavesComplete, currency;

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

	public int getHealth()
	{
		return health;
	}

	public int getWavesComplete()
	{
		return wavesComplete;
	}

	public int getCurrency()
	{
		return currency;
	}
}
