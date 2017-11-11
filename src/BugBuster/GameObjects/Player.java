package BugBuster.GameObjects;

public class Player
{
	int health; // 1 hp per heart, max 10
	int wavesComplete, currency;

	public Player()
	{
		health = 10;
		wavesComplete = 0;
		currency = 10000;
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
