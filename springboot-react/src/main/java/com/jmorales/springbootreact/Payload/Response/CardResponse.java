package com.jmorales.springbootreact.Payload.Response;

import com.jmorales.springbootreact.Exception.PackAlreadyExistException;
import com.jmorales.springbootreact.Model.Card;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Blob;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class CardResponse {
    private Long id;
    private String name;
    private String image;
    private String description;
    private String boosterPack;


    public CardResponse(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public CardResponse(Long id, String name, String image,String description, String boosterPack) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.boosterPack = boosterPack;
    }

}
