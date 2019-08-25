package com.library.sampleservice;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody TaskDTO task) {
        Task savedTask = taskRepository.save(task.toTask());
        return ResponseEntity.created(
                URI.create("/task/" + savedTask.getId())
        ).body(savedTask);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                taskRepository.findById(id)
        );
    }

    @JsonView(Views.OnlyTasksName.class)
    @GetMapping(path = {"/onlyNames"})
    public ResponseEntity obterApenasNomes() {
        return ResponseEntity.ok(
                taskRepository.findAll().stream()
                        .map(TaskDTO::fromTask)
                        .collect(Collectors.toList())
        );
    }

    @JsonView(Views.BasicTask.class)
    @GetMapping(path = {"/basic"})
    public ResponseEntity obterApenasBasico() {
        return ResponseEntity.ok(
                taskRepository.findAll().stream()
                        .map(TaskDTO::fromTask)
                        .collect(Collectors.toList())
        );
    }

}
