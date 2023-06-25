package tm.pl.taskmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import tm.pl.taskmanager.Status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long project_id;
    private String project_name;
    private String project_description;
    @Enumerated(EnumType.STRING)
    private Status project_status;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
}
