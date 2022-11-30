package com.epetkov.restjungle.data.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "foods", schema = "jungle")
public class FoodEntity extends BaseEntity {

}
