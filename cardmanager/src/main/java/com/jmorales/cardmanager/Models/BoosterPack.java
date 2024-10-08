package com.jmorales.cardmanager.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "BoosterPacks")
public class BoosterPack {
    @Id
    private String id;
    private String name;
    private String image;
    private List<Card> cardList;
}
