// package schema.example.schemarelation.response;
// import schema.example.schemarelation.entity.Plan;
// import java.util.ArrayList;
// import java.util.List;
// public class PlanResponse {
//     private List<Plan> plans = new ArrayList<>();
//     private Plan plan;
//     private String message;
//     public PlanResponse(String message) {
//         this.message = message;
//     }

//     public PlanResponse() {
//         //TODO Auto-generated constructor stub
//     }

//     public List<Plan> getPlans() {
//         return plans;
//     }

//     public void setPlans(List<Plan> plans) {
//         this.plans = plans;
//     }

//     public void addPlan(Plan plan) {
//         this.plans.add(plan);
//     }

//     public Plan getPlan() {
//         return plan;
//     }
//     public void setPlan(Plan plan) {
//         this.plan = plan;
//     }

//     public String getMessage() {
//         return message;
//     }
//     public void setMessage(String message) {
//         this.message = message;
//     }
// }
package schema.example.schemarelation.response;

import schema.example.schemarelation.entity.Plan;
import java.util.ArrayList;
import java.util.List;

public class PlanResponse {
    private List<Plan> plans = new ArrayList<>();
    private String message;

    public PlanResponse() {
        // Default constructor
    }

    public PlanResponse(String message) {
        this.message = message;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
