package tm.pl.taskmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import tm.pl.taskmanager.Status;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tasks_id;
    private String task_name;
    private String task_description;
    private String assigned_person;
    @Enumerated(EnumType.STRING)
    private Status task_status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projects;
}
