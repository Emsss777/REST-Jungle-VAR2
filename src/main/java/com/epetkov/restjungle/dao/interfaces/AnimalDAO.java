package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface AnimalDAO extends BaseRepDAO<AnimalDTO> {

    ResponseEntity<List<AnimalDTO>> getAllAnimals();

    ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food);

    ResponseEntity<AnimalDTO> createNewAnimal(AnimalDTO animalDTO);

    ResponseEntity<Boolean> deleteAnimalByName(String name);

    ResponseEntity<Map<String, Integer>> countLegsByFoodAndFamilyNames(String name);
}
