package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.dao.interfaces.*;
import com.epetkov.restjungle.data.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
class AnimalDAOImplTest {

    @Autowired
    AnimalDAO animalDAO;

    @Test
    void testGetAllAnimals() {

        List<AnimalDTO> animalDTOList = animalDAO.getAllAnimals().getBody();

        assertThat(animalDTOList).isNotNull();
        assertEquals(7, animalDTOList.size());
    }

    @Test
    void testGetAnimalByID() {

        AnimalDTO animalDTO = animalDAO.getAnimalByID(3).getBody();

        assertThat(animalDTO).isNotNull();
        assertEquals("Deer", animalDTO.getName());
        assertEquals(4, animalDTO.getLegs());
        assertEquals("leaves", animalDTO.getFoodDTO().getName());
        assertEquals("mammal", animalDTO.getFamilyDTO().getName());
    }

    @Test
    void testGetAnimalByName() {

        AnimalDTO animalDTO = animalDAO.getAnimalByName("Deer").getBody();
        
        assertThat(animalDTO).isNotNull();
        assertEquals(3, animalDTO.getId());
        assertEquals(4, animalDTO.getLegs());
        assertEquals("leaves", animalDTO.getFoodDTO().getName());
        assertEquals("mammal", animalDTO.getFamilyDTO().getName());
    }

    @Test
    void testGetAnimalsByFoodName() {

        List<AnimalDTO> animalDTOList = animalDAO.getAnimalsByFoodName("leaves").getBody();

        assertThat(animalDTOList).isNotNull();
        assertEquals(2, animalDTOList.size());
    }

    @Test
    void testCreateNewAnimalOK() {

        AnimalDTO animalDTO =
                new AnimalDTO("Donkey", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        assertThat(savedAnimal).isNotNull();
        assertEquals("Donkey", savedAnimal.getName());
        assertEquals(2, savedAnimal.getFoodDTO().getId());
        assertEquals(1, savedAnimal.getFamilyDTO().getId());

        animalDAO.deleteAnimalByName(Objects.requireNonNull(savedAnimal).getName()).getBody();
    }

    @Test
    void testCreateNewAnimalFAIL() {

        AnimalDTO animalDTO =
                new AnimalDTO("Deer", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        assertThat(savedAnimal).isNull();
    }

    @Test
    void testDeleteAnimalByNameOK() {

        AnimalDTO animalDTO =
                new AnimalDTO("Donkey", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        Boolean result = animalDAO.deleteAnimalByName(Objects.requireNonNull(savedAnimal).getName()).getBody();

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testDeleteAnimalByNameFAIL() {

        Boolean result = animalDAO.deleteAnimalByName("Monkey").getBody();

        assertEquals(Boolean.FALSE, result);
    }
}
