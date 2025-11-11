import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columns = {"ID", "Title", "Description", "Due Date", "Status"};
    private List<Task> tasks;

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() { return tasks.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public Object getValueAt(int row, int col) {
        Task t = tasks.get(row);
        switch (col) {
            case 0: return t.getId();
            case 1: return t.getTitle();
            case 2: return t.getDescription();
            case 3: return t.getDueDate();
            case 4: return t.getStatus();
        }
        return null;
    }

    @Override
    public String getColumnName(int col) { return columns[col]; }
}
