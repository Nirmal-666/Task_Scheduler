public class Task {
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private String status;

    public Task(int id, String title, String description, String dueDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDueDate() { return dueDate; }
    public String getStatus() { return status; }
}
