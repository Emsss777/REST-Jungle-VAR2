package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.data.dto.*;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.utils.URLc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.epetkov.restjungle.controllers.AbstractRestControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
class JungleControllerTest {

    public static final String ANIMAL = "Ape";
    public static final String ANIMAL_NEW = "Koala";
    public static final Integer LEGS = 2;
    public static final String FOOD = "leaves";
    public static final String FOOD_NEW = "nuts";
    public static final String FAMILY = "mammal";

    @Mock
    AnimalService animalService;

    @InjectMocks
    JungleController jungleController;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @BeforeEach
    void setUp() {

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

    @Test
    void testCreateNewFoodOK() throws Exception {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(FOOD_NEW);

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.FOOD_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(foodDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(FOOD_NEW));
    }

    @Test
    void testCreateNewFoodFAIL() throws Exception {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(FOOD);

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.FOOD_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(foodDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateNewAnimalOK() throws Exception {

        AnimalDTO animalDTO = new AnimalDTO(ANIMAL_NEW, 4, new FoodDTO(FOOD), new FamilyDTO(FAMILY));

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.ANIMAL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(animalDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(ANIMAL_NEW));
    }

    @Test
    void testCreateNewAnimalFAIL() throws Exception {

        AnimalDTO animalDTO = new AnimalDTO(ANIMAL, 4, new FoodDTO(FOOD), new FamilyDTO(FAMILY));

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.ANIMAL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(animalDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteAnimalByName() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(jungleController).build();

        mockMvc.perform(
                        delete(URLc.J_ANIMALS_URL + URLc.ANIMAL_PARAM, ANIMAL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(animalService).deleteAnimalByName(anyString());
    }

    @Test
    public void testCountLegsByFoodAndFamilyNames() throws Exception {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.FOOD_PARAM + URLc.FAMILY_PARAM,
                                "food", "family" + URLc.COUNT_LEGS_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].leaves", Matchers.is(6)))
                .andExpect(jsonPath("$[0].birdseed", Matchers.is(2)))
                .andExpect(jsonPath("$[0].insects", Matchers.is(8)))
                .andExpect(jsonPath("$[0].carrot", Matchers.is(4)))
                .andExpect(jsonPath("$[0].vermin", Matchers.is(4)))
                .andExpect(jsonPath("$[1].mammal", Matchers.is(10)))
                .andExpect(jsonPath("$[1].arthropod", Matchers.is(8)))
                .andExpect(jsonPath("$[1].reptile", Matchers.is(4)))
                .andExpect(jsonPath("$[1].birds", Matchers.is(2)));
    }
}
