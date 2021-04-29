import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContainerTest {

    protected Container<Product, Integer> product_prices;
    protected static ArrayList<Product> list = new ArrayList<>();

    @Before
    public void beforeeach(){
        list.add(new Product(100));
        list.add(new Product(100));
        list.add(new Product(200));
        list.add(new Product(200));
        product_prices = new Container<Product, Integer>(list);
    }



    @Test
    public void elements() {
    }


    @Test
    public void aggregateAllElements() {
    //    int counter = 0;
    //    int counter_for_list = 0;
        int sum = 0;
        int average = 0;
        int  summaries = 0;

        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getPrice();
            summaries = (product_prices.elements().get(i).aggregate(summaries));
            //       System.out.println("sum " + sum);
        }
        //counter_for_list++;
        //  System.out.println("How many changes?: " + list_of_prices.size());
       // System.out.println("In sum of all prices: " + (sum + price));

        Assert.assertEquals(sum, summaries);
    }

    @Test
    public void cloneElementAtIndex() {
    try {
        Product copy = product_prices.elements().get(2).deepClone();
        Assert.assertEquals(product_prices.elements().get(2).getPrice(), copy.getPrice());
    }
    catch(IndexOutOfBoundsException e) {
        System.out.println("Index too big or small");
    }
    }
}
