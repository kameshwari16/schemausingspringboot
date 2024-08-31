package schema.example.schemarelation.repository;
import schema.example.schemarelation.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
