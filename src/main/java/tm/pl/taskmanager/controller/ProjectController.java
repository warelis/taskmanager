package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.pl.taskmanager.entities.Project;
import tm.pl.taskmanager.entities.User;
import tm.pl.taskmanager.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final tm.pl.taskmanager.repositories.ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @PostMapping("/{user_id}")
    private ResponseEntity<Project> addProject(@PathVariable Long user_id, @RequestBody Project project){

        User user = userRepository.findById(user_id).orElse(null);
        if (user_id == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        project.setUsers(user);
        Project save = projectRepository.saveAndFlush(project);
        user.getProjects().add(save);
        userRepository.saveAndFlush(user);
        return ResponseEntity.ok(save);
    }
    @GetMapping
    private ResponseEntity<List<Project>> getAll(){
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }
    @DeleteMapping("/{project_id}")
    private ResponseEntity<String> deleteById(@PathVariable Long project_id){
        try{
            projectRepository.deleteById(project_id);
            return ResponseEntity.ok("Project deleted succesfully");
        }catch(EmptyResultDataAccessException exc){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

    @PutMapping("/{project_id}")
    private void projectUpdate(){

    }

    @PutMapping("/{project_id}")
    private void statusChange(){

    }
}