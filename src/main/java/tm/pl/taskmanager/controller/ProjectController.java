package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tm.pl.taskmanager.entities.Project;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final tm.pl.taskmanager.repositories.ProjectRepository ProjectRepository;
    @PostMapping
    private Project addProject(@RequestBody Project project){
        return ProjectRepository.saveAndFlush(project);
    }
    @GetMapping
    public List<Project> getAll(){
        return ProjectRepository.findAll();
    }
}