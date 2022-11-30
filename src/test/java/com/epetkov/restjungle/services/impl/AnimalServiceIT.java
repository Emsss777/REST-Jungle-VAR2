package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
import com.epetkov.restjungle.data.dto.*;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
public class AnimalServiceIT {

    public static final String ANIMAL = "Ape";
    public static final String ANIMAL_NEW = "Koala";
    public static final Integer LEGS = 4;
    public static final String FOOD = "leaves";
    public static final String FAMILY = "mammal";

    @Autowired
    AnimalService animalService;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    @Test
    public void testCreateNewAnimalOK() {

        List<AnimalEntity> animals = animalRepository.findAll();
        AnimalEntity testAnimalEntity = animals.iterator().next();
        AnimalDTO testAnimalDTO = animalEntityToAnimalDTO.convert(testAnimalEntity);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(FOOD);

        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setName(FAMILY);

        Objects.requireNonNull(testAnimalDTO).setName(ANIMAL_NEW);
        testAnimalDTO.setLegs(LEGS);
        testAnimalDTO.setFoodDTO(foodDTO);
        testAnimalDTO.setFamilyDTO(familyDTO);
        AnimalDTO savedAnimalDTO = animalService.createNewAnimal(testAnimalDTO).getBody();

        assertNotNull(savedAnimalDTO);
        assertEquals(ANIMAL_NEW, savedAnimalDTO.getName());
        assertEquals(LEGS, savedAnimalDTO.getLegs());
        assertEquals(FOOD, savedAnimalDTO.getFoodDTO().getName());
        assertEquals(FAMILY, savedAnimalDTO.getFamilyDTO().getName());
    }

    @Test
    public void testCreateNewAnimalFAIL() {

        List<AnimalEntity> animals = animalRepository.findAll();
        AnimalEntity testAnimalEntity = animals.iterator().next();
        AnimalDTO testAnimalDTO = animalEntityToAnimalDTO.convert(testAnimalEntity);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(FOOD);

        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setName(FAMILY);

        Objects.requireNonNull(testAnimalDTO).setName(ANIMAL);
        testAnimalDTO.setLegs(LEGS);
        testAnimalDTO.setFoodDTO(foodDTO);
        testAnimalDTO.setFamilyDTO(familyDTO);
        AnimalDTO savedAnimalDTO = animalService.createNewAnimal(testAnimalDTO).getBody();

        assertNull(savedAnimalDTO);
    }
}
