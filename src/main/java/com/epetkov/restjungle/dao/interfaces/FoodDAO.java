package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface FoodDAO {

    ResponseEntity<FoodDTO> getFoodByID(Integer id);

    ResponseEntity<FoodDTO> createNewFood(FoodDTO foodDTO);
}