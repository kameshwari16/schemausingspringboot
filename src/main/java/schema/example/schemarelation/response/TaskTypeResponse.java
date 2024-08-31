package schema.example.schemarelation.response;

import schema.example.schemarelation.entity.TaskType;
import java.util.ArrayList;
import java.util.List;

public class TaskTypeResponse {
    private List<TaskType> taskTypes = new ArrayList<>();
    private String message;

    public TaskTypeResponse() {
        // Default constructor
    }

    public TaskTypeResponse(String message) {
        this.message = message;
    }

    public List<TaskType> getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(List<TaskType> taskTypes) {
        this.taskTypes = taskTypes;
    }

    public void addTaskType(TaskType taskType) {
        this.taskTypes.add(taskType);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
