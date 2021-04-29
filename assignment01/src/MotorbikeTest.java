import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MotorbikeTest {

    private static final int road = 200;

    private Motorbike motorbike_1;


    @Before
    public void before(){
        motorbike_1 = new Motorbike(road);
      //  System.out.println("Motorbike 1. price: " + motorbike_1.getPrice());
        Assert.assertEquals(road, motorbike_1.getRoad());
    }

    @Test
    public void aggregate(){
        int aggregate = motorbike_1.aggregate(null);
        Assert.assertEquals(road, aggregate);

        System.out.println("Aggregate: ");
        final int add_road = 250;
        System.out.println("Sum of roads: " + motorbike_1.aggregate(add_road) + " km");

        Assert.assertEquals((int) (road + add_road), (int)(motorbike_1.aggregate(add_road)));
    }

    @Test
    public void deepClone(){
        Motorbike clone = motorbike_1.deepClone();
        Assert.assertEquals(motorbike_1.getRoad(), clone.getRoad());
        Assert.assertNotSame(motorbike_1, clone);
    }

}
