package schema.example.schemarelation.entity;
import jakarta.persistence.*;
import lombok.*;

// import java.util.Set;

// import schema.example.schemarelation.entity.Task;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTaskMapper {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapperId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(nullable = false)
    private String status;

}
