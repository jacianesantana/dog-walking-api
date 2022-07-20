package dogwalking.api.repository;

import dogwalking.api.model.DogWalking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogWalkingRepository extends JpaRepository<DogWalking, Long> {
}
