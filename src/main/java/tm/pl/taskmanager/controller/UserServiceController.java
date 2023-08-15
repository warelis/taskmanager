package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private ResponseEntity<User> addUser(@RequestBody User users){
        userRepository.saveAndFlush(users);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
    }

    @DeleteMapping("/{user_id}")
    private ResponseEntity<String> deleteUser(@PathVariable Long user_id){
        try{
            userRepository.deleteById(user_id);
            return ResponseEntity.ok("User deleted succesfully");
        } catch (EmptyResultDataAccessException exc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userRepository.findAll();

        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user_id}")
    private ResponseEntity<User> findById(@PathVariable Long user_id){
            User user = userRepository.findById(user_id).orElse(null);
            if(user == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else{
                return ResponseEntity.ok(user);
            }
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

    @PutMapping("/{user_id}")
    private ResponseEntity<User> userUpdate(@PathVariable Long user_id, @RequestBody User updatedUser){
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            existingUser.setUser_password(updatedUser.getUser_password());
            existingUser.setUser_email(updatedUser.getUser_email());
            userRepository.saveAndFlush(existingUser);

            return ResponseEntity.ok(existingUser);
    }

    //add functionality that shows how many projects does the chosen user have
    private void howManyProjects(){

    }






}
