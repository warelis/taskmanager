package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tm.pl.taskmanager.entities.Project;
import tm.pl.taskmanager.entities.Task;
import tm.pl.taskmanager.repositories.ProjectRepository;
import tm.pl.taskmanager.repositories.TaskRepository;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @PostMapping("/{project_id}")
    public Task addTask(@PathVariable Long project_id, @RequestBody Task task){
        Project project = projectRepository.findById(project_id).orElse(null);
        task.setProject(project);
        Task save = taskRepository.saveAndFlush(task);
        project.getTasks().add(save);
        projectRepository.saveAndFlush(project);
        return save;
    }
}
