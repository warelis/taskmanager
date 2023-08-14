package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
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
    private Project addProject(@PathVariable Long user_id, @RequestBody Project project){
        User user = userRepository.findById(user_id).orElse(null);
        project.setUsers(user);
        Project save = projectRepository.saveAndFlush(project);
        user.getProjects().add(save);
        userRepository.saveAndFlush(user);
        return save;
    }
    @GetMapping
    public List<Project> getAll(){
        return projectRepository.findAll();
    }
    @DeleteMapping("/{project_id}")
    private void deleteById(@PathVariable Long project_id){
        projectRepository.deleteById(project_id);
    }
}