package com.jmorales.cardmanager.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "Cards")
public class Card {

    @Id
    private String id;
    private String name;
    private String image;
    private String rarity;
    private String color;
    private int dp;
    private String boosterPack;

}
