package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface AnimalDAO {

    ResponseEntity<List<AnimalDTO>> getAllAnimals();

    ResponseEntity<AnimalDTO> getAnimalByID(Integer id);

    ResponseEntity<AnimalDTO> getAnimalByName(String name);

    ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food);

    ResponseEntity<AnimalDTO> createNewAnimal(AnimalDTO animalDTO);

    ResponseEntity<Boolean> deleteAnimalByName(String name);

    ResponseEntity<Map<String, Integer>> countLegsByFoodAndFamilyNames(String name);
}
