package TESTING;

import java.util.concurrent.Future;

public final class TaskWrapper {

    private final Task task;
    private final Future<TaskResult> future;
    private TaskResult TaskResult;

    public TaskWrapper(Task task, Future future){
        this.task = task;
        this.future = future;
    }

    public boolean is_done(){
        return future.isDone();
    }
    public boolean is_cancelled(){
        return future.isCancelled();
    }

    public TaskStatus get_status(){
        return task.get_status();
    }
    public String get_name(){
        return task.get_name();
    }

    public TaskResult get_result(){
        try {
            if(is_done()) TaskResult = future.get();
            return TaskResult;
        } catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }
}
