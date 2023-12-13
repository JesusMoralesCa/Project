package com.jmorales.springbootreact.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Blob image;

    @ManyToOne
    @JoinColumn(name = "boosterPack_id")
    private BoosterPack boosterPack;
}
