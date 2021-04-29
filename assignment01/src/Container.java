import java.util.ArrayList;
import java.util.List;

public final class Container<TElement extends IAggregable<TElement, TResult> & IDeeplyCloneable<TElement>, TResult> implements IContainer<TElement,TResult>{

    private List<TElement> ele;

    public Container(){}

    public Container(ArrayList<TElement> t) {
        this.ele = t;
    }

    @Override
    public List<TElement> elements() {
        return ele;
    }


    @Override
    public TResult aggregateAllElements() {
        TResult result = null;
        for (TElement element : ele) result = element.aggregate(result);
        return result;
    }

    @Override
    public TElement cloneElementAtIndex (int index) {
        if (index < 0) return null;
        if (ele.size() <= index) return null;

        TElement element = ele.get(index);
        return element.deepClone();
    }

    public void  add(TElement t){
        ele.add(t);
    }

 /*       public TElement get(int index){
            if (index < 0) return null;
            if (ele.size() <= index) return null;
            if (ele.size() > index) return
        }

  */
}