package BugBuster;

/**
 * Created by stc765 on 28/11/17.
 */
public interface FactoryIF
{
    public Object createProduct(int discriminator) throws InterruptedException;
}
