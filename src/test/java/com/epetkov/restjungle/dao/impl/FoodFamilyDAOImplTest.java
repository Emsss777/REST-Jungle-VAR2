package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.dao.interfaces.FamilyDAO;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
class FoodFamilyDAOImplTest {

    @Autowired
    FoodDAO foodDAO;

    @Autowired
    FamilyDAO familyDAO;

    @Test
    void testGetFoodByID() {

        FoodDTO foodDTO = foodDAO.getOneByID(2).getBody();

        assertThat(foodDTO).isNotNull();
        assertEquals("leaves", foodDTO.getName());
    }

    @Test
    void testGetFoodByName() {

        FoodDTO foodDTO = foodDAO.getOneByName("leaves").getBody();

        assertThat(foodDTO).isNotNull();
        assertEquals(2, foodDTO.getId());
        assertEquals("leaves", foodDTO.getName());
    }

    @Test
    void testCreateNewFoodOK() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("honey");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        assertThat(savedFood).isNotNull();
        assertEquals("honey", savedFood.getName());

        foodDAO.deleteFoodByName(savedFood.getName()).getBody();
    }

    @Test
    void testCreateNewFoodFAIL() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("leaves");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        assertThat(savedFood).isNull();
    }

    @Test
    void testGetFamilyByID() {

        FamilyDTO familyDTO = familyDAO.getOneByID(1).getBody();

        assertThat(familyDTO).isNotNull();
        assertEquals("mammal", familyDTO.getName());
    }

    @Test
    void testGetFamilyByName() {

        FamilyDTO familyDTO = familyDAO.getOneByName("mammal").getBody();

        assertThat(familyDTO).isNotNull();
        assertEquals(1, familyDTO.getId());
        assertEquals("mammal", familyDTO.getName());
    }

    @Test
    void testDeleteFoodByNameOK() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("honey");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        Boolean result = foodDAO.deleteFoodByName(Objects.requireNonNull(savedFood).getName()).getBody();

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testDeleteFoodByNameFAIL() {

        Boolean result = foodDAO.deleteFoodByName("mouse").getBody();

        assertEquals(Boolean.FALSE, result);
    }
}
