import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaskSchedulerUI extends JFrame {
    private TaskDAO dao = new TaskDAO();
    private JTable table;
    private JTextField titleField, descField, dateField;
    private JButton addBtn, refreshBtn, deleteBtn, completeBtn;

    public TaskSchedulerUI() {
        setTitle("Task Scheduler");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Header Label
        JLabel header = new JLabel("TASK SCHEDULER", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setForeground(new Color(33, 37, 41));
        add(header, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        titleField = new JTextField(15);
        descField = new JTextField(15);
        dateField = new JTextField("YYYY-MM-DD", 10);

        addBtn = styledButton("Add Task", new Color(0, 123, 255));
        refreshBtn = styledButton("Refresh", new Color(108, 117, 125));
        deleteBtn = styledButton("Delete", new Color(220, 53, 69));
        completeBtn = styledButton("Complete", new Color(40, 167, 69));

        c.gridx = 0; c.gridy = 0;
        inputPanel.add(new JLabel("Title:"), c);
        c.gridx = 1;
        inputPanel.add(titleField, c);

        c.gridx = 2;
        inputPanel.add(new JLabel("Description:"), c);
        c.gridx = 3;
        inputPanel.add(descField, c);

        c.gridx = 0; c.gridy = 1;
        inputPanel.add(new JLabel("Due Date:"), c);
        c.gridx = 1;
        inputPanel.add(dateField, c);

        c.gridx = 2;
        inputPanel.add(addBtn, c);
        c.gridx = 3;
        inputPanel.add(refreshBtn, c);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshTable();

        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 15));
        headerTable.setBackground(new Color(230, 230, 230));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Bottom Button Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(completeBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        addBtn.addActionListener(e -> {
            dao.addTask(titleField.getText(), descField.getText(), dateField.getText());
            clearInputs();
            refreshTable();
        });

        refreshBtn.addActionListener(e -> refreshTable());

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                dao.deleteTask(id);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.");
            }
        });

        completeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                dao.updateStatus(id, "complete");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to mark complete.");
            }
        });

        setVisible(true);
    }

    private JButton styledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(120, 35));
        return btn;
    }

    private void clearInputs() {
        titleField.setText("");
        descField.setText("");
        dateField.setText("YYYY-MM-DD");
    }

    private void refreshTable() {
        List<Task> tasks = dao.getAllTasks();
        table.setModel(new TaskTableModel(tasks));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskSchedulerUI::new);
    }
}
