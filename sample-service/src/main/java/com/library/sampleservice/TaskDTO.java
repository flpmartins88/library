package com.library.sampleservice;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDTO {

    @JsonView(Views.Base.class)
    private Long id;

    @JsonView( { Views.OnlyTasksName.class, Views.BasicTask.class } )
    @NotBlank @Size(min = 3, max = 50)
    private String name;

    @JsonView( { Views.Tasks.class } )
    @NotNull
    private Task.Category category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task.Category getCategory() {
        return category;
    }

    public void setCategory(Task.Category category) {
        this.category = category;
    }

    public Task toTask() {
        return new Task(this.name, this.category);
    }

    public static TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.id = task.getId();
        taskDTO.name = task.getName();
        taskDTO.category = task.getCategory();

        return taskDTO;
    }
}
