package com.epetkov.restjungle.data.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "animal_family", schema = "jungle")
public class FamilyEntity extends BaseEntity {

}
