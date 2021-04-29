package TESTING;

public enum  TaskStatus {

    Pending, Running, Accomplished, Failed;

    public String get_status(){
        return name();
    }
}