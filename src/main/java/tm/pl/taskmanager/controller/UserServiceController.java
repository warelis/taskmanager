package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.pl.taskmanager.entities.Project;
import tm.pl.taskmanager.entities.User;
import tm.pl.taskmanager.repositories.ProjectRepository;
import tm.pl.taskmanager.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserServiceController {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @PostMapping
    private User addUser(@RequestBody User users){
        return userRepository.saveAndFlush(users);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userRepository.findAll();

        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);

    }

    @DeleteMapping("/{user_id}")
    private void deleteUser(@PathVariable Long user_id){
        userRepository.deleteById(user_id);
    }
    @PutMapping("/{project_id}/assign/{user_id}")
    private ResponseEntity<String> assignProjectToUser(@PathVariable Long project_id, @PathVariable Long user_id){
        Project project = projectRepository.findById(project_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);

        if (project == null || user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project or user not found");
        }
        project.setUsers(user);
        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Project assigned to user");
    }






}
