package com.library.sampleservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void tearUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void deveSalvar() throws Exception {
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\": \"Teste\", \"category\": \"SIMPLE\"}"))

                .andDo(print())

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Teste"));


    }

    @Test
    public void naoDeveSalvarQuandoNomeEhNulo() throws Exception {
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"category\": \"SIMPLE\"}"))

                .andDo(print())

                .andExpect(status().isBadRequest());
    }

    /*
     * Esse método é diferente pq ele trabalha com o Page do Spring Data e Spring MVC
     */
    @Test
    public void deveObterLista() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void deveObterUmaTask() throws Exception {
        MvcResult taskCriada = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\": \"Teste\", \"category\": \"SIMPLE\"}"))

                .andDo(print())

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Teste"))
                .andReturn();

        String taskJson = taskCriada.getResponse().getContentAsString();
        TaskDTO task = objectMapper.readValue(taskJson, TaskDTO.class);

        mockMvc.perform(get("/tasks/" + task.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()))
                .andExpect(jsonPath("$.name").value(task.getName()))
                .andExpect(jsonPath("$.category").value(task.getCategory().name()));
    }
}
