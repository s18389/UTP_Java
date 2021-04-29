import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    private static final int price = 50;
    private Product product_1;


    @Before
    public void before(){
        product_1 = new Product(price);
        //  System.out.println("Product 1. price: " + product_1.getPrice());
        Assert.assertEquals(price, product_1.getPrice());
    }

    @Test
    public void aggregate(){
        int aggregate = product_1.aggregate(null);
        Assert.assertEquals(price, aggregate);

        System.out.println("Aggregate: ");
        final int rising = 50;
        System.out.println("Product 1. price afrer rising: " + product_1.aggregate(rising));

        Assert.assertEquals((int) (price + rising), (int)(product_1.aggregate(rising)));
    }

    @Test
    public void deepClone(){
        Product clone = product_1.deepClone();
        Assert.assertEquals(product_1.getPrice(), clone.getPrice());
        Assert.assertNotSame(product_1, clone);
    }

}
