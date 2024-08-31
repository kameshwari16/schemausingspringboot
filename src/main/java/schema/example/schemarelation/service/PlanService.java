//1)
// package schema.example.schemarelation.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import schema.example.schemarelation.entity.Plan;
// import schema.example.schemarelation.repository.PlanRepository;

// import java.util.List;

// @Service
// public class PlanService {
//      @Autowired
//     private PlanRepository planRepository;

//     public List<Plan> getAllPlans() {
//         return planRepository.findAll();
//     }

//     public Plan getPlanById(Long id) {
//         return planRepository.findById(id).orElse(null);
//     }

//     public Plan createPlan(Plan plan) {
//         return planRepository.save(plan);
//     }

//     public Plan updatePlan(Long id, Plan planDetails) {
//         Plan plan = getPlanById(id);
//         if (plan != null) {
//             plan.setPlanName(planDetails.getPlanName());
//             return planRepository.save(plan);
//         }
//         return null;
//     }

//     public void deletePlan(Long id) {
//         planRepository.deleteById(id);
//     }

//     // public Plan getPlanByName(String planName) {
//     //     return planRepository.findByName(planName).orElse(null);
//     // }
// }

//2)
package schema.example.schemarelation.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schema.example.schemarelation.entity.Plan;
import schema.example.schemarelation.repository.PlanRepository;
import schema.example.schemarelation.response.PlanResponse;
@Service
public class PlanService {
     private static final Logger logger = LoggerFactory.getLogger(PlanService.class);
    @Autowired
    private PlanRepository planRepository;

    public PlanResponse getAllPlans() {
        logger.info("Fetching all plans");
        PlanResponse planResponse = new PlanResponse();
        planResponse.setPlans(planRepository.findAll());
        return planResponse;
    }

    public PlanResponse getPlanById(Long id) {
        logger.info("Fetching plan with id: {}", id);
        PlanResponse planResponse = new PlanResponse();
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            planResponse.addPlan(plan);
        } else {
            planResponse.setMessage("Plan not found with id: " + id);
            logger.warn("Plan not found with id: {}", id);
        }
        return planResponse;
    }
    
    public PlanResponse createPlan(Plan plan) {
        logger.info("Creating plan: {}", plan);
        PlanResponse planResponse = new PlanResponse();
        Plan savedPlan = planRepository.save(plan);
        planResponse.addPlan(savedPlan);
        logger.info("Plan created successfully: {}", savedPlan);
        return planResponse;
    }
    public PlanResponse updatePlan(Long id, Plan planDetails) {
        logger.info("Updating plan with id: {}", id);
        PlanResponse planResponse = new PlanResponse();
        Plan existingPlan = planRepository.findById(id).orElse(null);
        if (existingPlan != null) {
            existingPlan.setPlanName(planDetails.getPlanName());

            Plan updatedPlan = planRepository.save(existingPlan);
            planResponse.addPlan(updatedPlan);
            logger.info("Plan updated successfully: {}", updatedPlan);
        } else {
            planResponse.setMessage("Plan not found with id: " + id);
            logger.warn("Plan not found with id: {}", id);
        }
        return planResponse;
    }
    
    public PlanResponse deletePlan(Long id) {
        logger.info("Deleting plan with id: {}", id);
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return new PlanResponse("Plan successfully deleted.");
        } else {
            return new PlanResponse("Plan not found with id: " + id);
        }
    }
    
    
}

//3
