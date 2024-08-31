package schema.example.schemarelation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import schema.example.schemarelation.entity.UserTaskMapper;

public interface UserTaskMapperRepository extends JpaRepository<UserTaskMapper, Long>{

}
