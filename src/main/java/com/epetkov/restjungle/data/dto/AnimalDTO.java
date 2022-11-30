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
}
