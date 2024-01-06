package com.jmorales.springbootreact.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter
public class BoosterPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private Blob image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boosterPack")
    private List<Card> cardsList;

}
