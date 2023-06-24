package tm.pl.taskmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import tm.pl.taskmanager.Status;

@Entity
@Data
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long project_id;
    private String project_name;
    private String task_description;
    @Enumerated(EnumType.STRING)
    private Status project_status;

}
