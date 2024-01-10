package com.jmorales.springbootreact.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jmorales.springbootreact.Serialice.BlobBase64Serializer;
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
    @JsonSerialize(using = BlobBase64Serializer.class)
    private Blob image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boosterPack")
    private List<Card> cardsList;

}
