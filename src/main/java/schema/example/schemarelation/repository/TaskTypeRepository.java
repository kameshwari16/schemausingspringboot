package schema.example.schemarelation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import schema.example.schemarelation.entity.TaskType;
@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}
