package schema.example.schemarelation.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String taskName;

    @ManyToOne
    @JoinColumn(name = "task_type_id", nullable = false)
    // @JoinColumn(name = "task_type_id")
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    // @JoinColumn(name = "task_type_id")
    private Plan plan;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTaskMapper> userTaskMappers;
   
}
