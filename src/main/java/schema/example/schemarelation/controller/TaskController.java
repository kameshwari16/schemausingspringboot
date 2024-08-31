// package schema.example.schemarelation.controller;
// import schema.example.schemarelation.entity.*;
// import schema.example.schemarelation.service.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// @RestController
// public class TaskController {
//     @Autowired
//     private TaskService taskService;
//     @PostMapping("/schemacrud/tasks")
//     public Task createTask(@RequestBody Task task) {
//         return taskService.createTask(task);
//     }

//     @GetMapping("/schemacrud/tasks")
//     public List<Task> getAllTasks() {
//         return taskService.getAllTasks();
//     }

//     @GetMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//         Task task = taskService.getTaskById(id);
//         return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
//     }

//     @PutMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//         Task updatedTask = taskService.updateTask(id, taskDetails);
//         return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//         taskService.deleteTask(id);
//         return ResponseEntity.noContent().build();
//     }
// }


// package schema.example.schemarelation.controller;

// import schema.example.schemarelation.entity.Task;
// import schema.example.schemarelation.service.TaskService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// public class TaskController {

//     @Autowired
//     private TaskService taskService;

//     @PostMapping("/schemacrud/tasks")
//     public ResponseEntity<?> createTask(@RequestBody Task task) {
//         if (task == null || task.getTaskName() == null || task.getTaskName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Task name is required.");
//         }

//         Task createdTask = taskService.createTask(task);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//     }

//     @GetMapping("/schemacrud/tasks")
//     public ResponseEntity<List<Task>> getAllTasks() {
//         List<Task> tasks = taskService.getAllTasks();
//         return ResponseEntity.ok(tasks);
//     }

//     @GetMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<?> getTaskById(@PathVariable Long id) {
//         Task task = taskService.getTaskById(id);
//         if (task == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
//         }
//         return ResponseEntity.ok(task);
//     }

//     @PutMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//         if (taskDetails == null || taskDetails.getTaskName() == null || taskDetails.getTaskName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Task name is required.");
//         }

//         Task updatedTask = taskService.updateTask(id, taskDetails);
//         if (updatedTask == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
//         }
//         return ResponseEntity.ok(updatedTask);
//     }

//     @DeleteMapping("/schemacrud/tasks/{id}")
//     public ResponseEntity<?> deleteTask(@PathVariable Long id) {
//         Task task = taskService.getTaskById(id);
//         if (task == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
//         }

//         taskService.deleteTask(id);
//         return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                 .header("X-Message", "Task with ID: " + id + " was successfully deleted.")
//                 .build();
//     }
// }



package schema.example.schemarelation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import schema.example.schemarelation.entity.Task;
import schema.example.schemarelation.service.TaskService;
import schema.example.schemarelation.response.TaskResponse;

@RestController
@RequestMapping("/schemacrud/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskResponse> getAllTasks() {
        logger.info("Received request to get all tasks");
        TaskResponse response = taskService.getAllTasks();
        if (response.getTasks().isEmpty()) {
            logger.info("No tasks found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        logger.info("Received request to get task with id: {}", id);
        TaskResponse response = taskService.getTaskById(id);
        if (response.getTasks().isEmpty() && response.getMessage() != null) {
            logger.warn("Task not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody Task task) {
        logger.info("Received request to create task: {}", task);
        TaskResponse response = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        logger.info("Received request to update task with id: {}", id);
        TaskResponse response = taskService.updateTask(id, taskDetails);
        if (response.getTasks().isEmpty() && response.getMessage() != null) {
            logger.warn("Task not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        logger.info("Received request to delete task with id: {}", id);
        TaskResponse response = taskService.deleteTask(id);
        if (response.getMessage().contains("not found")) {
            logger.warn("Task not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
