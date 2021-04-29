package TESTING;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public final class TaskTable extends JTable {

    private final TableCellRenderer renderer;

    public TaskTable(Table table){
        super(table);
        renderer = new TaskStatusCallRenderer();
    }

    public TableCellRenderer getCallRenderer(int rowIndex, int columnIndex){
        if(columnIndex == Table.statusColumnIndex){
            return renderer;
        }
        return super.getCellRenderer(rowIndex, columnIndex);
    }
}



//FINISHED
