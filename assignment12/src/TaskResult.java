import java.util.Random;

public final class TaskResult {

    private int result;

    public static TaskResult create(){
        Random random = new Random();
        int result = random.nextInt();
        return new TaskResult(result);
    }

    private TaskResult(int value){
        result = value;
    }

    public int get_value(){ return result; }
    public String toString(){ return Integer.toString(result); }
}