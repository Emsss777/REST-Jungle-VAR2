package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.data.converters.FoodEntityToFoodDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.FoodEntity;
import com.epetkov.restjungle.repositories.FoodRepository;
import com.epetkov.restjungle.services.interfaces.FoodService;
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
class FoodServiceImplTest {

    public static final String FOOD = "leaves";
    public static final String FOOD_NEW = "nuts";

    @Autowired
    FoodService foodService;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodEntityToFoodDTO foodEntityToFoodDTO;

    @Test
    void testCreateNewFoodOK() {

        List<FoodEntity> foods = foodRepository.findAll();
        FoodEntity testFoodEntity = foods.iterator().next();
        FoodDTO testFoodDTO = foodEntityToFoodDTO.convert(testFoodEntity);

        Objects.requireNonNull(testFoodDTO).setName(FOOD_NEW);
        FoodDTO savedFoodDTO = foodService.createNewFood(testFoodDTO).getBody();

        assertNotNull(savedFoodDTO);
        assertEquals(testFoodDTO.getId(), savedFoodDTO.getId());
        assertEquals(testFoodDTO.getName(), savedFoodDTO.getName());
    }

    @Test
    void testCreateNewFoodFAIL() {

        List<FoodEntity> foods = foodRepository.findAll();
        FoodEntity testFoodEntity = foods.iterator().next();
        FoodDTO testFoodDTO = foodEntityToFoodDTO.convert(testFoodEntity);

        Objects.requireNonNull(testFoodDTO).setName(FOOD);
        FoodDTO savedFoodDTO = foodService.createNewFood(testFoodDTO).getBody();

        assertNull(savedFoodDTO);
    }
}