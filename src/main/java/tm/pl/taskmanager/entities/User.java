package tm.pl.taskmanager.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String user_login;
    private String user_password;
    private String user_email;

    @OneToMany(mappedBy = "users")
    private List<Project> projects = new ArrayList<>();

}
