package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.Card;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ICardService {

    Card createCard(String name, MultipartFile file, String description, String packName) throws IOException, SQLException;

    List<Card> getCards();

    void deleteCard(String name);

    Card getCard(String name);

}
