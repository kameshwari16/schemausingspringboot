package schema.example.schemarelation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schema.example.schemarelation.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
    
}
