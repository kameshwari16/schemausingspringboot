// package schema.example.schemarelation.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import schema.example.schemarelation.service.*;
// import schema.example.schemarelation.entity.UserTaskMapper;
// @RestController
// public class UserTaskMapperController {
//      @Autowired
//     private UserTaskMapperService userTaskMapperService;

//     @GetMapping("/schemacrud/usertaskmappers")
//     public List<UserTaskMapper> getAllMappings() {
//         return userTaskMapperService.getAllMappings();
//     }

//     @GetMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<UserTaskMapper> getMappingById(@PathVariable Long id) {
//         UserTaskMapper mapping = userTaskMapperService.getMappingById(id);
//         return mapping != null ? ResponseEntity.ok(mapping) : ResponseEntity.notFound().build();
//     }

//     @PostMapping("/schemacrud/usertaskmappers")
//     public UserTaskMapper createMapping(@RequestBody UserTaskMapper mapping) {
//         return userTaskMapperService.createMapping(mapping);
//     }

//     @PutMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<UserTaskMapper> updateMapping(@PathVariable Long id, @RequestBody UserTaskMapper mappingDetails) {
//         UserTaskMapper updatedMapping = userTaskMapperService.updateMapping(id, mappingDetails);
//         return updatedMapping != null ? ResponseEntity.ok(updatedMapping) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<Void> deleteMapping(@PathVariable Long id) {
//         userTaskMapperService.deleteMapping(id);
//         return ResponseEntity.noContent().build();

//     }
// }


// package schema.example.schemarelation.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import schema.example.schemarelation.entity.UserTaskMapper;
// import schema.example.schemarelation.service.UserTaskMapperService;

// import java.util.List;

// @RestController
// public class UserTaskMapperController {

//     @Autowired
//     private UserTaskMapperService userTaskMapperService;

//     @GetMapping("/schemacrud/usertaskmappers")
//     public ResponseEntity<List<UserTaskMapper>> getAllMappings() {
//         List<UserTaskMapper> mappings = userTaskMapperService.getAllMappings();
//         return ResponseEntity.ok(mappings);
//     }

//     @GetMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<?> getMappingById(@PathVariable Long id) {
//         UserTaskMapper mapping = userTaskMapperService.getMappingById(id);
//         return mapping != null ? ResponseEntity.ok(mapping) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mapping not found with ID: " + id);
//     }

//     @PostMapping("/schemacrud/usertaskmappers")
//     public ResponseEntity<?> createMapping(@RequestBody UserTaskMapper mapping) {
//         if (mapping == null || mapping.getUser() == null || mapping.getTask() == null) {
//             return ResponseEntity.badRequest().body("Both User and Task are required for mapping.");
//         }

//         UserTaskMapper createdMapping = userTaskMapperService.createMapping(mapping);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdMapping);
//     }

//     @PutMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<?> updateMapping(@PathVariable Long id, @RequestBody UserTaskMapper mappingDetails) {
//         if (mappingDetails == null || mappingDetails.getUser() == null || mappingDetails.getTask() == null) {
//             return ResponseEntity.badRequest().body("Both User and Task are required for updating the mapping.");
//         }

//         UserTaskMapper updatedMapping = userTaskMapperService.updateMapping(id, mappingDetails);
//         return updatedMapping != null ? ResponseEntity.ok(updatedMapping) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mapping not found with ID: " + id);
//     }

//     @DeleteMapping("/schemacrud/usertaskmappers/{id}")
//     public ResponseEntity<?> deleteMapping(@PathVariable Long id) {
//         UserTaskMapper mapping = userTaskMapperService.getMappingById(id);
//         if (mapping == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mapping not found with ID: " + id);
//         }

//         userTaskMapperService.deleteMapping(id);
//         return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                 .header("X-Message", "Mapping with ID: " + id + " was successfully deleted.")
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

import schema.example.schemarelation.entity.UserTaskMapper;
import schema.example.schemarelation.service.UserTaskMapperService;
import schema.example.schemarelation.response.UserTaskMapperResponse;

@RestController
@RequestMapping("/schemacrud/usertaskmappers")
public class UserTaskMapperController {

    private static final Logger logger = LoggerFactory.getLogger(UserTaskMapperController.class);

    @Autowired
    private UserTaskMapperService userTaskMapperService;

    @GetMapping
    public ResponseEntity<UserTaskMapperResponse> getAllUserTaskMappers() {
        logger.info("Received request to get all UserTaskMappers");
        UserTaskMapperResponse response = userTaskMapperService.getAllUserTaskMappers();
        if (response.getUserTaskMappers().isEmpty()) {
            logger.info("No UserTaskMappers found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTaskMapperResponse> getUserTaskMapperById(@PathVariable Long id) {
        logger.info("Received request to get UserTaskMapper with id: {}", id);
        UserTaskMapperResponse response = userTaskMapperService.getUserTaskMapperById(id);
        if (response.getUserTaskMappers().isEmpty()) {
            logger.warn("UserTaskMapper not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserTaskMapperResponse("UserTaskMapper not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserTaskMapperResponse> createUserTaskMapper(@RequestBody UserTaskMapper userTaskMapper) {
        logger.info("Received request to create UserTaskMapper: {}", userTaskMapper);
        UserTaskMapperResponse response = userTaskMapperService.createUserTaskMapper(userTaskMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTaskMapperResponse> updateUserTaskMapper(@PathVariable Long id, @RequestBody UserTaskMapper userTaskMapperDetails) {
        logger.info("Received request to update UserTaskMapper with id: {}", id);
        UserTaskMapperResponse response = userTaskMapperService.updateUserTaskMapper(id, userTaskMapperDetails);
        if (response.getUserTaskMappers().isEmpty()) {
            logger.warn("UserTaskMapper not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserTaskMapperResponse("UserTaskMapper not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserTaskMapperResponse> deleteUserTaskMapper(@PathVariable Long id) {
        logger.info("Received request to delete UserTaskMapper with id: {}", id);
        UserTaskMapperResponse response = userTaskMapperService.deleteUserTaskMapper(id);
        if (response.getUserTaskMappers().isEmpty()) {
            logger.warn("UserTaskMapper not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserTaskMapperResponse("UserTaskMapper not found with id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
