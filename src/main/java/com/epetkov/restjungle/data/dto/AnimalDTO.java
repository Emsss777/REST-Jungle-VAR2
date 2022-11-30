package com.epetkov.restjungle.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

    private Integer id;
    private String name;
    private Integer legs;
    private FoodDTO foodDTO;
    private FamilyDTO familyDTO;

    public AnimalDTO(String name, Integer legs, FoodDTO foodDTO, FamilyDTO familyDTO) {

        this.name = name;
        this.legs = legs;
        this.foodDTO = foodDTO;
        this.familyDTO = familyDTO;
    }
}
