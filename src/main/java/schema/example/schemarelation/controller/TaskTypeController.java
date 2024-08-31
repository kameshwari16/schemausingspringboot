// package schema.example.schemarelation.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import schema.example.schemarelation.entity.TaskType;
// import schema.example.schemarelation.service.*;
// import java.util.List;

// @RestController
// public class TaskTypeController {
//      @Autowired
//     private TaskTypeService taskTypeService;

//     @GetMapping("/schemacrud/tasktypes")
//     public List<TaskType> getAllTaskTypes() {
//         return taskTypeService.getAllTaskTypes();
//     }

//     @GetMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<TaskType> getTaskTypeById(@PathVariable Long id) {
//         TaskType taskType = taskTypeService.getTaskTypeById(id);
//         return taskType != null ? ResponseEntity.ok(taskType) : ResponseEntity.notFound().build();
//     }

//     @PostMapping("/schemacrud/tasktypes")
//     public TaskType createTaskType(@RequestBody TaskType taskType) {
//         return taskTypeService.createTaskType(taskType);
//     }

//     @PutMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<TaskType> updateTaskType(@PathVariable Long id, @RequestBody TaskType taskTypeDetails) {
//         TaskType updatedTaskType = taskTypeService.updateTaskType(id, taskTypeDetails);
//         return updatedTaskType != null ? ResponseEntity.ok(updatedTaskType) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<Void> deleteTaskType(@PathVariable Long id) {
//         taskTypeService.deleteTaskType(id);
//         return ResponseEntity.noContent().build();
//     }
// }



// package schema.example.schemarelation.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import schema.example.schemarelation.entity.TaskType;
// import schema.example.schemarelation.service.TaskTypeService;

// import java.util.List;

// @RestController
// public class TaskTypeController {

//     @Autowired
//     private TaskTypeService taskTypeService;

//     @GetMapping("/schemacrud/tasktypes")
//     public ResponseEntity<List<TaskType>> getAllTaskTypes() {
//         List<TaskType> taskTypes = taskTypeService.getAllTaskTypes();
//         return ResponseEntity.ok(taskTypes);
//     }

//     @GetMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<?> getTaskTypeById(@PathVariable Long id) {
//         TaskType taskType = taskTypeService.getTaskTypeById(id);
//         return taskType != null ? ResponseEntity.ok(taskType) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("TaskType not found with ID: " + id);
//     }

//     @PostMapping("/schemacrud/tasktypes")
//     public ResponseEntity<?> createTaskType(@RequestBody TaskType taskType) {
//         if (taskType == null || taskType.getTypeName() == null || taskType.getTypeName().isEmpty()) {
//             return ResponseEntity.badRequest().body("TaskType name is required.");
//         }

//         TaskType createdTaskType = taskTypeService.createTaskType(taskType);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskType);
//     }

//     @PutMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<?> updateTaskType(@PathVariable Long id, @RequestBody TaskType taskTypeDetails) {
//         if (taskTypeDetails == null || taskTypeDetails.getTypeName() == null || taskTypeDetails.getTypeName().isEmpty()) {
//             return ResponseEntity.badRequest().body("TaskType name is required.");
//         }

//         TaskType updatedTaskType = taskTypeService.updateTaskType(id, taskTypeDetails);
//         return updatedTaskType != null ? ResponseEntity.ok(updatedTaskType) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("TaskType not found with ID: " + id);
//     }

//     @DeleteMapping("/schemacrud/tasktypes/{id}")
//     public ResponseEntity<?> deleteTaskType(@PathVariable Long id) {
//         TaskType taskType = taskTypeService.getTaskTypeById(id);
//         if (taskType == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TaskType not found with ID: " + id);
//         }

//         taskTypeService.deleteTaskType(id);
//         return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                 .header("X-Message", "TaskType with ID: " + id + " was successfully deleted.")
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

import schema.example.schemarelation.entity.TaskType;
import schema.example.schemarelation.service.TaskTypeService;
import schema.example.schemarelation.response.TaskTypeResponse;

@RestController
@RequestMapping("/schemacrud/tasktypes")
public class TaskTypeController {

    private static final Logger logger = LoggerFactory.getLogger(TaskTypeController.class);

    @Autowired
    private TaskTypeService taskTypeService;

    @GetMapping
    public ResponseEntity<TaskTypeResponse> getAllTaskTypes() {
        logger.info("Received request to get all task types");
        TaskTypeResponse response = taskTypeService.getAllTaskTypes();
        if (response.getTaskTypes().isEmpty()) {
            logger.info("No task types found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTypeResponse> getTaskTypeById(@PathVariable Long id) {
        logger.info("Received request to get task type with id: {}", id);
        TaskTypeResponse response = taskTypeService.getTaskTypeById(id);
        if (response.getTaskTypes().isEmpty()) {
            logger.warn("Task Type not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new TaskTypeResponse("Task Type not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskTypeResponse> createTaskType(@RequestBody TaskType taskType) {
        logger.info("Received request to create task type: {}", taskType);
        TaskTypeResponse response = taskTypeService.createTaskType(taskType);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskTypeResponse> updateTaskType(@PathVariable Long id, @RequestBody TaskType taskTypeDetails) {
        logger.info("Received request to update task type with id: {}", id);
        TaskTypeResponse response = taskTypeService.updateTaskType(id, taskTypeDetails);
        if (response.getTaskTypes().isEmpty()) {
            logger.warn("Task Type not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new TaskTypeResponse("Task Type not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskTypeResponse> deleteTaskType(@PathVariable Long id) {
        logger.info("Received request to delete task type with id: {}", id);
        TaskTypeResponse response = taskTypeService.deleteTaskType(id);
        if (response.getTaskTypes().isEmpty()) {
            logger.warn("Task Type not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new TaskTypeResponse("Task Type not found with id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
