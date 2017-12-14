package BugBuster;

/**
 * This interface defines the methods an object must provide to utilise the Factory Design Pattern
 * @author Henry Senior
 * @version 1.0.0
 */
public interface FactoryIF
{
    Object createProduct(int discriminator) throws InterruptedException;
}
