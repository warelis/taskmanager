package tm.pl.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tm.pl.taskmanager.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
