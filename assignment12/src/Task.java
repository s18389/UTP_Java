import java.util.Random;
import java.util.concurrent.Callable;

public final class Task implements Callable<TaskResult> {
    public final static int tasksNumbers = 20;

    private final static int minSleepTime = 3000;
    private final static int maxSleepTime = 5000;
    private final static int totalSleepTime = maxSleepTime - minSleepTime;
    private final static int threadFails = 3;
    private String name;
    private TaskStatus status;
    private TaskResult result;
    private long id;

    private static Random random;
    static {
        random = new Random();
    }


    public Task(long newId) {
            this("TASK nr " + newId);
    }

    public static Task getInstance(long newId){
        return new Task(newId);
    }

    private Task (String name){
        this.name = name;
        this.result = null;
        this.status = TaskStatus.Pending;
    }


    @Override
    public TaskResult call(){
        try{
            status = TaskStatus.Running;
            makeProcess();
            process();
        }catch (Exception e){
            status = TaskStatus.Failed;
            e.printStackTrace();
        }
        return result;
    }

    public TaskStatus get_status() { return status; }
    public String get_name(){
        return name;
    }
    public int get_sleepTime(){
        int randomValue = random.nextInt() % totalSleepTime;
        int sleepTime = minSleepTime + Math.abs(randomValue);
        return sleepTime;
    }

    private void makeProcess() throws InterruptedException {
        Thread.sleep(get_sleepTime());
    }

    private boolean processSuccess(){
        return ((random.nextInt() % threadFails) != 0);
    }

    private void process(){
        boolean success = processSuccess();
        if(success) {
            result = TaskResult.create();
            status = TaskStatus.Accomplished;
        }else {
            status = TaskStatus.Failed;
        }
    }

}
