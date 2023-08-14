package tm.pl.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import tm.pl.taskmanager.Status;

@Entity
@Data
@Table (name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;
    private String task_name;
    private String task_description;
    private String assigned_person;
    @Enumerated(EnumType.STRING)
    private Status task_status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;
}
