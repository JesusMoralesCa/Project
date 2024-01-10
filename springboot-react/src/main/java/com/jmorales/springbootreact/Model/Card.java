package com.jmorales.springbootreact.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jmorales.springbootreact.Serialice.BlobBase64Serializer;
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

    @Lob
    @JsonSerialize(using = BlobBase64Serializer.class)
    private Blob image;

    private String description;

    @ManyToOne
    @JoinColumn(name = "boosterPack_id")
    private BoosterPack boosterPack;



    public String getBoosterPackName() {
        return boosterPack != null ? boosterPack.getName() : null;
    }

}
