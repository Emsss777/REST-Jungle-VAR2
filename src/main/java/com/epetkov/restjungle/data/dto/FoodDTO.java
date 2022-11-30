package com.epetkov.restjungle.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDTO {

    private Integer id;
    private String name;
}
