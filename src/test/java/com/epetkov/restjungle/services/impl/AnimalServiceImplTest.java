package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    public static final String ANIMAL = "Koala";

    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        animalService = new AnimalServiceImpl(animalRepository, animalEntityToAnimalDTO);
    }

    @Test
    void testGetAllAnimals () {

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(ANIMAL);

        List<AnimalEntity> animals = Arrays.asList(animalEntity, animalEntity, animalEntity);

        when(animalRepository.findAll()).thenReturn(animals);

        List<AnimalDTO> allAnimalDTOs = animalService.getAllAnimals().getBody();

        assertNotNull(allAnimalDTOs);
        assertEquals(3, allAnimalDTOs.size());
    }

    @Test
    void testGetAnimalByName() {

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(ANIMAL);

        when(animalRepository.findOneByName(ANIMAL)).thenReturn(animalEntity);

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(animalEntity.getName());

        when(animalEntityToAnimalDTO.convert(animalEntity)).thenReturn(animalDTO);

        AnimalDTO foundAnimal = animalService.getAnimalByName(ANIMAL).getBody();

        assertNotNull(foundAnimal);
        verify(animalRepository, times(1)).findOneByName(anyString());
        verify(animalRepository, never()).findAll();
    }
}
