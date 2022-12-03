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
    void testCreateNewAnimal() {

        // Todo: impl
    }

    @Test
    void testDeleteAnimalByName() {

        // Todo: impl
    }
}
