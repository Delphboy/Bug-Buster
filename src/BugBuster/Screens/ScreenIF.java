package BugBuster.Screens;

/**
 * An Interface to outline the operations a UI screen should provide
 * @author Henry Senior
 * @version 1.0.0
 */
public interface ScreenIF
{
	// killScreen should be used to free memory used by components and stop any timers that the
	// screen utilises
	void killScreen();
}
