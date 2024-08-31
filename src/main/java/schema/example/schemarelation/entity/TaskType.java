package schema.example.schemarelation.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;
    
}
