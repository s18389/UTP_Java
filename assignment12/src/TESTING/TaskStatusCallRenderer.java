package TESTING;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public final class TaskStatusCallRenderer extends JLabel implements TableCellRenderer {

    private static final TableCellRenderer renderer = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value != null){
            JLabel label = new JLabel();
            label.setOpaque(true);
            TaskStatus taskStatus = (TaskStatus) value;
            label.setText(taskStatus.get_status());
            label.setBackground(TaskStatusPresentationUtility.getBackgroundColor(taskStatus));
            return label;
        }
        return this;
    }
}