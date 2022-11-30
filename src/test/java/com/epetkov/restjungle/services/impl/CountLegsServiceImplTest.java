package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@Transactional
class CountLegsServiceImplTest {

    @Autowired
    CountLegsServiceImpl countLegsService;

    @Test
    public void testCountLegsByFoodNames() {

        Map<String, Integer> savedMap = countLegsService.countLegsByFoodAndFamilyNames("food").getBody();

        assertThat(savedMap).isNotEmpty().hasSize(5)
                .containsExactly(
                        entry("leaves", 6),
                        entry("birdseed", 2),
                        entry("insects", 8),
                        entry("carrot", 4),
                        entry("vermin", 4)
                );
    }

    @Test
    public void testCountLegsByFamilyNames() {

        Map<String, Integer> savedMap = countLegsService.countLegsByFoodAndFamilyNames("family").getBody();

        assertThat(savedMap).isNotEmpty().hasSize(4)
                .containsExactly(
                        entry("mammal", 10),
                        entry("arthropod", 8),
                        entry("reptile", 4),
                        entry("birds", 2)
                );
    }
}