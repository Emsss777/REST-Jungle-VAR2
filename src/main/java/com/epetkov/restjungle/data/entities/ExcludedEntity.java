package com.epetkov.restjungle.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "study_excluded", schema = "jungle")
public class ExcludedEntity {

    @Id
    @Column(name = "id_animal_excluded")
    private Integer idAnimalExcluded;
}
