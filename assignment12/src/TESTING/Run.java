package TESTING;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run extends JFrame {

    private static MySequence seq;
    private int rowNumber = 0;
    private int refreshTime = 200;

    private Table table;
    private final JTable taskTable;
    private JTableHeader jTableHeader;
    private JButton jButton;
    private ExecutorService executorService;

    public static void Run(){
        new Run();
        seq = new MySequence();
    }

    public Run() {
      table = new Table();
      taskTable = new TaskTable(table);
      jTableHeader = taskTable.getTableHeader();
      NewAction action = new NewAction();
      jButton = new JButton(action);
      SwingUtilities.invokeLater(() -> tableMain());
      executorService = Executors.newFixedThreadPool(20);
    }

    private void tableMain(){
        Container container = getContentPane();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(jTableHeader);
        container.add(taskTable);
        container.add(jButton);
        System.out.println(rowNumber);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread thread = new Thread( () -> run() );
        thread.start();
    }

    private void run(){
        try{
            while (true){
                Thread.sleep(refreshTime);
                table.fireTableDataChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private final class NewAction extends AbstractAction{
        private NewAction(){
            putValue(NAME, "ADD TASK");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Task task = Task.getInstance(seq.getNextValue());
            Future<TaskResult> future = executorService.submit(task);
            if(rowNumber == 20) rowNumber = 0;
            table.addTask(task, future, rowNumber);
            System.out.println(rowNumber);
            rowNumber += 1;
        }
    }
}