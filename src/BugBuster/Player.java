package BugBuster;

/**
 * This class represents the player
 * This class implements the Singleton Design Pattern
 * @author Henry Senior
 * @version 1.0.0
 */
public class Player
{
	private static Player instance = null;

	int health; // 1 hp per heart, max 10
	int wavesComplete, currency;
	String currentImmunisationLevel;

	/**
	 * Create a new player object
	 * Constructor made private for use with singleton design pattern
	 */
	private Player()
	{
		health = 10;
		wavesComplete = 0;
		currency = 700;
		currentImmunisationLevel = "flu";
	}

	/**
	 * return an instance of the player, if no instance exists one is created
	 * @return
	 */
	public static Player getInstance()
	{
		if (instance == null)
		{
			instance = new Player();
		}
		return instance;
	}

	/**
	 * Set the instance of player to null. For use at end game scenarios, allowing the player to
	 * be manually reset for new games
	 */
	public static void setInstanceToNull()
	{
		instance = null;
	}

	/**
	 * Get how much health the player has
	 * @return health
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Set the amount of health the player has
	 * @param health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * get how many waves the player has completed
	 * @return wavesComplete
	 */
	public int getWavesComplete()
	{
		return wavesComplete;
	}

	/**
	 * set the amount of waves the player has created
	 * @param wavesComplete
	 */
	public void setWavesComplete(int wavesComplete)
	{
		this.wavesComplete = wavesComplete;
	}

	/**
	 * Get how much money the player has
	 * @return currency
	 */
	public int getCurrency()
	{
		return currency;
	}

	/**
	 * set how much money the player has
	 * @param currency
	 */
	public void setCurrency(int currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the immunisation level of the player
	 * @return immunisation
	 */
	public String getCurrentImmunisationLevel()
	{
		return currentImmunisationLevel;
	}

	/**
	 * Set the immunisation level of the player
	 * @param currentImmunisationLevel
	 */
	public void setCurrentImmunisationLevel(String currentImmunisationLevel)
	{
		this.currentImmunisationLevel = currentImmunisationLevel;
	}
}
