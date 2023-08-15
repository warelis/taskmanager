package tm.pl.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.pl.taskmanager.entities.Project;
import tm.pl.taskmanager.entities.Task;
import tm.pl.taskmanager.repositories.ProjectRepository;
import tm.pl.taskmanager.repositories.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @PostMapping("/{project_id}")
    public ResponseEntity<Task> addTask(@PathVariable Long project_id, @RequestBody Task task){
        Project project = projectRepository.findById(project_id).orElse(null);
        if(project_id == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        task.setProject(project);
        Task save = taskRepository.saveAndFlush(task);
        project.getTasks().add(save);
        projectRepository.saveAndFlush(project);
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
       List<Task> tasks = taskRepository.findAll();
       return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{task_id}")
    private ResponseEntity<String> deleteById(@PathVariable Long task_id){
        try{
            taskRepository.deleteById(task_id);
            return ResponseEntity.ok("Task deleted succesfully");
        }catch(EmptyResultDataAccessException exc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

    @PutMapping("/{task_id}")
    private ResponseEntity<Task> taskUpdate(@PathVariable Long task_id, @RequestBody Task updatedTask){
        Task existingTask = taskRepository.findById(task_id).orElse(null);
        if(existingTask == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        existingTask.setTask_status(updatedTask.getTask_status());
        existingTask.setTask_description(updatedTask.getTask_description());

        return ResponseEntity.ok(existingTask);
    }
}
