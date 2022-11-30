package com.epetkov.restjungle.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "animals", schema = "jungle")
public class AnimalEntity extends BaseEntity {

    @Column(name = "legs")
    private Integer legs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_food", referencedColumnName = "id")
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_family", referencedColumnName = "id")
    private FamilyEntity family;
}
