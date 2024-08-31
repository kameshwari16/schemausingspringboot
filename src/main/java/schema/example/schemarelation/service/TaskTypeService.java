// package schema.example.schemarelation.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import schema.example.schemarelation.entity.TaskType;
// import schema.example.schemarelation.repository.TaskTypeRepository;

// import java.util.List;

// @Service
// public class TaskTypeService {
//     @Autowired
//     private TaskTypeRepository taskTypeRepository;

//     public List<TaskType> getAllTaskTypes() {
//         return taskTypeRepository.findAll();
//     }

//     public TaskType getTaskTypeById(Long id) {
//         return taskTypeRepository.findById(id).orElse(null);
//     }

//     public TaskType createTaskType(TaskType taskType) {
//         return taskTypeRepository.save(taskType);
//     }

//     public TaskType updateTaskType(Long id, TaskType taskTypeDetails) {
//         TaskType taskType = getTaskTypeById(id);
//         if (taskType != null) {
//             taskType.setTypeName(taskTypeDetails.getTypeName());
//             return taskTypeRepository.save(taskType);
//         }
//         return null;
//     }

//     public void deleteTaskType(Long id) {
//         taskTypeRepository.deleteById(id);
//     }
// }

package schema.example.schemarelation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schema.example.schemarelation.entity.TaskType;
import schema.example.schemarelation.repository.TaskTypeRepository;
import schema.example.schemarelation.response.TaskTypeResponse;

import java.util.Optional;

@Service
public class TaskTypeService {

    private static final Logger logger = LoggerFactory.getLogger(TaskTypeService.class);

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    public TaskTypeResponse getAllTaskTypes() {
        logger.info("Fetching all task types");
        TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
        taskTypeResponse.setTaskTypes(taskTypeRepository.findAll());
        return taskTypeResponse;
    }

    public TaskTypeResponse getTaskTypeById(Long id) {
        logger.info("Fetching task type with id: {}", id);
        TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
        Optional<TaskType> taskTypeOptional = taskTypeRepository.findById(id);
        if (taskTypeOptional.isPresent()) {
            taskTypeResponse.addTaskType(taskTypeOptional.get());
            logger.info("Task Type found: {}", taskTypeOptional.get());
        } else {
            taskTypeResponse.setMessage("Task Type not found with id: " + id);
            logger.warn("Task Type not found with id: {}", id);
        }
        return taskTypeResponse;
    }

    public TaskTypeResponse createTaskType(TaskType taskType) {
        logger.info("Creating task type: {}", taskType);
        TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
        TaskType savedTaskType = taskTypeRepository.save(taskType);
        taskTypeResponse.addTaskType(savedTaskType);
        logger.info("Task Type created successfully: {}", savedTaskType);
        return taskTypeResponse;
    }

    public TaskTypeResponse updateTaskType(Long id, TaskType taskTypeDetails) {
        logger.info("Updating task type with id: {}", id);
        TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
        Optional<TaskType> taskTypeOptional = taskTypeRepository.findById(id);
        if (taskTypeOptional.isPresent()) {
            TaskType existingTaskType = taskTypeOptional.get();
            existingTaskType.setTypeName(taskTypeDetails.getTypeName());
            TaskType updatedTaskType = taskTypeRepository.save(existingTaskType);
            taskTypeResponse.addTaskType(updatedTaskType);
            logger.info("Task Type updated successfully: {}", updatedTaskType);
        } else {
            taskTypeResponse.setMessage("Task Type not found with id: " + id);
            logger.warn("Task Type not found with id: {}", id);
        }
        return taskTypeResponse;
    }

    public TaskTypeResponse deleteTaskType(Long id) {
        logger.info("Deleting task type with id: {}", id);
        TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
        Optional<TaskType> taskTypeOptional = taskTypeRepository.findById(id);
        if (taskTypeOptional.isPresent()) {
            taskTypeRepository.deleteById(id);
            taskTypeResponse.setMessage("Task Type deleted successfully.");
            logger.info("Task Type deleted successfully with id: {}", id);
        } else {
            taskTypeResponse.setMessage("Task Type not found with id: " + id);
            logger.warn("Task Type not found with id: {}", id);
        }
        return taskTypeResponse;
    }
}
