//1
// // package schema.example.schemarelation.controller;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;
// // import schema.example.schemarelation.service.*;
// // import schema.example.schemarelation.entity.Plan;

// // import java.util.List;

// // @RestController
// // public class PlanController {
// //     @Autowired
// //     private PlanService planService;
// //     @PostMapping("/schemacrud/plans")
// //     public Plan createPlan(@RequestBody Plan plan) {
// //         return planService.createPlan(plan);
// //     }
// //     @GetMapping("/schemacrud/plans")
// //     public List<Plan> getAllPlans() {
// //         return planService.getAllPlans();
// //     }

// //     @GetMapping("/schemacrud/plans/{id}")
// //     public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
// //         Plan plan = planService.getPlanById(id);
// //         return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
// //     }
// //     @PutMapping("/schemacrud/plans/{id}")
// //     public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan planDetails) {
// //         Plan updatedPlan = planService.updatePlan(id, planDetails);
// //         return updatedPlan != null ? ResponseEntity.ok(updatedPlan) : ResponseEntity.notFound().build();
// //     }

// //     @DeleteMapping("/schemacrud/plans/{id}")
// //     public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
// //         planService.deletePlan(id);
// //         return ResponseEntity.noContent().build();
// //     }

// // }


//2
// package schema.example.schemarelation.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import schema.example.schemarelation.entity.Plan;
// import schema.example.schemarelation.service.PlanService;

// import java.util.List;

// @RestController
// // @RequestMapping("/schemacrud/plans")
// public class PlanController {

//     @Autowired
//     private PlanService planService;

//     @PostMapping("/schemacrud/plans")
//     public ResponseEntity<?> createPlan(@RequestBody Plan plan) {
//         if (plan == null || plan.getPlanName() == null || plan.getPlanName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Plan name is required.");
//         }

//         Plan createdPlan = planService.createPlan(plan);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
//     }

//     @GetMapping("/schemacrud/plans")
//     public ResponseEntity<List<Plan>> getAllPlans() {
//         List<Plan> plans = planService.getAllPlans();
//         return ResponseEntity.ok(plans);
//     }

//     @GetMapping("/schemacrud/plans/{id}")
//     public ResponseEntity<?> getPlanById(@PathVariable Long id) {
//         Plan plan = planService.getPlanById(id);
//         if (plan == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found with ID: " + id);
//         }
//         return ResponseEntity.ok(plan);
//     }

//     @PutMapping("/schemacrud/plans/{id}")
//     public ResponseEntity<?> updatePlan(@PathVariable Long id, @RequestBody Plan planDetails) {
//         if (planDetails == null || planDetails.getPlanName() == null || planDetails.getPlanName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Plan name is required.");
//         }

//         Plan updatedPlan = planService.updatePlan(id, planDetails);
//         if (updatedPlan == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found with ID: " + id);
//         }
//         return ResponseEntity.ok(updatedPlan);
//         // return ResponseEntity.status(HttpStatus.UPDATED).body(updatedPlan);
//     }

//     @DeleteMapping("/schemacrud/plans/{id}")
//     public ResponseEntity<?> deletePlan(@PathVariable Long id) {
    //     Plan plan = planService.getPlanById(id);
    //     if (plan == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found with ID: " + id);
    //     }

    //     planService.deletePlan(id);
    //     // return ResponseEntity.noContent().build();
    //     return ResponseEntity.ok("Plan with ID: " + id + " was successfully deleted.");
    // }
// }

//3
package schema.example.schemarelation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schema.example.schemarelation.entity.Plan;
import schema.example.schemarelation.service.PlanService;
import schema.example.schemarelation.response.PlanResponse;

@RestController
public class PlanController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;

    @GetMapping("/schemacrud/plans")
    public ResponseEntity<PlanResponse> getAllPlans() {
        logger.info("Received request to get all plans");
        PlanResponse response = planService.getAllPlans();
        if (response.getPlans().isEmpty()) {
            logger.info("No plans found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/schemacrud/plans/{id}")
    public ResponseEntity<PlanResponse> getPlanById(@PathVariable Long id) {
        logger.info("Received request to get plan with id: {}", id);
        PlanResponse response = planService.getPlanById(id);
        if (response.getPlans().isEmpty() && response.getMessage() != null) {
            logger.warn("Plan not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/schemacrud/plans")
    public ResponseEntity<PlanResponse> createPlan(@RequestBody Plan plan) {
        logger.info("Received request to create plan: {}", plan);
        PlanResponse response = planService.createPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/schemacrud/plans/{id}")
    public ResponseEntity<PlanResponse> updatePlan(@PathVariable Long id, @RequestBody Plan planDetails) {
        logger.info("Received request to update plan with id: {}", id);
        PlanResponse response = planService.updatePlan(id, planDetails);
        if (response.getPlans().isEmpty() && response.getMessage() != null) {
            logger.warn("Plan not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/schemacrud/plans/{id}")
public ResponseEntity<PlanResponse> deletePlan(@PathVariable Long id) {
    logger.info("Received request to delete plan with id: {}", id);
    PlanResponse response = planService.deletePlan(id);
    if (response.getMessage().contains("not found")) {
        logger.warn("Plan not found with id: {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
}

}
