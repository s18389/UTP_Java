public class Motorbike implements IAggregable<Motorbike, Integer>, IDeeplyCloneable<Motorbike> {

    private int road;

    public Motorbike() {
    }

    public Motorbike(int road) {
        this.road = road;
    }

    public int getRoad() {
        return road;
    }

    protected int current_road = road;

    public Integer aggregate(Integer road_to_add) {
        if  (road_to_add == null) {
            return road;
        }
        current_road = current_road + road_to_add;
        return road + road_to_add;
    }

    public Motorbike deepClone() {
        Motorbike clone = new Motorbike();
        clone.road = road;
        return clone;
    }


}