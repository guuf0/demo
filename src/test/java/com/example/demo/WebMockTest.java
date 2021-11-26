package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Test
    @DisplayName("should test GET method")
    public void test() throws Exception {
        // given
        Optional<Integer> age = Optional.of(6);
        List<Person> people = new ArrayList<>();
        Person person = new Person(1, "Wiktor", "Kaszuba", 6);
        people.add(person);

        when(service.findAll(age)).thenReturn(people);

        // when
        ResultActions result = this.mockMvc.perform(get("/people?age=6"));

        // then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$", hasSize(1)));
        result.andExpect(jsonPath("$[0].id", is(1)));
        result.andExpect(jsonPath("$[0].firstName", is("Wiktor")));
        result.andExpect(jsonPath("$[0].lastName", is("Kaszuba")));
        result.andExpect(jsonPath("$[0].age", is(6)));
    }

    @Test
    @DisplayName("should test GET by id method")
    public void testGetPersonById() throws Exception {
        // given
        Long id = 1L;
        Person person = new Person(1, "Wiktor", "Kaszuba", 6);

        when(service.findById(id)).thenReturn(person);

        // when
        ResultActions result = this.mockMvc.perform(get("/people/1"));

        // then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id", is(1)));
        result.andExpect(jsonPath("$.firstName", is("Wiktor")));
        result.andExpect(jsonPath("$.lastName", is("Kaszuba")));
        result.andExpect(jsonPath("$.age", is(6)));
    }

}
