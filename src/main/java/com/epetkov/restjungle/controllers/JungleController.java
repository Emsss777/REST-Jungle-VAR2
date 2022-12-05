package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.services.interfaces.CountLegsService;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.services.interfaces.FoodService;
import com.epetkov.restjungle.utils.URLc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequestMapping(URLc.J_ANIMALS_URL)
@RestController
public class JungleController {

    private final AnimalService animalService;
    private final FoodService foodService;
    private final CountLegsService countLegsDao;

    public JungleController(AnimalService animalService,
                            FoodService foodService, CountLegsService countLegsDao) {

        this.animalService = animalService;
        this.foodService = foodService;
        this.countLegsDao = countLegsDao;
    }

    /**
     * Returns a List of All Animals in the Database.
     * @return ArrayList (AnimalDTO) ;
     */
    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {

        return animalService.getAllAnimals();
    }

    /**
     * Returns an Animal found by Name.
     * @param name ;
     * @return Object (AnimalDTO) ;
     */
    @GetMapping(URLc.ANIMAL_PARAM)
    public ResponseEntity<AnimalDTO> getAnimalByName(@PathVariable("animal") String name) {

        return animalService.getAnimalByName(name);
    }

    /**
     * Returns a List of Animals found by Food Name.
     * @param food ;
     * @return ArrayList (AnimalDTO) ;
     */
    @GetMapping(URLc.FOOD_URL + URLc.FOOD_PARAM)
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(@PathVariable("food") String food) {

        return animalService.getAnimalsByFoodName(food);
    }

    /**
     * Returns a new type of Food.
     * @param foodDTO ;
     * @return Object (foodDTO)
     */
    @PostMapping(URLc.FOOD_URL)
    public ResponseEntity<FoodDTO> createNewFood(@RequestBody FoodDTO foodDTO) {

        return foodService.createNewFood(foodDTO);
    }

    /**
     * Returns a new type of Animal.
     * @param animalDTO ;
     * @return Object (AnimalDTO)
     */
    @PostMapping(URLc.ANIMAL_URL)
    public ResponseEntity<AnimalDTO> createNewAnimal(@RequestBody AnimalDTO animalDTO) {

        return animalService.createNewAnimal(animalDTO);
    }

    /**
     * Deletes an Animal by Name.
     * @param animal ;
     * @return Boolean ;
     */
    @DeleteMapping(URLc.ANIMAL_PARAM)
    public ResponseEntity<Boolean> deleteAnimalByName(@PathVariable String animal) {

        return animalService.deleteAnimalByName(animal);
    }

    /**
     * Returns a number of Legs calculated by Food and Family Names.
     * @param food ;
     * @param family ;
     * @return HashMap ;
     */
    @GetMapping(URLc.FOOD_PARAM + URLc.FAMILY_PARAM + URLc.COUNT_LEGS_URL)
    public ResponseEntity<List<Map<String, Integer>>> countLegsByFoodAndFamilyNames(@PathVariable String food,
                                                                                    @PathVariable String family) {

        List<Map<String, Integer>> mapList = new ArrayList<>();

        mapList.add(countLegsDao.countLegsByFoodAndFamilyNames(food).getBody());
        mapList.add(countLegsDao.countLegsByFoodAndFamilyNames(family).getBody());

        return new ResponseEntity<>(mapList, HttpStatus.OK);
    }
}
