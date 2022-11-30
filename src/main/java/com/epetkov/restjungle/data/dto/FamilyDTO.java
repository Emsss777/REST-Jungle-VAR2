package com.epetkov.restjungle.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyDTO {

    private Integer id;
    private String name;

    public FamilyDTO(String name) {

        this.name = name;
    }
}
