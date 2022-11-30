package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.utils.URLc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
class JungleControllerTest {

    public static final String ANIMAL = "Ape";
    public static final Integer LEGS = 2;
    public static final String FOOD = "leaves";
    public static final String FAMILY = "mammal";

    @Autowired
    protected WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllAnimals() throws Exception {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[1].name").value(ANIMAL))
                .andExpect(jsonPath("$[1].legs").value(LEGS))
                .andExpect(jsonPath("$[1].foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$[1].familyDTO.name").value(FAMILY));
    }

    @Test
    void testGetAnimalByName() throws Exception  {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.ANIMAL_PARAM, ANIMAL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value(ANIMAL))
                .andExpect(jsonPath("$.legs").value(LEGS))
                .andExpect(jsonPath("$.foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$.familyDTO.name").value(FAMILY));
    }

    @Test
    void testGetAnimalsByFoodName() throws Exception  {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.FOOD_URL + URLc.FOOD_PARAM, FOOD)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value(ANIMAL))
                .andExpect(jsonPath("$[0].legs").value(2))
                .andExpect(jsonPath("$[0].foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$[0].familyDTO.name").value(FAMILY));
    }
}
