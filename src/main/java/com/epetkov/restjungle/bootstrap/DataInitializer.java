package com.epetkov.restjungle.bootstrap;

import com.epetkov.restjungle.data.entities.*;
import com.epetkov.restjungle.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FoodRepository foodRepository;
    private final FamilyRepository familyRepository;
    private final AnimalRepository animalRepository;
    private final ExcludedRepository excludedRepository;

    public DataInitializer(FoodRepository foodRepository,
                           FamilyRepository familyRepository,
                           AnimalRepository animalRepository,
                           ExcludedRepository excludedRepository) {

        this.foodRepository = foodRepository;
        this.familyRepository = familyRepository;
        this.animalRepository = animalRepository;
        this.excludedRepository = excludedRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        animalRepository.saveAll(getAnimals());
        getExcludedAnimals();
        printAnimals();
    }

    private List<AnimalEntity> getAnimals() {

        List<AnimalEntity> animals = new ArrayList<>();

        // Get Foods
        FoodEntity carrotFood = foodRepository.findOneByName("carrot");

        if (carrotFood == null) {

            throw new RuntimeException("Expected Food Not Found!");
        }

        FoodEntity leavesFood = foodRepository.findOneByName("leaves");

        if (leavesFood == null) {

            throw new RuntimeException("Expected Food Not Found!");
        }

        FoodEntity insectsFood = foodRepository.findOneByName("insects");

        if (insectsFood == null) {

            throw new RuntimeException("Expected Food Not Found!");
        }

        FoodEntity verminFood = foodRepository.findOneByName("vermin");

        if (verminFood == null) {

            throw new RuntimeException("Expected Food Not Found!");
        }

        FoodEntity birdseedFood = foodRepository.findOneByName("birdseed");

        if (birdseedFood == null) {

            throw new RuntimeException("Expected Food Not Found!");
        }

        // Get Families
        FamilyEntity mammalFamily = familyRepository.findOneByName("mammal");

        if (mammalFamily == null) {

            throw new RuntimeException("Expected Family Not Found!");
        }

        FamilyEntity reptileFamily = familyRepository.findOneByName("reptile");

        if (reptileFamily == null) {

            throw new RuntimeException("Expected Family Not Found!");
        }

        FamilyEntity birdsFamily = familyRepository.findOneByName("birds");

        if (birdsFamily == null) {

            throw new RuntimeException("Expected Family Not Found!");
        }

        FamilyEntity arthropodFamily = familyRepository.findOneByName("arthropod");

        if (arthropodFamily == null) {

            throw new RuntimeException("Expected Family Not Found!");
        }

        // Set Animals
        AnimalEntity rabbit = new AnimalEntity();
        rabbit.setName("Rabbit");
        rabbit.setLegs(4);
        rabbit.setFood(carrotFood);
        rabbit.setFamily(mammalFamily);

        animals.add(rabbit);

        AnimalEntity ape = new AnimalEntity();
        ape.setName("Ape");
        ape.setLegs(2);
        ape.setFood(leavesFood);
        ape.setFamily(mammalFamily);

        animals.add(ape);

        AnimalEntity deer = new AnimalEntity();
        deer.setName("Deer");
        deer.setLegs(4);
        deer.setFood(leavesFood);
        deer.setFamily(mammalFamily);

        animals.add(deer);

        AnimalEntity snake = new AnimalEntity();
        snake.setName("Snake");
        snake.setLegs(0);
        snake.setFood(insectsFood);
        snake.setFamily(reptileFamily);

        animals.add(snake);

        AnimalEntity crocodile = new AnimalEntity();
        crocodile.setName("Crocodile");
        crocodile.setLegs(4);
        crocodile.setFood(verminFood);
        crocodile.setFamily(reptileFamily);

        animals.add(crocodile);

        AnimalEntity chicken = new AnimalEntity();
        chicken.setName("Chicken");
        chicken.setLegs(2);
        chicken.setFood(birdseedFood);
        chicken.setFamily(birdsFamily);

        animals.add(chicken);

        AnimalEntity spider = new AnimalEntity();
        spider.setName("Spider");
        spider.setLegs(8);
        spider.setFood(insectsFood);
        spider.setFamily(arthropodFamily);

        animals.add(spider);

        return animals;
    }

    private void getExcludedAnimals() {

        ExcludedEntity exc1 = new ExcludedEntity();
        exc1.setIdAnimalExcluded(4);
        excludedRepository.save(exc1);
    }

    private void printAnimals() throws Exception {

        List<AnimalEntity> animalList = animalRepository.findAll();

        System.out.println("***********************************");
        System.out.println("*****  Animals in the Jungle  *****");
        System.out.println("===================================");

        if (animalList.isEmpty()) {

            throw new Exception("Nо Animals Found in the DATABASE!");
        }

        animalList.forEach(animal ->
                System.out.println("Animal ID: " + animal.getId() + " --> " + "Name: " + animal.getName()));

        System.out.println("===================================");
    }
}
