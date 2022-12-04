package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface FoodDAO {

    ResponseEntity<FoodDTO> getFoodByID(Integer id);

    ResponseEntity<FoodDTO> getFoodByName(String name);

    ResponseEntity<FoodDTO> createNewFood(FoodDTO foodDTO);

    ResponseEntity<Boolean> deleteFoodByName(String name);
}
