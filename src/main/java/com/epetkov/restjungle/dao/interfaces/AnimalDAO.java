package com.epetkov.restjungle.dao.interfaces;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AnimalDAO {

    ResponseEntity<List<AnimalDTO>> getAllAnimals();

    ResponseEntity<AnimalDTO> getAnimalByName(String name);

    ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food);

    ResponseEntity<AnimalDTO> createNewAnimal(AnimalDTO animalDTO);

    ResponseEntity<List<AnimalDTO>> deleteAnimalByName(String name);
}
