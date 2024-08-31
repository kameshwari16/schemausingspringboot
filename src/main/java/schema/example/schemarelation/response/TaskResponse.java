package schema.example.schemarelation.response;
import schema.example.schemarelation.entity.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskResponse {
    private List<Task> tasks = new ArrayList<>();
    private String message;
    public TaskResponse(String string) {
        //TODO Auto-generated constructor stub
    }

    public TaskResponse() {
        //TODO Auto-generated constructor stub
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // public Task getTask() {
    //     return tasks.isEmpty() ? null : tasks.get(0);
    // }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}