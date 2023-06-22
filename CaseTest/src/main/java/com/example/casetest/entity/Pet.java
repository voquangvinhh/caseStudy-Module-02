package com.example.casetest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePet;
    private String gender;
    private String age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoryPet categoryPet;
    private Double weight;
    private String avatar;
    @Column(name = "is_status")
    @ColumnDefault("true")
    private boolean isStatus;
    private Integer price;
    private int quantity;
    private String description;
}
