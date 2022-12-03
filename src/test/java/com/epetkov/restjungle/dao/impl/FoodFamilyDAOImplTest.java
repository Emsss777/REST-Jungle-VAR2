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

        FoodDTO foodDTO = foodDAO.getFoodByID(2).getBody();

        assertThat(foodDTO).isNotNull();
        assertEquals("leaves", foodDTO.getName());
    }

    @Test
    void testCreateNewFood() {

        // Todo: impl
    }

    @Test
    void testGetFamilyByID() {

        FamilyDTO familyDTO = familyDAO.getFamilyByID(1).getBody();

        assertThat(familyDTO).isNotNull();
        assertEquals("mammal", familyDTO.getName());
    }
}
