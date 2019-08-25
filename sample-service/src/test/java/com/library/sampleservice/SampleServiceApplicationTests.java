package com.library.sampleservice;

import com.library.sampleservice.spec.GlobalSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceApplicationTests {

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void tearUp() {
        List<Task> data = new ArrayList<>() {{
            add(new Task("Exemplo 001", Task.Category.SIMPLE));
            add(new Task("Exemplo 002", Task.Category.SIMPLE));
            add(new Task("Exemplo 003", Task.Category.SIMPLE));
            add(new Task("Exemplo 004", Task.Category.COMPLEX));
            add(new Task("Exemplo 005", Task.Category.COMPLEX));
        }};

        taskRepository.saveAll(data);
    }

    @After
    public void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void validateCustomRepository() {

        List<Task> result = taskRepository.findQualquerCoisa(Task.Category.SIMPLE);
        Task[] expected = taskRepository.findAll().stream()
                .filter(it -> it.getCategory().equals(Task.Category.SIMPLE))
                .toArray(Task[]::new);

        assertThat("Result from database must be the same from custom repository", result, hasItems(expected));
    }

    @Test
    public void validateSpecification() {

        Specification<Task> taskSpecification = Specification
                .where(GlobalSpecification.<Task, Task.Category>equals("category", Task.Category.SIMPLE))
                .and(GlobalSpecification.stringFieldAnywhere("name", "Exemplo"));

        List<Task> result = taskRepository.findAll(taskSpecification);

        Task[] expected = taskRepository.findAll().stream()
                .filter(it -> it.getCategory().equals(Task.Category.SIMPLE))
                .toArray(Task[]::new);

        assertThat("Itens da lista não correspondem", result, hasItems(expected));
        assertThat("Tamanho da lista não corresponde", result, hasSize(expected.length));
    }

}
