import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Table extends DefaultTableModel {

    private static final int numberOfColumns = 3;
    private static final int countRows = Task.tasksNumbers;

    public static final int nameColumnIndex = 0;
    public static final int statusColumnIndex = 1;
    public static final int resultColumnIndex = 2;

    private final List<TaskWrapper> taskList;

    public Table(){
        taskList = new ArrayList<TaskWrapper>(countRows);
    }

    public int getColumnCount(){
        return numberOfColumns;
    }
    public int getRowCount(){
        return countRows;
    }

    public synchronized Object getValueAt(int rowIndex, int columnIndex){
        if((taskList == null) || (rowIndex < 0) || (rowIndex>getRowCount()) || (columnIndex < 0) || (columnIndex > getColumnCount())) return null;

        TaskWrapper task = null;

        if(rowIndex < taskList.size()) task = taskList.get(rowIndex);

        return getValue(task, columnIndex);
    }

    public synchronized void addTask(Task task, Future<TaskResult> future, int index){
        if(index >= 0){
            TaskWrapper wrapper = new TaskWrapper(task, future);
            if(index < taskList.size()) taskList.set(index, wrapper);
            else taskList.add(index, wrapper);

            fireTableDataChanged();
        }
    }

    public boolean isFull(){
        return taskList.size() == countRows;
    }

    private Object getValue(TaskWrapper task, int columnIndex){
        if(task == null){
            return null;
        }
        switch (columnIndex){
            case nameColumnIndex: return task.get_name();
            case statusColumnIndex: return task.get_status();
            case resultColumnIndex: return task.get_result();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case nameColumnIndex: return "Name";
            case statusColumnIndex: return "Status";
            case resultColumnIndex: return "Result";
            default: return "WRONG NUMBER LOL";
        }
    }
}
