package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.utils.URLc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(URLc.J_ANIMALS_URL)
@RestController
public class JungleController {

    private final AnimalService animalService;

    public JungleController(AnimalService animalService) {

        this.animalService = animalService;
    }

    /**
     * Returns a List of All Animals in the Database.
     * @return ArrayList (AnimalDTO) ;
     */
    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {

        return animalService.getAllAnimals();
    }
}
