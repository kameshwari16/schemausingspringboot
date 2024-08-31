// package schema.example.schemarelation.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import schema.example.schemarelation.entity.User;
// import schema.example.schemarelation.entity.UserTaskMapper;
// import schema.example.schemarelation.repository.UserTaskMapperRepository;
// import java.util.List;
// @Service
// public class UserTaskMapperService {
//     @Autowired
//     private UserTaskMapperRepository userTaskMapperRepository;
//     @Autowired
//     private UserService userService;
//     public List<UserTaskMapper> getAllMappings() {
//         return userTaskMapperRepository.findAll();
//     }

//     public UserTaskMapper getMappingById(Long id) {
//         return userTaskMapperRepository.findById(id).orElse(null);
//     }

//     public UserTaskMapper createMapping(UserTaskMapper mapping) {
//         User user = userService.getUserById(mapping.getUser().getUserId());
//         if (user == null) {
//             throw new IllegalArgumentException("User not found with ID: " + mapping.getUser().getUserId());
//         }
        
//         return userTaskMapperRepository.save(mapping);
//     }

//     public UserTaskMapper updateMapping(Long id, UserTaskMapper mappingDetails) {
//         UserTaskMapper mapping = getMappingById(id);
//         if (mapping != null) {
//             User user = userService.getUserById(mappingDetails.getUser().getUserId());
//             if (user == null) {
//                 throw new IllegalArgumentException("User not found with ID: " + mappingDetails.getUser().getUserId());
//             }
//             mapping.setStatus(mappingDetails.getStatus());
//             mapping.setUser(mappingDetails.getUser());
//             mapping.setTask(mappingDetails.getTask());
//             return userTaskMapperRepository.save(mapping);
//         }
//         return null;
//     }

//     public void deleteMapping(Long id) {
//         userTaskMapperRepository.deleteById(id);
//     }
// }

package schema.example.schemarelation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schema.example.schemarelation.entity.UserTaskMapper;
import schema.example.schemarelation.repository.UserTaskMapperRepository;
import schema.example.schemarelation.response.UserTaskMapperResponse;

@Service
public class UserTaskMapperService {

    private static final Logger logger = LoggerFactory.getLogger(UserTaskMapperService.class);

    @Autowired
    private UserTaskMapperRepository userTaskMapperRepository;

    public UserTaskMapperResponse getAllUserTaskMappers() {
        logger.info("Fetching all UserTaskMappers");
        UserTaskMapperResponse userTaskMapperResponse = new UserTaskMapperResponse();
        userTaskMapperResponse.setUserTaskMappers(userTaskMapperRepository.findAll());
        return userTaskMapperResponse;
    }

    public UserTaskMapperResponse getUserTaskMapperById(Long id) {
        logger.info("Fetching UserTaskMapper with id: {}", id);
        UserTaskMapperResponse userTaskMapperResponse = new UserTaskMapperResponse();
        UserTaskMapper userTaskMapper = userTaskMapperRepository.findById(id).orElse(null);
        if (userTaskMapper != null) {
            userTaskMapperResponse.addUserTaskMapper(userTaskMapper);
            logger.info("UserTaskMapper found: {}", userTaskMapper);
        } else {
            logger.warn("UserTaskMapper not found with id: {}", id);
        }
        return userTaskMapperResponse;
    }

    public UserTaskMapperResponse createUserTaskMapper(UserTaskMapper userTaskMapper) {
        logger.info("Creating UserTaskMapper: {}", userTaskMapper);
        UserTaskMapperResponse userTaskMapperResponse = new UserTaskMapperResponse();
        UserTaskMapper savedUserTaskMapper = userTaskMapperRepository.save(userTaskMapper);
        userTaskMapperResponse.addUserTaskMapper(savedUserTaskMapper);
        logger.info("UserTaskMapper created successfully: {}", savedUserTaskMapper);
        return userTaskMapperResponse;
    }

    public UserTaskMapperResponse updateUserTaskMapper(Long id, UserTaskMapper userTaskMapperDetails) {
        logger.info("Updating UserTaskMapper with id: {}", id);
        UserTaskMapperResponse userTaskMapperResponse = new UserTaskMapperResponse();
        UserTaskMapper userTaskMapper = userTaskMapperRepository.findById(id).orElse(null);
        if (userTaskMapper != null) {
            userTaskMapper.setUser(userTaskMapperDetails.getUser());
            userTaskMapper.setTask(userTaskMapperDetails.getTask());
            UserTaskMapper updatedUserTaskMapper = userTaskMapperRepository.save(userTaskMapper);
            userTaskMapperResponse.addUserTaskMapper(updatedUserTaskMapper);
            logger.info("UserTaskMapper updated successfully: {}", updatedUserTaskMapper);
        } else {
            logger.warn("UserTaskMapper not found with id: {}", id);
        }
        return userTaskMapperResponse;
    }

    public UserTaskMapperResponse deleteUserTaskMapper(Long id) {
        logger.info("Deleting UserTaskMapper with id: {}", id);
        UserTaskMapperResponse userTaskMapperResponse = new UserTaskMapperResponse();
        UserTaskMapper userTaskMapper = userTaskMapperRepository.findById(id).orElse(null);
        if (userTaskMapper != null) {
            userTaskMapperRepository.deleteById(id);
            logger.info("UserTaskMapper deleted successfully with id: {}", id);
        } else {
            logger.warn("UserTaskMapper not found with id: {}", id);
        }
        return userTaskMapperResponse;
    }
}
