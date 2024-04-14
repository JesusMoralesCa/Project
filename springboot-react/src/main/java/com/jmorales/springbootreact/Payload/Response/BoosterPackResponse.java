package com.jmorales.springbootreact.Payload.Response;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class BoosterPackResponse {
    private Long id;
    private String name;
    private String image;
    private List<CardResponse> cardsList;


    public BoosterPackResponse(Long id, String name, String image, List<CardResponse> cardsList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cardsList = cardsList;
    }

    public BoosterPackResponse(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

}
