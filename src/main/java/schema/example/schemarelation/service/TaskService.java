// package schema.example.schemarelation.service;
// import schema.example.schemarelation.entity.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import schema.example.schemarelation.repository.TaskRepository;

// import java.util.List;

// @Service
// public class TaskService {
//     @Autowired
//     private TaskRepository taskRepository;

//     public List<Task> getAllTasks() {
//         return taskRepository.findAll();
//     }

//     public Task getTaskById(Long id) {
//         return taskRepository.findById(id).orElse(null);
//     }

//     public Task createTask(Task task) {
//         return taskRepository.save(task);
//     }

//     public Task updateTask(Long id, Task taskDetails) {
//         Task task = getTaskById(id);
//         if (task != null) {
//             task.setTaskName(taskDetails.getTaskName());
//             task.setTaskType(taskDetails.getTaskType());
//             task.setPlan(taskDetails.getPlan());
//             return taskRepository.save(task);
//         }
//         return null;
//     }

//     public void deleteTask(Long id) {
//         taskRepository.deleteById(id);
//     }

// }

package schema.example.schemarelation.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schema.example.schemarelation.entity.Task;
import schema.example.schemarelation.repository.TaskRepository;
import schema.example.schemarelation.response.TaskResponse;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse getAllTasks() {
        logger.info("Fetching all tasks");
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTasks(taskRepository.findAll());
        return taskResponse;
    }

    public TaskResponse getTaskById(Long id) {
        logger.info("Fetching task with id: {}", id);
        TaskResponse taskResponse = new TaskResponse();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskResponse.addTask(task.get());
            logger.info("Task found: {}", task.get());
        } else {
            taskResponse.setMessage("Task not found with id: " + id);
            logger.warn("Task not found with id: {}", id);
        }
        return taskResponse;
    }

    public TaskResponse createTask(Task task) {
        logger.info("Creating task: {}", task);
        TaskResponse taskResponse = new TaskResponse();
        Task savedTask = taskRepository.save(task);
        taskResponse.addTask(savedTask);
        logger.info("Task created successfully: {}", savedTask);
        return taskResponse;
    }

    public TaskResponse updateTask(Long id, Task taskDetails) {
        logger.info("Updating task with id: {}", id);
        TaskResponse taskResponse = new TaskResponse();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setTaskName(taskDetails.getTaskName());
            existingTask.setTaskType(taskDetails.getTaskType());
            existingTask.setPlan(taskDetails.getPlan());
            Task updatedTask = taskRepository.save(existingTask);
            taskResponse.addTask(updatedTask);
            logger.info("Task updated successfully: {}", updatedTask);
        } else {
            taskResponse.setMessage("Task not found with id: " + id);
            logger.warn("Task not found with id: {}", id);
        }
        return taskResponse;
    }

    public TaskResponse deleteTask(Long id) {
        logger.info("Deleting task with id: {}", id);
        TaskResponse taskResponse = new TaskResponse();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            taskResponse.setMessage("Task deleted successfully.");
            logger.info("Task deleted successfully with id: {}", id);
        } else {
            taskResponse.setMessage("Task not found with id: " + id);
            logger.warn("Task not found with id: {}", id);
        }
        return taskResponse;
    }
}
