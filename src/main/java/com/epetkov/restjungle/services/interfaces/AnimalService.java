package com.epetkov.restjungle.services.interfaces;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AnimalService {

    ResponseEntity<List<AnimalDTO>> getAllAnimals();

    ResponseEntity<AnimalDTO> getAnimalByName(String name);

    ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food);

    ResponseEntity<AnimalDTO> createNewAnimal(AnimalDTO animalDTO);
}
