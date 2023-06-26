package tm.pl.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tm.pl.taskmanager.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
