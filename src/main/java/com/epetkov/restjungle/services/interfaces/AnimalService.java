package com.epetkov.restjungle.services.interfaces;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AnimalService {

    ResponseEntity<List<AnimalDTO>> getAllAnimals();
}