package schema.example.schemarelation.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false)
    private String planName;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    }
    

